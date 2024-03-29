package uaic.info.daos;

import uaic.info.database.Database;
import uaic.info.entities.Artist;
import uaic.info.entities.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO{

    public Artist create(String name) throws SQLException
    {
        if(findByName(name) != null)
            return null;
        Connection connection = Database.getConnection();
        try(PreparedStatement pstmt = connection.prepareStatement("insert into artists(name) values (?)"))
        {
            pstmt.setString(1,name);
            pstmt.execute();
        }
        return findByName(name);
    }

    public Artist findByName(String name) throws SQLException
    {
        Connection connection = Database.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement("select id from artists where name = ?"))
        {
            preparedStatement.setString(1, name);
            ResultSet rs =  preparedStatement.executeQuery();
            return rs.next() ? new Artist(rs.getInt(1),name) : null;
        }
    }

    public Artist findById(int id) throws SQLException
    {
        Connection connection = Database.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement("select name from artists where id = ?"))
        {
            preparedStatement.setInt(1, id);
            ResultSet rs =  preparedStatement.executeQuery();
            return rs.next() ? new Artist(id,rs.getString(1)) : null;
        }
    }

    public List<Artist> findAll() throws SQLException {
        Connection connection = Database.getConnection();
        List<Artist> artists = null;
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select id,name from artists"))
        {
            while(rs.next())
            {
                if(artists == null)
                    artists = new ArrayList<>();
                artists.add(new Artist(rs.getInt(1),rs.getString(2)));
            }
            return artists;
        }
    }
}
