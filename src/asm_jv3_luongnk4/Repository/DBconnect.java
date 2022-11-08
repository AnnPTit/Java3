/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asm_jv3_luongnk4.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DBconnect {
    private static String url = String.format("jdbc:sqlserver://JAVA\\SQLEXPRESS:1433;"
            + "databaseName=ASM_JV3;user=sa;password=123456;"); 
    private static boolean ussingSSl=true;
    
    static {
        try {
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             
             if(ussingSSl){
                 url+= "encrypt=true;trustServerCertificate=true;";
             }
        } catch (Exception e) {
            System.out.println("Database connect erorr");
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url);
    }
    
}
