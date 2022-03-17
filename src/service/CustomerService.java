package service;

import model.Customer;

import java.util.*;

/**
 * Class which deals with services of customers
 * @author Ratan Dheeraj Kadirikota
 */
public class CustomerService {
    static Map<String, Customer> customers = new HashMap<>();

    public static void addCustomer(String email, String firstName, String lastName){
        Customer customer = new Customer(email,firstName,lastName);
        customers.put(email,customer);
    }

    public static Customer getCustomer(String customerEmail){
        return customers.get(customerEmail);
    }

    public static Collection<Customer> getAllCustomers(){
        return customers.values();
    }
}
