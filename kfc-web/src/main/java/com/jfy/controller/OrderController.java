package com.jfy.controller;


import com.jfy.feign.*;
import com.jfy.pojo.Cart;
import com.jfy.pojo.Order;
import com.jfy.pojo.Product;
import com.jfy.pojo.User;
import com.jfy.utils.AjaxResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jfy
 * @since 2024-05-19
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderFeignService orderFeignService;
    @Autowired
    private CartFeignService cartFeignService;
    @Autowired
    OrderDetailFeignService orderDetailFeignService;
    @Autowired
    UserFeignService userFeignService;
    @Autowired
    ProductFeignService productFeignService;

    @RequestMapping("/createOrder")
    public String createOrder(HttpSession session){
        User user = (User) session.getAttribute("user");
        Integer uid = user.getUid();
        //根据uid生成订单,并返回生成订单的oid
        Integer oid =  orderFeignService.addOrder(uid);
        //获取购物车列表,根据购物车信息获取pid
        List<Cart> cartList = cartFeignService.getCartWithProduct(uid);
        List<Integer> productIds = new ArrayList<>();
        for (Cart cart : cartList) {
            productIds.add(cart.getPid());
        }
        //将pid,oid加入中间表
        orderDetailFeignService.addOrderProduct(oid,productIds);

        return "redirect:/order/toOrderList";
    }


    @RequestMapping("/toOrderList")
    public String toOrderList(){
        return "order/orderList";
    }
    @RequestMapping("/toOrderList2")
    public String toOrderList2(){
        return "order/orderList2";
    }
    @RequestMapping("/toOrderList3")
    public String toOrderList3(){
        return "order/orderList3";
    }


    @RequestMapping("/getOrderList")
    @ResponseBody
    public List<Order> getOrderList(HttpSession session){
        User user = (User) session.getAttribute("user");
        Integer uid = user.getUid();
        List<Order> _orderList = orderFeignService.getOrderInfoByUid(uid);
        //筛选订单状态为待支付的订单
        List<Order> orderList = new ArrayList<>();

        for (Order order : _orderList) {
            if(order.getStatus().equals("待支付")){
                orderList.add(order);
            }
        }

//        System.out.println(orderList);
        return orderList;
    }

    @RequestMapping("/getOrderList2")
    @ResponseBody
    public List<Order> getOrderList2(HttpSession session){
        User user = (User) session.getAttribute("user");
        Integer uid = user.getUid();
        List<Order> _orderList = orderFeignService.getOrderInfoByUid(uid);
        //筛选订单状态为待支付的订单
        List<Order> orderList = new ArrayList<>();

        for (Order order : _orderList) {
            if(order.getStatus().equals("已支付")){
                orderList.add(order);
            }
        }

        return orderList;
    }
    @RequestMapping("/getOrderList3")
    @ResponseBody
    public List<Order> getOrderList3(HttpSession session){
        User user = (User) session.getAttribute("user");
        Integer uid = user.getUid();
        List<Order> _orderList = orderFeignService.getOrderInfoByUid(uid);
        //筛选订单状态为待支付的订单
        List<Order> orderList = new ArrayList<>();

        for (Order order : _orderList) {
            if(order.getStatus().equals("已取消")){
                orderList.add(order);
            }
        }

        return orderList;
    }

    @RequestMapping("/payOrder/{oid}")
    @ResponseBody
    public AjaxResult payOrder(@PathVariable("oid") Integer oid,HttpSession session){
        User user = (User) session.getAttribute("user");
        Integer uid = user.getUid();
        boolean flag =  userFeignService.updateUserBalance(uid,oid);
        if(!flag){
            return AjaxResult.error();
        }
        orderFeignService.updateOrderStatus(oid,"已支付");
        List<Order> orderList = orderFeignService.getOrderInfoByUid(uid);
        for (Order order : orderList) {

            if(order.getOid().equals(oid)){
                order.getProductList().forEach(product -> {
                    System.out.println("------------------quantity---------------------");
                    System.out.println(product.getCart().getPquantity());
                    productFeignService.updateProductCount(product.getPid(),product.getCart().getPquantity());
                });
            }
        }

        session.setAttribute("user",userFeignService.getById(uid));

        return AjaxResult.right();
    }

    @RequestMapping("/recoverOrderStatus/{oid}")
    @ResponseBody
    public AjaxResult recoverOrderStatus(@PathVariable("oid") Integer oid){
        orderFeignService.updateOrderStatus(oid,"待支付");
        return AjaxResult.right();
    }

    @RequestMapping("/cancelOrderStatus/{oid}")
    @ResponseBody
    public AjaxResult cancelOrderStatus(@PathVariable("oid") Integer oid){
        orderFeignService.updateOrderStatus(oid,"已取消");
        return AjaxResult.right();
    }

    @RequestMapping("/deleteOrder/{oid}")
    @ResponseBody
    public AjaxResult deleteOrder(@PathVariable("oid") Integer oid){
        boolean flag =  orderFeignService.deleteOrder(oid);
        return flag?AjaxResult.right():AjaxResult.error();
    }






}
