package com.jfy.feign;

import com.jfy.pojo.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "kfc-service",path = "/role")
public interface RoleFeignService {
    @RequestMapping("/getList")
    public List<Role> getList();
}
