package uaic.info.database;

import java.sql.*;

public class Database {

    private static final String url = "jdbc:oracle:thin:@//localhost:1521/XE";
    private static final String username = "java_user";
    private static final String password = "password";

    private static Connection connection = null;

    private Database() {}

    public static Connection getConnection()
    {
        if(connection == null)
            Database.createConnection();
        return connection;
    }

    private static void createConnection()
    {
        try
        {
            connection = DriverManager.getConnection(url,username,password);
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void closeConnection()
    {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printElementsTable(String tableName)
    {
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from " + tableName))
        {
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            // Print the column names
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(meta.getColumnName(i) + "\t");
            }
            System.out.println();

            // Print the contents of the result set
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    Object value = rs.getObject(i);
                    System.out.print(value + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
