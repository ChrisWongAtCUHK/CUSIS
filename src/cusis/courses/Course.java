package cusis.courses;

import java.lang.reflect.*;
import java.sql.*;

import cusis.students.*;
import cusis.db.*;

public class Course implements SQLiteObject {
	private String courseCode;
	private int availableSeat;
	private int waitListCapacity;
	
	// Constructor 1
	public Course(){
	}
	
	// Constructor 1
	public Course(String courseCode, int availableSeat, int waitListCapacity){
		this.courseCode = courseCode;
		this.availableSeat = availableSeat;
		this.waitListCapacity = waitListCapacity;
	}
	
	public void setCourseCode(String courseCode){
		this.courseCode = courseCode;
	}
	
	public String getCourseCode(){
		return this.courseCode;
	}
	
	public void setAvailableSeat(int availableSeat){
		this.availableSeat = availableSeat;
	}
	
	public int getAvailableSeat(){
		return this.availableSeat;
	}
	
	public void setWaitListCapacity(int waitListCapacity){
		this.waitListCapacity = waitListCapacity;
	}
	
	public int getWaitListCapacity(){
		return this.waitListCapacity;
	}
	
	// Factory method to handle static method(s) of interface, should be not necessary in Java 1.8?(TODO)
	public static Course dummyInstance(){
		return new Course();
	}
	
	// The should be called by the children
	@Override
	public Class<?> getSelfClass(){
		return Course.class;
	}
	
	// Define the methods such that ResutlSet would know to get data
	@Override
	public @SuppressWarnings("rawtypes") Method[] getRSMethods(){
		Class<?> clazz = ResultSet.class;
		Method[] methods = new Method[3];
		try {
			methods[0] = clazz.getMethod("getString", new Class[]{String.class});
			methods[1] = clazz.getMethod("getInt", new Class[]{String.class});
			methods[2] = clazz.getMethod("getInt", new Class[]{String.class});
			
			// Success
			return methods;
		}catch (NoSuchMethodException e) {
			// for java.lang.reflect.Method
			e.printStackTrace();
		} 
		
		// Fail, must be checked
		return null;
	}
	
	// Define the arguments for methods such that ResutlSet would know to get data
	@Override
	public Object[][] getRSArgs(){
		Object[][] args = new Object[3][];
		args[0] = new Object[]{"courseCode"};
		args[1] = new Object[]{"availableSeat"};
		args[2] = new Object[]{"waitListCapacity"};
		return args;
	}
	
	// Define the method array such that the instance would know to set fields
	@Override
	public @SuppressWarnings("rawtypes") Method[] fieldsMethodsSetter(){
		Class<?> clazz = Course.class;
		Method[] methods = new Method[3];
		try {
			methods[0] = clazz.getMethod("setCourseCode", new Class[]{String.class});
			methods[1] = clazz.getMethod("setAvailableSeat", new Class[]{Integer.TYPE});
			methods[2] = clazz.getMethod("setWaitListCapacity", new Class[]{Integer.TYPE});
			
			// Success
			return methods;
		}catch (NoSuchMethodException e) {
			// for java.lang.reflect.Method
			e.printStackTrace();
		} 
		
		// Fail, must be checked
		return null;
	}
	
	// How the data can be get
	@Override
	public @SuppressWarnings("rawtypes") Method[] fieldsMethodsGetter(){
		Class<?> clazz = Course.class;
		Method[] methods = new Method[3];
		try {
			methods[0] = clazz.getMethod("getCourseCode", new Class[]{});
			methods[1] = clazz.getMethod("getAvailableSeat", new Class[]{});
			methods[2] = clazz.getMethod("getWaitListCapacity", new Class[]{});
			
			// Success
			return methods;
		}catch (NoSuchMethodException e) {
			// for java.lang.reflect.Method
			e.printStackTrace();
		} 
		
		// Fail, must be checked
		return null;
	}
	
}