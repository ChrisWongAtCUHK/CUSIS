package cusis.students;

import java.lang.reflect.*;
import java.sql.*;

import cusis.courses.*;
import cusis.db.*;

// victim who is killed by CUSIS
public class Student extends SQLiteObject {
	private String name;
	private String sid;
	private String major;
	
	// Constructor 1
	public Student(){
	}
	
	// Constructor 2
	public Student(String name, String sid, String major){
		this.name = name;
		this.sid = sid;
		this.major = major;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setSid(String sid){
		this.sid = sid;
	}
	
	public String getSid(){
		return this.sid;
	}
	
	public void setMajor(String major){
		this.major = major;
	}
	
	public String getMajor(){
		return this.major;
	}
	
	// TODO: dunno who should be called
	public static Class<?> getSelfClass(){
		return Student.class;
	}
	
	// Define the methods such that ResutlSet would know to get data
	public static @SuppressWarnings("rawtypes") Method[] getRSMethods(){
		Class<?> clazz = ResultSet.class;
		try {
			// Success
			return new Method[]{clazz.getMethod("getString", new Class[]{String.class}), clazz.getMethod("getString", new Class[]{String.class}), clazz.getMethod("getString", new Class[]{String.class})};
		}catch (NoSuchMethodException e) {
			// for java.lang.reflect.Method
			e.printStackTrace();
		} 
		
		// Fail, must be checked
		return null;
	}
	
	// Define the arguments for methods such that ResutlSet would know to get data
	public static Object[][] getRSArgs(){
		Object[][] args = new Object[3][];
		args[0] = new Object[]{"name"};
		args[1] = new Object[]{"sid"};
		args[2] = new Object[]{"major"};
		return args;
	}
	
	// Define the method array such that the instance would know to set fields
	public static @SuppressWarnings("rawtypes") Method[] fieldsMethodsSetter(){
		Class<?> clazz = Student.class;
		Method[] methods = new Method[3];
		try {
			methods[0] = clazz.getMethod("setName", new Class[]{String.class});
			methods[1] = clazz.getMethod("setSid", new Class[]{String.class});
			methods[2] = clazz.getMethod("setMajor", new Class[]{String.class});
			
			// Success
			return methods;
		}catch (NoSuchMethodException e) {
			// for java.lang.reflect.Method
			e.printStackTrace();
		} 
		
		// Fail, must be checked
		return null;
	}
	
	// Define the method array such that a Student instance would know to set fields
	public static @SuppressWarnings("rawtypes") Method[] fieldsMethodsGetter(){
		Class<?> clazz = Student.class;
		Method[] methods = new Method[3];
		try {
			methods[0] = clazz.getMethod("getName", new Class[]{});
			methods[1] = clazz.getMethod("getSid", new Class[]{});
			methods[2] = clazz.getMethod("getMajor", new Class[]{});
			
			// Success
			//return new Method[]{clazz.getMethod("getName", new Class[]{}), clazz.getMethod("getSid", new Class[]{}), clazz.getMethod("getMajor", new Class[]{})};
			return methods;
		}catch (NoSuchMethodException e) {
			// for java.lang.reflect.Method
			e.printStackTrace();
		} 
		
		// Fail, must be checked
		return null;
	}
}