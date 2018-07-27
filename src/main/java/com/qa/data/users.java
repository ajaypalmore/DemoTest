package com.qa.data;
// POJO: Plain OLD JAVA OBject
public class users {
	String name;
	String job;
	String id;
	String createdAt;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getcreatedAt() {
		return createdAt;
	}
	public void setcreatedAt(String createdate) {
		this.createdAt = createdate;
	}
	public users()
	{
		
	}
	public users(String name, String job)
	{
		this.name= name;
		this.job = job;	
	}
	
	// getter and Setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}


}
