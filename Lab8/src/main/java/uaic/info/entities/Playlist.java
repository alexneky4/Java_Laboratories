package uaic.info.entities;

import java.util.ArrayList;

public class Playlist extends Entity{

    private int creationTime;
    private ArrayList<Album> albums;


    public Playlist(int id, String name,int creationTime) {
        super(id, name);
        this.creationTime = creationTime;
        albums = new ArrayList<>();
    }

    public Playlist(int id, String name,int creationTime,ArrayList<Album> albums) {
        super(id, name);
        this.creationTime = creationTime;
        this.albums = albums;
    }

    public void addAlbum(Album album)
    {
        albums.add(album);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "name=" + name +
                ", albums=" + albums +
                '}';
    }
}
