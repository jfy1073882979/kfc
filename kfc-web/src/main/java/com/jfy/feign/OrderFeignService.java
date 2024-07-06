package com.jfy.feign;

import com.jfy.pojo.Order;
import com.jfy.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "kfc-service",path = "/order")
public interface OrderFeignService {
    @RequestMapping("/addOrder/{uid}")
    Integer addOrder(@PathVariable("uid") Integer uid);

    @RequestMapping("/getOrderInfoByUid/{uid}")
    List<Order> getOrderInfoByUid(@PathVariable("uid") Integer uid);

    @RequestMapping("/updateOrderStatus/{oid}/{status}")
    void updateOrderStatus(@PathVariable("oid") Integer oid,@PathVariable("status") String status);

    @RequestMapping("/deleteOrder/{oid}")
    boolean deleteOrder(@PathVariable("oid") Integer oid);
}
