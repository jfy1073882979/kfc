package com.jfy.controller;


import com.jfy.pojo.Discuss;
import com.jfy.pojo.User;
import com.jfy.service.DiscussService;
import com.jfy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
@RequestMapping("/discuss")
public class DiscussController {
    @Autowired
    DiscussService discussService;
    @Autowired
    UserService userService;


    @RequestMapping("/addDiscuss/{uid}/{pid}")
    void addDiscuss(@PathVariable("uid") Integer uid, @PathVariable("pid") Integer pid, @RequestBody Discuss discuss){
        discuss.setUid(uid);
        discuss.setPid(pid);
        discuss.setCreatetime(new Date());
        discussService.save(discuss);
    }

//    @RequestMapping("/getDiscussByPid/{pid}")
//    List<Discuss> getDiscussByPid(@PathVariable("pid") Integer pid){
//        List<Discuss> discussList = discussService.getDiscussByPid(pid);
//        return discussList;
//    }

    @RequestMapping("/getDiscussByPid/{pid}")
    List<User> getDiscussByPid(@PathVariable("pid") Integer pid){
        List< User> userList = userService.getUserDiscussByPid(pid);
        return userList;
    }

    @RequestMapping("/getDiscussByUid/{uid}")
    List<Discuss> getDiscussByUid(@PathVariable("uid") Integer uid){
        List<Discuss> discussList = discussService.getDiscussByUid(uid);
        return discussList;
    }

    @RequestMapping("/deleteDiscuss/{pid}")
    boolean deleteDiscuss(@PathVariable("pid") Integer pid){
        boolean b = discussService.removeById(pid);
        return b;
    }
}
