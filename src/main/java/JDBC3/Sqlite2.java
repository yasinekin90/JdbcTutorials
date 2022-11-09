package JDBC3;

import java.sql.*;

/**
 * mysql de tempdb deki connection alındı
 * tempdb de kartal daki bilgiler alındı
 * sqlite a bağlantı yapıldı
 * tempdb deki kartaldan den bilgiler cekilip sqlitedaki yasin içine aktarıldı
 * sqlitedaki yasin ekrana yazdırıldı
 */
public class Sqlite2 {
    public static void main(String[] args) throws SQLException {
        Connection connMysql;
        Connection connSqlite;
        Statement stMysql;
        Statement stsqlite;
        ResultSet rsMysql;
        ResultSet rsSqlite;
//  mysql de tempdb deki connection alındı
        connMysql= DriverManager.getConnection(
                "jdbc:mysql://142.93.110.12:3306/tempdb",
                "gsuser",
                "Gsuser!123456"
        );

        //tempdb de kartal daki bilgiler alındı
        stMysql=connMysql.createStatement();
        String mysqlStr="SELECT * FROM kartal";
        rsMysql=stMysql.executeQuery(mysqlStr);

        // sqlite a bağlantı yapıldı
        connSqlite=DriverManager.getConnection("jdbc:sqlite:src/main/resources/data.sqlite");
        stsqlite= connSqlite.createStatement();

        //tempdb deki kartaldan den bilgiler cekilip sqlitedaki yasin içine aktarıldı
        while (rsMysql.next()){
            String sqlInsert="INSERT INTO yasin VALUES('"
                    +rsMysql.getString(1) +"','"
                    +rsMysql.getString(2)+"','"
                    +rsMysql.getString(3)+"','"
                    +rsMysql.getString(4)+"');";
            stsqlite.executeUpdate(sqlInsert);
        }


        // sqlitedaki yasin ekrana yazdırıldı
            String sqliteStr="SELECT * FROM yasin";
        rsSqlite=stsqlite.executeQuery(sqliteStr);
        int rowNum=rsSqlite.getMetaData().getColumnCount();

        for (int i = 1; i <=rowNum ; i++) {
            System.out.printf("%-20s \t",rsSqlite.getMetaData().getColumnName(i));
        }
        System.out.println();

        while (rsSqlite.next()){
            for (int i = 1; i <=rowNum ; i++) {
                System.out.printf("%-20s \t",rsSqlite.getString(i));
            }
            System.out.println();
        }

    }
}
