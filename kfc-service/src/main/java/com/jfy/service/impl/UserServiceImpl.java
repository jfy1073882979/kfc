package com.jfy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jfy.dao.UserMapper;
import com.jfy.pojo.User;
import com.jfy.service.UserService;
import com.jfy.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jfy
 * @since 2024-05-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",user.getName());
        queryWrapper.eq("password",user.getPassword());
        queryWrapper.eq("role_id",user.getRoleId());
//        System.out.println(user.getName()+" "+user.getPassword()+" "+user.getRoleId());
        User user2 = userMapper.selectOne(queryWrapper);
        return user2;

    }

    @Override
    public List<User> getUserDiscussByPid(Integer pid) {
        return userMapper.getUserDiscussByPid(pid);
    }



    @Override
    public PageResult<User> getPage(String uname, Integer currentPage, Integer pageSize) {
        PageResult<User> pageResult = new PageResult<>();
        pageResult.setCode(0);
        pageResult.setMsg("分页查询成功");
        Page<User> page = new Page<>(currentPage, pageSize);
        if (!uname.equals("2Af7348K..;sd")) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("name", uname);
            long count = userMapper.selectCount(queryWrapper);
            pageResult.setCount((int) count);
            IPage<User> iPage = userMapper.selectPage(page, queryWrapper);
            List<User> records = iPage.getRecords();
            pageResult.setData(records);

        } else {
            long count = userMapper.selectCount(null);
            pageResult.setCount((int) count);


            IPage<User> iPage = userMapper.selectPage(page, null);
            List<User> records = iPage.getRecords();
            pageResult.setData(records);
        }
        return pageResult;
    }
}
