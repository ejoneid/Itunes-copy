package no.noroff.Itunes.repositories;

import no.noroff.Itunes.model.Artist;
import no.noroff.Itunes.model.Genre;
import no.noroff.Itunes.model.Track;

import java.util.ArrayList;

/**
 * Interface for a MusicRepository
 */
public interface MusicRepository {
    ArrayList<Genre> getRandomGenres(int genresAmount);
    ArrayList<Artist> getRandomArtist(int artistAmount);
    ArrayList<Track> getRandomTracks(int tracksAmount);
    ArrayList<Track> getTrackByName(String name);
}
