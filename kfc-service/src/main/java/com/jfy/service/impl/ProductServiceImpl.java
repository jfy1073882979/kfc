package com.jfy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jfy.dao.ProductMapper;
import com.jfy.pojo.Product;
import com.jfy.service.ProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getProductByPage(Integer currentPage) {
        Page<Product> page = new Page<>(currentPage, 6);
        IPage<Product> iPage = productMapper.selectPage(page, null);
        List<Product> list = iPage.getRecords();
        return list;
    }

    @Override
    public PageResult<Product> getPage(String pname, Integer currentPage, Integer pageSize) {

        PageResult<Product> pageResult = new PageResult<>();
        pageResult.setCode(0);
        pageResult.setMsg("分页查询成功");
        Page<Product> page = new Page<>(currentPage, pageSize);
        if (!pname.equals("2Af7348K..;sd")) {
            QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("pname", pname);
            long count = productMapper.selectCount(queryWrapper);
            pageResult.setCount((int) count);
            IPage<Product> iPage = productMapper.selectPage(page, queryWrapper);
            List<Product> records = iPage.getRecords();
            pageResult.setData(records);

        } else {
            long count = productMapper.selectCount(null);
            pageResult.setCount((int) count);


            IPage<Product> iPage = productMapper.selectPage(page, null);
            List<Product> records = iPage.getRecords();
            pageResult.setData(records);
        }
        return pageResult;
    }
}
