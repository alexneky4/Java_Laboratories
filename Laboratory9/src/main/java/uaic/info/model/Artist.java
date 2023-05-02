package uaic.info.model;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "ARTISTS")
@SequenceGenerator(name="artist_seq", sequenceName = "ARTIST_SEQUENCE", allocationSize = 1)
//@NamedQueries({
//        @NamedQuery(name = "Artist.findAll",
//                query = "select e from Artist e order by e.name"),
//        @NamedQuery(name = "Artist.findByName",
//                query = "select e from Artist e where e.name = ?1")
//})
public class Artist implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artist_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    public Artist(String name) {
        this.name = name;
    }

    public Artist() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
