package com.jfy.controller;




import com.jfy.pojo.User;
import com.jfy.utils.SysMenu;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class MainController {



    //    跳转到登录首页
    @RequestMapping("/toIndex")
    public String toContent(HttpServletRequest request,HttpSession session) {
        //保存左侧功能栏


        request.setAttribute("userMenuList",getSysMenu(session));

        return "content";
    }


    //返回欢迎页面
    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }



    //  临时菜单
    private List<SysMenu> getSysMenu(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<SysMenu> ml = new ArrayList<SysMenu>();

        SysMenu m3 = new SysMenu("system", "个人管理", "layui-icon layui-icon-util", false, null);
        SysMenu m3_1 = new SysMenu("system", "修改信息", "layui-icon layui-icon-set ", true, "/user/updateUser");

        SysMenu m3_3 = new SysMenu("system", "评论管理", "layui-icon layui-icon-set", true, "/discuss/toDiscussList");
        SysMenu m3_2 = new SysMenu("system", "余额充值", "layui-icon layui-icon-set", true, "/user/toRecharge");
//
//        SysMenu m3_5 = new SysMenu("system", "订单管理", "layui-icon layui-icon-set ", true, "/dict/toList");
//
        m3.getChildMenuList().add(m3_1);
//
        m3.getChildMenuList().add(m3_2);
        m3.getChildMenuList().add(m3_3);
//
        //m3.getChildMenuList().add(m3_5);
//
        ml.add(m3);

        SysMenu m4 = new SysMenu("system", "订单管理", "layui-icon layui-icon-util", false, null);
        SysMenu m4_1 = new SysMenu("system", "当前订单", "layui-icon layui-icon-set ", true, "/order/toOrderList");
        SysMenu m4_2 = new SysMenu("system", "历史订单", "layui-icon layui-icon-set ", true, "/order/toOrderList2");
        SysMenu m4_3 = new SysMenu("system", "已取消订单", "layui-icon layui-icon-set ", true, "/order/toOrderList3");

        m4.getChildMenuList().add(m4_1);
        m4.getChildMenuList().add(m4_2);
        m4.getChildMenuList().add(m4_3);
        ml.add(m4);


        if(user == null||user.getRoleId() == 1){
            SysMenu m1 = new SysMenu("system", "系统管理", "layui-icon layui-icon-util", false, null);
            SysMenu m1_1 = new SysMenu("system", "用户管理", "layui-icon layui-icon-set ", true, "/user/toUserList");
//        SysMenu m1_2 = new SysMenu("system", "角色管理", "layui-icon layui-icon-set ", true, "/role/toRoleList");
            SysMenu m1_3 = new SysMenu("system", "商品管理", "layui-icon layui-icon-set", true, "/prod/toList");
//        SysMenu m1_4 = new SysMenu("system", "购物车管理", "layui-icon layui-icon-set ", true, "/cart/toCartList");
            //SysMenu m1_5 = new SysMenu("system", "订单管理", "layui-icon layui-icon-set ", true, "/dict/toList");
//        SysMenu m1_6 = new SysMenu("system", "订单月度分析", "layui-icon layui-icon-set", true, "/prod/toView1");
//        SysMenu m1_7 = new SysMenu("system", "订单占比分析", "layui-icon layui-icon-set", true, "/prod/toView2");
            m1.getChildMenuList().add(m1_1);
//        m1.getChildMenuList().add(m1_2);
            m1.getChildMenuList().add(m1_3);
//        m1.getChildMenuList().add(m1_4);
            //m1.getChildMenuList().add(m1_5);
//        m1.getChildMenuList().add(m1_6);
//        m1.getChildMenuList().add(m1_7);
            ml.add(m1);

//        再加入第二个二级菜单
//            SysMenu m2 = new SysMenu("system", "业务管理", "layui-icon layui-icon-snowflake", false, null);
//            SysMenu m2_1 = new SysMenu("system", "部门管理", "layui-icon layui-icon-water", true, null);
//            SysMenu m2_2 = new SysMenu("system", "员工管理", "layui-icon layui-icon-user", true, null);
//            m2.getChildMenuList().add(m2_1);
//            m2.getChildMenuList().add(m2_2);
//            ml.add(m2);
        }

        return ml;
    }
}
