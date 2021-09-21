package no.noroff.Itunes.model;

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
}
