package com.thogakade.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.thogakade.dao.impl.CustomerDaoImpl;
import com.thogakade.models.Customer;
import com.thogakade.utils.JSONService;
import com.thogakade.utils.ResponseWrapper;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.internalServerError;
import static play.mvc.Results.ok;

/**
 * Created by Amila Nuwan Thilina on 16/06/10.
 */
public class CustomerCotroller extends Controller {

    @Inject
    CustomerDaoImpl customerDAO;

    @Inject
    ObjectMapper mapper;

    @BodyParser.Of(BodyParser.Json.class)
    public Result add() {
        JsonNode jsonNode = request().body().asJson();

        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            Customer customer = mapper.treeToValue(jsonNode, Customer.class);
            Customer add = customerDAO.add(customer);
            if (add != null) {
                return ok(JSONService.toJsonNode(new ResponseWrapper<Customer>("Customer Added",add)));
            }
        } catch (JsonProcessingException e) {
            return internalServerError();
        }
        return badRequest("Customer Failed");
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result update() {
        JsonNode jsonNode = request().body().asJson();

        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            Customer customer = mapper.treeToValue(jsonNode, Customer.class);
            Customer updated = customerDAO.update(customer);
            if (updated != null) {
                return ok(JSONService.toJsonNode(new ResponseWrapper<Customer>("Customer Uppdated",updated)));
            }
        } catch (JsonProcessingException e) {
            return internalServerError();
        }
        return badRequest("Update Failed");
    }

    public Result delete(Long customerId) {
            Customer deleted = customerDAO.delete(customerId);
            if (deleted != null) {
                return ok(JSONService.toJsonNode(new ResponseWrapper<Customer>("Customer Deleted",deleted)));
            }
        return badRequest(JSONService.toJsonNode(new ResponseWrapper<Customer>("Failed",deleted)));
    }

    public Result find(Long customerId) {
        Customer found = customerDAO.find(customerId);
        if (found != null) {
            return ok(JSONService.toJsonNode(new ResponseWrapper<Customer>("Customer found",found)));
        }

        return badRequest(JSONService.toJsonNode(new ResponseWrapper<Customer>("Not available",found)));
    }

    public Result list(Integer offset, Integer limit){
        List<Customer> list = customerDAO.list(offset, limit);
        if (!list.isEmpty()){
            return ok(JSONService.toJsonNode(new ResponseWrapper<List<Customer>>("Customer Deleted",list)));
        }
        return badRequest(JSONService.toJsonNode(new ResponseWrapper<List<Customer>>("No Customers",list)));
    }
}
