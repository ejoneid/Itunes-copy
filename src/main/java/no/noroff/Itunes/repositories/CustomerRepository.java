package no.noroff.Itunes.repositories;

import no.noroff.Itunes.model.Customer;
import no.noroff.Itunes.model.CustomerCountry;
import no.noroff.Itunes.model.CustomerSpender;
import no.noroff.Itunes.model.Genre;

import java.util.ArrayList;
import java.util.HashMap;

public interface CustomerRepository {
    ArrayList<Customer> getAllCustomers();
    Customer getCustomerByID(int customerId);
    ArrayList<Customer> getCustomerByName(String name);
    ArrayList<Customer> getCustomerPage(int limit, int offset);
    boolean addCustomer(Customer customer);
    boolean changeCustomer(int id, Customer customer);
    ArrayList<CustomerCountry> getCustomerCountFromCountry();
    ArrayList<CustomerSpender> getHighSpenders();
    ArrayList<Genre> getPopularGenre(int customerID);
}
