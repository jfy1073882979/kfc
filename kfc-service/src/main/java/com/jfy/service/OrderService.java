package com.jfy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jfy.pojo.Order;

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
public interface OrderService extends IService<Order> {

    Integer addOrder(Integer uid, BigDecimal total);

    List<Order> getOrderInfoByUid(Integer uid);
}
