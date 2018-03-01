package com.thogakade.dao.impl;

import com.avaje.ebean.*;
import com.thogakade.dao.OrderDao;
import com.thogakade.models.Customer;
import com.thogakade.models.OrderDetails;
import com.thogakade.models.Orders;
import com.thogakade.models.User;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by Amila Nuwan Thilina on 16/06/10.
 */
public class OrderDaoImpl implements OrderDao {

    public static final Model.Finder<Long, Orders> find = new Model.Finder<>(Orders.class);

    @Override
    public Orders add(Orders orders) {
        orders.save();
        return orders;
    }

    @Override
    public Orders update(Orders orders) {
        orders.update();
        return orders;
    }

    @Override
    public Orders delete(Orders orders) {
        orders.delete();
        return orders;
    }

    @Override
    public Orders find(Long orderId) {
        return find.byId(orderId);
    }

    @Override
    public List<Orders> list(Integer offset, Integer limit) {
        PagedList<Orders> list = find.findPagedList(offset , limit);

        return list.getList();
    }

    @Override
    public List<Orders> listWithCusNItem(Long orderId) {
//        String sql = "Select c.name, u.user_name from Customer c, User u, Orders o where c.customer_id = o.customer_customer_id and u.user_id = o.user_user_id and order_id ="+orderId;
//        SqlQuery sqlQuery = Ebean.createSqlQuery(sql);
//
//        find.where().eq("order.customerId", "customer.customerId");
//        //sqlQuery.setParameter("order_id", orderId);
//        List<Orders> list = sqlQuery.findList();

//        ExpressionList<Orders> list = find.where().eq("orderId", orderId)
//                .and(Expr.eq("user.userId", "order.user.userId"), Expr.eq("customer.customerId", "order.customer.customerId"));
        List<Orders> list = Ebean.find(Orders.class).where().eq("orderId", orderId ).findList();
        return list;
    }

    @Override
    public Map<String, Double> dayIncome() {
        //Ebean.find(Double.class).select().fetch("",System,)
//        find.where().and(Expr.eq(""))
        String sql = "select o.date, sum(od.order_qty*i.unit_price) as income \n" +
                "from Orders o, OrderDetails od, Item i\n" +
                "where o.order_Id = od.orders_order_id and i.item_Code = od.item_item_Code\n" +
                "group by o.date";

        RawSql sql1 = RawSqlBuilder.parse(sql)
                .columnMapping("o.date", "orders.date")
                .columnMapping("i.unit_price", "item.unitPrice")
                .columnMapping("od.order_qty", "orderQty")
                .create();

        List<OrderDetails> income;
        income = Ebean.find(OrderDetails.class)
                .setRawSql(sql1)
                .findList();
        return null;
    }
}
