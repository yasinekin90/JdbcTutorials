package JDBC2;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.sql.*;
import java.util.Scanner;

public class jdbc3_insert {
    public static void main(String[] args) throws SQLException {
        Connection conn;
        Statement st;
        ResultSet rs;
        ResultSetMetaData rsmd;

        conn= DriverManager.getConnection(

                    "jdbc:mysql://142.93.110.12:3306/tempdb",
                    "gsuser",
                    "Gsuser!123456"
            );

        st= conn.createStatement();

        Scanner scanner=new Scanner(System.in);

        System.out.print("Name: ");
        String name=scanner.nextLine();

        System.out.print("LastName: ");
        String lastName=scanner.nextLine();

        System.out.print("Age: ");
        int age=scanner.nextInt();
        scanner.nextLine();

        System.out.print("Country: ");
        String country=scanner.nextLine();

        String sql="INSERT INTO kartal VALUES('"+name+"','"+lastName+"','"+age+"','"+country+"')";
        int effectedRows=st.executeUpdate(sql);

        if(effectedRows<1)
            throw new RuntimeException("Kayıt Eklenemedi");


        sql="SELECT * FROM kartal WHERE adi LIKE '%"+name+"%'";
        rs= st.executeQuery(sql);
        int cols=rs.getMetaData().getColumnCount();

        while (rs.next()){
            for (int i = 1; i <=cols ; i++) {
                System.out.printf(rs.getString(i)+"\t");
            }
            System.out.println();
        }

        conn.close();
        st.close();



        }

}

