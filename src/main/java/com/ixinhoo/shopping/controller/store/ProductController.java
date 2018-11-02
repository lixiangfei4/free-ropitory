package com.ixinhoo.shopping.controller.store;


import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.ListDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.api.code.DataTable;
import com.ixinhoo.api.entity.IdEntity;
import com.ixinhoo.api.util.DataTableUtil;
import com.ixinhoo.shopping.dto.store.ProductCouponDto;
import com.ixinhoo.shopping.dto.store.ProductDto;
import com.ixinhoo.shopping.dto.store.ProductPriceDto;
import com.ixinhoo.shopping.entity.EntitySetting;
import com.ixinhoo.shopping.service.store.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/bg/product")
public class ProductController {

    @Autowired
    private ProductService service;


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public StatusDto save(ProductDto reqDto) {
        return service.saveProduct(reqDto);
    }


    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public DetailDto<ProductDto> findById(@PathVariable("id") Long id) {
        return service.findProductAndBannerById(id,null);
    }

    @RequestMapping(value = "price/{id}", method = RequestMethod.GET)
    public ListDto<ProductPriceDto> findProductPriceById(@PathVariable("id") Long id) {
        return service.findProductPriceById(id);
    }

    @RequestMapping(value = "coupon/{id}", method = RequestMethod.GET)
    public ListDto<ProductCouponDto> findProductCouponById(@PathVariable("id") Long id) {
        return service.findProductCouponById(id);
    }

    @RequestMapping(value = "save-price", method = RequestMethod.POST)
    public StatusDto savePrice(@RequestBody List<ProductPriceDto> list) {
        return service.saveProductPrice(list);
    }


    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public StatusDto delete(@RequestParam("ids") List<Long> ids) {
        service.deleteByIds(ids);
        return new StatusDto(true);
    }

    @RequestMapping(value = "status", method = RequestMethod.POST)
    public StatusDto status(@RequestParam("ids") List<Long> ids, @RequestParam("status") IdEntity.DataStatus status) {
        return service.updateStatusByIds(ids, status);
    }


    @RequestMapping(value = "list", method = RequestMethod.POST)
    public DataTable<ProductDto> list(HttpServletRequest request) {
        return service.listProduct(DataTableUtil.toDataTable(request), EntitySetting.ProductType.SHOPPING_MALL);
    }

    @RequestMapping(value = "list-integral", method = RequestMethod.POST)
    public DataTable<ProductDto> listIntegral(HttpServletRequest request) {
        return service.listProduct(DataTableUtil.toDataTable(request), EntitySetting.ProductType.INTEGRAL_MALL);
    }




}