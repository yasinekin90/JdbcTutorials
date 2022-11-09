package JDBC3;

import java.sql.*;

public class sqlite1 {
    public static void main(String[] args) throws SQLException {
        Connection conn;
        Statement st;
        ResultSet rs;

        conn= DriverManager.getConnection(
                "jdbc:sqlite:src/main/resources/data.sqlite"
        );

        st= conn.createStatement();
        st.setQueryTimeout(30);
       //rs= st.executeQuery("SELECT * FROM personel LIMIT 5");
       rs=st.executeQuery("SELECT first_name,last_name,make,model,modelyear,COUNT(make) FROM personel INNER JOIN cars ON personel.car_id=cars.id GROUP BY make;");

       int rowNum=rs.getMetaData().getColumnCount();

        for (int i = 1; i <=rowNum ; i++) {
            System.out.printf("%-20s \t",rs.getMetaData().getColumnName(i));
        }
        System.out.println();

       while (rs.next()){
           for (int i = 1; i <=rowNum ; i++) {
               System.out.printf("%-20s \t",rs.getString(i));
           }
           System.out.println();
       }

        st.executeUpdate("DROP TABLE IF EXISTS yasin;");
       String sql="CREATE TABLE yasin(adi text,soyadi text,yas int,sehir text)";

           st.executeUpdate(sql);




        st.executeUpdate("DROP TABLE IF EXISTS yasin1;");
        String sql1="CREATE TABLE yasin1('adi','soyadi','yas','sehir')";

        st.executeUpdate(sql1);


       conn.close();
       st.close();
    }
}
