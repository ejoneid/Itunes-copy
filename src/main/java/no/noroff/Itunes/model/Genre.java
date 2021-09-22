package no.noroff.Itunes.model;

/**
 * Model class for Genre
 * Contains the needed fields to cover a Genre, here:
 * ID and Name.
 */
public class Genre {
    private int id;
    private String name;

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
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
