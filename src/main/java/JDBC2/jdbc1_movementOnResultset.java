package JDBC2;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class jdbc1_movementOnResultset {
    Connection conn;
    Statement st;
    ResultSet rs;
    ResultSetMetaData rsmd;


    @BeforeTest
    public void beforeTest() throws SQLException {
    conn= DriverManager.getConnection(

            "jdbc:mysql://142.93.110.12:3306/tempdb",
            "gsuser",
            "Gsuser!123456"
    );
        st= conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

    }

    @AfterTest
    public void afterTest() throws SQLException {
        conn.close();
        st.close();
    }

    @Test
    public void getResultSet() throws SQLException {
        String sql="SELECT * FROM meslekler1";
        rs=st.executeQuery(sql);
    }

    @Test(description = "rs.absolute",dependsOnMethods = "getResultSet")
    public void test1() throws SQLException {


        rs.absolute(10); //directly 10. row is output for the console
        System.out.println(rs.getString(1)+", "+rs.getString(2)+", "+rs.getString(3));

    }


    @Test(description = "rs.previous",dependsOnMethods = "getResultSet")
    public void test2() throws SQLException {
        rs.absolute(10); //directly 10. row is output for the console
        System.out.println(rs.getString(1)+", "+rs.getString(2)+", "+rs.getString(3));

        rs.previous();
        System.out.println(rs.getString(1)+", "+rs.getString(2)+", "+rs.getString(3));
    }

    @Test(description = "rs.previous",dependsOnMethods = "getResultSet")
    public void test3() throws SQLException {
        rs.absolute(10); //directly 10. row is output for the console
        System.out.println(rs.getString(1)+", "+rs.getString(2)+", "+rs.getString(3));

        rs.relative(2);//bulundugu yerden 2 kayıt ileri
        System.out.println(rs.getString(1)+", "+rs.getString(2)+", "+rs.getString(3));

        rs.relative(-4);//bulundugu yerden 4 kayıt geri
        System.out.println(rs.getString(1)+", "+rs.getString(2)+", "+rs.getString(3));
    }
}
