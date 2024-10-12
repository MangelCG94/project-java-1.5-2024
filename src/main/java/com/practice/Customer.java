package com.practice;

public class Customer {
    private final Long id;
    private String nombre, apellido, email;
    private static Long total = 0L;

    public Customer(Customer customer) {
        this.id = customer.getId();
        this.nombre = customer.getNombre();
        this.apellido = customer.getApellido();
        this.email = customer.getEmail();
    }

    public Customer(String nombre, String apellido, String email) {
        total++;
        this.id = total;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }


    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return id + "\t" + nombre + "\t" + apellido + "\t" + email;
    }
}