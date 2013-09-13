package cusis.db;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;

import cusis.courses.*;
import cusis.students.*;

// Database management class
public class SQLiteJDBC {
	private ArrayList<Student> students = new ArrayList<Student>();
	
	// Constructor
	public SQLiteJDBC(String classname, Object[] constructorArgs, String dbFile, String query){
		try {
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		
		Connection connection = null; 
		Class<?> clazz = ResultSet.class;
        //ResultSet rs = null; 
        try {
            // create a database connection 
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);		// TODO: how to get db in other directory 
            Statement statement = connection.createStatement(); 
            statement.setQueryTimeout(30);  										// set timeout to 30 sec. 
            
			
			try {
				@SuppressWarnings("rawtypes")

				ResultSet rs = statement.executeQuery(query);
				@SuppressWarnings("rawtypes")
				Method[] methods = {clazz.getMethod("getString", new Class[]{String.class}), clazz.getMethod("getString", new Class[]{String.class}), clazz.getMethod("getString", new Class[]{String.class})};
				while(rs.next()){
					// read the result set and creat an object from database
					Student student = new Student();
					student.setName((String)methods[0].invoke(rs, new Object[]{"name"}));
					student.setSid((String)methods[1].invoke(rs, new Object[]{"sid"}));
					student.setMajor((String)methods[2].invoke(rs, new Object[]{"major"}));
					this.students.add(student);	
				}
		
			} catch (NoSuchMethodException e) {
				// for java.lang.reflect.Method
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// for invoke()
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			/*rs = statement.executeQuery(query); 
            while(rs.next()){ 
                // read the result set 
				// TODO: how to reuse SQLiteJDBC.java for two tables(Students & Courses), Reflect !! 
                this.students.add(new Student(rs.getString("name"),   
                        rs.getString("sid"),   
                        rs.getString("major")));
            }*/
              
        } catch(SQLException sqlExc){ 
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
	
	public ArrayList<Student> getStudents(){
		return this.students;
	}
}