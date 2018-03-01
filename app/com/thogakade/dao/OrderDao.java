package com.thogakade.dao;

import com.avaje.ebean.SqlRow;
import com.thogakade.models.Orders;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by Amila Nuwan Thilina on 16/06/10.
 */
public interface OrderDao {

    Orders add(Orders orders);

    Orders update(Orders orders);

    Orders delete(Orders orders);

    Orders find(Long orderId);

    List<Orders> list(Integer offset, Integer limit);

    List<Orders> listWithCusNItem(Long orderId);

    Map<String, Double> dayIncome();
}
