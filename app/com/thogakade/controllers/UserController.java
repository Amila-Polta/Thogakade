package com.thogakade.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.thogakade.dao.impl.UserDAOImpl;
import com.thogakade.models.User;
import com.thogakade.utils.JSONService;
import com.thogakade.utils.ResponseWrapper;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by Amila Nuwan Thilina on 16/06/12.
 */
public class UserController extends Controller {

    @Inject
    UserDAOImpl userDAO;

    @Inject
    ObjectMapper mapper;

    @BodyParser.Of(BodyParser.Json.class)
    public Result add() {
        JsonNode jsonNode = request().body().asJson();

        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            User user = mapper.treeToValue(jsonNode, User.class);
            User add = userDAO.add(user);
            if (add != null) {
                return ok(JSONService.toJsonNode(new ResponseWrapper<User>("User Added",add)));
            }
        } catch (JsonProcessingException e) {
            return internalServerError();
        }
        return badRequest("User Failed");
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result update() {
        JsonNode jsonNode = request().body().asJson();

        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            User user = mapper.treeToValue(jsonNode, User.class);
            User updated = userDAO.update(user);
            if (updated != null) {
                return ok(JSONService.toJsonNode(new ResponseWrapper<User>("User Uppdated",updated)));
            }
        } catch (JsonProcessingException e) {
            return internalServerError();
        }
        return badRequest("Update Failed");
    }

    public Result delete(Long userId) {
        User deleted = userDAO.delete(userId);
        if (deleted != null) {
            return ok(JSONService.toJsonNode(new ResponseWrapper<User>("User Deleted",deleted)));
        }
        return badRequest("Failed");
    }

    public Result find(Long userId) {
        User found = userDAO.find(userId);
        if (found != null) {
            return ok(JSONService.toJsonNode(new ResponseWrapper<User>("User found",found)));
        }
        return badRequest("Not found");
    }

    public Result list(Integer offset, Integer limit){
        List<User> list = userDAO.list(offset, limit);
        if (!list.isEmpty()){
            return ok(JSONService.toJsonNode(new ResponseWrapper<List<User>>("User Deleted",list)));
        }
        return badRequest("Emty set");
    }

    public Result signIn(){
        JsonNode jsonNode = request().body().asJson();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        try {
            User user = mapper.treeToValue(jsonNode, User.class);
            User found = userDAO.find(user.getUserId());
            if (found != null) {
                if(user.getPassword().equals(found.getPassword())){
                    return ok(JSONService.toJsonNode(new ResponseWrapper<User>("User Uppdated",found)));
                }else{
                    return badRequest("Invalid Password");
                }
            }else{
                return badRequest("User Not Found");
            }
        } catch (JsonProcessingException e) {
            return internalServerError();
        }
    }
}
