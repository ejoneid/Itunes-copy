package no.noroff.Itunes.controllers;

import no.noroff.Itunes.model.Customer;
import no.noroff.Itunes.model.CustomerCountry;
import no.noroff.Itunes.model.CustomerSpender;
import no.noroff.Itunes.model.Genre;
import no.noroff.Itunes.repositories.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * REST Controller for the Customer API.
 * Covers GET, POST and PUT.
* */
@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * GETs all customers.
     * @return ArrayList of Customers.
     */
    @GetMapping("/api/customers")
    public ArrayList<Customer> getCustomers() {
        return customerRepository.getAllCustomers();
    }

    /**
     * GET specific customer.
     * @param id = ID of customer.
     * @return a single customer with the given ID.
     */
    @GetMapping("/api/customers/{id}")
    public Customer getCustomerByID(@PathVariable int id) {
        return customerRepository.getCustomerByID(id);
    }

    /**
     * GET all customers with a name containing the param name.
     * @param name = a string used to filter names.
     * @return ArrayList of Customers with a name that equals or includes the param name.
     */
    @GetMapping(value = "/api/customers", params = "name")
    public ArrayList<Customer> getCustomerByName(@RequestParam String name) {
        return customerRepository.getCustomerByName(name);
    }

    /**
     * GET a limited set of customers.
     * @param limit = number of customers to return.
     * @param offset = start index of where to get the limit amount.
     * @return ArrayList of Customer containing the limit amount of customers.
     */
    @GetMapping(value = "/api/customers", params = {"limit", "offset"})
    public ArrayList<Customer> getCustomerPage(@RequestParam int limit, @RequestParam int offset) {
        return customerRepository.getCustomerPage(limit, offset);
    }

    /**
     * POST creates a new Customer.
     * @param customer = a Customer object corresponding to the Customer model.
     * @return boolean true or false.
     */
    @PostMapping("/api/customers")
    public boolean addCustomer(@RequestBody Customer customer) {
        return customerRepository.addCustomer(customer);
    }

    /**
     * PUT updates a selected customer.
     * @param id = ID of customer to update.
     * @param customer = the new customer object to replace the old.
     * @return boolean true or false.
     */
    @PutMapping("/api/customers/{id}")
    public boolean changeCustomer(@PathVariable int id, @RequestBody Customer customer) {
        return customerRepository.changeCustomer(id, customer);
    }

    /**
     * GET all customers, grouped by country and ordered by the country with the most customers first.
     * @return ArrayList of CustomerCountry.
     */
    @GetMapping(value = "/api/customers/countries")
    public ArrayList<CustomerCountry> getCustomerCountByCountry() {
        return customerRepository.getCustomerCountFromCountry();
    }

    /**
     * GET all customers with one or more invoices, grouped by their invoice total cost with the highest spending
     * customer first.
     * @return ArrayList of CustomerSpender.
     */
    @GetMapping(value = "/api/customers/highSpenders")
    public ArrayList<CustomerSpender> getHighSpenders() {
        return customerRepository.getHighSpenders();
    }

    /**
     * GET a single customers' most popular genre(s).
     * @param id = ID of customer.
     * @return ArrayList of Genre.
     */
    @GetMapping("/api/customers/{id}/popular/genre")
    public ArrayList<Genre> getPopularGenre(@PathVariable int id) {
        return customerRepository.getPopularGenre(id);
    }
}
