package com.vn.bookstore.connection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMYSQL {

    public static Connection getConnect(){
        Connection conn = null;

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String url="jdbc:mySQL://localhost:3306/bookstore?useSSL=false";
            String user="root";
            String pass="123456789";
            conn = DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  conn;
    }
    public static void closeConnection(Connection c) {
        try {
            if(c!=null) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printInfo(Connection c) {
        try {
            if(c!=null) {
                DatabaseMetaData mtdt = c.getMetaData();
                System.out.println(mtdt.getDatabaseProductName());
                System.out.println(mtdt.getDatabaseProductVersion());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
