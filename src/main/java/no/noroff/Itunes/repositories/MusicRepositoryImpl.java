package no.noroff.Itunes.repositories;

import no.noroff.Itunes.model.*;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class MusicRepositoryImpl implements MusicRepository {

    private String URL = ConnectionHelper.CONNECTION_URL;
    private Connection conn = null;

    @Override
    public ArrayList<Genre> getRandomGenres(int genresAmount) {
        ArrayList<Genre> genres = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT Genreid, Name FROM genre ORDER BY RANDOM() LIMIT ?;");
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

    @Override
    public ArrayList<Artist> getRandomArtist(int artistAmount) {
        ArrayList<Artist> artists = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT Artistid, Name FROM artist ORDER BY RANDOM() LIMIT ?;");
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

    @Override
    public ArrayList<Track> getRandomTracks(int tracksAmount) {
        ArrayList<Track> tracks = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT Trackid, Name FROM track ORDER BY RANDOM() LIMIT ?;");
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

    public ArrayList<Track> getTrackByName(String name) {
        ArrayList<Track> tracks = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT track.Trackid, track.Name, artist.Artistid, artist.Name, album.Albumid, album.Title, genre.Genreid, genre.Name from track inner join album on track.AlbumId = album.AlbumId inner join genre on track.genreId = genre.genreId inner join artist on album.ArtistId = artist.ArtistId where track.Name LIKE ?;");
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tracks.add(new Track(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        new Artist(resultSet.getInt(3), resultSet.getString(4)),
                        new Album(resultSet.getInt(5), resultSet.getString(6)),
                        new Genre(resultSet.getInt(7), resultSet.getString(8))
                    )
                );
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

        System.out.println(tracks);
        return tracks;
    }
}
