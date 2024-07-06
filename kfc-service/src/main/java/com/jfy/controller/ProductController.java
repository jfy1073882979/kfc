package com.jfy.controller;


import com.jfy.pojo.Product;
import com.jfy.pojo.User;
import com.jfy.service.ProductService;
import com.jfy.utils.PageResult;
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
@RequestMapping("/prod")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/getPage/{pname}/{page}/{limit}")
    public PageResult<Product> getPage(@PathVariable("pname") String pname,
                                       @PathVariable("page") Integer page,
                                       @PathVariable("limit") Integer limit) {
        System.out.println("-----------getPage-----------");

        PageResult<Product> pageResult = productService.getPage(pname, page, limit);
        return pageResult;
    }

    @RequestMapping("/rmbyids")
    public boolean rmbyids(@RequestBody List<Integer> list) {
        boolean b = productService.removeByIds(list);
        return b;
    }

    @RequestMapping("/getProductByPage/{currentPage}")
    public List<Product> getProductByPage(@PathVariable("currentPage") Integer currentPage){
        List<Product> list =productService.getProductByPage(currentPage);
        return list;
    }

    @RequestMapping("/count")
    public long count(){
    long count = productService.count();
    return count;
    }

    @RequestMapping("/getProductById/{id}")
    public Product getProductById(@PathVariable("id") Integer id){
        Product product = productService.getById(id);
        return product;
    }

    @RequestMapping("/save")
    public Boolean save(@RequestBody Product product){
        return productService.save(product);
    }

    @RequestMapping("/updateById")
    public Boolean updateById(@RequestBody Product product){
        return productService.updateById(product);
    }

    @RequestMapping("/removeById/{id}")
    public Boolean removeById(@PathVariable("id") Integer id){
        return productService.removeById(id);
    }

    @RequestMapping("/getById/{id}")
    public Product getById(@PathVariable("id") Integer id){
        return productService.getById(id);
    }

    @RequestMapping("/updateProductCount/{pid}/{pquantity}")
    void updateProductCount(@PathVariable("pid") Integer pid, @PathVariable("pquantity") Integer pquantity){
        Product product = productService.getById(pid);
        product.setCount(product.getCount()+pquantity);
        productService.updateById(product);
    }


}
