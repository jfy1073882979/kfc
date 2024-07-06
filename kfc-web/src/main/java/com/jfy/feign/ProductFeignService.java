package com.jfy.feign;

import com.jfy.pojo.Product;
import com.jfy.utils.AjaxResult;
import com.jfy.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "kfc-service",path = "/prod")
public interface ProductFeignService {

    @RequestMapping("/getPage/{pname}/{page}/{limit}")
    public PageResult<Product> getPage(@PathVariable("pname") String pname,
                                       @PathVariable("page") Integer page,
                                          @PathVariable("limit") Integer limit);

    @RequestMapping("/rmbyids")
    public boolean rmbyids(@RequestBody List<Integer> list);

    @RequestMapping("/getProductByPage/{currentPage}")
    public List<Product> getProductByPage(@PathVariable("currentPage") Integer currentPage);

    @RequestMapping("/count")
    public long count();

    @RequestMapping("/getProductById/{id}")
    public Product getProductById(@PathVariable("id") Integer id);

    @RequestMapping("/save")
    public Boolean save(@RequestBody Product product);

    @RequestMapping("/updateById")
    public Boolean updateById(@RequestBody Product product);
    @RequestMapping("/removeById/{id}")
    public Boolean removeById(@PathVariable("id") Integer id);

    @RequestMapping("/getById/{id}")
    public Product getById(@PathVariable("id") Integer id);

    @RequestMapping("/updateProductCount/{pid}/{pquantity}")
    void updateProductCount(@PathVariable("pid") Integer pid, @PathVariable("pquantity") Integer pquantity);
}
