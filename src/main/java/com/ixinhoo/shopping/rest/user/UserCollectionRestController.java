package com.ixinhoo.shopping.rest.user;


import com.ixinhoo.api.client.ListDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.shopping.dto.store.ProductSimpleDto;
import com.ixinhoo.shopping.service.user.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user-collection")
public class UserCollectionRestController {

    @Autowired
    private UserCollectionService service;

    /**
     * 收藏商品、取消收藏
     *
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public StatusDto save(@RequestParam("userId") Long userId, @RequestParam("dataId") Long dataId) {
        return service.saveUserCollectionByUserId(userId, dataId);
    }

    /**
     * 删除收藏商品
     *
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public StatusDto delete(@RequestParam("userId") Long userId, @RequestParam("dataId") Long dataId) {
        return service.deleteUserCollectionByUserId(userId, dataId);
    }


    /**
     * 查看我的收藏商品
     *
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ListDto<ProductSimpleDto> page(@RequestParam("userId") Long userId, Integer p, Integer s) {
        return service.pageUserCollection(userId, p, s);
    }


}