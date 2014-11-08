package org.wahlzeit.location;

import org.wahlzeit.utils.HtmlUtil;

public class MapCodeLocation extends AbstractLocation {

	private String m_code;
	private String m_territory;
	
	@Override
	public String getMapLink() 
	{
		return "http://www.mapcode.com/de/getcoords.html?mapcode=" + getCode();
	}
	
	public static String getRegEx()
	{
		return "^[A-Z]+ [A-Z0-9]{2,3}\\.[A-Z0-9]{2,3}$";
	}
	
	private String toMapCode()
	{
		return m_territory + " " + m_code;
	}
	
	public String asString()
	{
		return getCode();
	}
	
	protected void createfromString(String loc)
	{
		setCode(loc);
	}
	
	public String getCode()		
	{
		return toMapCode();
	}
	
	public void setCode(String pcode)
	{
		String[] parts = pcode.split(" ");
		if (parts.length == 2)
		{
			m_territory = parts[0];
			m_code = parts[1];
		}
	}

	public boolean isValid(String loc)
	{
		if (loc.matches(MapCodeLocation.getRegEx()))
		{
			return true;
		}
		return false;
	}

	@Override
	protected void createFromString(String loc)
	{
		setCode(loc);
	}
}
