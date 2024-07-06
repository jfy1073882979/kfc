package com.jfy.feign;

import com.jfy.pojo.Cart;
import com.jfy.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(value = "kfc-service",path = "/cart")
public interface CartFeignService {
    @RequestMapping("/getCartWithProduct/{uid}")
    public List<Cart> getCartWithProduct(@PathVariable("uid") Integer uid);

    @RequestMapping("/findCart/{uid}/{pid}")
    public Cart findCart(@PathVariable("uid") Integer uid, @PathVariable("pid") Integer pid);

    @RequestMapping("/save")
    public Boolean save(@RequestBody Cart cart);

    @RequestMapping("/updateById")
    public Boolean updateById(@RequestBody Cart cart);

    @RequestMapping("/removeById/{id}")
    public Boolean removeById(@PathVariable("id") Integer id);

    @RequestMapping("/getById/{id}")
    public Cart getById(@PathVariable("id") Integer id);

    @RequestMapping("/getPage/{uid}")
    public PageResult<Cart> getPage(
            @PathVariable("uid") Integer uid);

    @RequestMapping("/countMoney/{uid}")
    public BigDecimal countMoney(@PathVariable("uid") Integer uid);

    @RequestMapping("/deleteCart/{uid}")
    void deleteCart(@PathVariable("uid") Integer uid);
}
