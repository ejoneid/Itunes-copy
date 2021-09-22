package no.noroff.Itunes.model;

/**
 * Model class for CustomerCountry
 * Contains the needed fields to cover a CustomerCountry, here:
 * Country and Count.
 */
public class CustomerCountry {
    String country;
    int count;

    public CustomerCountry(String country, int count) {
        this.country = country;
        this.count = count;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
