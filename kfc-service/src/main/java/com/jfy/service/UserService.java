package com.jfy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jfy.pojo.User;
import com.jfy.utils.PageResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jfy
 * @since 2024-05-19
 */
public interface UserService extends IService<User> {
    public User login(User user);

    List<User> getUserDiscussByPid(Integer pid);


    public PageResult<User> getPage(String uname, Integer currentPage, Integer pageSize);
}
