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
		
        try {
			@SuppressWarnings("rawtypes")
			Constructor<?> constructor = clazz.getConstructor(new Class[] {String.class, String.class, String.class});

			Student object = (Student) constructor.newInstance(new Object[] {"Chris Wong", "123456", "CS"});

			Method[] methods = Student.getSetFieldsMethods();
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