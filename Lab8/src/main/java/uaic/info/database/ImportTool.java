package uaic.info.database;

import uaic.info.daos.AlbumDAO;
import uaic.info.daos.ArtistDAO;
import uaic.info.daos.GenreDAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

public class ImportTool {

    private ArtistDAO artistDAO = new ArtistDAO();
    private GenreDAO genreDAO = new GenreDAO();
    private AlbumDAO albumDAO = new AlbumDAO();

    public void importAlbumDatabase(String path)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                String year = fields[1];
                String title = fields[2];
                String artist = fields[3];
                StringBuilder genres = null;
                String[] subgenres = fields[4].replace("/",",").replace("\"","").split(",");
                for(String subgenre : subgenres)
                    if(genres == null)
                        genres = new StringBuilder(subgenre.trim());
                    else
                        genres = genres.append(",").append(subgenre.trim());
                subgenres = fields[5].replace("/",",").replace("\"","").split(",");
                for(String subgenre : subgenres)
                    genres.append(",").append(subgenre.trim());
                albumDAO.create(Integer.parseInt(year),title,artist,genres.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
