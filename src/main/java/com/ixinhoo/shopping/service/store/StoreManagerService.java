package com.ixinhoo.shopping.service.store;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.ListDto;
import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.api.service.BaseService;
import com.ixinhoo.crumbs.code.util.collection.Collections3;
import com.ixinhoo.shopping.dao.order.UserOrderDao;
import com.ixinhoo.shopping.dao.order.UserOrderProductDao;
import com.ixinhoo.shopping.dao.store.StoreDao;
import com.ixinhoo.shopping.dao.user.MobileUserDao;
import com.ixinhoo.shopping.dao.user.UserEvaluateDao;
import com.ixinhoo.shopping.dto.order.UserOrderListReqDto;
import com.ixinhoo.shopping.dto.order.UserOrderListRspDto;
import com.ixinhoo.shopping.dto.order.UserOrderProductDto;
import com.ixinhoo.shopping.dto.store.StoreManagerInfoDto;
import com.ixinhoo.shopping.dto.system.UserStoreEvaluateDto;
import com.ixinhoo.shopping.entity.EntitySetting;
import com.ixinhoo.shopping.entity.order.UserOrder;
import com.ixinhoo.shopping.entity.order.UserOrderProduct;
import com.ixinhoo.shopping.entity.store.Store;
import com.ixinhoo.shopping.entity.user.MobileUser;
import com.ixinhoo.shopping.entity.user.UserEvaluate;
import com.ixinhoo.shopping.vo.order.UserOrderVo;
import com.ixinhoo.shopping.vo.user.UserEvaluateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StoreManagerService extends BaseService {
    @Autowired
    private StoreDao<Store> dao;
    @Autowired
    private UserOrderDao userOrderDao;
    @Autowired
    private UserOrderProductDao userOrderProductDao;
    @Autowired
    private UserEvaluateDao userEvaluateDao;
    @Autowired
    private MobileUserDao mobileUserDao;

    @Override
    protected BaseDao<Store> getBaseDao() {
        return dao;
    }


    public DetailDto<StoreManagerInfoDto> findStoreManagerInfoById(Long storeId) {
        DetailDto<StoreManagerInfoDto> dto = new DetailDto<>(false);
        if (storeId == null || storeId == 0L) {
            dto.setMsg("门店数据为空");
        } else {
            Store store = getBaseDao().selectById(storeId);
            if (store == null) {
                dto.setMsg("门店不存在");
            } else {
                dto.setStatus(true);
                StoreManagerInfoDto smi = new StoreManagerInfoDto();
                smi.setScore(store.getScore());
                smi.setName(store.getName());
                Long taking = userOrderDao.selectCountByAddressIdAndStatusAndType(storeId, EntitySetting.OrderStatus.TAKING, EntitySetting.BuyProductType.STORE);
                Long taken = userOrderDao.selectCountByAddressIdAndStatusAndType(storeId, EntitySetting.OrderStatus.TAKEN, EntitySetting.BuyProductType.STORE);
                Long cancel = userOrderDao.selectCountByAddressIdAndStatusAndType(storeId, EntitySetting.OrderStatus.CANCEL, EntitySetting.BuyProductType.STORE);
                if (taking != null) {
                    smi.setTaking(taking.intValue());
                } else {
                    smi.setTaking(0);
                }
                if (taken != null) {
                    smi.setTaken(taken.intValue());
                } else {
                    smi.setTaken(0);
                }
                if (cancel != null) {
                    smi.setCancel(cancel.intValue());
                } else {
                    smi.setCancel(0);
                }
                dto.setDetail(smi);
            }
        }
        return dto;
    }

    public ListDto<UserOrderListRspDto> pageOrderList(UserOrderListReqDto reqDto) {
        ListDto<UserOrderListRspDto> dto = new ListDto<>();
        dto.setStatus(false);
        if (reqDto == null) {
            dto.setMsg("参数为空");
        } else if (reqDto.getStoreId() == null || reqDto.getStoreId() == 0L) {
            dto.setMsg("门店不存在");
        } else {
            Store store = getBaseDao().selectById(reqDto.getStoreId());
            if (store == null) {
                dto.setMsg("门店不存在");
            } else {
                dto.setStatus(true);
                UserOrderVo vo = new UserOrderVo();
                if (reqDto != null) {
                    Integer s = reqDto.getS() == null || reqDto.getS() == 0L ? Integer.MAX_VALUE : reqDto.getS();
                    Integer p = reqDto.getP() == null ? 0 : reqDto.getP() * s;
                    vo.setCcStart(p);
                    vo.setCcEnd(s);
                    vo.setAddressId(reqDto.getStoreId());
                    vo.setBuyType(EntitySetting.BuyProductType.STORE);
                    vo.setOrderStatus(reqDto.getStatus());
                }
                vo.setCcSort("DESC");
                vo.setCcField("order_time");
                vo.setUserId(reqDto.getUserId());
                List<UserOrder> orders = userOrderDao.select(vo);
                if (Collections3.isNotEmpty(orders)) {
                    List<Long> ids = orders.stream().map(d -> d.getId()).collect(Collectors.toList());
                    //查询商品
                    List<UserOrderProduct> products = userOrderProductDao.selectByOrderIds(ids);
                    Map<Long, List<UserOrderProductDto>> map = Maps.newHashMap();
                    if (Collections3.isNotEmpty(products)) {
                        products.forEach((d) -> {
                            List<UserOrderProductDto> list;
                            if (map.containsKey(d.getOrderId())) {
                                list = map.get(d.getOrderId());
                            } else {
                                list = Lists.newArrayList();
                            }
                            UserOrderProductDto temp = new UserOrderProductDto();
                            temp.setImage(d.getProductImage());
                            temp.setCategory(d.getCategoryName());
                            temp.setConvertIntegral(d.getProductIntegral());
                            temp.setId(d.getProductId());
                            temp.setNumber(d.getProductNum());
                            temp.setName(d.getProductName());
                            temp.setPrice(d.getRealPrice());
                            temp.setType(d.getProductType());
                            temp.setRemark(d.getProductRemark());
                            list.add(temp);
                            map.put(d.getOrderId(), list);
                        });
                    }
                    List<UserOrderListRspDto> orderList = Lists.newArrayList();
                    orders.forEach(d -> {
                        UserOrderListRspDto temp = new UserOrderListRspDto();
                        temp.setAddressName(store.getName());
                        temp.setOrderNum(d.getOrderNum());
                        temp.setOrderId(d.getId());
                        temp.setBuyType(d.getBuyType());
                        temp.setEvaluated(d.getEvaluated());
                        temp.setStatus(d.getOrderStatus());
                        if (map.containsKey(d.getId())) {
                            temp.setProducts(map.get(d.getId()));
                        }
                        orderList.add(temp);
                    });
                    dto.setList(orderList);
                }
            }
        }
        return dto;
    }

    public ListDto<UserStoreEvaluateDto> pageOrderEvaluate(Long storeId, Long orderId, Integer p, Integer s) {
        ListDto<UserStoreEvaluateDto> dto = new ListDto<>();
        dto.setStatus(false);
        if (storeId == null || storeId == 0L) {
            dto.setMsg("门店不存在");
        } else {
            dto.setStatus(true);
            UserEvaluateVo vo = new UserEvaluateVo();
            s = s == null || s == 0L ? Integer.MAX_VALUE : s;
            p = p == null ? 0 : p * s;
            vo.setCcStart(p);
            vo.setCcEnd(s);
            if (orderId != null && orderId != 0L) {
                vo.setDataId(orderId);
            }
            vo.setType(EntitySetting.UserEvaluateType.STORE);
            List<UserEvaluate> list = userEvaluateDao.select(vo);
            if (Collections3.isNotEmpty(list)) {
                Map<Long,MobileUser> userMap = Maps.newHashMap();
                List<Long> ids = list.stream().map(d -> d.getUserId()).collect(Collectors.toList());
                if(Collections3.isNotEmpty(ids)){
                    List<MobileUser> userList = mobileUserDao.selectByIds(ids);
                    if(Collections3.isNotEmpty(userList)){
                        userList.forEach(d->{
                            userMap.put(d.getId(),d);
                        });
                    }
                }
                List<UserStoreEvaluateDto> dtoList = Lists.newArrayList();
                list.forEach(d->{
                    UserStoreEvaluateDto temp = new UserStoreEvaluateDto();
                    temp.setContent(d.getContent());
                    temp.setTime(d.getTime());
                    if(userMap.containsKey(d.getUserId())){
                        MobileUser user = userMap.get(d.getUserId());
                        temp.setImage(user.getImage());
                        temp.setNickName(user.getNickName());
                    }
                    dtoList.add(temp);
                });
                dto.setList(dtoList);
            }

        }
        return dto;
    }
}