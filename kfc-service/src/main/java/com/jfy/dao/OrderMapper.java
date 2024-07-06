package com.jfy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfy.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jfy
 * @since 2024-05-19
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    List<Order> getOrderInfoByUid(Integer uid);
}
