package cusis.db;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;

// Database management class
public class SQLiteJDBC {
	private ArrayList<Object> sqliteObjs = new ArrayList<Object>();
	
	// Constructor
	public @SuppressWarnings("rawtypes") SQLiteJDBC(Class<?> clazz, String dbFile, String query){
		try {
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		
		Connection connection = null; 
		Class<?> rsClazz = ResultSet.class;
		
		// Set methods declaration
		Method fieldsMethodsSetter = null;
		Method[] setMethods = null;

        try {
            // create a database connection 
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);		// TODO: how to get db in other directory 
            Statement statement = connection.createStatement(); 
            statement.setQueryTimeout(30);  										// set timeout to 30 sec. 
            
			try {
				ResultSet rs = statement.executeQuery(query);
				
				// Dummy object to call static method(s), should be not necessary in Java 1.8?(TODO)
				// Reflect constructor
				Constructor[] constructors = clazz.getDeclaredConstructors();
				@SuppressWarnings("rawtypes")
				Constructor constructor = null;
				for (int i = 0; i < constructors.length; i++) {
					constructor = constructors[i];
					if (constructor.getGenericParameterTypes().length == 0)
						break;
				}
				
				constructor.setAccessible(true);
				Object dummyObj = constructor.newInstance();
			
				// Get the methods such that ResutlSet would know to get data
				@SuppressWarnings("rawtypes")
				Method getRSMethods = clazz.getMethod("getRSMethods", new Class[]{});
				Method[] methods = (Method[])getRSMethods.invoke(dummyObj, new Object[]{});			// invoke static method without argument
			
				// Get the arguments for methods such that ResutlSet would know to get data
				@SuppressWarnings("rawtypes")
				Method getRSArgs = clazz.getMethod("getRSArgs", new Class[]{});
				Object[][] argsList = (Object[][])getRSArgs.invoke(dummyObj, new Object[]{});	// invoke static method without argument
				
				// Set methods invocation
				fieldsMethodsSetter = clazz.getMethod("fieldsMethodsSetter", new Class[]{});
				setMethods = (Method[])fieldsMethodsSetter.invoke(dummyObj, new Object[]{});
			
				// Loop through the data base row by row
				while(rs.next()){
					Object obj = constructor.newInstance();
					obj = clazz.cast(obj);
	
					// TODO: loop through the methods by 2D nested loop
					for(int i = 0; i < setMethods.length; i++){
						setMethods[i].invoke(obj, (String)methods[i].invoke(rs, argsList[i]));
						
					}
					this.sqliteObjs.add(obj);
				}
				
			} catch (InstantiationException e) {
				// for constructor.newInstance
				e.printStackTrace();
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
	
	public ArrayList<Object> getSqliteObjs(){
		return this.sqliteObjs;
	}
}