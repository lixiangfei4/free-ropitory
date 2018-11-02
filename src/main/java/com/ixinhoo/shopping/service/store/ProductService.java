package com.ixinhoo.shopping.service.store;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.ListDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.code.DataTable;
import com.ixinhoo.api.code.DataTableMeta;
import com.ixinhoo.api.code.DataTableRequest;
import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.api.entity.IdEntity;
import com.ixinhoo.api.service.BaseService;
import com.ixinhoo.api.util.UsersUtil;
import com.ixinhoo.crumbs.code.util.collection.Collections3;
import com.ixinhoo.crumbs.code.util.mapper.BeanMapper;
import com.ixinhoo.shopping.dao.setting.CouponSettingDao;
import com.ixinhoo.shopping.dao.setting.GradeSettingDao;
import com.ixinhoo.shopping.dao.setting.IntegralSettingDao;
import com.ixinhoo.shopping.dao.store.ProductCategoryDao;
import com.ixinhoo.shopping.dao.store.ProductDao;
import com.ixinhoo.shopping.dao.store.ProductPriceDao;
import com.ixinhoo.shopping.dao.store.StoreDao;
import com.ixinhoo.shopping.dao.user.MobileUserDao;
import com.ixinhoo.shopping.dao.user.UserAddressDao;
import com.ixinhoo.shopping.dto.store.*;
import com.ixinhoo.shopping.entity.EntitySetting;
import com.ixinhoo.shopping.entity.setting.CouponSetting;
import com.ixinhoo.shopping.entity.setting.GradeSetting;
import com.ixinhoo.shopping.entity.setting.IntegralSetting;
import com.ixinhoo.shopping.entity.store.*;
import com.ixinhoo.shopping.entity.user.MobileUser;
import com.ixinhoo.shopping.entity.user.UserAddress;
import com.ixinhoo.shopping.vo.store.ProductVo;
import com.ixinhoo.shopping.vo.store.StoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService extends BaseService {
    @Autowired
    private ProductDao dao;
    @Autowired
    private ProductPriceDao<ProductPrice> productPriceDao;
    @Autowired
    private GradeSettingDao<GradeSetting> gradeSettingDao;
    @Autowired
    private ProductCategoryDao<ProductCategory> productCategoryDao;
    @Autowired
    private MobileUserDao<MobileUser> userDao;
    @Autowired
    private StoreDao<Store> storeDao;
    @Autowired
    private UserAddressDao addressDao;
    @Autowired
    private IntegralSettingDao<IntegralSetting> integralSettingDao;
    @Autowired
    private CouponSettingDao<CouponSetting> couponSettingDao;

    @Override
    protected BaseDao<Product> getBaseDao() {
        return dao;
    }

    @Transactional
    public StatusDto saveProduct(ProductDto reqDto) {
        StatusDto dto = new StatusDto(false);
        if (reqDto == null) {
            dto.setMsg("参数不能为空");
        } else {
            Product entity = BeanMapper.map(reqDto, Product.class);
            if (entity.getId() == null || entity.getId() == 0L) {
                entity.setStatus(IdEntity.DataStatus.ENABLE);
                super.create(entity);
                dto.setStatus(true);
            } else {
                Product dbEntity = getBaseDao().selectById(entity.getId());
                if (dbEntity == null) {
                    dto.setMsg("实体不存在");
                } else {
                    entity.setIntegral(dbEntity.getIntegral());
                    entity.setBuyCount(dbEntity.getBuyCount());
                    entity.setCollectCount(dbEntity.getCollectCount());
                    super.updateById(entity);
                    dao.deleteBannerByProductId(entity.getId());
                    dto.setStatus(true);
                }
            }
            if (dto.getStatus()) {
                if (Collections3.isNotEmpty(reqDto.getBannerList())) {
                    int sort = 100;
                    for (String e : reqDto.getBannerList()) {
                        ProductBanner banner = new ProductBanner();
                        banner.setAddress(e);
                        banner.setProductId(entity.getId());
                        banner.setSort(sort);
                        sort += 100;
                        dao.insertBanner(banner);
                    }
                }
            }
        }
        return dto;
    }

    public DataTable<ProductDto> listProduct(DataTableRequest dataTable, EntitySetting.ProductType type) {
        DataTable<ProductDto> dto = new DataTable<>();
        ProductVo vo = new ProductVo();
        DataTableMeta meta = new DataTableMeta();
        if (dataTable != null) {
            vo.setCcStart((dataTable.getPagination().getPage() == 0 ? 0 : dataTable.getPagination().getPage() - 1) * dataTable.getPagination().getPerpage());
            vo.setCcEnd(dataTable.getPagination().getPerpage());
            vo.setCcSort(dataTable.getPagination().getSort());
            vo.setCcField(dataTable.getPagination().getField());
            if (!Strings.isNullOrEmpty(dataTable.getQuery())) {
                vo.setNameLike(dataTable.getQuery());
            }
            meta.setPerpage(dataTable.getPagination().getPerpage());
            meta.setPages(dataTable.getPagination().getPages());
            meta.setPage(dataTable.getPagination().getPage());
        }
        if (type != null) {
            vo.setType(type);
        }
        List<Product> list = getBaseDao().select(vo);
        dto.setData(BeanMapper.mapList(list, ProductDto.class));
        Long count = getBaseDao().selectCount(vo);
        meta.setTotal(count == null ? 0 : count.intValue());
        dto.setMeta(meta);
        return dto;

    }

    public DetailDto<ProductDto> findProductAndBannerById(Long id, Long userId) {
        DetailDto<ProductDto> dto = new DetailDto<>(false);
        if (id == null || id == 0L) {
            dto.setMsg("参数为空");
        } else {
            Product product = dao.findProductAndBannerById(id);
            if (product == null) {
                dto.setMsg("数据不存在");
            } else {
                ProductDto productDto = BeanMapper.map(product, ProductDto.class);
                if (Collections3.isNotEmpty(product.getBanners())) {
                    List<String> list = Lists.newArrayList();
                    product.getBanners().forEach((d) -> {
                        list.add(d.getAddress());
                    });
                    productDto.setBannerList(list);
                } else {
                    productDto.setBannerList(Lists.newArrayList());
                }
                //查询当前用户可以获取到的真正的价格
                if (userId != null && userId != 0L) {
                    List<ProductPrice> productPrices = productPriceDao.selectByProductId(id);
                    if (Collections3.isNotEmpty(productPrices)) {
                        //查询用户等级
                        MobileUser user = BeanMapper.map(userDao.selectById(userId), MobileUser.class);
                        if (user != null) {
                            Long gradeId = user.getGradeId();
                            if (gradeId != null && gradeId != 0L) {
                                for (ProductPrice d : productPrices) {
                                    if (gradeId == d.getGradeId()) {
                                        productDto.setPrice(d.getPrice());
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                dto.setDetail(productDto);
                dto.setStatus(true);
            }
        }
        return dto;
    }

    @Transactional
    public StatusDto updateStatusByIds(List<Long> ids, IdEntity.DataStatus status) {
        StatusDto dto = new StatusDto(false);
        if (Collections3.isEmpty(ids) || status == null) {
            dto.setMsg("参数为空");
        } else {
            dao.updateStatusByIds(ids, status);
            dto.setStatus(true);
        }
        return dto;
    }

    public ListDto<ProductPriceDto> findProductPriceById(Long id) {
        ListDto<ProductPriceDto> dto = new ListDto<>();
        dto.setStatus(false);
        if (id == null || id == 0L) {
            dto.setMsg("参数为空");
        } else {
            List<ProductPrice> list = productPriceDao.selectByProductId(id);
            List<ProductPriceDto> dtoList = Lists.newArrayList();
            dto.setStatus(true);
            //查询等级
            List<GradeSetting> gsList = gradeSettingDao.select(null);
            Map<Long, ProductPrice> map = Maps.newHashMap();
            if (Collections3.isNotEmpty(list)) {
                list.forEach((d) -> {
                    map.put(d.getGradeId(), d);
                });
            }
            if (Collections3.isNotEmpty(gsList)) {
                gsList.forEach((d) -> {
                    ProductPriceDto p = new ProductPriceDto();
                    p.setGradeId(d.getId());
                    p.setGradeName(d.getName());
                    if (map.containsKey(d.getId())) {
                        ProductPrice g = map.get(d.getId());
                        p.setIntegral(g.getIntegral());
                        p.setPrice(g.getPrice());
                        p.setProductId(g.getProductId());
                        p.setId(g.getId());
                    }
                    dtoList.add(p);
                });
            }
            dto.setList(dtoList);
        }
        return dto;
    }

    @Transactional
    public StatusDto saveProductPrice(List<ProductPriceDto> list) {
        StatusDto dto = new StatusDto(false);
        if (Collections3.isEmpty(list)) {
            dto.setMsg("参数为空");
        } else {
            for (ProductPriceDto e : list) {
                ProductPrice productPrice = BeanMapper.map(e, ProductPrice.class);
                if (productPrice.getId() != null && productPrice.getId() != 0L) {
                    productPrice.setUpdateId(UsersUtil.id());
                    productPrice.setUpdateTime(System.currentTimeMillis());
                    productPriceDao.updateById(productPrice);
                } else {
                    productPrice.setCreateId(UsersUtil.id());
                    productPrice.setUpdateId(UsersUtil.id());
                    productPrice.setUpdateTime(System.currentTimeMillis());
                    productPrice.setUpdateTime(System.currentTimeMillis());
                    productPriceDao.insert(productPrice);
                }
            }
            dto.setStatus(true);
        }
        return dto;
    }

    public ListDto<ProductCouponDto> findProductCouponById(Long id) {
        ListDto<ProductCouponDto> dto = new ListDto<>();
        dto.setStatus(false);
        if (id == null || id == 0L) {
            dto.setMsg("参数为空");
        } else {
            //查询优惠券
            List<ProductPrice> list = productPriceDao.selectByProductId(id);
            List<ProductCouponDto> dtoList = Lists.newArrayList();
            dto.setStatus(true);
            //查询等级
            List<GradeSetting> gsList = gradeSettingDao.select(null);
            Map<Long, ProductPrice> map = Maps.newHashMap();
            if (Collections3.isNotEmpty(list)) {
                list.forEach((d) -> {
                    map.put(d.getGradeId(), d);
                });
            }
//            if (Collections3.isNotEmpty(gsList)) {
//                gsList.forEach((d) -> {
//                    ProductPriceDto p = new ProductPriceDto();
//                    p.setGradeId(d.getId());
//                    p.setGradeName(d.getName());
//                    if (map.containsKey(d.getId())) {
//                        ProductPrice g = map.get(d.getId());
//                        p.setIntegral(g.getIntegral());
//                        p.setPrice(g.getPrice());
//                        p.setProductId(g.getProductId());
//                        p.setId(g.getId());
//                    }
//                    dtoList.add(p);
//                });
//            }
            dto.setList(dtoList);
        }
        return dto;
    }

    public ListDto<ProductDto> pageProductByClassify(ProductClassifyDto reqDto) {
        ListDto<ProductDto> dto = new ListDto<>();
        dto.setStatus(true);
        if (reqDto != null && reqDto.getClassifyId() != null && reqDto.getClassifyId() != 0L) {
            Integer s = reqDto.getS() == null || reqDto.getS() == 0L ? Integer.MAX_VALUE : reqDto.getS();
            Integer p = reqDto.getP() == null ? 0 : reqDto.getP() * s;
            ProductVo vo = new ProductVo();
            vo.setNameLike(reqDto.getNameLike());
            vo.setCcStart(p);
            vo.setCcEnd(s);
            vo.setClassifyId(reqDto.getClassifyId());
            vo.setStatus(IdEntity.DataStatus.ENABLE);
            List<Product> list = dao.selectByClassifyId(vo);
            dto.setList(BeanMapper.mapList(list, ProductDto.class));
        }
        return dto;
    }

    public ListDto<ProductCategory> findProductCategoryById(Long id) {
        ListDto<ProductCategory> dto = new ListDto<>();
        dto.setStatus(false);
        if (id == null || id == 0L) {
            dto.setMsg("参数为空");
        } else {
            dto.setList(productCategoryDao.selectByProductId(id));
            dto.setStatus(true);
        }
        return dto;
    }

    public DetailDto<ProductBuyRspDto> findBuyProductDetail(ProductBuyReqDto reqDto) {
        DetailDto<ProductBuyRspDto> dto = new DetailDto<>(false);
        if (reqDto == null) {
            dto.setMsg("参数为空");
        } else if (reqDto.getUserId() == null || reqDto.getUserId() == 0L) {
            dto.setMsg("用户未传输");
        } else if (reqDto.getProductId() == null || reqDto.getProductId() == 0L) {
            dto.setMsg("商品未传输");
        } else {
            MobileUser user = BeanMapper.map(userDao.selectById(reqDto.getUserId()), MobileUser.class);
            if (user == null) {
                dto.setMsg("用户不存在");
            } else {
                Product product = BeanMapper.map(dao.selectById(reqDto.getProductId()), Product.class);
                if (product == null) {
                    dto.setMsg("商品不存在");
                } else if (IdEntity.DataStatus.DISABLE.equals(product.getStatus())) {
                    dto.setMsg("商品已经禁用");
                } else {
                    ProductBuyRspDto rsp = new ProductBuyRspDto();
                    ProductSimpleDto productSimpleDto = new ProductSimpleDto();
                    productSimpleDto.setCategoryName(reqDto.getCategory());
                    productSimpleDto.setId(reqDto.getProductId());
                    productSimpleDto.setName(product.getName());
                    productSimpleDto.setRemark(product.getRemark());
                    productSimpleDto.setNumber(reqDto.getNumber());
                    productSimpleDto.setImage(product.getImage());
                    //查询当前用户可以获取到的真正的价格
                    List<ProductPrice> productPrices = productPriceDao.selectByProductId(reqDto.getProductId());
                    if (Collections3.isNotEmpty(productPrices)) {
                        Long gradeId = user.getGradeId();
                        if (gradeId != null && gradeId != 0L) {
                            for (ProductPrice d : productPrices) {
                                if (gradeId == d.getGradeId()) {
                                    productSimpleDto.setPrice(d.getPrice());
                                    break;
                                }
                            }
                        }
                    }
                    productSimpleDto.setConvertIntegral(product.getConvertIntegral());
                    productSimpleDto.setType(product.getType());
                    rsp.setProduct(productSimpleDto);
                    if (EntitySetting.BuyProductType.STORE.name().equalsIgnoreCase(reqDto.getBuy())) {
                        StoreVo vo = new StoreVo();
                        vo.setCcStart(0);
                        vo.setCcEnd(1);
                        vo.setCcSort("DESC");
                        vo.setLatitude(reqDto.getLa());
                        vo.setLongitude(reqDto.getLg());
                        //查询门店地址
                        List<Store> storeList = storeDao.selectStoreByDistance(vo);
                        if (Collections3.isNotEmpty(storeList)) {
                            Store store = storeList.get(0);
                            rsp.setStore(BeanMapper.map(store, StoreDto.class));
                        }
                    } else {
                        //查询默认地址
                        List<UserAddress> addresses = addressDao.selectByUserIdAndDefault(reqDto.getUserId(), EntitySetting.YesOrNoType.YES);
                        if (Collections3.isNotEmpty(addresses)) {
                            rsp.setAddress(addresses.get(0));
                        }
                    }
                    /**
                     *     private Integer integral;//用户积分数目
                     private Double integralPrice;//一个积分可以兑换的商品价格
                     private CouponSetting coupon;//可以使用的最优惠的优惠券
                     */
                    rsp.setIntegral(user.getIntegral() == null ? 0 : user.getIntegral().intValue());
                    //查询积分配置
                    List<IntegralSetting> integralSettings = integralSettingDao.select(null);
                    if (Collections3.isNotEmpty(integralSettings)) {
                        rsp.setIntegralPrice(integralSettings.get(0).getPrice());
                    }
                    //查询优惠券
                    List<CouponSetting> couponSettings = couponSettingDao.selectCommonOrId(EntitySetting.CouponType.COMMON, product.getCouponId());
                    if (Collections3.isNotEmpty(couponSettings)) {
                        Double price = product.getPrice();
                        //过滤满足条件的价格  TODO cici 待测
                        CouponSetting couponSetting = couponSettings.stream().filter(d -> price > d.getConditionPrice()).sorted(Comparator.comparing(CouponSetting::getReducePrice)).collect(Collectors.toList()).get(0);
                        rsp.setCoupon(couponSetting);
                    }
                    dto.setDetail(rsp);
                    dto.setStatus(true);
                }
            }
        }
        return dto;
    }

    public ListDto<ProductDto> pageProduct(ProductClassifyDto reqDto) {
        ListDto<ProductDto> dto = new ListDto<>();
        dto.setStatus(true);
        ProductVo vo = new ProductVo();
        if (reqDto != null) {
            Integer s = reqDto.getS() == null || reqDto.getS() == 0L ? Integer.MAX_VALUE : reqDto.getS();
            Integer p = reqDto.getP() == null ? 0 : reqDto.getP() * s;
            vo.setCcStart(p);
            vo.setCcEnd(s);
            vo.setStatus(IdEntity.DataStatus.ENABLE);
            if (!Strings.isNullOrEmpty(reqDto.getNameLike())) {
                vo.setNameLike(reqDto.getNameLike());
            }
            if (reqDto.getClassifyId() != null && reqDto.getClassifyId() != 0L) {
                vo.setClassifyId(reqDto.getClassifyId());
            }
            if (reqDto.getType() != null) {
                vo.setType(reqDto.getType());
                if (EntitySetting.ProductType.SHOPPING_MALL.equals(reqDto.getType())) {
                    if (Collections3.isNotEmpty(reqDto.getCategory())) {
                        vo.setCategory(reqDto.getCategory());
                    }
                } else {
                    vo.setBeginIntegral(reqDto.getBeginIntegral());
                    vo.setEndIntegral(reqDto.getEndIntegral());
                }
            }
            vo.setCcSort(reqDto.getOrder());
            vo.setCcField(reqDto.getField());
        }
        List<Product> list = dao.selectProduct(vo);
        dto.setList(BeanMapper.mapList(list, ProductDto.class));
        return dto;
    }
}