package com.jfy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jfy.pojo.Discuss;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jfy
 * @since 2024-05-19
 */
public interface DiscussService extends IService<Discuss> {


    List<Discuss> getDiscussByUid(Integer uid);
}
