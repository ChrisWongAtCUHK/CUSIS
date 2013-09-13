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
		Class<?> studentClass = Student.class;
		
		cusis.show("Kill us!!");
		
		SQLiteJDBC studentTable = new SQLiteJDBC(studentClass, "info.db", "SELECT * FROM Students");
		/*ArrayList<Student> students = studentTable.getStudents();
		for(Student student: students){
			cusis.show(student.getName() + " is a " + student.getMajor() + " student with id: " + student.getSid());
		}*/
		
		// http://kodejava.org/how-do-i-create-object-using-constructor-object/
		
		ArrayList<? super SQLiteObject> sqliteObjs = new ArrayList<SQLiteObject>();
        try {
			// Reflect constructor
			Constructor[] constructors = studentClass.getDeclaredConstructors();
			Constructor constructor = null;
			for (int i = 0; i < constructors.length; i++) {
				constructor = constructors[i];
				if (constructor.getGenericParameterTypes().length == 0)
					break;
			}
			
			constructor.setAccessible(true);
			Object student = constructor.newInstance();
			student = studentClass.cast(student);
			
			// TODO: set methods test
			Method fieldsMethodsSetter = studentClass.getMethod("fieldsMethodsSetter", new Class[]{});
			Method[] setMethods = (Method[])fieldsMethodsSetter.invoke(null, new Object[]{});
			
			setMethods[0].invoke(student, "Chris Wong");
			setMethods[1].invoke(student, "123456");
			setMethods[2].invoke(student, "CS");
			// TODO: how to solved sqliteObjs.add((Student)studentClass.cast(student));
			sqliteObjs.add((Student)studentClass.cast(student));
			
			// TODO: get methods test
			Method fieldsMethodsGetter = studentClass.getMethod("fieldsMethodsGetter", new Class[]{});
			Method[] getMethods = (Method[])fieldsMethodsGetter.invoke(null, new Object[]{});

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
		
	}
}