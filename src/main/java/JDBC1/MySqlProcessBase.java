package JDBC1;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.sql.*;

public class MySqlProcessBase {

    Connection conn;
    Statement statement;
    ResultSet resultSet;
    @BeforeTest
    public void beforeTest() throws SQLException {
        conn= DriverManager.getConnection(
                "jdbc:mysql://142.93.110.12:3306/sakila",
                "gsuser",
                "Gsuser!123456"
        );

        statement=conn.createStatement();
    }

    @AfterTest
    public void afterTest() throws SQLException {
        statement.close();
        conn.close();
    }
}
