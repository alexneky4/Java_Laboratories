package uaic.info.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Album extends Entity{

    private int releaseYear;
    private Artist artist;
    private List<Genre> genreList = new ArrayList<>();

    public Album(int id, int releaseYear, String title, Artist artist) {
        super(id,title);
        this.releaseYear = releaseYear;
        this.artist = artist;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public void addGenre(Genre genre)
    {
        genreList.add(genre);
    }

    @Override
    public String toString() {
        return "Album{" +
                "releaseYear=" + releaseYear +
                ", artist=" + artist +
                ", genreList=" + genreList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Album album = (Album) o;
        return releaseYear == album.releaseYear && Objects.equals(artist, album.artist) && Objects.equals(genreList, album.genreList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), releaseYear, artist, genreList);
    }
}
