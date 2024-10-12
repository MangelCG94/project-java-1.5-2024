package com.practice;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static final CustomerRepository repository = new CustomerRepository();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\\n");

        while (true) {
            System.out.println();
            System.out.println("---------------------------------");
            System.out.println("  1. Crear");
            System.out.println("  2. Listar");
            System.out.println("  3. Modificar");
            System.out.println("  4. Buscar");
            System.out.println("  5. Borrar");
            System.out.println("  6. Datos de test");
            System.out.println("  0. Salir");
            System.out.println("---------------------------------");

            System.out.println("Elige una opción numérica");
            int opcion;

            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                opcion = -1;
            }
            System.out.println();

            switch (opcion) {
                case 1:
                    create(scanner);
                    break;
                case 2:
                    list();
                    break;
                case 3:
                    update(scanner);
                    break;
                case 4:
                    find(scanner);
                    break;
                case 5:
                    delete(scanner);
                    break;
                case 6:
                    initialize();
                    break;
                case 0:
                    System.out.println("Cerrando programa...");
                    break;
                default:
                    System.out.println("La opción no es correcta. Elige una de las opciones dadas.");
                    break;
            }
            System.out.println("Pulsa una tecla para continuar");
            scanner.next();

            if (opcion == 0) {
                scanner.close();
                System.out.println("Adios");
                break;
            }
        }
    }

    static void create(Scanner scanner) {
        Customer customer = customerTemplate(scanner);

        if (customer == null)
            return;

        repository.addCustomer(customer);
    }

    static void list() {
        Map<Long,Customer> customers = repository.findAll();

        if (!customers.isEmpty()) {
            System.out.println("id   nombre    apellido     email");
            for (Map.Entry<Long, Customer> customer : customers.entrySet())
                System.out.println(customer.toString());
        } else
            System.out.println("No hay clientes");
    }

    static void update(Scanner scanner) {
        try {
            System.out.println("Escribe la id del cliente (-1 para volver)");
            long id = scanner.nextLong();

            if (id == -1)
                return;

            if (repository.findCustomerById(id) == null) {
                System.out.println("El cliente no existe");
                return;
            }

            Customer customer = customerTemplate(scanner);
            if (customer == null)
                return;

            repository.updateCustomer(id, customer);
        } catch (InputMismatchException e) {
            System.out.println("Error por meter un valor no valido");
        }
    }

    static void find(Scanner scanner) {
        System.out.println("Escribe la id del cliente (-1 para volver)");
        try {
            long id = scanner.nextLong();

            Customer customer = repository.findCustomerById(id);

            if (customer == null) {
                System.out.println("El cliente no existe");
                return;
            }

            System.out.println("id   nombre    apellido     email");
            System.out.println(customer);
        } catch (InputMismatchException e) {
            System.out.println("Error por meter un valor no valido");
        }
    }

    static void delete(Scanner scanner) {
        System.out.println("Escribe la id del cliente (-1 para volver)");
        try {
            long id = scanner.nextLong();
            if (id == -1)
                return;
            repository.removeCustomer(id);
        } catch (InputMismatchException e) {
            System.out.println("Error por meter un valor no valido");
        }
    }

    static void initialize() {
        repository.createTestData();
    }




    static Customer customerTemplate(Scanner scanner) {
        try {
            System.out.println("Escribe el nombre (-1 para volver al menú)");
            String nombre = scanner.next();

            if (nombre.equals("-1")) {
                return null;
            }

            System.out.println("Escribe el apellido");
            String apellido = scanner.next();
            System.out.println("Escribe el email");
            String email = scanner.next();

            return new Customer(nombre, apellido, email);
        } catch (InputMismatchException e) {
            System.out.println("Error por meter un valor no valido");
            return null;
        }
    }
}
