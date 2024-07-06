package com.jfy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfy.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jfy
 * @since 2024-05-19
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}
