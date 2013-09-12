package cusis.db;

import java.sql.*;

import cusis.courses.*;
import cusis.students.*;

// 
public class SQLiteJDBC {
	
	// Constructor
	public SQLiteJDBC(String dbFile, String query){
		Connection connection = null; 
        ResultSet rs = null; 
        try{ 
            // create a database connection 
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);		// TODO: how to get db in other directory 
            Statement statement = connection.createStatement(); 
            statement.setQueryTimeout(30);  										// set timeout to 30 sec. 
            rs = statement.executeQuery(query); 
            while(rs.next()){ 
                // read the result set 
                /*this.addElement(new Audio(rs.getString("filename"),   
                        rs.getString("songTitle"),   
                        rs.getString("singer"), 
                        rs.getString("album"))); */
            } 
              
        }catch(SQLException sqlExc){ 
              // if the error message is "out of memory",  
              // it probably means no database file is found 
              System.err.println(sqlExc.getMessage()); 
        }finally{ 
			try{ 
			  if(connection != null) 
				connection.close(); 
			}catch(SQLException sqlExc){ 
			  // connection close failed. 
			  System.err.println(sqlExc); 
			} 
        }
	}
}