package com.fooddelivery.userservice;

public class DeliveryPerson extends User {
    public DeliveryPerson() {}
    public DeliveryPerson(int id, String name, long contactNo) {
        super(id, name, contactNo);
    }
    public int getDeliveryPersonId() { return getUserId(); }
    @Override
    public String toString(){ return "DeliveryPerson{id="+getUserId()+", name="+getUsername()+"}"; }
}
