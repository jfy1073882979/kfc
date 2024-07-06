package com.jfy.controller;


import com.jfy.pojo.Cart;
import com.jfy.pojo.Product;
import com.jfy.pojo.User;
import com.jfy.service.CartService;
import com.jfy.service.ProductService;
import com.jfy.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jfy
 * @since 2024-06-17
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    public CartService cartService;
    @Autowired
    private ProductService productService;

    @RequestMapping("/getCartWithProduct/{uid}")
    public List<Cart> getCartWithProduct(@PathVariable("uid") Integer uid) {
        List<Cart> cartList = cartService.getCartWithProduct(uid);
        return cartList;
    }

    @RequestMapping("/findCart/{uid}/{pid}")
    public Cart findCart(@PathVariable("uid") Integer uid, @PathVariable("pid") Integer pid) {

        Cart cart = cartService.findCart(uid, pid);
        return cart;
    }

    @RequestMapping("/save")
    public Boolean save(@RequestBody Cart cart){
        return cartService.save(cart);
    }

    @RequestMapping("/updateById")
    public Boolean updateById(@RequestBody Cart cart){
        return cartService.updateById(cart);
    }

    @RequestMapping("/removeById/{id}")
    public Boolean removeById(@PathVariable("id") Integer id){
        return cartService.removeById(id);
    }

    @RequestMapping("/getById/{id}")
    public Cart getById(@PathVariable("id") Integer id){
        return cartService.getById(id);
    }

    @RequestMapping("/getPage/{uid}")
    public PageResult<Cart> getPage(
                                    @PathVariable("uid") Integer uid) {

        List<Cart> cartList = cartService.getCartWithProduct(uid);

        PageResult<Cart> pageResult = new PageResult<>();
        pageResult.setCode(0);
        pageResult.setMsg("查询成功");
        pageResult.setCount(5);
        pageResult.setData(cartList);

        return pageResult;
    }

    @RequestMapping("/countMoney/{uid}")
    public BigDecimal countMoney(@PathVariable("uid") Integer uid){
        List<Cart> cartList = cartService.getCartWithProduct(uid);
        BigDecimal total = cartService.getMoneyByCartList(cartList);

        return total;
    }
    @RequestMapping("/deleteCart/{uid}")
    void deleteCart(@PathVariable("uid") Integer uid){
        cartService.deleteCart(uid);
    }


}
