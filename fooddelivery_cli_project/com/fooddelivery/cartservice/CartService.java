package com.fooddelivery.cartservice;

import com.fooddelivery.fooditemservice.FoodItemService;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CartService {
    private final Map<Integer, Cart> carts = new ConcurrentHashMap<>();
    private final FoodItemService foodItemService;

    public CartService(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    public Cart getOrCreateCart(int customerId) {
        return carts.computeIfAbsent(customerId, id -> new Cart(id));
    }

    public void addToCart(int customerId, int foodId, int qty) {
        if (!foodItemService.exists(foodId)) throw new IllegalArgumentException("Food not found");
        Cart c = getOrCreateCart(customerId);
        c.addItem(foodId, qty);
    }

    public Cart viewCart(int customerId) { return getOrCreateCart(customerId); }

    public void clearCart(int customerId) { getOrCreateCart(customerId).clear(); }
}
