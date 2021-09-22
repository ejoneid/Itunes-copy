package no.noroff.Itunes.repositories;

import no.noroff.Itunes.model.Customer;
import no.noroff.Itunes.model.CustomerCountry;
import no.noroff.Itunes.model.CustomerSpender;
import no.noroff.Itunes.model.Genre;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

/**
 * Customer Repository Implementation for all the needed methods to serve the endpoints in the CustomerController.
 */
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private String URL = ConnectionHelper.CONNECTION_URL;
    private Connection conn = null;

    /**
     * Tries to execute the prepared SQL statement that gets all the customers from the database, creates customer
     * objects and adds them to an ArrayList.
     *
     * @return ArrayList of Customer
     */
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
            } catch (Exception error) {
                System.out.println(error);
            }
        }
        return customers;
    }

    /**
     * Tries to execute the prepared SQL statement that gets the specific customer by ID.
     * @param customerId = ID to look up in the database.
     * @return A single customer with the corresponding ID.
     */
    @Override
    public Customer getCustomerByID(int customerId) {
        Customer customer = null;

        try {
            conn = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement =
                    conn.prepareStatement("select CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email from customer where CustomerId = ?");
            preparedStatement.setInt(1, customerId);

            ResultSet resultSet = preparedStatement.executeQuery();

            customer = new Customer(
                    resultSet.getInt("CustomerId"),
                    resultSet.getString("FirstName"),
                    resultSet.getString("LastName"),
                    resultSet.getString("Country"),
                    resultSet.getInt("PostalCode"),
                    resultSet.getString("Phone"),
                    resultSet.getString("Email")
            );

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception error) {
                System.out.println(error);
            }
        }
        return customer;
    }

    /**
     * Tries to get all customers with a name matching the parameter name.
     *
     * @param name = A string to use for looking for a customer with a name like the parameter.
     * @return All customers with a corresponding name, first or last name.
     */
    @Override
    public ArrayList<Customer> getCustomerByName(String name) {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM customer WHERE FirstName LIKE ? OR LastName LIKE ?");
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setString(2, "%" + name + "%");

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
            } catch (Exception error) {
                System.out.println(error);
            }
        }
        return customers;
    }

    /**
     * Tries to get all customers of a given interval, starting at the offset given and gets the number corresponding to
     * the limit-parameter.
     *
     * @param limit = Number of customers to retrieve.
     * @param offset = The starting index.
     * @return ArrayList of Customers.
     */
    @Override
    public ArrayList<Customer> getCustomerPage(int limit, int offset) {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("select CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email from customer LIMIT ? OFFSET ?;");
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
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
            } catch (Exception error) {
                System.out.println(error);
            }
        }
        return customers;
    }

    /**
     * Tries to add a new Customer to the database.
     *
     * @param customer = The customer object to insert into the database.
     * @return boolean true or false.
     */
    @Override
    public boolean addCustomer(Customer customer) {
        boolean success = false;
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO customer (FirstName, LastName, Country, PostalCode, Phone, Email) VALUES (?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getCountry());
            preparedStatement.setInt(4, customer.getPostalCode());
            preparedStatement.setString(5, customer.getPhone());
            preparedStatement.setString(6, customer.getEmail());

            int result = preparedStatement.executeUpdate();
            success = (result != 0);

        } catch (SQLException error) {
            System.out.println(error);
        } finally {
            try {
                conn.close();
            } catch (Exception error) {
                System.out.println(error);
            }
        }

        return success;
    }

    /**
     * Tries to update a specific customer, targeted by their ID.
     *
     * @param id = ID of customer to update.
     * @param customer = Customer object containing the updated values to insert into the database.
     * @return boolean true or false.
     */
    @Override
    public boolean changeCustomer(int id, Customer customer) {
        Boolean success = false;
        try {
            conn = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement =
                    conn.prepareStatement("UPDATE customer SET CustomerId = ?, FirstName = ?, LastName = ?, Country = ?, PostalCode = ?, Phone = ?, Email = ? WHERE CustomerId = ?;");
            preparedStatement.setInt(1, customer.getCustomerID());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getCountry());
            preparedStatement.setInt(5, customer.getPostalCode());
            preparedStatement.setString(6, customer.getPhone());
            preparedStatement.setString(7, customer.getEmail());
            preparedStatement.setInt(8, id);

            // Execute Query
            int result = preparedStatement.executeUpdate();
            success = (result != 0);


        } catch (Exception error) {
            System.out.println(error);
        } finally {
            try {
                conn.close();
            } catch (Exception error) {
                System.out.println(error);
            }
        }
        return success;
    }


    /**
     * Gets all customers grouped by their country, ordered by the country and the country with the most customers first.
     *
     * @return ArrayList of CustomerCountry.
     */
    @Override
    public ArrayList<CustomerCountry> getCustomerCountFromCountry() {
        ArrayList<CustomerCountry> result = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT Country, COUNT (*) AS Number FROM customer GROUP BY Country ORDER BY Number DESC;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(new CustomerCountry(resultSet.getString("Country"),
                        resultSet.getInt("Number")));
            }
        } catch (SQLException error) {
            System.out.println(error);
        } finally {
            try {
                conn.close();
            } catch (Exception error) {
                System.out.println(error);
            }
        }

        return result;
    }

    /**
     * Tries to get all the customers also found in the invoice table, with their total spending amount and the customer
     * with the highest spending coming first.
     *
     * @return ArrayList of CustomerSpender
     */
    @Override
    public ArrayList<CustomerSpender> getHighSpenders() {
        ArrayList<CustomerSpender> customerSpenders = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT customer.CustomerId, customer.FirstName, customer.LastName, customer.Country, customer.PostalCode, customer.Phone, customer.Email, sum(invoice.Total) as sumTotal from Customer inner join invoice on customer.CustomerId = invoice.CustomerId GROUP BY customer.CustomerId ORDER BY sumTotal DESC;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customerSpenders.add(new CustomerSpender(new Customer(
                        resultSet.getInt("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getInt("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email")),
                        resultSet.getDouble("sumTotal")
                ));
            }
        } catch (SQLException error) {
            System.out.println(error);
        } finally {
            try {
                conn.close();
            } catch (Exception error) {
                System.out.println(error);
            }
        }

        return customerSpenders;
    }

    /**
     * Tries to get the most popular genre for a specific customer, decided by the genre found most often in their invoices.
     *
     * @param id = ID of customer
     * @return ArrauList of Genre.
     */
    @Override
    public ArrayList<Genre> getPopularGenre(int id) {

        ArrayList<Genre> genres = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT GenreIds, GenreName, MAX(GenreCount) FROM (SELECT customer.CustomerId as ID, genre.GenreId as GenreIds, genre.Name as GenreName, COUNT(genre.GenreId) as GenreCount FROM customer inner join invoice on ID = invoice.CustomerId inner join invoiceLine on invoice.InvoiceId = invoiceLine.InvoiceId inner join track on invoiceLine.TrackId = track.TrackId inner join genre on track.GenreId = genre.GenreId where ID = ? GROUP BY genre.GenreId) a GROUP BY ID;");
            preparedStatement.setInt(1, id);


            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                genres.add(new Genre(
                        resultSet.getInt("GenreIds"),
                        resultSet.getString("GenreName")
                ));
            }
        } catch (SQLException error) {
            System.out.println(error);
        } finally {
            try {
                conn.close();
            } catch (Exception error) {
                System.out.println(error);
            }
        }
        return genres;
    }

}

