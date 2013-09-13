package cusis.db;

import java.lang.reflect.*;

// Use reflect to invoke method to get data by SQLite
public class SQLiteObject {

	// Constructor
	public SQLiteObject(){
	}
	
	// The should be called by the children
	public static Class<?> getSelfClass(){
		return null;
	}
	
	// Define the methods such that ResutlSet would know to get data
	public static @SuppressWarnings("rawtypes") Method[] getRSMethods(){
		return null;
	}
	
	// Define the arguments for methods such that ResutlSet would know to get data
	public static Object[][] getRSArgs(){
		return null;
	}
	
	public static @SuppressWarnings("rawtypes") Method[] fieldsMethodsGetter(){
		return null;
	}
	
	// Define the method array such that the instance would know to set fields
	public static @SuppressWarnings("rawtypes") Method[] fieldsMethodsSetter(){
		return null;
	}
}