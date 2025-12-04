package com.fooddelivery.restaurantservice;

import com.fooddelivery.fooditemservice.FoodItemService;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RestaurantService {
    private final Map<Integer, Restaurant> store = new ConcurrentHashMap<>();
    private final FoodItemService foodItemService;

    public RestaurantService(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    public Restaurant create(int id, String name) {
        Restaurant r = new Restaurant(id, name);
        store.put(id, r);
        return r;
    }

    public Restaurant getById(int id) { return store.get(id); }
    public Collection<Restaurant> getAll() { return store.values(); }

    public void addFoodToRestaurant(int restaurantId, int foodItemId) {
        Restaurant r = store.get(restaurantId);
        if (r == null) throw new IllegalArgumentException("Restaurant not found: " + restaurantId);
        if (!foodItemService.exists(foodItemId)) throw new IllegalArgumentException("FoodItem not found: " + foodItemId);
        r.addFoodItem(foodItemId);
    }

    public void removeFoodFromRestaurant(int restaurantId, int foodItemId) {
        Restaurant r = store.get(restaurantId);
        if (r == null) throw new IllegalArgumentException("Restaurant not found: " + restaurantId);
        r.removeFoodItem(foodItemId);
    }
}
