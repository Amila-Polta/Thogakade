package com.thogakade.models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Amila Nuwan Thilina on 16/06/10.
 */
@Entity
@Table(name = "orderDetails")
public class OrderDetails extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderDetailId;
    @ManyToOne
    @JsonBackReference
    private Orders orders;
    @ManyToOne
    @JsonManagedReference
    @JsonIgnoreProperties({"qtyOnHand"})
    private Item item;
    private Integer orderQty;
    private Double discount;



    public OrderDetails(){

    }

    public OrderDetails(Orders orders, Item item, Integer orderQty, Double discount) {
        this.orders = orders;
        this.item = item;
        this.orderQty = orderQty;
        this.discount = discount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }


    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Integer getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }


    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
