package com.fooddelivery.restaurantservice;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private int id;
    private String name;
    private final List<Integer> menu = new ArrayList<>();

    public Restaurant() {}
    public Restaurant(int id, String name) { this.id = id; this.name = name; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Integer> getMenu() { return menu; }

    public void addFoodItem(int foodId) { menu.add(foodId); }
    public void removeFoodItem(int foodId) { menu.remove((Integer) foodId); }

    @Override
    public String toString() {
        return "Restaurant{id=" + id + ", name='" + name + "', menuCount=" + menu.size() + "}";
    }
}
