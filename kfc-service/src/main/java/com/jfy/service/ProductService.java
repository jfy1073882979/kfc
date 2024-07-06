package com.jfy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jfy.pojo.Product;
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
public interface ProductService extends IService<Product> {
    public List<Product> getProductByPage(Integer currentPage);

    public PageResult<Product> getPage(String pname, Integer currentPage, Integer pageSize);

}
