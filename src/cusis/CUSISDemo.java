package cusis;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;

import cusis.courses.*;
import cusis.db.*;
import cusis.platform.*;
import cusis.students.*;

public class CUSISDemo {
	public static void main(String[] args){
		IPlatform cusis = new CmdPlatform();
		cusis.show("Kill us!!");
		
		SQLiteJDBC studentTable = new SQLiteJDBC("cusis.students.students.Student", "info.db", "SELECT * FROM Students");
		/*ArrayList<Student> students = studentTable.getStudents();
		for(Student student: students){
			cusis.show(student.getName() + " is a " + student.getMajor() + " student with id: " + student.getSid());
		}*/
		
		// http://kodejava.org/how-do-i-create-object-using-constructor-object/
		Class<?> clazz = Student.class;
		ArrayList<? extends SQLiteObject> sqlObjs = new ArrayList<SQLiteObject>();
        try {
			//@SuppressWarnings("rawtypes")
			//Constructor<?> constructor = clazz.getConstructor(new Class[] {String.class, String.class, String.class});

			//Student student = (Student) constructor.newInstance(new Object[] {"Chris Wong", "123456", "CS"});
			
			Constructor[] ctors = clazz.getDeclaredConstructors();
			@SuppressWarnings("rawtypes")
			Constructor ctor = null;
			for (int i = 0; i < ctors.length; i++) {
				ctor = ctors[i];
				if (ctor.getGenericParameterTypes().length == 0)
					break;
			}
			
			ctor.setAccessible(true);
			Object student = ctor.newInstance();
			student = clazz.cast(student);
			Method[] setMethods = Student.fieldsMethodsSetter();
			setMethods[0].invoke(student, new Object[]{"Chris Wong"});
			setMethods[1].invoke(student, new Object[]{"123456"});
			setMethods[2].invoke(student, new Object[]{"CS"});
			
			Method[] getMethods = Student.fieldsMethodsGetter();
			String name = (String)getMethods[0].invoke(student, new Object[]{});
			String sid = (String)getMethods[1].invoke(student, new Object[]{});
			String major = (String)getMethods[2].invoke(student, new Object[]{});
			
			cusis.show(name + " is a " + major + " student with id: " + sid);
        /*} catch (NoSuchMethodException e) {
			// for java.lang.reflect.Method
            e.printStackTrace();*/
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