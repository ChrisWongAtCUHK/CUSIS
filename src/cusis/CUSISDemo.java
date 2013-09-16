package cusis;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;

import cusis.courses.*;
import cusis.db.*;
import cusis.platform.*;
import cusis.students.*;

public class CUSISDemo {

	public static @SuppressWarnings("rawtypes") void main(String[] args){
		IPlatform cusis = new CmdPlatform();
		
		cusis.show("Kill us!!");
		
		cusis.show("====================Studnets demo====================");
		Class<?> studentClass = Student.class;
		
		
		// Reflect constructor
		Constructor[] constructors = null;
		Constructor constructor = null;
		
		// Dummby object to handle static method(s) of interface, should be not necessary in Java 1.8?(TODO)
		Object dummystudent = null;
		
		// Set methods declaration
		Method fieldsMethodsSetter = null;
		Method[] setMethods = null;
			
		// Get methods declaration
		Method fieldsMethodsGetter = null;
		Method[] getMethods = null;
			
		// Database file
		String dbFile = "db//info.db";
		
		// Static methods invocation
        try {
			// Reflect constructor
			constructors = studentClass.getDeclaredConstructors();

			for (int i = 0; i < constructors.length; i++) {
				constructor = constructors[i];
				if (constructor.getGenericParameterTypes().length == 0)
					break;
			}
			
			constructor.setAccessible(true);
			// Dummby object to handle static method(s) of interface, should be not necessary in Java 1.8?(TODO)
			dummystudent = constructor.newInstance();
			
			Object student = constructor.newInstance();
			student = studentClass.cast(student);
			
			// Set methods invocation
			fieldsMethodsSetter = studentClass.getMethod("fieldsMethodsSetter", new Class[]{});
			setMethods = (Method[])fieldsMethodsSetter.invoke(dummystudent, new Object[]{});
			
			// Get methods invocation
			fieldsMethodsGetter = studentClass.getMethod("fieldsMethodsGetter", new Class[]{});
			getMethods = (Method[])fieldsMethodsGetter.invoke(dummystudent, new Object[]{});

			// TODO: test for usage, to be deleted
			setMethods[0].invoke(student, "Chris Wong");
			setMethods[1].invoke(student, "123456");
			setMethods[2].invoke(student, "CS");
			
			ArrayList<Object> sqliteObjs = new ArrayList<Object>();
			sqliteObjs.add(studentClass.cast(student));
			
			String name = (String)getMethods[0].invoke(sqliteObjs.get(0), new Object[]{});
			String sid = (String)getMethods[1].invoke(sqliteObjs.get(0), new Object[]{});
			String major = (String)getMethods[2].invoke(sqliteObjs.get(0), new Object[]{});
			
			cusis.show(name + " is a " + major + " student with id: " + sid);
        } catch (NoSuchMethodException e) {
			// for java.lang.reflect.Method
            e.printStackTrace();
        } catch (InstantiationException e) {
			// for constructor.newInstance
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
		
		// Apply database
		SQLiteJDBC studentTable = new SQLiteJDBC(studentClass, dbFile, "SELECT * FROM Students");
		ArrayList<Object> students = studentTable.getSqliteObjs();
		try{
		
			for(Object student: students){
				String name = (String)getMethods[0].invoke(student, new Object[]{});
				String sid = (String)getMethods[1].invoke(student, new Object[]{});
				String major = (String)getMethods[2].invoke(student, new Object[]{});
				cusis.show(name + " is a " + major + " student with id: " + sid);
			}
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
		
		cusis.show("====================Courses demo====================");
		Class<?> courseClass = Course.class;
		
		
		// Dummby object to handle static method(s) of interface, should be not necessary in Java 1.8?(TODO)
		Object dummycourse = null;
		
		// Static methods invocation
        try {
			// Reflect constructor
			constructors = courseClass.getDeclaredConstructors();

			for (int i = 0; i < constructors.length; i++) {
				constructor = constructors[i];
				if (constructor.getGenericParameterTypes().length == 0)
					break;
			}
			
			constructor.setAccessible(true);
			// Dummby object to handle static method(s) of interface, should be not necessary in Java 1.8?(TODO)
			dummycourse = constructor.newInstance();
			
			Object course = constructor.newInstance();
			course = courseClass.cast(course);
			
			// Set methods invocation
			fieldsMethodsSetter = courseClass.getMethod("fieldsMethodsSetter", new Class[]{});
			setMethods = (Method[])fieldsMethodsSetter.invoke(dummycourse, new Object[]{});
			
			// Get methods invocation
			fieldsMethodsGetter = courseClass.getMethod("fieldsMethodsGetter", new Class[]{});
			getMethods = (Method[])fieldsMethodsGetter.invoke(dummycourse, new Object[]{});

        } catch (NoSuchMethodException e) {
			// for java.lang.reflect.Method
            e.printStackTrace();
        } catch (InstantiationException e) {
			// for constructor.newInstance
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
		
		// Apply database
		SQLiteJDBC courseTable = new SQLiteJDBC(courseClass, dbFile, "SELECT * FROM Courses");
		ArrayList<Object> courses = courseTable.getSqliteObjs();
		try{
		
			for(Object course: courses){
				String courseCode = (String)getMethods[0].invoke(course, new Object[]{});
				int availableSeat = (int)getMethods[1].invoke(course, new Object[]{});
				int waitListCapacity = (int)getMethods[2].invoke(course, new Object[]{});
				cusis.show(courseCode + " has " + availableSeat + " available seat(s) with " + waitListCapacity + " in wait list.");
			}
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
	}
}