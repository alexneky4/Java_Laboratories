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
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select id from genres where name = '" + name + "'"))
        {
            return rs.next() ? new Genre(rs.getInt(1),name) : null;
        }
    }

    public Genre findById(int id) throws SQLException
    {
        Connection connection = Database.getConnection();
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select name from genres where id = " + id + ";"))
        {
            return rs.next() ? new Genre(id, rs.getString(1)) : null;
        }
    }

    public List<Genre> findAll(String name) throws SQLException {
        Connection connection = Database.getConnection();
        List<Genre> genres = null;
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select id from genres where name = '" + name + "'"))
        {
            while(rs.next())
            {
                if(genres == null)
                    genres = new ArrayList<>();
                genres.add(new Genre(rs.getInt(1),name));
            }
            return genres;
        }
    }
}
