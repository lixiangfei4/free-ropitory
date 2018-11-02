package com.ixinhoo.shopping.service.order;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.ListDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.api.service.BaseService;
import com.ixinhoo.api.util.UsersUtil;
import com.ixinhoo.crumbs.code.util.collection.Collections3;
import com.ixinhoo.crumbs.code.util.key.CodeNumberMaker;
import com.ixinhoo.crumbs.code.util.mapper.BeanMapper;
import com.ixinhoo.shopping.dao.order.UserOrderAddressDao;
import com.ixinhoo.shopping.dao.order.UserOrderDao;
import com.ixinhoo.shopping.dao.order.UserOrderProductDao;
import com.ixinhoo.shopping.dao.setting.CouponSettingDao;
import com.ixinhoo.shopping.dao.setting.IntegralSettingDao;
import com.ixinhoo.shopping.dao.store.ProductDao;
import com.ixinhoo.shopping.dao.store.StoreDao;
import com.ixinhoo.shopping.dao.user.MobileUserDao;
import com.ixinhoo.shopping.dao.user.UserAddressDao;
import com.ixinhoo.shopping.dao.user.UserConsumeIntegralDao;
import com.ixinhoo.shopping.dto.order.*;
import com.ixinhoo.shopping.dto.user.UserAndStoreAddressDto;
import com.ixinhoo.shopping.entity.EntitySetting;
import com.ixinhoo.shopping.entity.order.UserOrder;
import com.ixinhoo.shopping.entity.order.UserOrderAddress;
import com.ixinhoo.shopping.entity.order.UserOrderProduct;
import com.ixinhoo.shopping.entity.setting.CouponSetting;
import com.ixinhoo.shopping.entity.setting.IntegralSetting;
import com.ixinhoo.shopping.entity.store.Product;
import com.ixinhoo.shopping.entity.store.Store;
import com.ixinhoo.shopping.entity.user.MobileUser;
import com.ixinhoo.shopping.entity.user.UserAddress;
import com.ixinhoo.shopping.entity.user.UserConsumeIntegral;
import com.ixinhoo.shopping.vo.order.UserOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserOrderService extends BaseService {
    @Autowired
    private UserOrderDao<UserOrder> dao;
    @Autowired
    private MobileUserDao<MobileUser> userDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private StoreDao<Store> storeDao;
    @Autowired
    private UserAddressDao<UserAddress> userAddressDao;
    @Autowired
    private UserOrderAddressDao<UserOrderAddress> userOrderAddressDao;
    @Autowired
    private CouponSettingDao<CouponSetting> couponSettingDao;
    @Autowired
    private IntegralSettingDao<IntegralSetting> integralSettingDao;
    @Autowired
    private UserOrderProductDao<UserOrderProduct> userOrderProductDao;
    @Autowired
    private UserConsumeIntegralDao<UserConsumeIntegral> userConsumeIntegralDao;

    @Override
    protected BaseDao<UserOrder> getBaseDao() {
        return dao;
    }

    /**
     * 下单购买。TODO cici 待测
     *
     * @param reqDto
     * @return
     */
    @Transactional
    public StatusDto buyProduct(UserOrderBuyDto reqDto) {
        StatusDto dto = new StatusDto(false);
        if (reqDto == null) {
            dto.setMsg("参数为空");
        } else if (reqDto.getUserId() == null || reqDto.getUserId() == 0L) {
            dto.setMsg("用户为空");
        } else if (reqDto.getProductId() == null || reqDto.getProductId() == 0L) {
            dto.setMsg("商品为空");
        } else if (reqDto.getAddressId() == null || reqDto.getAddressId() == 0L) {
            dto.setMsg("地址为空");
        } else {
            MobileUser user = (MobileUser) userDao.selectById(reqDto.getUserId());
            if (user == null) {
                dto.setMsg("用户不存在");
            } else {
                Integer convertIntegral = reqDto.getConvertIntegral();
                if (convertIntegral != null && convertIntegral != 0L && convertIntegral > (user.getIntegral() == null ? 0 : user.getIntegral().intValue())) {
                    dto.setMsg("用户积分不足");
                    dto.setStatus(false);
                } else {
                    Product product = (Product) productDao.selectById(reqDto.getProductId());
                    if (product == null) {
                        dto.setMsg("商品不存在");
                        dto.setStatus(false);
                    } else {
                        UserOrder userOrder = new UserOrder();
                        userOrder.setUserId(reqDto.getUserId());
                        userOrder.setOrderStatus(EntitySetting.OrderStatus.TAKING);
                        userOrder.setOrderTime(System.currentTimeMillis());
                        userOrder.setProductNum(reqDto.getNumber());
                        UserOrderAddress userOrderAddress = null;
                        String orderNum = CodeNumberMaker.getInstance().orderCodeNum();
                        UserOrderProduct userOrderProduct = new UserOrderProduct();
                        UserConsumeIntegral userConsumeIntegral = new UserConsumeIntegral();
                        userOrderProduct.setOrderNum(orderNum);
                        if (EntitySetting.BuyProductType.STORE.equals(reqDto.getBuy())) {//门店
                            Store store = (Store) storeDao.selectById(reqDto.getAddressId());
                            if (store == null) {
                                dto.setMsg("门店不存在");
                                dto.setStatus(false);
                            } else {
                                userOrder.setBuyType(EntitySetting.BuyProductType.STORE);
                                userOrder.setAddressId(reqDto.getAddressId());
                                userOrderAddress = new UserOrderAddress();
                                userOrderAddress.setName(store.getName());
                                userOrderAddress.setProvince(store.getProvince());
                                userOrderAddress.setCity(store.getCity());
                                userOrderAddress.setArea(store.getArea());
                                userOrderAddress.setAddress(store.getAddress());
                                userOrderAddress.setUserId(reqDto.getUserId());
                                userOrderAddress.setPhone(store.getPhone());
                                userOrderAddress.setLongitude(store.getLongitude());
                                userOrderAddress.setLatitude(store.getLatitude());
                                dto.setStatus(true);
                            }
                        } else {//快递
                            UserAddress address = (UserAddress) userAddressDao.selectById(reqDto.getAddressId());
                            if (address == null) {
                                dto.setMsg("地址不存在");
                                dto.setStatus(false);
                            } else {
                                userOrder.setAddressId(address.getId());
                                userOrder.setBuyType(EntitySetting.BuyProductType.EXPRESS);
                                userOrderAddress = BeanMapper.map(address, UserOrderAddress.class);
                                dto.setStatus(true);
                            }
                        }
                        if (EntitySetting.ProductType.SHOPPING_MALL.equals(product.getType())) {//普通商城
                            Double price = product.getPrice();
                            if (reqDto.getCouponId() != null && reqDto.getCouponId() != 0L) {
                                CouponSetting couponSetting = (CouponSetting) couponSettingDao.selectById(reqDto.getCouponId());
                                if (couponSetting == null) {
                                    dto.setMsg("优惠券不存在");
                                    dto.setStatus(false);
                                } else {
                                    if (price > couponSetting.getConditionPrice()) {
                                        price -= (couponSetting.getReducePrice() == null ? 0 : couponSetting.getReducePrice());
                                        dto.setStatus(true);
                                        userOrderProduct.setCouponId(reqDto.getCouponId());
                                        userOrderProduct.setCouponPrice(couponSetting.getReducePrice());
                                    }
                                }
                            } else {
                                dto.setStatus(true);
                            }
                            if (convertIntegral != null && convertIntegral != 0L) {
                                //查询积分能兑换的金额
                                List<IntegralSetting> integralSettingList = integralSettingDao.select(null);
                                if (Collections3.isNotEmpty(integralSettingList)) {
                                    IntegralSetting integralSetting = integralSettingList.get(0);
                                    if (integralSetting.getPrice() != null) {
                                        price -= convertIntegral * integralSetting.getPrice();
                                        userOrder.setOrderIntegral(convertIntegral);
                                        userOrderProduct.setProductIntegral(convertIntegral);
                                        userOrderProduct.setIntegralPrice(convertIntegral * integralSetting.getPrice());
                                        userConsumeIntegral.setIntegral(convertIntegral);
                                    }
                                }
                            }
                            userOrder.setOrderPrice(price < 0L ? 0 : price);
                        } else {//积分商城
                            if (user.getIntegral() == null || user.getIntegral() == 0L || user.getIntegral() == null || user.getIntegral() == 0L || user.getIntegral() < convertIntegral) {
                                dto.setMsg("积分不足");
                            } else {
                                userOrder.setOrderPrice(product.getPrice());
                                userOrder.setOrderIntegral(product.getIntegral());
                                userOrderProduct.setProductIntegral(product.getIntegral());
                                userConsumeIntegral.setIntegral(product.getIntegral());
                                dto.setStatus(true);
                            }
                        }
                        if (dto.getStatus()) {
                            userOrder.setOrderNum(orderNum);
                            Long orderId = super.create(userOrder);
                            userOrderProduct.setOrderId(orderId);
                            userOrderProduct.setProductNum(userOrder.getProductNum());
                            userOrderProduct.setCategoryName(reqDto.getCategory());
                            userOrderProduct.setRealPrice(userOrder.getOrderPrice());
                            userOrderProduct.setProductPrice(product.getPrice());
                            userOrderProduct.setProductImage(product.getImage());
                            userOrderProduct.setProductRemark(product.getRemark());
                            userOrderProduct.setUserId(reqDto.getUserId());
                            userOrderProduct.setCreateId(UsersUtil.id());
                            userOrderProduct.setUpdateId(UsersUtil.id());
                            userOrderProduct.setCreateTime(System.currentTimeMillis());
                            userOrderProduct.setUpdateTime(System.currentTimeMillis());
                            userOrderProduct.setProductId(product.getId());
                            userOrderProduct.setProductName(product.getName());
                            userOrderProduct.setProductType(product.getType());
                            userOrderProductDao.insert(userOrderProduct);
                            if (userOrderAddress != null) {
                                userOrderAddress.setOrderId(orderId);
                                userOrderAddress.setOrderNum(orderNum);
                                userOrderAddress.setCreateId(UsersUtil.id());
                                userOrderAddress.setUpdateId(UsersUtil.id());
                                userOrderAddress.setCreateTime(System.currentTimeMillis());
                                userOrderAddress.setUpdateTime(System.currentTimeMillis());
                                userOrderAddressDao.insert(userOrderAddress);
                            }
                            //消费积分
                            userConsumeIntegral.setUserId(reqDto.getUserId());
                            userConsumeIntegral.setTime(System.currentTimeMillis());
                            userConsumeIntegral.setCreateId(UsersUtil.id());
                            userConsumeIntegral.setUpdateId(UsersUtil.id());
                            userConsumeIntegral.setCreateTime(System.currentTimeMillis());
                            userConsumeIntegral.setUpdateTime(System.currentTimeMillis());
                            userConsumeIntegralDao.insert(userConsumeIntegral);
                            user.setIntegral(user.getIntegral() - convertIntegral);
                            user.setUpdateId(UsersUtil.id());
                            user.setUpdateTime(System.currentTimeMillis());
                            userDao.updateById(user);
                        }
                    }
                }
            }
        }
        return dto;
    }

    public ListDto<UserOrderListRspDto> pageMyOrder(UserOrderListReqDto reqDto) {
        ListDto<UserOrderListRspDto> dto = new ListDto<>();
        dto.setStatus(false);
        if (reqDto == null) {
            dto.setMsg("参数为空");
        } else if (reqDto.getUserId() == null || reqDto.getUserId() == 0L) {
            dto.setMsg("用户不存在");
        } else {
            dto.setStatus(true);
            UserOrderVo vo = new UserOrderVo();
            if (reqDto != null) {
                Integer s = reqDto.getS() == null || reqDto.getS() == 0L ? Integer.MAX_VALUE : reqDto.getS();
                Integer p = reqDto.getP() == null ? 0 : reqDto.getP() * s;
                vo.setCcStart(p);
                vo.setCcEnd(s);
                vo.setCcStart(p);
                vo.setCcEnd(s);
                vo.setCcStart(p);
                vo.setUserId(reqDto.getUserId());
                vo.setOrderStatus(reqDto.getStatus());
            }
            vo.setCcSort("DESC");
            vo.setCcField("order_time");
            vo.setUserId(reqDto.getUserId());
            List<UserOrder> orders = getBaseDao().select(vo);
            if (Collections3.isNotEmpty(orders)) {
                List<Long> ids = orders.stream().map(d -> d.getId()).collect(Collectors.toList());
                //查询商品
                List<UserOrderProduct> products = userOrderProductDao.selectByOrderIds(ids);
                Map<Long, List<UserOrderProductDto>> map = Maps.newHashMap();
                Map<Long, String> addressMap = Maps.newHashMap();
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
                //查询地址
                List<UserOrderAddress> userOrderAddresses = userOrderAddressDao.selectByOrderIds(ids);
                if (Collections3.isNotEmpty(userOrderAddresses)) {
                    userOrderAddresses.forEach(d -> {
                        addressMap.put(d.getOrderId(), d.getAddress());
                    });
                }
                List<UserOrderListRspDto> orderList = Lists.newArrayList();
                orders.forEach(d -> {
                    UserOrderListRspDto temp = new UserOrderListRspDto();
                    if (addressMap.containsKey(d.getId())) {
                        temp.setAddressName(addressMap.get(d.getId()));
                    }
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
        return dto;
    }

    public DetailDto<UserOrderDetailDto> findUserOrderDetailByOrderId(Long orderId) {
        DetailDto<UserOrderDetailDto> dto = new DetailDto<>(false);
        if (orderId == null || orderId == 0L) {
            dto.setMsg("请求参数为空");
        } else {
            //查询订单信息
            UserOrder userOrder = getBaseDao().selectById(orderId);
            if (userOrder == null) {
                dto.setMsg("订单不存在");
            } else {
                UserOrderDetailDto detailDto = new UserOrderDetailDto();
                detailDto.setStatus(userOrder.getOrderStatus());
                detailDto.setEvaluated(userOrder.getEvaluated());
                detailDto.setBuyType(userOrder.getBuyType());
                detailDto.setOrderNum(userOrder.getOrderNum());
                detailDto.setOrderId(userOrder.getId());
                detailDto.setOrderTime(userOrder.getOrderTime());
                detailDto.setCancelTime(userOrder.getCancelTime());
                detailDto.setTakeTime(userOrder.getTakeTime());
                detailDto.setCloseTime(userOrder.getCloseTime());
                //查询订单的地址
                List<UserOrderAddress> addresses = userOrderAddressDao.selectByOrderIds(Lists.newArrayList(orderId));
                if (Collections3.isNotEmpty(addresses)) {
                    UserOrderAddress address = addresses.get(0);
                    detailDto.setAddress(BeanMapper.map(address, UserAndStoreAddressDto.class));
                }
                //查询优惠信息和商品信息
                List<UserOrderProductDto> products = Lists.newArrayList();
                List<UserOrderProduct> userOrderProducts = userOrderProductDao.selectByOrderIds(Lists.newArrayList(orderId));
                if (Collections3.isNotEmpty(userOrderProducts)) {
                    userOrderProducts.forEach(d -> {
                        UserOrderProductDto temp = new UserOrderProductDto();
                        temp.setRemark(d.getProductRemark());
                        temp.setType(d.getProductType());
                        temp.setName(d.getProductName());
                        temp.setCategory(d.getCategoryName());
                        temp.setId(d.getProductId());
                        temp.setNumber(d.getProductNum());
                        temp.setImage(d.getProductImage());
                        temp.setConvertIntegral(d.getProductIntegral());
                        temp.setIntegralPrice(d.getIntegralPrice());
                        temp.setPrice(d.getRealPrice());
                        products.add(temp);
                    });
                }
                detailDto.setProducts(products);
                dto.setDetail(detailDto);
                dto.setStatus(true);
            }
        }
        return dto;
    }

    @Transactional
    public StatusDto cancelUserOrderByOrderId(Long orderId) {
        StatusDto dto = new StatusDto(false);
        if (orderId == null || orderId == 0L) {
            dto.setMsg("订单id为空");
        } else {
            UserOrder userOrder = getBaseDao().selectById(orderId);
            if (userOrder == null) {
                dto.setMsg("订单不存在");
            } else {
                userOrder.setOrderStatus(EntitySetting.OrderStatus.CANCEL);
                userOrder.setCancelTime(System.currentTimeMillis());
                getBaseDao().updateById(userOrder);
                dto.setStatus(true);
            }
        }
        return dto;
    }

    @Transactional
    public StatusDto deleteUserOrderByOrderId(Long orderId) {
        StatusDto dto = new StatusDto(false);
        if (orderId == null || orderId == 0L) {
            dto.setMsg("订单id为空");
        } else {
            UserOrder userOrder = getBaseDao().selectById(orderId);
            if (userOrder == null) {
                dto.setMsg("订单不存在");
            } else {
                getBaseDao().deleteById(orderId);
                dto.setStatus(true);
            }
        }
        return dto;
    }
}