/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.programword;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Magnu
 */
public class DbHolder {
    
    public static DbHolder INSTANCE = new DbHolder();
    
    String connectionString = "jdbc:sqlite:sqlword.db";
    Connection conn = null;
    
    public void InsertTekst(String Tekst){
        try {
            
            conn = DriverManager.getConnection(connectionString);
            Statement state = conn.createStatement();
            
        
        state.executeUpdate("INSERT INTO WordTextfiles(Tekst) VALUES('"+Tekst+"'); ");
        System.out.println("success");
        } catch (SQLException ex) {
            Logger.getLogger(DbHolder.class.getName()).log(Level.SEVERE, null, ex);

        }
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbHolder.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
    
    ResultSet Exec(String sql) throws SQLException
    {
        conn = DriverManager.getConnection(connectionString);
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        return rs;
    }
    
    
}
