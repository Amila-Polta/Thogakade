package com.thogakade.dao;

import com.thogakade.models.Customer;

import java.util.List;

/**
 * Created by Amila Nuwan Thilina on 16/06/10.
 */
public interface CustomerDao {

    Customer add(Customer customer);

    Customer update(Customer customer);

    Customer delete(Long customerId);

    Customer find(Long customerId);

    List<Customer> list(Integer offset, Integer limit);
}
