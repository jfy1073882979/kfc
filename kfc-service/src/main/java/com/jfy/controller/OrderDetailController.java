package com.jfy.controller;


import com.jfy.pojo.OrderDetail;
import com.jfy.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jfy
 * @since 2024-06-17
 */
@RestController
@RequestMapping("/order-detail")
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;
    @RequestMapping("/addOrderProduct/{oid}")
    void addOrderProduct(@PathVariable("oid") Integer oid, @RequestBody List<Integer> productIds){
        for (Integer productId : productIds) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOid(oid);
            orderDetail.setPid(productId);
            orderDetailService.save(orderDetail);
        }
    }

}
