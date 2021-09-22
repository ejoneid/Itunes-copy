package no.noroff.Itunes.model;

/**
 * Model class for Album
 * Contains the needed fields to cover an Album, here:
 * ID and Title.
 */
public class Album {
    private int id;
    private String title;

    public Album(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
