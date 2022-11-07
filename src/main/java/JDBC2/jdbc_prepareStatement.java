package JDBC2;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_prepareStatement {
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
        st= conn.createStatement();

    }

    @AfterTest
    public void afterTest() throws SQLException {
        conn.close();
        st.close();
    }

    @Test
    public void test1() throws SQLException {

        String sql="INSERT INTO kartal VALUES('Cem','Yilmaz',50,'IST');";
        int insNum=st.executeUpdate(sql);
        System.out.println(insNum+" kayıt eklendi");
    }

    @Test
    public void test2() throws SQLException {

        String sql="SELECT * FROM kartal";

        rs=st.executeQuery(sql);

        int col=rs.getMetaData().getColumnCount();
        while (rs.next()){
            for (int i = 1; i <=col ; i++) {
                System.out.printf("%-20s",rs.getString(i));
            }
            System.out.println();
        }
            

    }

    @Test
    public void newRecord() throws SQLException {
        String sql="INSERT INTO kartal VALUES('mert','yilmaz','45','Ankara');";
        st.executeUpdate(sql);
        String sql2="SELECT * FROM kartal";
        rs=st.executeQuery(sql2);

        int colNum=rs.getMetaData().getColumnCount();
        while (rs.next()){
            for (int i = 1; i <=colNum ; i++) {
                System.out.printf("%-20s",rs.getString(i));
            }
            System.out.println();
        }

    }
}
