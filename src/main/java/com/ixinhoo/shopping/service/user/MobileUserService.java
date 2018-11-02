package com.ixinhoo.shopping.service.user;

import com.google.common.base.Strings;
import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.code.DataTable;
import com.ixinhoo.api.code.DataTableMeta;
import com.ixinhoo.api.code.DataTableRequest;
import com.ixinhoo.api.constants.UserPasswordEncrypt;
import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.api.entity.IdEntity;
import com.ixinhoo.api.service.BaseService;
import com.ixinhoo.api.util.UsersUtil;
import com.ixinhoo.crumbs.code.util.collection.Collections3;
import com.ixinhoo.crumbs.code.util.key.CodeNumberMaker;
import com.ixinhoo.crumbs.code.util.mapper.BeanMapper;
import com.ixinhoo.shopping.dao.order.UserOrderDao;
import com.ixinhoo.shopping.dao.setting.GradeSettingDao;
import com.ixinhoo.shopping.dao.user.MessageCodeDao;
import com.ixinhoo.shopping.dao.user.MobileUserDao;
import com.ixinhoo.shopping.dao.user.UserPhoneDao;
import com.ixinhoo.shopping.dao.user.UserIntegralDao;
import com.ixinhoo.shopping.dto.user.MobileUserDto;
import com.ixinhoo.shopping.dto.user.MobileUserRegisterDto;
import com.ixinhoo.shopping.dto.user.UserGradeDto;
import com.ixinhoo.shopping.dto.user.UserMyselfDto;
import com.ixinhoo.shopping.entity.EntitySetting;
import com.ixinhoo.shopping.entity.order.UserOrder;
import com.ixinhoo.shopping.entity.setting.GradeSetting;
import com.ixinhoo.shopping.entity.user.MessageCode;
import com.ixinhoo.shopping.entity.user.MobileUser;
import com.ixinhoo.shopping.entity.user.UserPhone;
import com.ixinhoo.shopping.entity.user.UserIntegral;
import com.ixinhoo.shopping.vo.user.MobileUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.TimeZone;

@Service
public class MobileUserService extends BaseService {
    @Autowired
    private MobileUserDao<MobileUser> dao;
    @Autowired
    private MessageCodeDao<MessageCode> messageCodeDao;
    @Autowired
    private UserIntegralDao<UserIntegral> userSignIntegralDao;
    @Autowired
    private GradeSettingDao<GradeSetting> gradeSettingDao;
    @Autowired
    private UserOrderDao<UserOrder> userOrderDao;
    @Autowired
    private UserPhoneDao<UserPhone> userPhoneDao;


    @Override
    protected BaseDao<MobileUser> getBaseDao() {
        return dao;
    }

    @Transactional
    public StatusDto verifyCodeByPhone(String phone, EntitySetting.MessageCodeType type) {
        StatusDto dto = new StatusDto(false);
        if (Strings.isNullOrEmpty(phone)) {
            dto.setMsg("手机号为空");
        } else if (!phone.matches("\\d{11}")) {
            dto.setMsg("手机号格式不正确");
        } else {
            MessageCode entity = new MessageCode();
            dto.setStatus(true);
            entity.setPhone(phone);
            String number = CodeNumberMaker.getInstance().messageCodeNum();
            //TODO cici 调用第三方发送短信
            dto.setCode(number);
            entity.setCode(number);
            if (type == null || EntitySetting.MessageCodeType.REGISTER.equals(type)) {
                entity.setMessage("发送的短信验证码：" + number);
                entity.setType(EntitySetting.MessageCodeType.REGISTER);
            } else {
                entity.setMessage("发送的更换手机短信验证码：" + number);
                entity.setType(EntitySetting.MessageCodeType.CHANGE_PHONE);
            }
            Long time = System.currentTimeMillis();
            entity.setSendTime(time);
            entity.setStatus(IdEntity.DataStatus.ENABLE);
            //TODO cici 10分钟有效
            entity.setInvalidTime(time + 10 * 60 * 1000);
            messageCodeDao.insert(entity);
        }
        return dto;
    }

    @Transactional
    public StatusDto registerMobileUser(MobileUserRegisterDto reqDto) {
        StatusDto dto = new StatusDto(false);
        if (reqDto == null) {
            dto.setMsg("参数为空");
        } else {
            if (Strings.isNullOrEmpty(reqDto.getPhone()) || Strings.isNullOrEmpty(reqDto.getCode())) {
                dto.setMsg("手机号、验证码不能为空");
            } else {
                MobileUser user = dao.selectByPhone(reqDto.getPhone());
                if (user != null) {
                    dto.setMsg("该手机号已存在,请直接登录");
                } else {
                    MessageCode messageCode = messageCodeDao.selectByPhoneAndTime(reqDto.getPhone(), System.currentTimeMillis(), EntitySetting.MessageCodeType.REGISTER);
                    if (messageCode == null) {
                        dto.setMsg("该手机验证码不存在");
                    } else if (!reqDto.getCode().equals(messageCode.getCode())) {
                        dto.setMsg("验证码不正确");
                    } else {
                        user = new MobileUser();
                        if (Strings.isNullOrEmpty(reqDto.getPassword())) {
                            reqDto.setPassword("123456");
                        }
                        UserPasswordEncrypt up = UsersUtil.encryptPassword(reqDto.getPassword());
                        user.setPassword(up.getPassword());
                        user.setSalt(up.getSalt());
                        user.setStatus(IdEntity.DataStatus.ENABLE);
                        user.setImage(reqDto.getImage());
                        user.setNickName(reqDto.getNickName());
                        user.setOpenid(reqDto.getOpenid());
                        user.setPhone(reqDto.getPhone());
                        Long userId = super.create(user);
                        dto.setStatus(true);
                        messageCode.setStatus(IdEntity.DataStatus.DISABLE);
                        messageCodeDao.updateById(messageCode);
                        //插入用户手机号表
                        UserPhone userPhone = new UserPhone();
                        userPhone.setPhone(reqDto.getPhone());
                        userPhone.setUserId(userId);
                        userPhone.setTime(System.currentTimeMillis());
                        userPhoneDao.insert(userPhone);
                    }
                }
            }
        }
        return dto;
    }

    public DetailDto<MobileUserDto> verifyUserOpenid(String openid) {
        DetailDto<MobileUserDto> dto = new DetailDto<>(false);
        if (Strings.isNullOrEmpty(openid)) {
            dto.setMsg("openid为空");
        } else {
            List<MobileUser> userList = dao.selectByOpenid(openid);
            if (Collections3.isEmpty(userList)) {
                dto.setMsg("用户不存在");
            } else {
                dto.setStatus(true);
                dto.setDetail(BeanMapper.map(userList.get(0), MobileUserDto.class));
            }
        }
        return dto;
    }

    public DetailDto<UserGradeDto> verifyUserSign(Long userId) {
        DetailDto<UserGradeDto> dto = new DetailDto<>(false);
        if (userId == null || userId == 0L) {
            dto.setMsg("用户不存在");
        } else {
            MobileUser user = getBaseDao().selectById(userId);
            if (user == null) {
                dto.setMsg("用户不存在");
            } else {
                long current = System.currentTimeMillis();//当前时间毫秒数
                long beginTime = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
                long endTime = beginTime + 24 * 60 * 60 * 1000 - 1;//今天23点59分59秒的毫秒数
                List<UserIntegral> list = userSignIntegralDao.selectByUserIdAndTime(userId, beginTime, endTime);
                dto.setStatus(true);
                UserGradeDto userGradeDto = new UserGradeDto();
                if (Collections3.isNotEmpty(list)) {
                    userGradeDto.setSigned(true);
                }
                userGradeDto.setIntegral(user.getExperience() == null ? 0 : user.getExperience().intValue());
                //查询等级
                GradeSetting gradeSetting = gradeSettingDao.selectByIntegral(user.getExperience() == null ? 0 : user.getExperience());
                if (gradeSetting != null) {
                    userGradeDto.setDays(user.getSignDay() == null ? 0 : user.getSignDay());
                    userGradeDto.setNextIntegral(gradeSetting.getEndIntegral() == null || gradeSetting.getEndIntegral() == 0 ? -1 : gradeSetting.getEndIntegral() - (user.getSignDay() == null || user.getSignDay() == 0 ? 0 : user.getSignDay()));
                    userGradeDto.setNextName(gradeSetting.getName());
                }
                //查询用户签到总数
                Long sum = userSignIntegralDao.selectSumIntegralByUserId(userId);
                userGradeDto.setSignIntegral(sum);
                dto.setDetail(userGradeDto);
            }

        }
        return dto;
    }

    @Transactional
    public StatusDto userSignDay(Long userId) {
        StatusDto dto = new StatusDto(false);
        if (userId == null || userId == 0L) {
            dto.setMsg("用户不存在");
        } else {
            MobileUser user = getBaseDao().selectById(userId);
            if (user == null) {
                dto.setMsg("用户不存在");
            } else {
                long current = System.currentTimeMillis();//当前时间毫秒数
                long beginTime = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
                long endTime = beginTime + 24 * 60 * 60 * 1000 - 1;//今天23点59分59秒的毫秒数
                List<UserIntegral> list = userSignIntegralDao.selectByUserIdAndTime(userId, beginTime, endTime);
                dto.setStatus(true);
                if (Collections3.isEmpty(list)) {
                    UserIntegral userSignIntegral = new UserIntegral();
                    Long time = System.currentTimeMillis();
                    userSignIntegral.setSignTime(time);
                    userSignIntegral.setUserId(userId);
                    userSignIntegral.setCreateTime(time);
                    userSignIntegral.setUpdateTime(time);
                    userSignIntegral.setCreateId(userId);
                    userSignIntegral.setUpdateId(userId);
                    //TODO cici 待确定签到积分
                    userSignIntegral.setIntegral(1);
                    userSignIntegralDao.insert(userSignIntegral);
                    UserGradeDto userGradeDto = new UserGradeDto();
                    if (Collections3.isNotEmpty(list)) {
                        userGradeDto.setSigned(true);
                    }
                    //查询上一天，是否有签到记录
                    long lastBeginTime = beginTime - (24 * 60 * 60 * 100);
                    long lastEndTime = endTime - (24 * 60 * 60 * 100);
                    List<UserIntegral> lastList = userSignIntegralDao.selectByUserIdAndTime(userId, lastBeginTime, lastEndTime);
                    if (Collections3.isNotEmpty(lastList)) {
                        user.setSignDay(user.getSignDay() == null ? 1 : user.getSignDay() + 1);
                    } else {
                        user.setSignDay(1);
                    }
                    //TODO cici 待确认经验值和积分
                    user.setExperience(user.getExperience() == null ? 1 : user.getExperience() + 1);
                    user.setIntegral(user.getIntegral() == null ? 1 : user.getIntegral() + 1);
                    //查询用户经验对应的等级和等级名称
                    GradeSetting gradeSetting = gradeSettingDao.selectByIntegral(user.getExperience() == null ? 0 : user.getExperience());
                    if (gradeSetting != null) {
                        user.setGradeId(gradeSetting.getId());
                        user.setGradeName(gradeSetting.getName());
                    }
                    super.updateById(user);
                }
            }
        }
        return dto;
    }

    public DetailDto<UserMyselfDto> userMyself(Long id) {
        DetailDto<UserMyselfDto> dto = new DetailDto<>(false);
        if (id == null || id == 0L) {
            dto.setMsg("用户id为空");
        } else {
            MobileUser mobileUser = (MobileUser) dao.selectById(id);
            if (mobileUser == null) {
                dto.setMsg("用户不存在");
            } else {
                UserMyselfDto um = new UserMyselfDto();
                um.setId(id);
                um.setImage(mobileUser.getImage());
                um.setNickName(mobileUser.getNickName());
                um.setGradeName(mobileUser.getGradeName());
                um.setGradeId(mobileUser.getGradeId());
                um.setIntegral(mobileUser.getIntegral() == null ? 0 : mobileUser.getIntegral().intValue());
                um.setOpenid(mobileUser.getOpenid());
                UserOrder userOrder = new UserOrder();
                userOrder.setUserId(id);
                userOrder.setOrderStatus(EntitySetting.OrderStatus.TAKING);
                //查询待提货、已提货总数
                Long taking = userOrderDao.selectCount(userOrder);
                if (taking != null) {
                    um.setTakingSum(taking.intValue());
                }
                userOrder.setOrderStatus(EntitySetting.OrderStatus.TAKEN);
                //查询待提货、已提货总数
                Long taken = userOrderDao.selectCount(userOrder);
                if (taken != null) {
                    um.setTakenSum(taken.intValue());
                }
                dto.setDetail(um);
                dto.setStatus(true);
            }
        }
        return dto;
    }

    @Transactional
    public StatusDto updatePasswordByUserId(Long id, String oldPwd, String newPwd) {
        StatusDto dto = new StatusDto(false);
        if (id == null || id == 0L) {
            dto.setMsg("用户不存在");
        } else if (Strings.isNullOrEmpty(oldPwd) || Strings.isNullOrEmpty(newPwd)) {
            dto.setMsg("旧密码和新密码都不能为空");
        } else {
            MobileUser user = getBaseDao().selectById(id);
            if (user == null) {
                dto.setMsg("用户不存在");
            } else {
                String pwd = UsersUtil.encryptPasswordBySalt(oldPwd, user.getSalt());
                if (!pwd.equals(user.getPassword())) {
                    dto.setMsg("旧密码不正确");
                } else {
                    String newPassword = UsersUtil.encryptPasswordBySalt(newPwd, user.getSalt());
                    user.setPassword(newPassword);
                    super.updateById(user);
                    dto.setStatus(true);
                }
            }
        }
        return dto;
    }

    @Transactional
    public StatusDto changeUserPhoneByIdCode(Long id, String phone, String code) {
        StatusDto dto = new StatusDto(false);
        if (id == null || id == 0L) {
            dto.setMsg("用户参数为空");
        } else if (Strings.isNullOrEmpty(phone)) {
            dto.setMsg("手机号为空");
        } else if (Strings.isNullOrEmpty(code)) {
            dto.setMsg("验证码为空");
        } else {
            MobileUser mobileUser = getBaseDao().selectById(id);
            if (mobileUser == null) {
                dto.setMsg("用户不存在");
            } else {
                String userPhone = mobileUser.getPhone();
                if (phone.equals(userPhone)) {
                    dto.setMsg("手机号一致的,无需修改");
                } else {
                    MessageCode messageCode = messageCodeDao.selectByPhoneAndTime(phone, System.currentTimeMillis(), EntitySetting.MessageCodeType.CHANGE_PHONE);
                    if (messageCode == null) {
                        dto.setMsg("手机号验证码不存在");
                    } else if (!code.equals(messageCode.getCode())) {
                        dto.setMsg("验证码不正确");
                    } else {
                        mobileUser.setPhone(phone);
                        super.updateById(mobileUser);
                        //插入用户手机号表
                        UserPhone up = new UserPhone();
                        up.setPhone(phone);
                        up.setUserId(mobileUser.getId());
                        up.setTime(System.currentTimeMillis());
                        userPhoneDao.insert(up);
                        dto.setStatus(true);
                    }
                }
            }
        }
        return dto;
    }

    public DataTable<MobileUserDto> listMobileUser(DataTableRequest dataTable) {
        DataTable<MobileUserDto> dto = new DataTable<>();
        MobileUserVo vo = new MobileUserVo();
        DataTableMeta meta = new DataTableMeta();
        if (dataTable != null) {
            vo.setCcStart((dataTable.getPagination().getPage() == 0 ? 0 : dataTable.getPagination().getPage() - 1) * dataTable.getPagination().getPerpage());
            vo.setCcEnd(dataTable.getPagination().getPerpage());
            vo.setCcSort(dataTable.getPagination().getSort());
            vo.setCcField(dataTable.getPagination().getField());
            meta.setPerpage(dataTable.getPagination().getPerpage());
            meta.setPages(dataTable.getPagination().getPages());
            meta.setPage(dataTable.getPagination().getPage());
        }
        List<MobileUser> list = getBaseDao().select(vo);
        dto.setData(BeanMapper.mapList(list, MobileUserDto.class));
        Long count = getBaseDao().selectCount(vo);
        meta.setTotal(count == null ? 0 : count.intValue());
        dto.setMeta(meta);
        return dto;
    }
    @Transactional
    public StatusDto updateUserIntegralById(Long id, Long integral) {
        StatusDto dto = new StatusDto(false);
        if (id == null || id == 0L) {
            dto.setMsg("用户为空");
        } else if (integral == null) {
            dto.setMsg("积分为空");
        } else {
            MobileUser user = getBaseDao().selectById(id);
            if (user == null) {
                dto.setMsg("用户不存在");
            }else{

            }
        }
        return dto;
    }
}