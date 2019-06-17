package com.ccy.passbook.passbook.controller;

import com.ccy.passbook.passbook.log.LogConstants;
import com.ccy.passbook.passbook.log.LogoGenerator;
import com.ccy.passbook.passbook.service.IFeedbackService;
import com.ccy.passbook.passbook.service.IGainPassTemplateService;
import com.ccy.passbook.passbook.service.IInventoryService;
import com.ccy.passbook.passbook.service.IUserPassService;
import com.ccy.passbook.passbook.vo.Feedback;
import com.ccy.passbook.passbook.vo.GainPassTemplateRequest;
import com.ccy.passbook.passbook.vo.Pass;
import com.ccy.passbook.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;

/**
 * Passbook Rest Controller
 * @author CCY
 * @date 2019/6/16 23:16
 */
@Slf4j
@RestController
@RequestMapping("/passbook")
public class PassbookController {

    //用户优惠券服务
    private final IUserPassService userPassService;

    //优惠券库存服务
    private final IInventoryService iInventoryService;

    //领取优惠券服务
    private final IGainPassTemplateService gainPassTemplateService;

    //反馈服务
    private final IFeedbackService iFeedbackService;

    private final HttpServletRequest httpServletRequest;

    @Autowired
    public PassbookController(IUserPassService userPassService,
                              IInventoryService iInventoryService,
                              IGainPassTemplateService gainPassTemplateService,
                              IFeedbackService iFeedbackService,
                              HttpServletRequest httpServletRequest) {
        this.userPassService = userPassService;
        this.iInventoryService = iInventoryService;
        this.gainPassTemplateService = gainPassTemplateService;
        this.iFeedbackService = iFeedbackService;
        this.httpServletRequest = httpServletRequest;
    }

    //获取用户个人的优惠券信息
    @ResponseBody
    @GetMapping("/userpassinfo")
    Response userPassInfo(Long userId) throws Exception{
        LogoGenerator.genLog(
            httpServletRequest,
                userId,
                LogConstants.ActionName.USER_PASS_INFO,
                null
        );
        return userPassService.getUserPassInfo(userId);
    }

    //获取用户使用了的优惠券信息
    @ResponseBody
    @GetMapping("/userusedpassinfo")
    Response userUsedPassInfo(Long userId) throws Exception{
        LogoGenerator.genLog(
                httpServletRequest,
                userId,
                LogConstants.ActionName.USER_USED_PASS_INFO,
                null
        );
        return userPassService.getUserUsedPassInfo(userId);
    }

    //用户使用优惠券
    @ResponseBody
    @PostMapping("/userusepass")
    Response userUsePass(Pass pass){
        LogoGenerator.genLog(
                httpServletRequest,
                pass.getUserId(),
                LogConstants.ActionName.USER_USE_PASS,
                pass
        );
        return userPassService.userUsePass(pass);
    }

    //获取库存信息
    @ResponseBody
    @GetMapping("/inventoryinfo")
    Response inventoryInfo(Long userId) throws Exception{
        LogoGenerator.genLog(
                httpServletRequest,
                userId,
                LogConstants.ActionName.INVENTORY_INFO,
                null
        );
        return iInventoryService.getInventoryInfo(userId);
    }

    //用户领取优惠券
    @ResponseBody
    @PostMapping("/gainpasstemplate")
    Response gainPassTemplate(@RequestBody GainPassTemplateRequest request) throws Exception{
        LogoGenerator.genLog(
                httpServletRequest,
                request.getUserId(),
                LogConstants.ActionName.GAIN_PASS_TEMPLATE,
                request
        );
        return gainPassTemplateService.gainPassTemplate(request);
    }

    //用户创建评论
    @ResponseBody
    @PostMapping("/createfeedback")
    Response createFeedback(@RequestBody Feedback feedback){
        LogoGenerator.genLog(
                httpServletRequest,
                feedback.getUserId(),
                LogConstants.ActionName.CREATE_FEEDBACK,
                feedback
        );
        return iFeedbackService.createFeedback(feedback);
    }

    @ResponseBody
    @GetMapping("/getfeedback")
    Response getFeedback(Long userId){
        LogoGenerator.genLog(
                httpServletRequest,
                userId,
                LogConstants.ActionName.GET_FEEDBACK,
                null
        );
        return iFeedbackService.getFeedback(userId);
    }

    //异常演示接口
    @ResponseBody
    @GetMapping("/exception")
    Response exception()throws Exception{
        throw new Exception("Welcome!");
    }
}

