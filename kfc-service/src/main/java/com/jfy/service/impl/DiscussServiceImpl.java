package com.jfy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jfy.dao.DiscussMapper;
import com.jfy.pojo.Discuss;
import com.jfy.service.DiscussService;
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
public class DiscussServiceImpl extends ServiceImpl<DiscussMapper, Discuss> implements DiscussService {


    @Override
    public List<Discuss> getDiscussByUid(Integer uid) {
        QueryWrapper<Discuss> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        List<Discuss> discussList = baseMapper.selectList(queryWrapper);
        return discussList;
    }
}
