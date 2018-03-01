package com.thogakade.dao.impl;

import com.avaje.ebean.Model;
import com.avaje.ebean.PagedList;
import com.thogakade.dao.ItemDao;
import com.thogakade.models.Customer;
import com.thogakade.models.Item;
import com.thogakade.models.OrderDetails;

import java.util.List;

/**
 * Created by Amila Nuwan Thilina on 16/06/10.
 */
public class ItemDaoImpl implements ItemDao {

    public static final Model.Finder<Long, Item> find = new Model.Finder<>(Item.class);

    @Override
    public Item add(Item item) {
        item.save();
        return item;
    }

    @Override
    public Item update(Item item) {
        item.update();
        return item;
    }

    @Override
    public Item delete(Long itemCode) {
        Item item = find.byId(itemCode);
        if(item != null) {
            item.delete();
        }
        return item;
    }

    @Override
    public Item find(Long itemCode) {
        return find.byId(itemCode);
    }

    @Override
    public List<Item> list(Integer offset, Integer limit) {
        PagedList<Item> list = find.findPagedList(offset , limit);

        return list.getList();
    }
}
