package com.thogakade.dao.impl;

import com.avaje.ebean.Model;
import com.avaje.ebean.PagedList;
import com.thogakade.dao.OrderDetailDao;
import com.thogakade.models.Customer;
import com.thogakade.models.OrderDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amila Nuwan Thilina on 16/06/10.
 */
public class OrderDetailDaoImpl implements OrderDetailDao {

    public static final Model.Finder<Long, OrderDetails> find = new Model.Finder<>(OrderDetails.class);

    @Override
    public OrderDetails add(OrderDetails orderDetails) {
        orderDetails.save();
        return orderDetails;
    }

    @Override
    public OrderDetails update(OrderDetails orderDetails) {
        orderDetails.update();
        return orderDetails;
    }

    @Override
    public OrderDetails delete(OrderDetails orderDetails) {
        orderDetails.delete();
        return orderDetails;
    }

    @Override
    public OrderDetails find(Long orderDetailId) {
        return find.byId(orderDetailId);
    }

    @Override
    public List<OrderDetails> list(Integer offset, Integer limit) {
        PagedList<OrderDetails> list = find.findPagedList(offset , limit);

        return list.getList();
    }
}
