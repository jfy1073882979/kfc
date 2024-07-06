package com.jfy.feign;

import com.jfy.pojo.Role;
import com.jfy.pojo.User;
import com.jfy.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "kfc-service",path = "/user")
public interface UserFeignService {
    @RequestMapping("/getRole")
    public List<Role> getRole();

    @RequestMapping("/login")
    User login(User user);

    @RequestMapping("/save")
    public Boolean save(@RequestBody User user);

    @RequestMapping("/updateById")
    public Boolean updateById(@RequestBody User user);

    @RequestMapping("/removeById/{id}")
    public Boolean removeById(@PathVariable("id") Integer id);

    @RequestMapping("/getById/{id}")
    public User getById(@PathVariable("id") Integer id);

    @RequestMapping("/getPage/{pname}/{page}/{limit}")
    public PageResult<User> getPage(@PathVariable("pname") String pname,
                                    @PathVariable("page") Integer page,
                                    @PathVariable("limit") Integer limit);

    @RequestMapping("/updateUserBalance/{uid}/{oid}")
    boolean updateUserBalance(@PathVariable("uid") Integer uid, @PathVariable("oid") Integer oid);
}
