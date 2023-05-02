package uaic.info.daos;

import uaic.info.database.Database;
import uaic.info.entities.Album;
import uaic.info.entities.Artist;
import uaic.info.entities.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {

    private GenreDAO genresDAO = new GenreDAO();
    private ArtistDAO artistsDAO = new ArtistDAO();
    public Album create(int releaseYear, String title, String artist, String genre) throws SQLException
    {
        Connection connection = Database.getConnection();
        String[] genres = genre.split("[,'\"]+");
        Album album = findByTitleAndArtist(title, artist);
        if(album != null)
            return null;
        artist = artist.trim();
        artistsDAO.create(artist);
        try(PreparedStatement pstmt = connection.prepareStatement("insert into albums(release_year,title,artist,genre) values (?,?,?,?)"))
        {
            pstmt.setInt(1,releaseYear);
            pstmt.setString(2, title);
            pstmt.setString(3, artist);
            pstmt.setString(4, genres[0]);
            pstmt.execute();
        }
        album = findByTitleAndArtist(title, artist);
        try(PreparedStatement pstmt1 = connection.prepareStatement("insert into album_genres values(?,?)");
            PreparedStatement pstmt2 = connection.prepareStatement("select * from album_genres where album_id = ? and genre_id = ?"))
        {
            pstmt2.setInt(1,album.getId());
            pstmt1.setInt(1,album.getId());
            for(int i = 0; i < genres.length; i++)
            {
                Genre genreAux = genresDAO.findByName(genres[i]);
                if(genreAux == null)
                    genreAux = genresDAO.create(genres[i]);
                pstmt1.setInt(2, genreAux.getId());
                pstmt2.setInt(2, genreAux.getId());
                ResultSet rs = pstmt2.executeQuery();
                if(rs.next())
                {
                    continue;
                }
                pstmt1.execute();
            }
        }

        return album;
    }


    public Album findByTitleAndArtist(String title,String artist) throws SQLException
    {
        Connection connection = Database.getConnection();
        Album album;
        try(PreparedStatement preparedStatement = connection.prepareStatement("select * from albums where title = ? and artist = ?"))
        {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, artist);
            ResultSet rs = preparedStatement.executeQuery();
            if(!rs.next())
                return null;
                 album = new Album(rs.getInt(1),rs.getInt(2),rs.getString(3),
                        artistsDAO.findByName(rs.getString(4)));
                try(Statement stmt2 = connection.createStatement();
                    ResultSet rs2 = stmt2.executeQuery("select genre_id from album_genres where album_id = " + album.getId()))
                {
                    while(rs2.next())
                    {
                        album.addGenre(genresDAO.findById(rs2.getInt(1)));
                    }
                }

        }

        return album;
    }
    public List<Album> findByTitle(String title) throws SQLException
    {
        Connection connection = Database.getConnection();
        List<Album> albums = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement("select * from albums where title = ?"))
        {
            preparedStatement.setString(1, title);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                if(albums == null)
                    albums = new ArrayList<>();
                Album album = new Album(rs.getInt(1),rs.getInt(2),rs.getString(3),
                        artistsDAO.findByName(rs.getString(4)));
                try(Statement stmt2 = connection.createStatement();
                    ResultSet rs2 = stmt2.executeQuery("select genre_id from album_genres where album_id = " + album.getId()))
                {
                    while(rs2.next())
                    {
                        album.addGenre(genresDAO.findById(rs2.getInt(1)));
                    }
                }
                albums.add(album);
            }
        }

        return albums;
    }

    public Album findById(int id) throws SQLException
    {
        Connection connection = Database.getConnection();
        Album album;
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from albums where id = " + id))
        {
                if(rs.next() == false)
                    return null;
                album = new Album(rs.getInt(1),rs.getInt(2),rs.getString(3),
                        artistsDAO.findByName(rs.getString(4)));
                try(Statement stmt2 = connection.createStatement();
                    ResultSet rs2 = stmt2.executeQuery("select genre_id from album_genres where album_id = " + album.getId()))
                {
                    while(rs2.next())
                    {
                        album.addGenre(genresDAO.findById(rs2.getInt(1)));
                    }
                }
        }

        return album;
    }

    public List<Artist> findArtistByAlbumTitle(String title) throws SQLException
    {
        Connection connection = Database.getConnection();
        List<Artist> artists = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement("select * from albums where title = ?"))
        {
            preparedStatement.setString(1, title);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                if(artists == null)
                    artists = new ArrayList<>();
                else
                    artists.add(artistsDAO.findByName(rs.getString(1)));
            }
        }

        return artists;
    }

    public List<Album> findAlbumsByArtist(String name) throws SQLException
    {

        Connection connection = Database.getConnection();
        List<Album> albums = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement("select title from albums where artist = ?"))
        {
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                if(albums == null)
                    albums = new ArrayList<>();
                Album album = new Album(rs.getInt(1),rs.getInt(2),rs.getString(3),
                       artistsDAO.findByName(rs.getString(4)));
                try(Statement stmt2 = connection.createStatement();
                    ResultSet rs2 = stmt2.executeQuery("select genre_id from album_genres where album_id = " + album.getId()))
                {
                    while(rs2.next())
                    {
                        album.addGenre(genresDAO.findById(rs2.getInt(1)));
                    }
                }
                albums.add(album);
            }
        }
        return albums;
    }

    public List<Album> findAll() throws SQLException
    {
        Connection connection = Database.getConnection();
        List<Album> albums = null;
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select id,release_year,title,artist,genre from albums"))
        {
            while(rs.next())
            {
                if(albums == null)
                    albums = new ArrayList<>();
                Artist artist = artistsDAO.findByName(rs.getString(4));
                List<Genre> genres = new ArrayList<>();
                genres.add(genresDAO.findByName(rs.getString(5)));
                try(PreparedStatement pstmt = connection.prepareStatement("select genre_id from album_genres where album_id = ?"))
                {
                    pstmt.setInt(1,rs.getInt(1));
                    ResultSet rs2 = pstmt.executeQuery();
                    while(rs2.next())
                        genres.add(genresDAO.findById(rs2.getInt(1)));
                }
                Album album = new Album(rs.getInt(1),
                        rs.getInt(2), rs.getString(3),artist);
                album.setGenreList(genres);
                albums.add(album);

            }
            return albums;
        }
    }
}
