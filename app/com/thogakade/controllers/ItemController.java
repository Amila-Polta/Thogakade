package com.thogakade.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.thogakade.dao.impl.ItemDaoImpl;
import com.thogakade.models.Customer;
import com.thogakade.models.Item;
import com.thogakade.utils.JSONService;
import com.thogakade.utils.ResponseWrapper;
import play.mvc.BodyParser;
import play.mvc.Result;

import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.internalServerError;
import static play.mvc.Results.ok;

/**
 * Created by Amila Nuwan Thilina on 16/06/10.
 */
public class ItemController {

    @Inject
    ItemDaoImpl itemDao;

    @Inject
    ObjectMapper mapper;

    @BodyParser.Of(BodyParser.Json.class)
    public Result add(){
        JsonNode jsonNode = request().body().asJson();

        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            Item item = mapper.treeToValue(jsonNode, Item.class);
            Item add = itemDao.add(item);
            if (add != null){
                return ok(JSONService.toJsonNode(new ResponseWrapper<Item>("Item Added",add)));
            }
        } catch (JsonProcessingException e) {
            return internalServerError();
        }
        return badRequest("Item Failed");
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result update() {
        JsonNode jsonNode = request().body().asJson();

        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            Item item = mapper.treeToValue(jsonNode, Item.class);
            Item updated = itemDao.update(item);
            if (updated != null) {
                return ok(JSONService.toJsonNode(new ResponseWrapper<Item>("Item Uppdated",updated)));
            }
        } catch (JsonProcessingException e) {
            return internalServerError();
        }
        return badRequest("Update Failed");
    }

    public Result delete(Long itemCode) {
        Item deleted = itemDao.delete(itemCode);
        if (deleted != null) {
            return ok(JSONService.toJsonNode(new ResponseWrapper<Item>("Item Uppdated",deleted)));
        }
        return badRequest("Failed");
    }

    public Result find(Long itemId) {
        Item found = itemDao.find(itemId);
        if (found != null) {
            return ok(JSONService.toJsonNode(new ResponseWrapper<Item>("Item found",found)));
        }

        return badRequest("Not found");
    }
}
