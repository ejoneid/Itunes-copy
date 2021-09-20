package no.noroff.Itunes.repositories;

import no.noroff.Itunes.model.Customer;
import no.noroff.Itunes.model.Genre;

import java.util.ArrayList;
import java.util.HashMap;

public interface CustomerRepository {
    ArrayList<Customer> getAllCustomers();
    Customer getCustomerByID();
    Customer getCustomerByName();
    ArrayList<Customer> getCustomerPage(int limit, int offset);
    boolean addCustomer(Customer customer);
    void changeCustomer(int id, int put);
    HashMap<String, Integer> getCustomerCountFromCountry();
    ArrayList<Customer> getHighSpenders();
    ArrayList<Genre> getPopularGenre(int customerID);
}
