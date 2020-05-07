package com.switchfully.order.domain.items;

import com.switchfully.order.domain.EntityDatabase;
import com.switchfully.order.domain.items.prices.Price;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Named
public class ItemDatabase extends EntityDatabase<Item> {
//    private Map<UUID, Item> entities;
//
//    @Autowired
//    public ItemDatabase(Map<UUID, Item> entities) {
//        this.entities = entities;
//        this.entities.put(new UUID(10,20) ,new Item( new Item.ItemBuilder().withAmountOfStock(10)
//                .withDescription("cheses")
//                .withId(new UUID(10,20))
//                .withName("clke")
//                .withPrice(new Price(new BigDecimal(10)))));
//    }



}
