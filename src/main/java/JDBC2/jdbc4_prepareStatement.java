package JDBC2;

import java.sql.*;
import java.util.Scanner;

public class jdbc4_prepareStatement {

   static Connection conn;
   static  Statement st;
   static ResultSet rs;
   static ResultSetMetaData rsmd;
   static PreparedStatement prst;

    public static void main(String[] args) throws SQLException {


     connect();

   prst.setString(1,"first_name");
   prst.setString(2,"last_name");
   prst.setInt(3,55);
   prst.setString(4,"city");

    prst.executeUpdate();


     close();




        }
        public static void connect() throws SQLException {
            conn= DriverManager.getConnection(

                    "jdbc:mysql://142.93.110.12:3306/tempdb",
                    "gsuser",
                    "Gsuser!123456"
            );

            prst =conn.prepareStatement("INSERT INTO kartal VALUES (?,?,?,?)");
        }
        public static void close() throws SQLException {
            conn.close();
            prst.close();

        }

}

