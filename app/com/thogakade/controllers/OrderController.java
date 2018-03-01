package com.thogakade.controllers;

import com.avaje.ebean.Model;
import com.avaje.ebean.SqlRow;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.thogakade.dao.impl.CustomerDaoImpl;
import com.thogakade.dao.impl.OrderDaoImpl;
import com.thogakade.dao.impl.OrderDetailDaoImpl;
import com.thogakade.models.Customer;
import com.thogakade.models.Item;
import com.thogakade.models.OrderDetails;
import com.thogakade.models.Orders;
import com.thogakade.utils.JSONService;
import com.thogakade.utils.ResponseWrapper;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Amila Nuwan Thilina on 16/06/10.
 */
public class OrderController extends Controller{

    @Inject
    OrderDaoImpl orderDao;
    @Inject
    OrderDetailDaoImpl orderDetailDao;

    @Inject
    ObjectMapper mapper;

    public Result find(Long orderId){
            Orders add = orderDao.find(orderId);
            if (add != null){
                return ok(JSONService.toJsonNode(new ResponseWrapper<Orders>("Order Uppdated",add)));
            }
        return badRequest(JSONService.toJsonNode(new ResponseWrapper<Orders>("Order Uppdated",add)));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result update(){
        JsonNode jsonNode = request().body().asJson();

        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            Orders orders = mapper.treeToValue(jsonNode, Orders.class);
            orderDao.update(orders);
            List<OrderDetails> detailsList = orders.getDetailsList();
            Orders orders1 = orderDao.find(orders.getOrderId());
            List<OrderDetails> list = orders1.getDetailsList();
            List<OrderDetails> ndele = new ArrayList<>();
            if (orders != null){
                for (OrderDetails orderDetails:
                     detailsList) {
                    orderDetails.setOrders(orders);
                    boolean updated = false;
                    for (OrderDetails od :
                            list) {
                        if (orderDetails.getOrderDetailId() == od.getOrderDetailId()){
                            ndele.add(orderDetails);
                            OrderDetails add1 = orderDetailDao.update(orderDetails);
                            updated = true;
                            break;
                        }

                    }

                    if (!updated){
                        OrderDetails ods= orderDetailDao.add(orderDetails);
                    }

                }
                for (OrderDetails od :
                        list) {
                    boolean b = false;
                    for (OrderDetails o :
                            ndele) {
                        if (od.getOrderDetailId() == o.getOrderDetailId()){
                            b = true;
                        }
                    }
                    if (!b){
                        orderDetailDao.delete(od);
                    }
                }
                return ok(JSONService.toJsonNode(new ResponseWrapper<Orders>("Order Uppdated",orders)));
            }
        } catch (JsonProcessingException e) {
            return internalServerError();
        }
        return badRequest("Customer Failed");
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result add(){
        JsonNode jsonNode = request().body().asJson();

        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            Orders orders = mapper.treeToValue(jsonNode, Orders.class);
            Orders add = orderDao.add(orders);
            List<OrderDetails> detailsList = orders.getDetailsList();
            if (add != null){
                for (OrderDetails orderDetails:
                        detailsList) {
                    orderDetails.setOrders(add);
                    OrderDetails add1 = orderDetailDao.add(orderDetails);
                    if (add1 == null){
                        break;
                    }
                }
                return ok(JSONService.toJsonNode(new ResponseWrapper<Orders>("Order Added",orders)));
            }
        } catch (JsonProcessingException e) {
            return internalServerError();
        }
        return badRequest("Customer Failed");
    }


    public Result findWithCusNUser(Long orderId){
        List<Orders> add = orderDao.listWithCusNItem(orderId);
        if (add != null){
            return ok(JSONService.toJsonNode(new ResponseWrapper<List<Orders>>("Order Available", add)));
        }
        return badRequest(JSONService.toJsonNode(new ResponseWrapper<List<Orders>>("Order Error", add)));
    }

    public Result findIncome(){
        Map<String, Double> stringDoubleMap = orderDao.dayIncome();
        return ok("WOrking");
    }
}
