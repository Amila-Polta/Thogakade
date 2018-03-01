package com.thogakade.models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import play.data.format.Formats;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Amila Nuwan Thilina on 16/06/10.
 */
@Entity
@Table(name = "orders")
public class Orders extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    @Formats.DateTime(pattern = "yyyy-MM-dd")
    private Date date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"dob","city","salary","province","address"})
    private Customer customer;
    @OneToMany(mappedBy = "orders" ,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<OrderDetails> detailsList;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @Transient
    private String asdfg;

    public Orders(){

    }

    public Orders(Long orderId, Date date, Customer customer, List<OrderDetails> detailsList, User user, String asdfg) {
        this.orderId = orderId;
        this.date = date;
        this.customer = customer;
        this.detailsList = detailsList;
        this.user = user;
        this.asdfg =asdfg;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @JsonProperty(value = "customersId")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "customerId")
    @JsonIdentityReference(alwaysAsId = true)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDetails> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<OrderDetails> detailsList) {
        this.detailsList = detailsList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAsdfg() {
        return asdfg;
    }

    public void setAsdfg(String asdfg) {
        this.asdfg = asdfg;
    }

    @Override
    public String toString() {
        return String.format("{orderId:%s,date:%s,customer:%s,dateilList:%s}", orderId, date, customer.toString(), detailsList);
    }
}
