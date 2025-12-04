package com.fooddelivery.userservice;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserService {
    private final Map<Integer, User> store = new ConcurrentHashMap<>();

    public Customer createCustomer(int id, String name, long contact) {
        Customer c = new Customer(id, name, contact);
        store.put(id, c);
        return c;
    }

    public DeliveryPerson createDeliveryPerson(int id, String name, long contact) {
        DeliveryPerson d = new DeliveryPerson(id, name, contact);
        store.put(id, d);
        return d;
    }

    public User getById(int id) { return store.get(id); }
    public Collection<User> getAll() { return store.values(); }
    public boolean exists(int id) { return store.containsKey(id); }
}
