package no.noroff.Itunes.model;

/**
 * Model class for CustomerSpender
 * Contains the needed fields to cover a CustomerSpender, here:
 * Customer and Spending.
 */
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
