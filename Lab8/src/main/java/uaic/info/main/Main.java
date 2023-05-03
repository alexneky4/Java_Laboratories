package uaic.info.main;

import uaic.info.algorithm.Algorithm;
import uaic.info.daos.AlbumDAO;
import uaic.info.daos.ArtistDAO;
import uaic.info.daos.GenreDAO;
import uaic.info.database.Database;
import uaic.info.database.ImportTool;
import uaic.info.entities.Album;
import uaic.info.entities.Artist;
import uaic.info.entities.Genre;
import uaic.info.entities.Playlist;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        try {
//            var artists = new ArtistDAO();
//            Artist artist1 = artists.create("Pink Floyd");
//            Artist artist2 = artists.create("Michael Jackson");
//            System.out.println(artist1);
//            System.out.println(artist2);
//            var genres = new GenreDAO();
//            Genre genre1 = genres.create("Rock");
//            Genre genre2 = genres.create("Funk");
//            Genre genre3 = genres.create("Soul");
//            Genre genre4 = genres.create("Pop");
//            System.out.println(genre1);
//            System.out.println(genre2);
//            System.out.println(genre3);
//            System.out.println(genre4);
//            Database.getConnection().commit();
//            var albums = new AlbumDAO();
//            Album album1 = albums.create(1979, "The Wall", "Pink Floyd", "Rock");
//
//            Album album2 = albums.create(1982, "Thriller", "Michael Jackson", "Funk,Soul,Pop");
//            System.out.println(album1);
//            System.out.println(album2);

//            ImportTool importTool = new ImportTool();
//            importTool.importAlbumDatabase("C:\\Users\\ADMIN\\Desktop\\albumlist.csv");
//            Database.getConnection().commit();
//            Database.printElementsTable("albums");
//            Database.closeConnection();
            List<Playlist> maximalPlayLists = Algorithm.maximalPlaylists(new AlbumDAO().findAll());
            for (Playlist playlist : maximalPlayLists)
                System.out.println(playlist);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                Database.getConnection().rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        }
    }
}
