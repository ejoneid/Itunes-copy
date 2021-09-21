package no.noroff.Itunes.controllers;

import no.noroff.Itunes.model.Customer;
import no.noroff.Itunes.model.Genre;
import no.noroff.Itunes.repositories.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @GetMapping("/api/customers")
    public ArrayList<Customer> getCustomers() {
        return customerRepository.getAllCustomers();
    }

    @GetMapping("/api/customers/{id}")
    public Customer getCustomerByID(@PathVariable int id) {
        return customerRepository.getCustomerByID(id);
    }

    @GetMapping(value = "/api/customers", params = "name")
    public ArrayList<Customer> getCustomerByName(@RequestParam String name) {
        return customerRepository.getCustomerByName(name);
    }

    @GetMapping(value = "/api/customers", params = {"limit", "offset"})
    public ArrayList<Customer> getCustomerPage(@RequestParam int limit, @RequestParam int offset) {
        return customerRepository.getCustomerPage(limit, offset);
    }

    @PostMapping("/api/customers")
    public boolean addCustomer(@RequestBody Customer customer) {
        return customerRepository.addCustomer(customer);
    }

    @PutMapping("/api/customers/{id}")
    public boolean changeCustomer(@PathVariable int id, @RequestBody Customer customer) {
        return customerRepository.changeCustomer(id, customer);
    }

    @GetMapping(value = "/api/customers/countries")
    public HashMap<String, Integer> getCustomerCountByCountry() {
        return customerRepository.getCustomerCountFromCountry();
    }

    @GetMapping(value = "/api/customers/highSpenders")
    public ArrayList<Customer> getHighSpenders() {
        return customerRepository.getHighSpenders();
    }

    @GetMapping("/api/customers/{id}/popularGenre")
    public ArrayList<Genre> getPopularGenre(@PathVariable int id) {
        return customerRepository.getPopularGenre(id);
    }
}
