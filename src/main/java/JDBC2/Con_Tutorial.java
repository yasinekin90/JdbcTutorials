package JDBC2;

import java.sql.*;

public class Con_Tutorial {
    public static void main(String[] args) throws SQLException {
        Connection conn= DriverManager.getConnection(
                "jdbc:mysql://142.93.110.12:3306/tempdb",
                "gsuser",
                "Gsuser!123456"
        );
        Statement statement= conn.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT first_name,last_name,gender FROM yasinsTable3");

        ResultSetMetaData rsmd=resultSet.getMetaData();

        int rowNum=rsmd.getColumnCount();

        for (int i = 1; i <=rowNum ; i++) {
            int size=rsmd.getColumnDisplaySize(i);
            System.out.printf("%-"+size+"s",rsmd.getColumnName(i));
        }
        System.out.println();

        while (resultSet.next()){
            for (int i = 1; i <=rowNum ; i++) {
                int size=rsmd.getColumnDisplaySize(i);
                System.out.printf("%-"+size+"s",resultSet.getString(i));
            }
            System.out.println();
        }
        conn.close();
        statement.close();
    }
}
