package no.noroff.Itunes.controllers;

import no.noroff.Itunes.model.Customer;
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
    public String getCustomerByID(@PathVariable int id) {
        return "Customer " + id;
    }

    @GetMapping(value = "/api/customers", params = "name")
    public String getCustomerByName(@RequestParam String name) {
        return "Customer " + name;
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
    public void changeCustomer(@PathVariable int id, @RequestBody String put) {
        System.out.println(id + " " + put);
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
    public String getPopularGenre(@PathVariable int id) {
        return "Customer " + id;
    }
}
