package com.p2p.controller;

import com.p2p.conf.AlipayConfig;
import com.p2p.result.ResultMap;
import com.p2p.service.AlipayService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/alipay")
public class AlipayController{

    @Autowired
    private AlipayConfig alipayConfig;
    @Autowired
    private AlipayService alipayService;


    /**
     * 创建订单
     */
    @ApiOperation(value = "创建订单", notes = "支付宝支付创建订单")
    @GetMapping("/createOrder")
    public void createOrder(@ApiParam(value = "订单号") @RequestParam String orderNo,
                                 @ApiParam(value = "订单金额") @RequestParam double amount,
                                 @ApiParam(value = "商品名称") @RequestParam String body,HttpServletRequest request, HttpServletResponse
                                 response) {
        try {
            // 1、验证订单是否存在

            // 2、创建支付宝订单
            String orderStr = alipayService.createOrder(orderNo, amount, body);
            System.out.println(orderStr);
//            return ResultMap.ok().put("data", orderStr);
            try {
                response.setContentType("text/html");
                response.getWriter().write(orderStr);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.getMessage();
//            return ResultMap.error("订单生成失败");
        }
    }

    /**
     * 支付异步通知
     * 接收到异步通知并验签通过后，一定要检查通知内容，
     * 包括通知中的app_id、out_trade_no、total_amount是否与请求中的一致，并根据trade_status进行后续业务处理。
     * https://docs.open.alipay.com/194/103296
     */
    @RequestMapping("/notify")
    public String notify(HttpServletRequest request) {
        // 验证签名
        boolean flag = alipayService.rsaCheckV1(request);
        if (flag) {
            String tradeStatus = request.getParameter("trade_status"); // 交易状态
            String outTradeNo = request.getParameter("out_trade_no"); // 商户订单号
            String tradeNo = request.getParameter("trade_no"); // 支付宝订单号
            /**
             * 还可以从request中获取更多有用的参数，自己尝试
             */
            boolean notify = alipayService.notify(tradeStatus, outTradeNo, tradeNo);
            if(notify){
                return "success";
            }
        }
        return "fail";
    }

    @ApiOperation(value = "退款", notes = "退款")
    @PostMapping("/refund")
    public ResultMap refund(@ApiParam(value = "订单号") @RequestParam String orderNo,
                            @ApiParam(value = "退款金额") @RequestParam double amount,
                            @ApiParam(value = "退款原因") @RequestParam(required = false) String refundReason) {
        return alipayService.refund(orderNo, amount, refundReason);
    }
}
