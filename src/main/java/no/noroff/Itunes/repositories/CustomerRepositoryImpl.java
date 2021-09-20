package no.noroff.Itunes.repositories;

import no.noroff.Itunes.model.Customer;
import no.noroff.Itunes.model.Genre;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;


@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private String URL = ConnectionHelper.CONNECTION_URL;
    private Connection conn = null;

    @Override
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("select CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email from customer;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customers.add(new Customer(
                        resultSet.getInt("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getInt("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email")
                ));
            }
        } catch (SQLException error) {
            System.out.println(error);
        } finally {
            try {
                conn.close();
            }
            catch (Exception error){
                System.out.println(error);
            }
        }

        return customers;
    }

    @Override
    public Customer getCustomerByID() {
        return null;
    }

    @Override
    public Customer getCustomerByName() {
        return null;
    }

    @Override
    public ArrayList<Customer> getCustomerPage(int limit, int offset) {
        return null;
    }

    @Override
    public void addCustomer(Customer customer) {

    }

    @Override
    public void changeCustomer(int id, int put) {

    }

    @Override
    public ArrayList<Customer> getCustomersFromCountry() {
        return null;
    }

    @Override
    public ArrayList<Customer> getHighSpenders() {
        return null;
    }

    @Override
    public ArrayList<Genre> getPopularGenre(int customerID) {
        return null;
    }
}
