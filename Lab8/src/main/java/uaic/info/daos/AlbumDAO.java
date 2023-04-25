package uaic.info.daos;

import uaic.info.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {

    public void create(int releaseYear, String title, String artist, String genre) throws SQLException
    {
        Connection connection = Database.getConnection();
        try(PreparedStatement pstmt = connection.prepareStatement("insert into albums(release_year,title,artist,genre) values (?,?,?,?)"))
        {
            pstmt.setInt(1,releaseYear);
            pstmt.setString(2,title);
            pstmt.setString(3,artist);
            pstmt.setString(4,genre);
            pstmt.execute();
        }
    }

    public List<Integer> findByTitle(String title) throws SQLException
    {
        Connection connection = Database.getConnection();
        List<Integer> ids = null;
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select id from albums where title = " + title))
        {
            while(rs.next())
            {
                if(ids == null)
                {
                    ids = new ArrayList<>();
                    ids.add(rs.getInt(1));
                }
                else
                    ids.add(rs.getInt(1));
            }
        }

        return ids;
    }

    public String findById(int id) throws SQLException
    {
        Connection connection = Database.getConnection();
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select title from albums where id = " + id))
        {
            return rs.next() ? rs.getString(1) : null;
        }
    }

    public List<String> findArtistByAlbumTitle(String title) throws SQLException
    {
        Connection connection = Database.getConnection();
        List<String> artists = null;
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select artist from albums where title = " + title))
        {
            while(rs.next())
            {
                if(artists == null)
                {
                    artists = new ArrayList<>();
                    artists.add(rs.getString(1));
                }
                else
                    artists.add(rs.getString(1));
            }
        }

        return artists;
    }

    public List<String> findAlbumsByArtist(String name) throws SQLException
    {
        Connection connection = Database.getConnection();
        List<String> albums = null;
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select title from albums where artist = " + name))
        {
            while(rs.next())
            {
                if(albums == null)
                {
                    albums = new ArrayList<>();
                    albums.add(rs.getString(1));
                }
                else
                    albums.add(rs.getString(1));
            }
        }

        return albums;
    }


}
