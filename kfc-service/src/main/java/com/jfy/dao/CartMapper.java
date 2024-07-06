package com.jfy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfy.pojo.Cart;
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
public interface CartMapper extends BaseMapper<Cart> {

    List<Cart> getCartWithProduct(Integer uid);
}
