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
@Table(name = "item")
public class Item extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemCode;
    private String description;
    private Double unitPrice;
    private Integer qtyOnHand;
    @OneToMany(mappedBy = "item")
    @JsonBackReference
    private List<OrderDetails> detailsList;

    public Item(){

    }

    public Item(String description, Double unitPrice, Integer qtyOnHand, List<OrderDetails> detailsList) {
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
        this.detailsList = detailsList;
    }

    public List<OrderDetails> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<OrderDetails> detailsList) {
        this.detailsList = detailsList;
    }

    public void setItemCode(Long itemCode) {
        this.itemCode = itemCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQtyOnHand(Integer qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public Long getItemCode() {

        return itemCode;
    }

    public String getDescription() {
        return description;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public Integer getQtyOnHand() {
        return qtyOnHand;
    }
}
