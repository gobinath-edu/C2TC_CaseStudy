package com.fooddelivery.orderservice;

import com.fooddelivery.cartservice.CartService;
import com.fooddelivery.cartservice.Cart;
import com.fooddelivery.fooditemservice.FoodItemService;
import com.fooddelivery.userservice.UserService;
import java.util.Map;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderService {
    private final Map<Integer, Order> orders = new ConcurrentHashMap<>();
    private final AtomicInteger seq = new AtomicInteger(1);
    private final CartService cartService;
    private final FoodItemService foodItemService;
    private final UserService userService;

    public OrderService(CartService cartService, FoodItemService foodItemService, UserService userService){
        this.cartService = cartService;
        this.foodItemService = foodItemService;
        this.userService = userService;
    }

    public Order placeOrder(int customerId, String address){
        if (!userService.exists(customerId)) throw new IllegalArgumentException("Customer not found: " + customerId);
        Cart cart = cartService.viewCart(customerId);
        if (cart.getItems().isEmpty()) throw new IllegalStateException("Cart is empty");
        int id = seq.getAndIncrement();
        Order o = new Order(id, customerId);
        for(Map.Entry<Integer,Integer> e: cart.getItems().entrySet()){
            if (!foodItemService.exists(e.getKey())) throw new IllegalArgumentException("Food item not found: " + e.getKey());
            o.addItem(e.getKey(), e.getValue());
        }
        o.setDeliveryAddress(address);
        orders.put(id, o);
        cartService.clearCart(customerId);
        return o;
    }

    public Collection<Order> getAllOrders(){ return orders.values(); }

    public Order getOrder(int id){ return orders.get(id); }

    public void assignDeliveryPerson(int orderId, int deliveryPersonId){
        Order o = orders.get(orderId);
        if (o == null) throw new IllegalArgumentException("Order not found: " + orderId);
        if (!userService.exists(deliveryPersonId)) throw new IllegalArgumentException("Delivery person not found: " + deliveryPersonId);
        o.setDeliveryPersonId(deliveryPersonId);
        o.setStatus("Assigned");
    }

    public void updateStatus(int orderId, String status){
        Order o = orders.get(orderId);
        if (o == null) throw new IllegalArgumentException("Order not found: " + orderId);
        o.setStatus(status);
    }
}
