package cusis.db;

import java.lang.reflect.*;

// Use reflect to invoke method to get data by SQLite
public interface SQLiteObject {
	
	// The should be called by the children
	public Class<?> getSelfClass();
	
	// Define the methods such that ResutlSet would know to get data
	public @SuppressWarnings("rawtypes") Method[] getRSMethods();
	
	// Define the arguments for methods such that ResutlSet would know to get data
	public Object[][] getRSArgs();
	
	// Define the method array such that the instance would know to set fields
	public @SuppressWarnings("rawtypes") Method[] fieldsMethodsSetter();
	
	// How the data can be get
	public @SuppressWarnings("rawtypes") Method[] fieldsMethodsGetter();
	
}