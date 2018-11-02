package com.ixinhoo.shopping.rest.store;


import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.ListDto;
import com.ixinhoo.shopping.dto.store.ProductBuyReqDto;
import com.ixinhoo.shopping.dto.store.ProductBuyRspDto;
import com.ixinhoo.shopping.dto.store.ProductClassifyDto;
import com.ixinhoo.shopping.dto.store.ProductDto;
import com.ixinhoo.shopping.entity.store.ProductCategory;
import com.ixinhoo.shopping.service.store.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductRestController {

    @Autowired
    private ProductService service;


    /**
     * 根据分类主键id查询商品列表；
     *
     * @return
     */
    @RequestMapping(value = "classify", method = RequestMethod.POST)
    public ListDto<ProductDto> pageProductByClassify(ProductClassifyDto reqDto) {
        return service.pageProductByClassify(reqDto);
    }

    /**
     * 根据分类主键id查询商品列表；TODO cici 弄成通用的商品分页查询
     *
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ListDto<ProductDto> pageProduct(ProductClassifyDto reqDto) {
        return service.pageProduct(reqDto);
    }



    /**
     * 根据主键id查询商品
     *
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public DetailDto<ProductDto> productDetail(@RequestParam("id") Long id, @RequestParam("userId") Long userId) {
        return service.findProductAndBannerById(id, userId);
    }

    /**
     * 根据主键id查询商品规格
     *
     * @return
     */
    @RequestMapping(value = "category", method = RequestMethod.POST)
    public ListDto<ProductCategory> productCategory(@RequestParam("id") Long id) {
        return service.findProductCategoryById(id);
    }

    /**
     * 根据主键id查询商品规格
     *
     * @return
     */
    @RequestMapping(value = "buy", method = RequestMethod.POST)
    public DetailDto<ProductBuyRspDto> buyProduct(ProductBuyReqDto reqDto) {
        return service.findBuyProductDetail(reqDto);
    }


}