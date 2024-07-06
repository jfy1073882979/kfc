package com.jfy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jfy.dao.OrderMapper;
import com.jfy.pojo.Order;
import com.jfy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Override
    public Integer addOrder(Integer uid, BigDecimal total) {
        Order order = new Order();
        order.setUid(uid);
        order.setStatus("待支付");
        order.setTotalPrice(total);
        order.setCreatetime(new Date());
        orderMapper.insert(order);
        return order.getOid();
    }

    @Override
    public List<Order> getOrderInfoByUid(Integer uid) {
        return orderMapper.getOrderInfoByUid(uid);
    }
}
