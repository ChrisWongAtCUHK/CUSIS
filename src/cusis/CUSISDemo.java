package cusis;

import java.util.*;
import java.sql.*;

import cusis.platform.*;
import cusis.courses.*;
import cusis.students.*;

public class CUSISDemo {
	public static void main(String[] args){
		IPlatform cusis = new CmdPlatform();
		cusis.show("Kill us!!");
		
		ArrayList<Student> students = new ArrayList<Student>();
		/*students.add(new Student("a", "1", "x"));
		students.add(new Student("b", "2", "y"));
		students.add(new Student("c", "3", "z"));*/
		
		for(Student student: students){
			cusis.show(student.getName() + " is a " + student.getMajor() + " student with id: " + student.getSid());
		}
		
	}
}