package com.example.glezma.group6project;

/**
 * Created by glezma on 12/10/17.
 */

public class TradeIntent {

    public String game,game_date,type, user,status;
    public Integer price, quantity;

    public TradeIntent() {
    }

    public TradeIntent(String game, String game_date,String type, Integer price, Integer quantity, String user, String status) {
        this.game = game;
        this.game_date= game_date;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.user = user;
        this.status = status;

    }
}
