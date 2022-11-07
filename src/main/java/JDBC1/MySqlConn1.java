package JDBC1;

import java.sql.*;

public class MySqlConn1 {

    public static void main(String[] args) throws SQLException {

        // jdbc connectıon icin kullanılacak
        Connection conn;
        Statement statement;
        ResultSet resultSet;

        // url = "jdbc:mysql://[ ip | localhost | 127.0.0.1 ]:[port]/[database]",
        // jdbc:mysql://[ip]:3306/sakila
        // https://localhost/sakila
        conn= DriverManager.getConnection(
                "jdbc:mysql://142.93.110.12:3306/sakila",
                "gsuser",
                "Gsuser!123456"
        );

        statement=conn.createStatement();
        resultSet=statement.executeQuery("SELECT * FROM actor LIMIT 5;");

        while (resultSet.next()){
           int actor_id= resultSet.getInt(1);
           String first_name=resultSet.getString("first_name");
           String last_name=resultSet.getString(3);
            System.out.printf("%-5d%-20s%-20s\n", actor_id, first_name, last_name);
        }



        statement.close();
        conn.close();
    }
}
