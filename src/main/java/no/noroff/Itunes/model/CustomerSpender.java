package no.noroff.Itunes.model;

public class CustomerSpender {
    Customer customer;
    double spending;

    public CustomerSpender(Customer customer, double spending) {
        this.customer = customer;
        this.spending = spending;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getSpending() {
        return spending;
    }

    public void setSpending(double spending) {
        this.spending = spending;
    }
}
