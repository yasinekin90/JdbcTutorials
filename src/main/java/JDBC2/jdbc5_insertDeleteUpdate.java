package JDBC2;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.internal.PackageUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class jdbc5_insertDeleteUpdate {
     /*
        1. Before, After Test
        2. DataProvider ile 10 kayit ekleyin
        3. Update ile 2 kayit güncelleyin
        4. Delete ile 5 kayit silin.
     */

    Connection conn;
    Statement st;
    ResultSet rs;
    ResultSetMetaData rsmd;


    @BeforeTest
    public void beforeTest() throws SQLException, IOException, FileNotFoundException {
        FileInputStream fis = new FileInputStream("src/main/resources/DB.properties");
        Properties properties = new Properties();
        properties.load(fis);
        String hostname = properties.getProperty("gs.hostname");
        String user = properties.getProperty("gs.user");
        String password = properties.getProperty("gs.pass");
        conn = DriverManager.getConnection(
                "jdbc:mysql://" + hostname + ":3306/tempdb",
                user,
                password
        );
        fis.close();

        st = conn.createStatement();
    }

    @AfterTest
    public void afterTest() throws SQLException {
        conn.close();
        st.close();
    }

    @Test(dataProvider ="getData")
    public void insertData(Object[] arr) throws SQLException {
      String sql="INSERT INTO kartal " +
              "VALUES('"+ arr[0] + "', '"+ arr[1] + "', " + arr[2] +", '"+ arr[3] + "');";

        if (st.executeUpdate(sql)<1)
            throw new RuntimeException("Kayit eklenemedi\n" + sql);
        writeResult(st.executeQuery("SELECT * FROM kartal"));
    }

    @Test(dataProvider ="getDataForUpdate")
    public void updateData(Object[] arr) throws SQLException {
        String sql = "UPDATE kartal " +
                "SET adi = '"+ arr[1] + "', " +
                "soyadi = '"+ arr[2] + "', " +
                "yas = "+ arr[3] + ", " +
                "sehir = '"+ arr[4] + "' " +
                "WHERE adi = '"+ arr[0] + "';";

        if (st.executeUpdate(sql)<1)
            throw new RuntimeException("Kayit eklenemedi\n" + sql);
        writeResult(st.executeQuery("SELECT * FROM kartal"));
    }



    public void writeResult(ResultSet rs) throws SQLException {
        int cols=rs.getMetaData().getColumnCount();

        while (rs.next()){
            for (int i = 1; i <=cols ; i++) {
                System.out.printf("%-20s",rs.getString(i));
            }
            System.out.println();
        }

    }

    @Test
    public void deleteAllData() throws SQLException {
        String sql = "DELETE FROM kartal;";
          st.executeUpdate(sql);
        if (st.execute(sql))
            throw new RuntimeException("Kayit Silinemedi\n" + sql);

    }


    @DataProvider
    public Object[][] getData(){
        return new Object[][]{
                {"B2","B3",11,"B4"},
                {"B5","B6",12,"B7"},
                {"B8","B9",13,"B10"},
                {"B11","B12",14,"B13"},
                {"B14","B15",15,"B16"},
                {"B17","B18",16,"B19"}

        };
    }


    @DataProvider
    public Object[][] getDataForUpdate(){
        return new Object[][]{
                {"B2", "A11","A12",21,"A11"},
                {"B5", "A22","A22",22, "A22"},
                {"B8", "A33","A33",23, "A33"},
                {"B11", "A44","A44",24, "A44"},
                {"B14", "A55","A55",25, "A55"},
        };
    }
}
