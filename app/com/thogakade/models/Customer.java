package com.thogakade.models;


import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.annotation.Order;
import play.data.format.Formats;
import scala.util.parsing.json.JSON;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Amila Nuwan Thilina on 16/06/10.
 */
@Entity
@Table(name = "customer")
//@JsonIgnoreProperties("postalCode")
public class Customer extends Model{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long customerId;
    private String title;
    private String name;

    @Formats.DateTime(pattern = "yyyy-MM-dd")
    private Date dob;
    private Double salary;
    private String city;
    private String province;
    private String address;
    private String postalCode;
    @OneToMany(mappedBy = "customer")
    @JsonBackReference
    private List<Orders> ordersList;

    public Customer() {
    }

    public Customer(String title, String name, Date dob, Double salary, String city, String province, String address, String postalCode, List<Orders> ordersList) {
        this.title = title;
        this.name = name;
        this.dob = dob;
        this.salary = salary;
        this.city = city;
        this.province = province;
        this.address = address;
        this.postalCode = postalCode;
        this.ordersList = ordersList;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    @Override
    public String toString() {
        return String.format("{customerId:%s,title:%s,name:%s,dob:%s,salary:%s,city:%s,province:%s,address:%s,postalCode:%s}",
                customerId, title, name, dob, salary, city, province, address, postalCode);
    }


}
