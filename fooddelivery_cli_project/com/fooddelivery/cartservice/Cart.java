package com.fooddelivery.cartservice;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private int customerId;
    private final Map<Integer,Integer> items = new HashMap<>();

    public Cart() {}
    public Cart(int customerId) { this.customerId = customerId; }

    public int getCustomerId() { return customerId; }
    public Map<Integer,Integer> getItems() { return items; }

    public void addItem(int foodId, int qty) { items.put(foodId, items.getOrDefault(foodId, 0)+qty); }
    public void removeItem(int foodId) { items.remove(foodId); }
    public void clear() { items.clear(); }

    public String toString(){ return "Cart{customerId=" + customerId + ", items=" + items + "}"; }
}
