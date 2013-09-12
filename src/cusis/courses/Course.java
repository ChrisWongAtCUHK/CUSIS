package cusis.courses;

import cusis.students.*;

public class Course{
	private String courseCode;
	private int availableSeat;
	private int waitListCapacity;
	
	// Constructor
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
}