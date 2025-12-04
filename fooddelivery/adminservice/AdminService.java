package com.fooddelivery.adminservice;

import com.fooddelivery.restaurantservice.*;
import com.fooddelivery.fooditemservice.*;
import com.fooddelivery.userservice.*;
import com.fooddelivery.orderservice.*;

import java.util.Collection;

public class AdminService {
    private final RestaurantService restaurantService;
    private final FoodItemService foodItemService;
    private final UserService userService;
    private final OrderService orderService;

    public AdminService(RestaurantService r, FoodItemService f, UserService u, OrderService o){
        this.restaurantService=r; this.foodItemService=f; this.userService=u; this.orderService=o;
    }

    public Restaurant addRestaurant(int id,String name){ return restaurantService.create(id,name); }
    public FoodItem addFoodItem(int id,String name,double price){ return foodItemService.create(id,name,price); }
    public void addFoodToRestaurant(int rId,int fId){ restaurantService.addFoodToRestaurant(rId,fId); }
    public DeliveryPerson addDeliveryPerson(int id,String name,long c){ return userService.createDeliveryPerson(id,name,c); }
    public Collection<Order> viewOrders(){ return orderService.getAllOrders(); }
    public void assignDelivery(int orderId,int deliveryPersonId){ orderService.assignDeliveryPerson(orderId, deliveryPersonId); }
}
