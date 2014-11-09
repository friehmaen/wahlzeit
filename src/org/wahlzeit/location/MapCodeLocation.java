package org.wahlzeit.location;

import com.mapcode.MapcodeCodec;
import com.mapcode.Point;
import com.mapcode.Territory;
import com.mapcode.UnknownMapcodeException;
import com.mapcode.UnknownTerritoryException;


public class MapCodeLocation extends AbstractLocation {

	private String m_code;
	private String m_territory;
	
	@Override
	public String getMapLink() 
	{
		return "http://www.mapcode.com/de/getcoords.html?mapcode=" + asString();
	}
	
	public String getRegEx()
	{
		return "^[A-Z]+ [A-Z0-9]{2,3}\\.[A-Z0-9]{2,3}$";
	}
	
	public String asString()
	{
		return m_territory + " " + m_code;
	}
	
	protected void createFromString(String loc)
	{
		String[] parts = loc.split(" ");
		if (parts.length == 2)
		{
			m_territory = parts[0];
			m_code = parts[1];
		}
	}
	
	public GpsLocation toGps()
	{
		try 
		{
			Territory t = Territory.fromString(m_territory);
			Point p = MapcodeCodec.decode(m_code, t);
			return LocationFactory.create(p.getLatDeg(), p.getLonDeg());
			
		} catch (UnknownTerritoryException | IllegalArgumentException | UnknownMapcodeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public MapCodeLocation toMapCode()
	{
		return this;
	}
	
	public void setCode(String code)
	{
		m_code = code;
	}
	
	public String getCode()
	{
		return m_code;
	}
	
	public void setTerritory(String territory)
	{
		m_territory = territory;
	}
	
	public String getTerritory()
	{
		return m_territory;
	}
}
