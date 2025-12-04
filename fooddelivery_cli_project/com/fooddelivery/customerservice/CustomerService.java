package com.fooddelivery.customerservice;

import com.fooddelivery.userservice.*;
import com.fooddelivery.cartservice.*;
import com.fooddelivery.orderservice.*;
import com.fooddelivery.fooditemservice.*;

import java.util.Collection;

public class CustomerService {
    private final UserService userService;
    private final CartService cartService;
    private final OrderService orderService;
    private final FoodItemService foodItemService;

    public CustomerService(UserService u, CartService c, OrderService o, FoodItemService f){
        this.userService=u; this.cartService=c; this.orderService=o; this.foodItemService=f;
    }

    public Customer addCustomer(int id,String name,long c){ return userService.createCustomer(id,name,c); }
    public void addToCart(int cid,int fid,int qty){ cartService.addToCart(cid,fid,qty); }
    public Cart viewCart(int cid){ return cartService.viewCart(cid); }
    public Order placeOrder(int cid,String addr){ return orderService.placeOrder(cid,addr); }
    public Collection<Order> viewMyOrders(int customerId){
        return orderService.getAllOrders().stream().filter(o -> o.getCustomerId() == customerId).toList();
    }

    public Collection<FoodItem> viewAllFoodItems(){ return foodItemService.getAll(); }
}
