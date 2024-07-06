package com.jfy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jfy.dao.CartMapper;
import com.jfy.pojo.Cart;
import com.jfy.pojo.Product;
import com.jfy.pojo.User;
import com.jfy.service.CartService;
import com.jfy.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Autowired
    private CartMapper cartMapper;
    @Override
    public Cart findCart(Integer uid, Integer pid) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        queryWrapper.eq("pid",pid);
        Cart cart = baseMapper.selectOne(queryWrapper);
        return cart;
    }

    @Override
    public List<Cart> getCartWithProduct(Integer uid) {
        return cartMapper.getCartWithProduct(uid);
    }

    @Override
    public BigDecimal getMoneyByCartList(List<Cart> cartList) {
        BigDecimal total = new BigDecimal(0);
        for(Cart cart : cartList){
            List<Product> productList = cart.getProductList();
            for(Product product : productList){
                total = total.add(product.getPrice().multiply(new BigDecimal(cart.getPquantity())));
            }

        }
        return total;
    }

    @Override
    public void deleteCart(Integer uid) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        cartMapper.delete(queryWrapper);
    }


//    @Override
//    public PageResult<Cart> getPage(String cname, Integer currentPage, Integer pageSize) {
//        PageResult<Cart> pageResult = new PageResult<>();
//        pageResult.setCode(0);
//        pageResult.setMsg("分页查询成功");
//        Page<Cart> page = new Page<>(currentPage, pageSize);
//        if (!cname.equals("2Af7348K..;sd")) {
//            QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
//            queryWrapper.like("name", cname);
//            long count = cartMapper.selectCount(queryWrapper);
//            pageResult.setCount((int) count);
//            IPage<Cart> iPage = cartMapper.selectPage(page, queryWrapper);
//            List<Cart> records = iPage.getRecords();
//            pageResult.setData(records);
//
//        } else {
//            long count = cartMapper.selectCount(null);
//            pageResult.setCount((int) count);
//
//
//            IPage<Cart> iPage = cartMapper.selectPage(page, null);
//            List<Cart> records = iPage.getRecords();
//            pageResult.setData(records);
//        }
//        return pageResult;
//    }
}
