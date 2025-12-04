package com.fooddelivery.userservice;

public class Customer extends User {
    public Customer() {}
    public Customer(int id, String name, long contactNo) {
        super(id, name, contactNo);
    }
    public int getCustomerId() { return getUserId(); }
    @Override
    public String toString(){ return "Customer{id="+getUserId()+", name="+getUsername()+"}"; }
}
