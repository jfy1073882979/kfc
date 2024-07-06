package com.jfy.controller;


import com.jfy.pojo.Order;
import com.jfy.pojo.Role;
import com.jfy.pojo.User;
import com.jfy.service.OrderService;
import com.jfy.service.RoleService;
import com.jfy.service.UserService;
import com.jfy.utils.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;
    @Autowired
    OrderService orderService;


    @RequestMapping("/getRole")
    public List<Role> getRole(){
        List<Role> list = roleService.list();
        return list;
    }

    @RequestMapping("/login")
    public User login(@RequestBody User _user){
        System.out.println("-----------login-----------");
        System.out.println(_user);
        User user = userService.login(_user);
        return user;
    }

    @RequestMapping("/save")
    public Boolean save(@RequestBody User user){
        return userService.save(user);
    }

    @RequestMapping("/updateById")
    public Boolean updateById(@RequestBody User user){
        return userService.updateById(user);
    }

    @RequestMapping("/removeById/{id}")
    public Boolean removeById(@PathVariable("id") Integer id){
        return userService.removeById(id);
    }

    @RequestMapping("/getById/{id}")
    public User getById(@PathVariable("id") Integer id){
        return userService.getById(id);
    }

    @RequestMapping("/getPage/{pname}/{page}/{limit}")
    public PageResult<User> getPage(@PathVariable("pname") String pname,
                                   @PathVariable("page") Integer page,
                                   @PathVariable("limit") Integer limit) {


        PageResult<User> pageResult = userService.getPage(pname, page, limit);
        return pageResult;
    }

    @RequestMapping("/updateUserBalance/{uid}/{oid}")
    boolean updateUserBalance(@PathVariable("uid") Integer uid, @PathVariable("oid") Integer oid){
        Order order = orderService.getById(oid);
        User user = userService.getById(uid);
        //判断用户余额是否足够
        if(user.getBalance().compareTo(order.getTotalPrice()) < 0){
            return false;
        }
        user.setBalance(user.getBalance().subtract(order.getTotalPrice()));
        userService.updateById(user);
        return true;
    }


}
