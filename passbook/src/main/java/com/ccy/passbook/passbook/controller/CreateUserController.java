package com.ccy.passbook.passbook.controller;

import com.ccy.passbook.passbook.log.LogConstants;
import com.ccy.passbook.passbook.log.LogoGenerator;
import com.ccy.passbook.passbook.service.IUserService;
import com.ccy.passbook.passbook.vo.Response;
import com.ccy.passbook.passbook.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 创建用户服务
 * @author CCY
 * @date 2019/6/17 12:44
 */
@Slf4j
@RestController
@RequestMapping("/passbook")
public class CreateUserController {

    private final IUserService userService;

    private final HttpServletRequest httpServletRequest;

    @Autowired
    public CreateUserController(IUserService userService, HttpServletRequest httpServletRequest) {
        this.userService = userService;
        this.httpServletRequest = httpServletRequest;
    }

    @ResponseBody
    @PostMapping("/createuser")
    Response createUser(@RequestBody User user) throws Exception{
        LogoGenerator.genLog(
                httpServletRequest,
                -1L,
                LogConstants.ActionName.CREATE_USER,
                user
        );
        return userService.createUser(user);
    }
}
