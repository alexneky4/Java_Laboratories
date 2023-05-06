package uaic.info.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "albums")
@SequenceGenerator(name="album_seq", sequenceName = "ALBUM_SEQUENCE", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = "Album.findAll",
        query = "select e from Album e order by e.name"),
        @NamedQuery(name = "Album.findByName",
        query = "select e from Album e where e.name = :name"),
        @NamedQuery(name = "Album.findByArtist",
        query = "select e from Album e where e.artist = :artist"),
        @NamedQuery(name = "Album.findByNameAndArtist",
        query = "select e from Album e where e.name = :name and e.artist = :artist")
})
public class Album implements Serializable,AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String name;

    @Column(name = "releaseYear")
    private LocalDate releaseYear;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToMany
    @JoinTable(
            name = "album_genres",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    public Album(String name, LocalDate releaseYear, Artist artist, List<Genre> genres) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.artist = artist;
        this.genres = genres;
    }

    public Album()
    {}

    public Album(String name, LocalDate releaseYear, Artist artist) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(LocalDate releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void addGenre(Genre genre)
    {
        genres.add(genre);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                ", artist=" + artist +
                ", genres=" + genres +
                '}';
    }
}
