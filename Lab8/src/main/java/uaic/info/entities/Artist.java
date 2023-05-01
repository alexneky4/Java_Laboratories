package uaic.info.entities;

public class Artist extends Entity{


    public Artist(int id,String name) {
        super(id,name);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
