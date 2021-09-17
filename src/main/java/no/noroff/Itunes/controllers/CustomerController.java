package no.noroff.Itunes.controllers;

import no.noroff.Itunes.model.Customer;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @GetMapping("/api/customers")
    public String getCustomers() {
        return "Many customers here!";
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
    public String getCustomerPage(@RequestParam int limit, @RequestParam int offset) {
        return "Customers " + limit + " " + offset;
    }

    @PostMapping("/api/customers")
    public void addCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
    }

    @PatchMapping("/api/customers/{id}")
    public void changeCustomer(@PathVariable int id, @RequestBody String patch) {
        System.out.println(id + " " + patch);
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
