package com.hedgehog.gdzietabiedra.utils;

import com.hedgehog.gdzietabiedra.pojo.Shops.Shop;

/**
 * Created by Adam on 2015-06-29.
 */
public class MessageEvent {
    public String message;
    public Shop shop;
    public enum types {DATABASE_UPDATE, ITEM_CLICK};
    public types type;

    public MessageEvent(String message, Shop shop, types type) {
        this.message = message;
        this.shop = shop;
        this.type = type;
    }
    public MessageEvent(String message, types type){
        this.message = message;
        this.type = type;
    }
}
