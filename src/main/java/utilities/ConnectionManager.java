package utilities;

import java.sql.*;

public class ConnectionManager {

    //sadece connection uretecek bir driver

    Connection conn;
    Statement st;
  String connStr;
  String dbName;
  int port;
  String userName;
  String password;

    public ConnectionManager(String connStr, String userName, String password)  {
        this.connStr = connStr;
        this.userName = userName;
        this.password = password;

        try {
            conn= DriverManager.getConnection(connStr,userName,password);
            st=conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public ConnectionManager(String connStr)  {
        this.connStr = connStr;
        try {
            conn= DriverManager.getConnection(connStr);
            st=conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public Connection getConn() {
        return conn;
    }

    public Statement getSt() {
        return st;
    }



    public ResultSet getResultSet(String sql){
        try {
            return st.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
