package no.noroff.Itunes.controllers;

import no.noroff.Itunes.model.Customer;
import no.noroff.Itunes.repositories.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
    public String getCustomerPage(@RequestParam int limit, @RequestParam int offset) {
        return "Customers " + limit + " " + offset;
    }

    @PostMapping("/api/customers")
    public void addCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
    }

    @PutMapping("/api/customers/{id}")
    public boolean changeCustomer(@PathVariable int id, @RequestBody Customer customer) {
        return customerRepository.changeCustomer(id, customer);
    }

    @GetMapping(value = "/api/customers", params = "country")
    public String getCustomersByCountry(@RequestParam String country) {
        return "Many customers from " + country + " here!";
    }

    @GetMapping(value = "/api/customers/highSpenders")
    public String getHighSpenders() {
        return "Many customers!";
    }

    @GetMapping("/api/customers/{id}/popularGenre")
    public String getPopularGenre(@PathVariable int id) {
        return "Customer " + id;
    }
}
