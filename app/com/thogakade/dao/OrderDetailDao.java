package com.thogakade.dao;

import com.thogakade.models.OrderDetails;

import java.util.List;

/**
 * Created by Amila Nuwan Thilina on 16/06/10.
 */
public interface OrderDetailDao {

    OrderDetails add(OrderDetails orderDetails);

    OrderDetails update(OrderDetails orderDetails);

    OrderDetails delete(OrderDetails orderDetails);

    OrderDetails find(Long orderDetailId);

    List<OrderDetails> list(Integer offset, Integer limit);
}
