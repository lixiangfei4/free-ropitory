package com.ixinhoo.shopping.rest.user;


import com.ixinhoo.api.client.DetailDto;
import com.ixinhoo.api.client.StatusDto;
import com.ixinhoo.shopping.dto.user.MobileUserDto;
import com.ixinhoo.shopping.dto.user.MobileUserRegisterDto;
import com.ixinhoo.shopping.dto.user.UserGradeDto;
import com.ixinhoo.shopping.dto.user.UserMyselfDto;
import com.ixinhoo.shopping.entity.EntitySetting;
import com.ixinhoo.shopping.service.user.MobileUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user")
public class MobileUserRestController {

    @Autowired
    private MobileUserService service;

    /**
     * 验证码 TODO cici
     *
     * @param phone
     * @return
     */
    @RequestMapping(value = "verify-code", method = RequestMethod.POST)
    public StatusDto verifyCode(@RequestParam("phone") String phone, @RequestParam("type")EntitySetting.MessageCodeType type) {
        return service.verifyCodeByPhone(phone,type);
    }

    /**
     * 注册
     *
     * @param reqDto
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public StatusDto register(MobileUserRegisterDto reqDto) {
        return service.registerMobileUser(reqDto);
    }


    /**
     * 校验openid是否存在
     *
     * @param openid
     * @return
     */
    @RequestMapping(value = "verify-openid", method = RequestMethod.POST)
    public DetailDto<MobileUserDto> verifyOpenid(@RequestParam("openid") String openid) {
        return service.verifyUserOpenid(openid);
    }

    /**
     * 今天是否已经签到
     *
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "verify-sign", method = RequestMethod.POST)
    public DetailDto<UserGradeDto> verifySign(@RequestParam("id") Long id) {
        return service.verifyUserSign(id);
    }

    /**
     * 用户签到
     *
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "sign", method = RequestMethod.POST)
    public StatusDto sign(@RequestParam("id") Long id) {
        return service.userSignDay(id);
    }

    /**
     * 个人中心
     *
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "myself", method = RequestMethod.POST)
    public DetailDto<UserMyselfDto> myself(@RequestParam("id") Long id) {
        return service.userMyself(id);
    }


    /**
     * 更新密码
     *
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "update-pwd", method = RequestMethod.POST)
    public StatusDto updatePassword(@RequestParam("id") Long id,@RequestParam("oldPwd")String oldPwd,@RequestParam("newPwd")String newPwd) {
        return service.updatePasswordByUserId(id,oldPwd,newPwd);
    }


    /**
     * 修改手机号
     *
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "update-phone", method = RequestMethod.POST)
    public StatusDto changePhone(@RequestParam("id") Long id,@RequestParam("phone")String phone,@RequestParam("code")String code) {
        return service.changeUserPhoneByIdCode(id,phone,code);
    }



}