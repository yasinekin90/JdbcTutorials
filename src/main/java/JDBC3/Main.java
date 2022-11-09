package JDBC3;

import utilities.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        MySqlConnection();
    }




    public static void sqLiteConnection() throws SQLException {
        ConnectionManager cm=new ConnectionManager("jdbc:sqlite:src/main/resources/data.sqlite");
        ResultSet rs=cm.getResultSet("SELECT * FROM yasin");

        while (rs.next()){

            System.out.println(
                    rs.getString(1)+", "
                            +rs.getString(2)+", "
                            +rs.getString(3)+", "
                            +rs.getString(4));
        }

        cm.getConn().close();
        cm.getSt().close();
    }



    public static void MySqlConnection() throws SQLException {
        ConnectionManager cm=new ConnectionManager(
                "jdbc:mysql://142.93.110.12:3306/sakila",
                "gsuser",
                "Gsuser!123456");


        // sakila filmlerde oynayan aktör sayıları
        ResultSet rs=cm.getResultSet("SELECT title,COUNT(*) FROM film INNER JOIN film_actor ON film.film_id=film_actor.film_id GROUP BY title");

        while (rs.next()){

            System.out.println(
                    rs.getString(1)+", "
                            +rs.getString(2)
                            );
        }

        cm.getConn().close();
        cm.getSt().close();
    }
}
