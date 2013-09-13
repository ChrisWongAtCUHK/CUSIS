package cusis.db;

import java.lang.reflect.*;

// Use reflect to invoke method to get data by SQLite
public abstract class SQLiteObject {

	// Define the method array such that ResutlSet would know to get data
	public static @SuppressWarnings("rawtypes") Method[] getRSMethods(){
		return null;
	}
	

	// Define the method array such that the instance would know to set fields
	public static @SuppressWarnings("rawtypes") Method[] getSetFieldsMethods(){
		return null;
	}
}