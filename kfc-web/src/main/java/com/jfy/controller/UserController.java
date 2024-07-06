package com.jfy.controller;

import com.jfy.feign.RoleFeignService;
import com.jfy.feign.UserFeignService;
import com.jfy.pojo.Role;
import com.jfy.pojo.User;
import com.jfy.utils.AjaxResult;
import com.jfy.utils.PageResult;
import com.jfy.utils.TransFerPlace;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserFeignService userFeignService;

    @Autowired
    RoleFeignService roleFeignService;

    @RequestMapping("/toLogin")
    public String getRole(HttpServletRequest request){
        request.setAttribute("list",userFeignService.getRole());

        return "user/login";
    }
    @RequestMapping("/login")
    @ResponseBody
    public AjaxResult login(User _user, HttpSession session){
        System.out.println("-----------login-----------");
        System.out.println(_user);

        User user = userFeignService.login(_user);
        if (user!=null){
            session.setAttribute("user",user);
            return AjaxResult.right();
        }else {
            return AjaxResult.error();
        }

    }


    @RequestMapping("/center")
    public String center(HttpSession session){
        System.out.println("-----------center-----------");
//        User user = (User) session.getAttribute("user");
//        //跳转tologin请求
//        if(user == null) return "redirect:/user/toLogin";
        return "redirect:/toIndex";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        System.out.println("-----------logout-----------");
        //销毁user对象
        session.removeAttribute("user");
        return "redirect:/prod/index.html";
    }

    @RequestMapping("/updateUser")
    public String updateUser(HttpSession session,HttpServletRequest request){
        System.out.println("-----------updateUser-----------");

        List<Role> list = roleFeignService.getList();
        request.setAttribute("list",list);
        return "user/edit";
    }

    @RequestMapping("/editUser")
    @ResponseBody
    public AjaxResult editUser(User user,HttpSession session){
        System.out.println("-----------editUser-----------");
        System.out.println(user);
        userFeignService.updateById(user);
        User _user = userFeignService.getById(user.getUid());
        session.setAttribute("user",_user);
        return AjaxResult.right();
    }

    @RequestMapping("/upload")
    @ResponseBody
    public Map<String, Object> upload(MultipartFile file, HttpSession session){
        System.out.println("-----------upload----------");
        System.out.println("file:"+file);

        String name = file.getOriginalFilename();
        System.out.println("name:"+name);

        String path=session.getServletContext().getRealPath("/avatar");
        System.out.println("path:"+path);

        File file1 = new File(path);
        if(!file1.exists()){
            file1.mkdir();
        }

        UUID uuid = UUID.randomUUID();
        String realName = uuid+name;

        File file2 = new File(file1,realName);
        try {
            file.transferTo(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        TransFerPlace.transfer(file2,"avatar");

        Map<String,Object>map=new HashMap<>();
        map.put("code",0);
        map.put("msg","文件上传成功");
        map.put("data","/avatar/"+realName);

        return map;
    }

    @RequestMapping("/toResgister")
    public String toResgister(HttpServletRequest request){
        System.out.println("-----------toResgister-----------");
        List<Role> list = roleFeignService.getList();
        request.setAttribute("list",list);
        return "user/reg";
    }

    @RequestMapping("/userRegister")
    @ResponseBody
    public AjaxResult userRegister(User user){
        System.out.println("-----------userRegister-----------");
        System.out.println(user);
        user.setCreateTime(new Date());
        boolean flag = userFeignService.save(user);
        return flag?AjaxResult.right():AjaxResult.error();
    }

    @RequestMapping("/toRecharge")
    public String toRecharge(){
        return "user/recharge";
    }

    @RequestMapping("/doRecharge")
    @ResponseBody
    public AjaxResult doRecharge(HttpSession session, User user){
        System.out.println("-----------doRecharge-----------");
        System.out.println(user);
        User _user = (User) session.getAttribute("user");
        _user.setBalance(_user.getBalance().add(user.getBalance()));
        boolean flag = userFeignService.updateById(_user);
        return flag?AjaxResult.right():AjaxResult.error();

    }

    @RequestMapping("/toUserList")
    public String toUserList(){
        return "user/list";
    }

    @RequestMapping("/userList")
    @ResponseBody
    public PageResult<User> userList(String pname, Integer page, Integer limit){
        System.out.println("-----------userList-----------");
        System.out.println("pname = " + pname+",page = "+page+",limit = "+limit);

        if("".equals(pname)){
            pname = "2Af7348K..;sd";
        }


        //模糊分页和分页查询
        PageResult<User> pageResult = userFeignService.getPage(pname,page,limit);
        return pageResult;

    }

    @RequestMapping("/deleteUser/{uid}")
    @ResponseBody
    public AjaxResult deleteUser(@PathVariable("uid") Integer uid){
        System.out.println("-----------deleteUser-----------");
//        System.out.println("uid = " + uid);
        boolean b =  userFeignService.removeById(uid);
        return b?AjaxResult.right():AjaxResult.error();

    }

    @RequestMapping("/toEditUser/{uid}")
    public String toEditUser(@PathVariable("uid") Integer uid,HttpServletRequest request){
        System.out.println("-----------toEditUser-----------");
//        System.out.println("uid = " + uid);
        User user = userFeignService.getById(uid);
        request.setAttribute("user2",user);
        List<Role> list = roleFeignService.getList();
        request.setAttribute("list",list);
        return "user/edit2";
    }

    @RequestMapping("/doEdit")
    @ResponseBody
    public AjaxResult doEdit(User user){
        System.out.println("-----------doEdit-----------");
        System.out.println(user);
        boolean b = userFeignService.updateById(user);
        return b?AjaxResult.right():AjaxResult.error();
    }

    @RequestMapping("/toAdd")
    public String toAdd(HttpServletRequest request){
        System.out.println("-----------toAdd---------");
        List<Role> list = roleFeignService.getList();
        request.setAttribute("list",list);
        return "user/add";
    }

    @RequestMapping("/doAdd")
    @ResponseBody
    public AjaxResult doAdd(User user){
        System.out.println("-----------doAdd-----------");
        System.out.println(user);
        user.setCreateTime(new Date());
        boolean flag = userFeignService.save(user);
        return flag?AjaxResult.right():AjaxResult.error();
    }

}
