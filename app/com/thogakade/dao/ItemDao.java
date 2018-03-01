package com.thogakade.dao;

import com.thogakade.models.Item;

import java.util.List;

/**
 * Created by Amila Nuwan Thilina on 16/06/10.
 */
public interface ItemDao {
    Item add(Item item);

    Item update(Item item);

    Item delete(Long itemCode);

    Item find(Long itemCode);

    List<Item> list(Integer offset, Integer limit);
}
