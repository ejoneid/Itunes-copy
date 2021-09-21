package no.noroff.Itunes.repositories;

import no.noroff.Itunes.model.*;

import java.sql.*;
import java.util.ArrayList;

public class MusicRepositoryImpl {

    private String URL = ConnectionHelper.CONNECTION_URL;
    private Connection conn = null;

    public ArrayList<Genre> getRandomGenres(int genresAmount) {
        ArrayList<Genre> genres = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT Genreid, Name FROM genres ORDER BY RAND() LIMIT ?;");
            preparedStatement.setInt(1, genresAmount);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                genres.add(new Genre(resultSet.getInt("Genreid"), resultSet.getString("Name")));
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

        return genres;
    }

    public ArrayList<Artist> getRandomArtist(int artistAmount) {
        ArrayList<Artist> artists = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT Artistid, Name FROM artists ORDER BY RAND() LIMIT ?;");
            preparedStatement.setInt(1, artistAmount);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                artists.add(new Artist(resultSet.getInt("Artistid"), resultSet.getString("Name")));
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

        return artists;
    }

    public ArrayList<Track> getRandomTracks(int tracksAmount) {
        ArrayList<Track> tracks = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT Trackid, Name FROM tracks ORDER BY RAND() LIMIT ?;");
            preparedStatement.setInt(1, tracksAmount);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tracks.add(new Track(resultSet.getInt("Trackid"), resultSet.getString("Name")));
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

        return tracks;
    }
}
