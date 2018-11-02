package com.ixinhoo.shopping.controller.store;


import com.ixinhoo.api.client.ListDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.shopping.entity.store.ProductCategory;
import com.ixinhoo.shopping.service.store.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/bg/product-category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService service;


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public StatusDto save(@RequestBody List<ProductCategory> reqDto) {
        return service.saveProductCategory(reqDto);
    }


    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public ListDto<ProductCategory> findById(@PathVariable("id") Long id) {
        return service.findProductCategoryById(id);
    }


}