package ua.com.sportfood.models;

import java.util.List;
import java.util.UUID;

public class Cart {
    private UUID id;
    private Customer customer;
    private List<Booking> bookings;

    public Cart(Customer customer) {
        this.customer = customer;
    }

    public Cart() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "customer=" + customer +
                '}';
    }
}
