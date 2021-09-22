package no.noroff.Itunes.repositories;

import no.noroff.Itunes.model.*;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

/**
 * Music Repository Implementation for all the needed methods to serve the endpoints in MusicController.
 */
@Repository
public class MusicRepositoryImpl implements MusicRepository {

    private String URL = ConnectionHelper.CONNECTION_URL;
    private Connection conn = null;

    /**
     * Tries to retrieve a given amount random genres found in the genre table in the database.
     *
     * @param genresAmount = Amount of genres to get.
     * @return ArrayList of Genres.
     */
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

    /**
     * Tries to retrieve a given amount random artists found in the artist table in the database
     *
     * @param artistAmount = Amount of artists to get.
     * @return ArrayList of Artists.
     */
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

    /**
     * Tries to retrieve a given amount of random Tracks found in the track table in the database.
     *
     * @param tracksAmount = Amount of tracks to get
     * @return ArrayList of Tracks.
     */
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

    /**
     * Tries to get all tracks with a name corresponding to the parameter name, including the genre, artist and album.
     *
     * @param name = Name used to find the matching tracks.
     * @return ArrayList of Tracks
     */
    @Override
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

        return tracks;
    }
}
