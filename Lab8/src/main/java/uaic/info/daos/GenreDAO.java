package uaic.info.daos;

import uaic.info.database.Database;
import uaic.info.entities.Artist;
import uaic.info.entities.Entity;
import uaic.info.entities.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO{

    public Genre create(String name) throws SQLException
    {
        if(findByName(name) != null) {
            System.out.println(findByName(name).toString());
            return null;
        }
        Connection connection = Database.getConnection();
        try(PreparedStatement pstmt = connection.prepareStatement("insert into genres(name) values (?)"))
        {
            pstmt.setString(1, name);
            pstmt.execute();
        }
        return findByName(name);
    }

    public Genre findByName(String name) throws SQLException
    {
        Connection connection = Database.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement("select id from genres where name = ?"))
        {
            preparedStatement.setString(1, name);
            ResultSet rs =  preparedStatement.executeQuery();
            return rs.next() ? new Genre(rs.getInt(1),name) : null;
        }
    }

    public Genre findById(int id) throws SQLException
    {
        Connection connection = Database.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement("select name from genres where id = ?"))
        {
            preparedStatement.setInt(1, id);
            ResultSet rs =  preparedStatement.executeQuery();
            return rs.next() ? new Genre(id, rs.getString(1)) : null;
        }
    }

    public List<Genre> findAll(String name) throws SQLException {
        Connection connection = Database.getConnection();
        List<Genre> genres = null;
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select id,name from genres"))
        {
            while(rs.next())
            {
                if(genres == null)
                    genres = new ArrayList<>();
                genres.add(new Genre(rs.getInt(1),rs.getString(2)));
            }
            return genres;
        }
    }
}
