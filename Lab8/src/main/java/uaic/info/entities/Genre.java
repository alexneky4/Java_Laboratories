package uaic.info.entities;

public class Genre extends Entity{


    public Genre(int id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
