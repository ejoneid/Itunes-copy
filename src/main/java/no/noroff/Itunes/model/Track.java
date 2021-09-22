package no.noroff.Itunes.model;

/**
 * Model class for Tracj
 * Contains the needed fields to cover a Track, here:
 * ID, Name, Artist, Album and Genre.
 */
public class Track {
    private int id;
    private String name;
    private Artist artist;
    private Album album;
    private Genre genre;

    public Track(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Track(int id, String name, Artist artist, Album album, Genre genre) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist=" + artist +
                ", album=" + album +
                ", genre=" + genre +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
