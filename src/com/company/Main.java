package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        // write your code here
        String url = "jdbc:mysql://127.0.0.1:8889/DataStructuresYay";
        String username = "Aitor";
        String password = "saint123";
        SQLhelper myHelper = new SQLhelper();


        System.out.println(myHelper.getStudentID("Aitor", "Arrese-Igor"));

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch(SQLException e){
            e.printStackTrace();
        }
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        myHelper.addTransaction(1,1,1);
        //try {

            //stmt.execute(sql);
       // }catch(SQLException e){
        //    e.printStackTrace();
        //}


    }

}

