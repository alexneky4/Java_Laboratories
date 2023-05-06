package uaic.info.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "GENRES")
@SequenceGenerator(name="genre_seq", sequenceName = "GENRE_SEQUENCE", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = "Genre.findAll",
                query = "select e from Genre e order by e.name"),
        @NamedQuery(name = "Genre.findByName",
                query = "select  e from Genre e where e.name = :name")
})
public class Genre implements Serializable,AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public Genre() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
