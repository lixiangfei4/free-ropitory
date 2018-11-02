package com.ixinhoo.shopping.rest.user;


import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.shopping.dto.user.UserEvaluateDto;
import com.ixinhoo.shopping.service.user.UserEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user-evaluate")
public class UserEvaluateRestController {

    @Autowired
    private UserEvaluateService service;

    /**
     * 评价店铺
     *
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public StatusDto save(UserEvaluateDto reqDto) {
        return service.saveUserEvaluate(reqDto);
    }


}