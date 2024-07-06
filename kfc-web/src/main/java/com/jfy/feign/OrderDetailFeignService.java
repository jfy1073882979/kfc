package com.jfy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "kfc-service",path = "/order-detail")
public interface OrderDetailFeignService {
    @RequestMapping("/addOrderProduct/{oid}")
    void addOrderProduct(@PathVariable("oid") Integer oid,@RequestBody List<Integer> productIds);
}
