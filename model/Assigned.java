package model;

public class Assigned 
{
	private String eventId;
	private String eventName;
	private String fristName;
	private String lastName;
	private String userName;
	private String email;
	private String phone;
	public void setEvent(String e)
	{
		eventId = e;
	}
	public void setEventName(String a)
	{
		eventName = a;
	}
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
	public void setEmail(String a)
	{
		email = a;
	}
	public void setPhone(String a)
	{
		phone = a;
	}
	public String getEventId()
	{
		return eventId;
	}
	public String getEventName()
	{
		return eventName;
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
	public String getEmail()
	{
		return email;
	}
	public String getPhone()
	{
		return phone;
	}
}
