package cusis.students;

import cusis.courses.*;

// victim who is killed by CUSIS
public class Student {
	private String name;
	private String sid;
	private String major;
	
	// Constructor
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
}