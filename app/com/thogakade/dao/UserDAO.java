package com.thogakade.dao;

import com.thogakade.models.User;
import play.mvc.Result;

import java.util.List;

/**
 * Created by Amila Nuwan Thilina on 16/06/12.
 */
public interface UserDAO {

    User add(User user);

    User update(User user);

    User delete(Long userId);

    User find(Long user);

    List<User> list(Integer offset, Integer limit);
}
