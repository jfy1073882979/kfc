package com.jfy.controller;


import com.jfy.pojo.Cart;
import com.jfy.pojo.Order;
import com.jfy.service.CartService;
import com.jfy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
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
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    private CartService cartService;

    @RequestMapping("/addOrder/{uid}")
    public Integer addOrder(@PathVariable("uid") Integer uid){

        List<Cart> cartList = cartService.getCartWithProduct(uid);
        BigDecimal total = cartService.getMoneyByCartList(cartList);
        //添加订单并返回订单id
        Integer oid = orderService.addOrder(uid,total);


        return oid;
    }

    @RequestMapping("/getOrderInfoByUid/{uid}")
    public List<Order> getOrderInfoByUid(@PathVariable("uid") Integer uid){
        List<Order> orderList = orderService.getOrderInfoByUid(uid);
        return orderList;
    }

    @RequestMapping("/updateOrderStatus/{oid}/{status}")
    void updateOrderStatus(@PathVariable("oid") Integer oid,@PathVariable("status") String status){
        Order order = orderService.getById(oid);
        order.setStatus(status);
        orderService.updateById(order);
    }

    @RequestMapping("/deleteOrder/{oid}")
    boolean deleteOrder(@PathVariable("oid") Integer oid){
        return orderService.removeById(oid);
    }

}
