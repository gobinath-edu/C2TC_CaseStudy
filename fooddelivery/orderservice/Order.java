package com.fooddelivery.orderservice;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private int orderId;
    private int customerId;
    private final Map<Integer,Integer> items = new HashMap<>();
    private String status = "Pending";
    private Integer deliveryPersonId;
    private String deliveryAddress;

    public Order(){}
    public Order(int orderId, int customerId){
        this.orderId = orderId; this.customerId = customerId;
    }

    public int getOrderId(){return orderId;}
    public int getCustomerId(){return customerId;}
    public Map<Integer,Integer> getItems(){return items;}
    public String getStatus(){return status;}
    public void setStatus(String s){ this.status = s; }
    public Integer getDeliveryPersonId(){ return deliveryPersonId; }
    public void setDeliveryPersonId(Integer id){ this.deliveryPersonId = id; }
    public String getDeliveryAddress(){ return deliveryAddress; }
    public void setDeliveryAddress(String addr){ this.deliveryAddress = addr; }

    public void addItem(int foodId,int qty){ items.put(foodId, items.getOrDefault(foodId,0)+qty); }

    public String toString(){
        return "Order{id="+orderId+", customerId="+customerId+", items="+items+", status="+status+"}";
    }
}
