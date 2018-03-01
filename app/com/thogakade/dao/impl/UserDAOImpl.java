package com.thogakade.dao.impl;

import com.avaje.ebean.Model;
import com.avaje.ebean.PagedList;
import com.thogakade.dao.UserDAO;
import com.thogakade.models.User;

import java.util.List;

/**
 * Created by Amila Nuwan Thilina on 16/06/12.
 */
public class UserDAOImpl implements UserDAO {
    public static final Model.Finder<Long, User> find = new Model.Finder<>(User.class);

    @Override
    public User add(User user) {
        user.save();
        return user;
    }

    @Override
    public User update(User user) {
        user.update();
        return user;
    }

    @Override
    public User delete(Long userId) {
        User user = find.byId(userId);
        if(user != null){
            user.delete();
        }
        return user;
    }

    @Override
    public User find(Long userId) {
        User user = find.byId(userId);
        return user;
    }

    @Override
    public List<User> list(Integer offset, Integer limit) {
        PagedList<User> list = find.findPagedList(offset , limit);
        return list.getList();
    }
}
