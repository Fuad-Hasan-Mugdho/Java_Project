package model;

public class Registration 
{
	private String fristName;
	private String lastName;
	private String userName;
	private String password;
	private String department;
	private String cgpa;
	private String clubName;
	
	public void setFname(String a)
	{
		fristName = a;
	}
	public void setLname(String a)
	{
		lastName = a;
	}
	public void setUname(String a)
	{
		userName = a;
	}
	public void setPass(String a)
	{
		password = a;
	}
	public void setDep(String a)
	{
		department = a;
	}
	public void setCgpa(String a)
	{
		cgpa = a;
	}
	public void setClubName(String a)
	{
		clubName = a;
	}
	
	public String getFirstName()
	{
		return fristName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public String getUserName()
	{
		return userName;
	}
	public String getPassword()
	{
		return password;
	}
	public String getDepartment()
	{
		return department;
	}
	public String getCgpa()
	{
		return cgpa;
	}
	public String getClubName()
	{
		return clubName;
	}
}
