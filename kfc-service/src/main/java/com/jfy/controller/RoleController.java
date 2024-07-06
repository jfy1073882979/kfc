package com.jfy.controller;


import com.jfy.pojo.Role;
import com.jfy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/getList")
    public List<Role> getList(){
        List<Role> list = roleService.list();
        return list;
    }

}
