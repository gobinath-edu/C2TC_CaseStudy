package com.fooddelivery.main;

import com.fooddelivery.fooditemservice.*;
import com.fooddelivery.restaurantservice.*;
import com.fooddelivery.userservice.*;
import com.fooddelivery.cartservice.*;
import com.fooddelivery.orderservice.*;
import com.fooddelivery.adminservice.*;
import com.fooddelivery.customerservice.*;

import java.util.Scanner;
import java.util.Collection;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        FoodItemService food = new FoodItemService();
        RestaurantService rest = new RestaurantService(food);
        UserService users = new UserService();
        CartService carts = new CartService(food);
        OrderService orders = new OrderService(carts, food, users);

        AdminService admin = new AdminService(rest, food, users, orders);
        CustomerService customer = new CustomerService(users, carts, orders, food);

        System.out.println("Welcome to Food Delivery CLI (mini-project)");

        boolean running = true;
        while (running) {
            try {
                System.out.println("\nMain Menu:\n1. Admin\n2. Customer\n3. Exit\nChoose:");
                int mainChoice = Integer.parseInt(sc.nextLine().trim());
                switch (mainChoice) {
                    case 1 -> adminMenu(sc, admin);
                    case 2 -> customerMenu(sc, customer);
                    case 3 -> { running = false; System.out.println("Exiting..."); }
                    default -> System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        sc.close();
    }

    private static void adminMenu(Scanner sc, AdminService admin) {
        boolean back = false;
        while (!back) {
            try {
                System.out.println("\nAdmin Menu:\n1.Add Restaurant\n2.Add FoodItem\n3.Add Food to Restaurant\n4.Add Delivery Person\n5.View Orders\n6.Assign Delivery\n7.Back\nChoose:");
                int ch = Integer.parseInt(sc.nextLine().trim());
                switch (ch) {
                    case 1 -> {
                        System.out.print("Enter Restaurant ID: "); int id = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Enter Restaurant Name: "); String name = sc.nextLine().trim();
                        System.out.println("Added: " + admin.addRestaurant(id, name));
                    }
                    case 2 -> {
                        System.out.print("Enter FoodItem ID: "); int fid = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Enter FoodItem Name: "); String fname = sc.nextLine().trim();
                        System.out.print("Enter Price: "); double p = Double.parseDouble(sc.nextLine().trim());
                        System.out.println("Added: " + admin.addFoodItem(fid, fname, p));
                    }
                    case 3 -> {
                        System.out.print("Restaurant ID: "); int rid = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("FoodItem ID: "); int fid2 = Integer.parseInt(sc.nextLine().trim());
                        admin.addFoodToRestaurant(rid, fid2);
                        System.out.println("Food added to restaurant");
                    }
                    case 4 -> {
                        System.out.print("DeliveryPerson ID: "); int did = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Name: "); String dname = sc.nextLine().trim();
                        System.out.print("Contact: "); long contact = Long.parseLong(sc.nextLine().trim());
                        System.out.println("Added: " + admin.addDeliveryPerson(did, dname, contact));
                    }
                    case 5 -> {
                        Collection<com.fooddelivery.orderservice.Order> orders = admin.viewOrders();
                        System.out.println("Orders:");
                        for (var o : orders) System.out.println(o);
                    }
                    case 6 -> {
                        System.out.print("Order ID: "); int oid = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("DeliveryPerson ID: "); int dp = Integer.parseInt(sc.nextLine().trim());
                        admin.assignDelivery(oid, dp);
                        System.out.println("Assigned");
                    }
                    case 7 -> back = true;
                    default -> System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void customerMenu(Scanner sc, CustomerService customer) {
        boolean back = false;
        while (!back) {
            try {
                System.out.println("\nCustomer Menu:\n1.Add Customer\n2.View Food Items\n3.Add Food to Cart\n4.View Cart\n5.Place Order\n6.View My Orders\n7.Back\nChoose:");
                int ch = Integer.parseInt(sc.nextLine().trim());
                switch (ch) {
                    case 1 -> {
                        System.out.print("Customer ID: "); int id = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Name: "); String name = sc.nextLine().trim();
                        System.out.print("Contact: "); long c = Long.parseLong(sc.nextLine().trim());
                        System.out.println("Added: " + customer.addCustomer(id, name, c));
                    }
                    case 2 -> {
                        System.out.println("Food Items:");
                        for (var f : customer.viewAllFoodItems()) System.out.println(f);
                    }
                    case 3 -> {
                        System.out.print("Customer ID: "); int cid = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("FoodItem ID: "); int fid = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Quantity: "); int q = Integer.parseInt(sc.nextLine().trim());
                        customer.addToCart(cid, fid, q);
                        System.out.println("Added to cart");
                    }
                    case 4 -> {
                        System.out.print("Customer ID: "); int cid2 = Integer.parseInt(sc.nextLine().trim());
                        System.out.println(customer.viewCart(cid2));
                    }
                    case 5 -> {
                        System.out.print("Customer ID: "); int cid3 = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Delivery Address: "); String addr = sc.nextLine().trim();
                        System.out.println(customer.placeOrder(cid3, addr));
                    }
                    case 6 -> {
                        System.out.print("Customer ID: "); int cid4 = Integer.parseInt(sc.nextLine().trim());
                        System.out.println("My Orders:");
                        for (var o : customer.viewMyOrders(cid4)) System.out.println(o);
                    }
                    case 7 -> back = true;
                    default -> System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
