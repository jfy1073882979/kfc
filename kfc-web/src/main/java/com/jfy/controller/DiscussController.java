package com.jfy.controller;


import com.jfy.feign.DiscussFeignService;
import com.jfy.pojo.Discuss;
import com.jfy.pojo.User;
import com.jfy.utils.AjaxResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("/discuss")
public class DiscussController {
    @Autowired
    DiscussFeignService discussFeignService;

    @RequestMapping("/addDiscuss/{pid}")
    @ResponseBody
    public AjaxResult addDiscuss(@PathVariable("pid") Integer pid, @RequestBody Discuss discuss, HttpSession session){
//        System.out.println("-----------addDiscuss-----------");
//        System.out.println("pid : " + pid+" discuss : "+discuss);
        User user = (User) session.getAttribute("user");
        Integer uid = user.getUid();
        discussFeignService.addDiscuss(uid,pid,discuss);
        return AjaxResult.right();
    }

    @RequestMapping("/getDiscussByPid/{pid}")
    @ResponseBody
    public List<User> getDiscussByPid(@PathVariable("pid") Integer pid) {
        System.out.println("-------------------getDiscussByPid-------------------");
        List<User> discussList = discussFeignService.getDiscussByPid(pid);

        System.out.println("discussList : " + discussList);
        return discussList;
    }


    @RequestMapping("/getDiscussByUid/{uid}")
    @ResponseBody
    public List<Discuss> getDiscussByUid(@PathVariable("uid") Integer uid) {
        System.out.println("-------------------getDiscussByUid-------------------");
        List<Discuss> discussList = discussFeignService.getDiscussByUid(uid);

        System.out.println("discussList : " + discussList);
        return discussList;
    }

    @RequestMapping("/toDiscussList")
    public String toDiscussList(){
        return "discuss/discussList";
    }


    @RequestMapping("/deleteDiscuss/{pid}")
    @ResponseBody
    public AjaxResult deleteDiscuss(@PathVariable("pid") Integer pid){
        System.out.println("-----------deleteDiscuss-----------");
        System.out.println("pid:"+pid);

        boolean b = discussFeignService.deleteDiscuss(pid);
        return b?AjaxResult.right():AjaxResult.error();
    }

//    }

//    @RequestMapping("/getDiscussByPid/{pid}")
//    @ResponseBody
//    public List<Discuss> getDiscussByPid(@PathVariable("pid") Integer pid){
//        System.out.println("-------------------getDiscussByPid-------------------");
//        List<Discuss> discussList =  discussFeignService.getDiscussByPid(pid);
//
//        System.out.println("discussList : "+discussList);
//        return discussList;
//    }

}
