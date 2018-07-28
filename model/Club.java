package model;



public class Club 
{
	private String clubName;
	private String clubId;
	public void setClubId(String e)
	{
		clubId = e;
	}
	public void setClubName(String a)
	{
		clubName = a;
	}
	public String getClubId()
	{
		return clubId;
	}
	public String getClubName()
	{
		return clubName;
	}
	public String toString(){
		return this.clubName;
	}
}
