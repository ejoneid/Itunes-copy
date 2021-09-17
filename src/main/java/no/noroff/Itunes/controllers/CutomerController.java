package no.noroff.Itunes.controllers;

import no.noroff.Itunes.model.Customer;
import org.springframework.web.bind.annotation.*;

@RestController
public class CutomerController {

    @GetMapping("/api/customers/")
    public String getCustomers() {
        return "Many customers here!";
    }

    @GetMapping("/api/customers/{id}")
    public String getCustomerByID(@PathVariable int id) {
        return "Customer " + id;
    }

    @GetMapping(value = "/api/customers/", params = "name")
    public String getCustomerByName(@RequestParam String name) {
        return "Customer " + name;
    }

    @GetMapping(value = "/api/customers/", params = {"limit", "offset"})
    public String getCustomerPage(@RequestParam int limit, @RequestParam int offset) {
        return "Customers " + limit + " " + offset;
    }

    @PostMapping("/api/customers")
    public void addCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
    }
}
