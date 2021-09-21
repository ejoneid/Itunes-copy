package no.noroff.Itunes.repositories;

import no.noroff.Itunes.model.Customer;
import no.noroff.Itunes.model.Genre;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


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
            } catch (Exception error) {
                System.out.println(error);
            }
        }

        return customers;
    }

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
            }
            catch (Exception error){
                System.out.println(error);
            }
        }

        return customers;
    }

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
            }
            catch (Exception error){
                System.out.println(error);
            }
        }

        return success;
    }

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


    @Override
    public HashMap<String, Integer> getCustomerCountFromCountry() {
        HashMap<String, Integer> result = new HashMap<>();
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT Country, COUNT (*) AS Number FROM customer GROUP BY Country ORDER BY Number DESC;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.put(resultSet.getString("Country"), resultSet.getInt("Number"));
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

        return result;
    }

    @Override
    public ArrayList<Customer> getHighSpenders() {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT customer.CustomerId, customer.FirstName, customer.LastName, customer.Country, customer.PostalCode, customer.Phone, customer.Email, sum(invoice.Total) as sumTotal from Customer inner join invoice on customer.CustomerId = invoice.CustomerId GROUP BY customer.CustomerId ORDER BY sumTotal DESC;");
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

