package model;

public class Event 
{
	private String eventId;
	private String eventName;
	private String startTime;
	private String endTime;
	private String clubName;
	
	public void setEvent(String e)
	{
		eventId = e;
	}
	public void setEventName(String e)
	{
		eventName = e;
	}
	public void setStime(String e)
	{
		startTime = e;
	}
	public void setEtime(String e)
	{
		endTime = e;
	}
	public void setClub(String e)
	{
		clubName = e;
	}
	public String getEventId()
	{
		return eventId;
	}
	public String getEventName()
	{
		return eventName;
	}
	public String getStime()
	{
		return startTime;
	}
	public String getEtime()
	{
		return endTime;
	}
	public String getClub()
	{
		return clubName;
	}
}
