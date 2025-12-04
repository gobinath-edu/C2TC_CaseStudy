package com.fooddelivery.fooditemservice;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FoodItemService {
    private final Map<Integer, FoodItem> store = new ConcurrentHashMap<>();

    public FoodItem create(int id, String name, double price) {
        FoodItem f = new FoodItem(id, name, price);
        store.put(id, f);
        return f;
    }

    public FoodItem getById(int id) { return store.get(id); }
    public Collection<FoodItem> getAll() { return store.values(); }
    public void delete(int id) { store.remove(id); }
    public boolean exists(int id) { return store.containsKey(id); }
}
