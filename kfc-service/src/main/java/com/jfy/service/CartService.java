package com.jfy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jfy.pojo.Cart;
import com.jfy.utils.PageResult;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jfy
 * @since 2024-05-19
 */
public interface CartService extends IService<Cart> {

    Cart findCart(Integer uid, Integer pid);
    List<Cart> getCartWithProduct(Integer uid);


    BigDecimal getMoneyByCartList(List<Cart> cartList);

    void deleteCart(Integer uid);
}
