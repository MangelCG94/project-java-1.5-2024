package com.practice;

import java.util.HashMap;
import java.util.Map;

public class CustomerRepository {
    private final Map<Long,Customer> customers;

    public CustomerRepository() {
        customers = new HashMap<>();
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
        System.out.println("Cliente añadido");
    }

    public Map<Long,Customer> findAll() {
        System.out.println("Mostrando lista de clientes");
        return new HashMap<>(customers);
    }

    public Customer findCustomerById(Long id) {
        return customers.getOrDefault(id, null);
    }

    public void updateCustomer(Long id, Customer newCustomer) {
        customers.put(id, new Customer(newCustomer));
        System.out.println("Cliente actualizado correctamente");
    }

    public void removeCustomer(Long id) {
        System.out.println("Borrando cliente...");
        customers.remove(id);
        System.out.println("Cliente borrado correctamente");
    }

    void createTestData() {
        Customer cus1 = new Customer("Pedro Jose", "Pereira","pedro@hotmail.com");
        Customer cus2 = new Customer("Ana", "Bautista","ana@hotmail.com");
        Customer cus3 = new Customer("Cintia", "López","cintia@hotmail.com");
        customers.putAll(Map.of(cus1.getId(), cus1,
                cus2.getId(), cus2,cus3.getId(), cus3));
        System.out.println("Clientes creados");
    }
}