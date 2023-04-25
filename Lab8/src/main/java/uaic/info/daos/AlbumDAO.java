package uaic.info.daos;

import uaic.info.database.Database;
import uaic.info.entities.Album;
import uaic.info.entities.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {

    public Album create(int releaseYear, String title, String artist, String genre) throws SQLException
    {
        Connection connection = Database.getConnection();
        try(PreparedStatement pstmt = connection.prepareStatement("insert into albums(release_year,title,artist,genre) values (?,?,?,?)"))
        {
            pstmt.setInt(1,releaseYear);
            pstmt.setString(2, title);
            pstmt.setString(3, artist);
            pstmt.setString(4, genre);
            pstmt.execute();
        }

        Album album = findByTitleAndArtist(title, artist);
        try(PreparedStatement pstmt = connection.prepareStatement("insert into album_genres values(?,?)"))
        {
            pstmt.setInt(1,album.getId());
            for(int i = 0; i < album.getGenreList().size(); i++)
            {
                pstmt.setInt(2,album.getGenreList().get(i).getId());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }

        return album;
    }


    public Album findByTitleAndArtist(String title,String artist) throws SQLException
    {
        Connection connection = Database.getConnection();
        Album album;
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from albums where title = '" + title + "' and artist = '" + artist + "'"))
        {
            if(!rs.next())
                return null;
                 album = new Album(rs.getInt(1),rs.getInt(2),rs.getString(3),
                        new ArtistDAO().findByName(rs.getString(4)));
                try(Statement stmt2 = connection.createStatement();
                    ResultSet rs2 = stmt2.executeQuery("select genre_id from album_genres where album_id = " + album.getId()))
                {
                    while(rs2.next())
                    {
                        album.addGenre(new GenreDAO().findById(rs2.getInt(1)));
                    }
                }

        }

        return album;
    }
    public List<Album> findByTitle(String title) throws SQLException
    {
        Connection connection = Database.getConnection();
        List<Album> albums = null;
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from albums where title = '" + title + "'"))
        {
            while(rs.next())
            {
                if(albums == null)
                    albums = new ArrayList<>();
                Album album = new Album(rs.getInt(1),rs.getInt(2),rs.getString(3),
                        new ArtistDAO().findByName(rs.getString(4)));
                try(Statement stmt2 = connection.createStatement();
                    ResultSet rs2 = stmt2.executeQuery("select genre_id from album_genres where album_id = " + album.getId()))
                {
                    while(rs2.next())
                    {
                        album.addGenre(new GenreDAO().findById(rs2.getInt(1)));
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
                        new ArtistDAO().findByName(rs.getString(4)));
                try(Statement stmt2 = connection.createStatement();
                    ResultSet rs2 = stmt2.executeQuery("select genre_id from album_genres where album_id = " + album.getId()))
                {
                    while(rs2.next())
                    {
                        album.addGenre(new GenreDAO().findById(rs2.getInt(1)));
                    }
                }
        }

        return album;
    }

    public List<Artist> findArtistByAlbumTitle(String title) throws SQLException
    {
        Connection connection = Database.getConnection();
        List<Artist> artists = null;
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select artist from albums where title = '" + title + "'"))
        {
            while(rs.next())
            {
                if(artists == null)
                    artists = new ArrayList<>();
                else
                    artists.add( new ArtistDAO().findByName(rs.getString(1)));
            }
        }

        return artists;
    }

    public List<Album> findAlbumsByArtist(String name) throws SQLException
    {
        Connection connection = Database.getConnection();
        List<Album> albums = null;
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select title from albums where artist = '" + name + "'"))
        {
            while(rs.next())
            {
                if(albums == null)
                    albums = new ArrayList<>();
                Album album = new Album(rs.getInt(1),rs.getInt(2),rs.getString(3),
                        new ArtistDAO().findByName(rs.getString(4)));
                try(Statement stmt2 = connection.createStatement();
                    ResultSet rs2 = stmt2.executeQuery("select genre_id from album_genres where album_id = " + album.getId()))
                {
                    while(rs2.next())
                    {
                        album.addGenre(new GenreDAO().findById(rs2.getInt(1)));
                    }
                }
                albums.add(album);
            }
        }
        return albums;
    }

}
