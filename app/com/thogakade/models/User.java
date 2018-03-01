package com.thogakade.models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Amila Nuwan Thilina on 16/06/12.
 */
@Entity
@Table(name = "user")
public class User extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String userName;
    private String password;
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Orders> ordersList;

    public User() {
    }

    public User(String userName, String password, List<Orders> ordersList) {
        this.userName = userName;
        this.password = password;
        this.ordersList = ordersList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }
}
