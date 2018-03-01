package com.thogakade.dao.impl;

import com.avaje.ebean.Model;
import com.avaje.ebean.PagedList;
import com.thogakade.dao.CustomerDao;
import com.thogakade.models.Customer;
import com.thogakade.models.OrderDetails;

import java.util.List;

/**
 * Created by Amila Nuwan Thilina on 16/06/10.
 */
public class CustomerDaoImpl implements CustomerDao {

    public static final Model.Finder<Long, Customer> find = new Model.Finder<>(Customer.class);

    @Override
    public Customer add(Customer customer) {
        customer.save();
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        customer.update();
        return customer;
    }

    @Override
    public Customer delete(Long customerId) {
        Customer customer = find.byId(customerId);
        customer.delete();
        return customer;
    }

    @Override
    public Customer find(Long customerId) {
        return find.byId(customerId);
    }

    @Override
    public List<Customer> list(Integer offset, Integer limit) {
        PagedList<Customer> list = find.findPagedList(offset , limit);

        return list.getList();
    }
}
