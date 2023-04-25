package uaic.info.main;

import uaic.info.daos.AlbumDAO;
import uaic.info.daos.ArtistDAO;
import uaic.info.daos.GenreDAO;
import uaic.info.database.Database;

import java.sql.SQLException;

public class Main {
    public static void main(String args[]) {
        try {
            var artists = new ArtistDAO();
            artists.create("Pink Floyd");
            artists.create("Michael Jackson");
            var genres = new GenreDAO();
            genres.create("Rock");
            genres.create("Funk");
            genres.create("Soul");
            genres.create("Pop");
            Database.getConnection().commit();
            var albums = new AlbumDAO();
            albums.create(1979, "The Wall", "Pink Floyd", "Rock");

            albums.create(1982, "Thriller", "Michael Jackson", "Funk,Soul,Pop");
            Database.getConnection().commit();
            Database.printElementsTable("albums");
            Database.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                Database.getConnection().rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
