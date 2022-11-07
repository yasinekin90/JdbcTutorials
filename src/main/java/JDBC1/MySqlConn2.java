package JDBC1;

import java.sql.*;

public class MySqlConn2 {

    public static void main(String[] args) throws SQLException {

        Connection conn;
        Statement statement;
        ResultSet resultSet;

        conn= DriverManager.getConnection(
                "jdbc:mysql://142.93.110.12:3306/sakila",
                "gsuser",
                "Gsuser!123456"
        );

String sql="SELECT first_name,last_name,title" +
        "                        FROM actor" +
        "                        INNER JOIN film_actor ON actor.actor_id=film_actor.actor_id" +
        "                        INNER JOIN film ON film_actor.film_id=film.film_id" +
        "                        WHERE first_name LIKE 'A%';";
        statement= conn.createStatement();
        resultSet=statement.executeQuery(sql);
        while (resultSet.next()){

            String first_name=resultSet.getString(1);
            String last_name=resultSet.getString(2);
            String title=resultSet.getString("title");

            System.out.printf("%-10s%-10s%-30s\n",  first_name,last_name,title);
        }
        statement.close();
        conn.close();
    }
}
