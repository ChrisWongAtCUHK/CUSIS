package cusis;

import java.util.*;
import java.lang.reflect.*;
import java.sql.*;

import cusis.courses.*;
import cusis.db.*;
import cusis.platform.*;
import cusis.students.*;

public class CUSISDemo {
	public static void main(String[] args){
		IPlatform cusis = new CmdPlatform();
		cusis.show("Kill us!!");
		
		//SQLiteJDBC studentTable = new SQLiteJDBC("cusis.students.students.Student", new Object[]{}, "info.db", "SELECT * FROM Students");
		//ArrayList<Student> students = studentTable.getStudents();
		//ArrayList<Student> students = new ArrayList<Student>();
		//students.add(new Student("a", "1", "x"));
		//students.add(new Student("b", "2", "y"));
		//students.add(new Student("c", "3", "z"));
		
		// http://kodejava.org/how-do-i-create-object-using-constructor-object/
		Class<?> clazz = Student.class;
		
        try {
			@SuppressWarnings("rawtypes")
			Constructor<?> constructor = clazz.getConstructor(new Class[] {String.class, String.class, String.class});

			Student object = (Student) constructor.newInstance(new Object[] {"Chris Wong", "123456", "CS"});
			@SuppressWarnings("rawtypes")
			Method[] methods = {clazz.getMethod("getName", new Class[]{}), clazz.getMethod("getSid", new Class[]{}), clazz.getMethod("getMajor", new Class[]{})};
			String name = (String)methods[0].invoke(object, new Object[]{});
			String sid = (String)methods[1].invoke(object, new Object[]{});
			String major = (String)methods[2].invoke(object, new Object[]{});
			
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