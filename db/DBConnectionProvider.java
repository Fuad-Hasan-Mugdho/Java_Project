package db;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnectionProvider{ 
    private static Connection conn;
    
    
    public static Connection getDBConnection(){
        if(conn==null){
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:8080/clubmanagement", "root", "root");
                System.out.println("Connection successfull");
            }catch(Exception ex){
                System.out.println("Failed to get DBConn:: "+ex.getMessage());
            }

            
            return conn;
        }
        else
            return conn;
    }
    
    
}