package uaic.info.daos;

import uaic.info.database.Database;

import java.sql.*;

public class ArtistDAO {

    public void create(String name) throws SQLException
    {
        Connection connection = Database.getConnection();
        try(PreparedStatement pstmt = connection.prepareStatement("insert into artists(name) values (?)"))
        {
            pstmt.setString(1,name);
            pstmt.execute();
        }
    }

    public Integer findByName(String name) throws SQLException
    {
        Connection connection = Database.getConnection();
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select id from artists where name = " + name ))
        {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public String findById(int id) throws SQLException
    {
        Connection connection = Database.getConnection();
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select name from artists where id = " + id))
        {
            return rs.next() ? rs.getString(1) : null;
        }
    }
}
