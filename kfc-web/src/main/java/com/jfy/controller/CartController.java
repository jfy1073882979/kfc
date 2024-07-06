package com.jfy.controller;


import com.jfy.feign.CartFeignService;
import com.jfy.pojo.Cart;
import com.jfy.pojo.User;

import com.jfy.utils.AjaxResult;
import com.jfy.utils.PageResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
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
@RequestMapping("/cart")
public class CartController {

    @Autowired
    public CartFeignService cartFeignService;

    @RequestMapping("/showcartList")
    public String showcartList(HttpSession session, HttpServletRequest request){

        System.out.println("-----------showcartList-----------");
        User user = (User) session.getAttribute("user");
        Integer uid = user.getUid();
        List<Cart> cartList = cartFeignService.getCartWithProduct(uid);
        request.setAttribute("cartList",cartList);
        for(Cart cart : cartList){
            System.out.println(cart);
        }
//        return "cart/cart";
             return "cart/showcartList";
    }

    @RequestMapping("/addCart/{pid}")
    @ResponseBody
    public AjaxResult addCart(@PathVariable("pid") Integer pid, HttpSession session){
        System.out.println("-----------addCart-----------");
        System.out.println("pid = " + pid);
        User user = (User) session.getAttribute("user");
        Integer uid = user.getUid();
        //如果购物车中已经存在该商品，则数量加1，否则添加新的商品

        Cart cart = cartFeignService.findCart(uid, pid);
        Cart _cart = new Cart();
        _cart.setUid(uid);
        _cart.setPid(pid);
        if(cart != null){
            Integer quantity = cart.getPquantity() + 1;
            cart.setPquantity(quantity);
            cartFeignService.updateById(cart);

        }else{

            _cart.setPquantity(1);
            cartFeignService.save(_cart);
        }

        return AjaxResult.right();



    }

//    @RequestMapping("/cartList")
//    @ResponseBody
//    public PageResult<Cart> userList(String pname, Integer page, Integer limit){
//        System.out.println("-----------userList-----------");
//        System.out.println("pname = " + pname+",page = "+page+",limit = "+limit);
//
//        if("".equals(pname)){
//            pname = "2Af7348K..;sd";
//        }
//
//
//        //模糊分页和分页查询
//        PageResult<Cart> pageResult = cartFeignService.getPage(pname,page,limit);
//        return pageResult;
//
//    }
@RequestMapping("/cartList")
@ResponseBody
public PageResult<Cart> userList(HttpSession session, String pname, Integer page, Integer limit){
    System.out.println("-----------userList-----------");
    System.out.println("pname = " + pname+",page = "+page+",limit = "+limit);

    if("".equals(pname)){
        pname = "2Af7348K..;sd";
    }
    User user = (User) session.getAttribute("user");
    Integer uid = user.getUid();

    //模糊分页和分页查询
    PageResult<Cart> pageResult = cartFeignService.getPage(uid);
    return pageResult;

}

    @RequestMapping("/countMoney")
    @ResponseBody
    public BigDecimal countMoney(HttpSession session){
        User user = (User) session.getAttribute("user");
        Integer uid = user.getUid();
        return cartFeignService.countMoney(uid);
    }

    @RequestMapping("/increaseCartByCid/{cid}")
    @ResponseBody
    public AjaxResult increaseCartByCid(@PathVariable("cid") Integer cid){
        Cart cart = cartFeignService.getById(cid);
        cart.setPquantity(cart.getPquantity()+1);
        cartFeignService.updateById(cart);
        return AjaxResult.right();
    }

    @RequestMapping("/decreaseCartByCid/{cid}")
    @ResponseBody
    public AjaxResult decreaseCartByCid(@PathVariable("cid") Integer cid){
        Cart cart = cartFeignService.getById(cid);
        cart.setPquantity(cart.getPquantity()-1);
        cartFeignService.updateById(cart);
        if (cart.getPquantity() == 0) {
            cartFeignService.removeById(cid);
        }
        return AjaxResult.right();
    }

    @RequestMapping("/deleteCartByCid/{cid}")
    @ResponseBody
    public AjaxResult deleteCartByCid(@PathVariable("cid") Integer cid){
        cartFeignService.removeById(cid);
        return AjaxResult.right();
    }

}
