package com.ixinhoo.shopping.service.store;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.ListDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.code.DataTable;
import com.ixinhoo.api.code.DataTableMeta;
import com.ixinhoo.api.code.DataTableRequest;
import com.ixinhoo.api.dao.BaseDao;
import com.ixinhoo.api.service.BaseService;
import com.ixinhoo.crumbs.code.util.collection.Collections3;
import com.ixinhoo.crumbs.code.util.location.LocationPathUtil;
import com.ixinhoo.crumbs.code.util.mapper.BeanMapper;
import com.ixinhoo.shopping.dao.store.StoreDao;
import com.ixinhoo.shopping.dto.store.*;
import com.ixinhoo.shopping.entity.store.Store;
import com.ixinhoo.shopping.entity.store.StoreBanner;
import com.ixinhoo.shopping.vo.store.StoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StoreService extends BaseService {
    @Autowired
    private StoreDao<Store> dao;


    @Override
    protected BaseDao<Store> getBaseDao() {
        return dao;
    }

    @Transactional
    public StatusDto saveStore(StoreDto reqDto) {
        StatusDto dto = new StatusDto(false);
        if (reqDto == null) {
            dto.setMsg("参数不能为空");
        } else {
            Store entity = BeanMapper.map(reqDto, Store.class);
            if (entity.getId() == null || entity.getId() == 0L) {
                super.create(entity);
                dto.setStatus(true);
            } else {
                Store dbEntity = getBaseDao().selectById(entity.getId());
                if (dbEntity == null) {
                    dto.setMsg("实体不存在");
                } else {
                    super.updateById(entity);
                    dao.deleteBannerByStoreId(entity.getId());
                    dto.setStatus(true);
                }
            }
            if (dto.getStatus()) {
                if (Collections3.isNotEmpty(reqDto.getBannerList())) {
                    int sort = 100;
                    for (String e : reqDto.getBannerList()) {
                        StoreBanner banner = new StoreBanner();
                        banner.setAddress(e);
                        banner.setStoreId(entity.getId());
                        banner.setSort(sort);
                        sort += 100;
                        dao.insertBanner(banner);
                    }
                }
            }
        }
        return dto;
    }

    public DataTable<Store> listStore(DataTableRequest dataTable) {
        DataTable<Store> dto = new DataTable<>();
        StoreVo vo = new StoreVo();
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
        List<Store> list = getBaseDao().select(vo);
        dto.setData(list);
        Long count = getBaseDao().selectCount(vo);
        meta.setTotal(count == null ? 0 : count.intValue());
        dto.setMeta(meta);
        return dto;

    }

    public DetailDto<StoreDto> findStoreAndBannerById(Long id) {
        DetailDto<StoreDto> dto = new DetailDto<>(false);
        if (id == null || id == 0L) {
            dto.setMsg("参数为空");
        } else {
            Store store = dao.findStoreAndBannerById(id);
            if (store == null) {
                dto.setMsg("数据不存在");
            } else {
                StoreDto storeDto = BeanMapper.map(store, StoreDto.class);
                if (Collections3.isNotEmpty(store.getBanners())) {
                    List<String> list = Lists.newArrayList();
                    store.getBanners().forEach((d) -> {
                        list.add(d.getAddress());
                    });
                    storeDto.setBannerList(list);
                } else {
                    storeDto.setBannerList(Lists.newArrayList());
                }
                dto.setDetail(storeDto);
                dto.setStatus(true);
            }
        }
        return dto;
    }

    public ListDto<StoreInfoDto> pageStore(StorePageDto reqDto) {
        ListDto<StoreInfoDto> dto = new ListDto<>();
        dto.setStatus(true);
        StoreVo vo = new StoreVo();
        vo.setCcStart((reqDto.getP() == 0 ? 0 : reqDto.getP() - 1) * reqDto.getS());
        vo.setCcEnd(reqDto.getS());
        if (!Strings.isNullOrEmpty(reqDto.getOrder())) {
            vo.setCcSort("DESC");
            vo.setCcField(reqDto.getOrder());
        }
        vo.setNameLike(reqDto.getSearch());
        List<Store> storeList = dao.selectStoreByDistance(vo);
        List<StoreInfoDto> dtoList = Lists.newArrayList();
        if (Collections3.isNotEmpty(storeList)) {
            LocationPathUtil util = LocationPathUtil.getInstance();
            if (reqDto.getLa() == null || reqDto.getLa() == 0L || reqDto.getLg() == null || reqDto.getLg() == 0L) {
                dtoList = BeanMapper.mapList(storeList, StoreInfoDto.class);
            } else {
                Double reqLg = reqDto.getLg();
                Double reqLa = reqDto.getLa();
                for (Store e : storeList) {
                    Double lg = e.getLongitude();
                    Double la = e.getLatitude();
                    StoreInfoDto sld = BeanMapper.map(e, StoreInfoDto.class);
                    if (lg != null && la != null) {
                        sld.setDistance(util.distance(reqLg, reqLa, lg, la));
                    }
                    dtoList.add(sld);
                }
            }
        }
        dto.setList(BeanMapper.mapList(dtoList, StoreInfoDto.class));
        return dto;
    }

    public DetailDto<StoreInfoDto> findStoreDetail(StoreDetailReqDto reqDto) {
        DetailDto<StoreInfoDto> dto = new DetailDto<>(false);
        if (reqDto == null || reqDto.getId() == null || reqDto.getId() == 0L) {
            dto.setMsg("参数为空");
        } else {
            Store store = dao.findStoreAndBannerById(reqDto.getId());
            if (store == null) {
                dto.setMsg("数据不存在");
            } else {
                StoreInfoDto storeDto = BeanMapper.map(store, StoreInfoDto.class);
                if (Collections3.isNotEmpty(store.getBanners())) {
                    List<String> list = Lists.newArrayList();
                    store.getBanners().forEach((d) -> {
                        list.add(d.getAddress());
                    });
                    storeDto.setBannerList(list);
                } else {
                    storeDto.setBannerList(Lists.newArrayList());
                }
                if (reqDto.getLa() != null && reqDto.getLa() != 0L && reqDto.getLg() != null && reqDto.getLg() != 0L) {
                    storeDto.setDistance(LocationPathUtil.getInstance().distance(reqDto.getLg(), reqDto.getLa(), store.getLongitude(), store.getLatitude()));
                }
                dto.setDetail(storeDto);
                dto.setStatus(true);
            }
        }
        return dto;
    }

}