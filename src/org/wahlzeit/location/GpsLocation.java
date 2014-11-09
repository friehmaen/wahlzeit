package org.wahlzeit.location;

import java.util.List;

import com.mapcode.Mapcode;
import com.mapcode.MapcodeCodec;

public class GpsLocation extends AbstractLocation {

	private double m_long;
	private double m_lat;
	
	@Override
	public String getMapLink() 
	{
		String slong = Double.toString(m_long);
		String slat = Double.toString(m_lat);
		
		return "http://www.openstreetmap.org/#map=16/" + slat + "/" + slong;
	}
	
	public String getRegEx()
	{
		return "^[0-9]{2}\\.[0-9]+ [0-9]{2}\\.[0-9]+";
	}
	
	public String asString()
	{
		return Double.toString(m_lat) + " " + Double.toString(m_long);
	}
	
	@Override
	protected void createFromString(String loc) 
	{
		String[] parts =  loc.split(" ");
		if (parts.length == 2)
		{
			setLat(Double.parseDouble(parts[0]));
			setLong(Double.parseDouble(parts[1]));
		}
	}
	
	public GpsLocation toGps()
	{
		return this;
	}
	
	public MapCodeLocation toMapCode()
	{
		List<Mapcode> mapCodes = MapcodeCodec.encode(m_lat, m_long);
		
		if (mapCodes.size() >= 1)
		{
			String mc = mapCodes.get(0).getMapcode();
			String t = mapCodes.get(0).getTerritory().name();
			
			return LocationFactory.create(t, mc);
		}
		
		return null;
	}
	
	/**
	 * @return the m_long
	 */
	public double getLong()
	{
		return m_long;
	}

	/**
	 * @param long the m_long to set
	 */
	public void setLong(double plong) 
	{
		this.m_long = plong;
	}

	/**
	 * @return the m_lat
	 */
	public double getLat() {
		return m_lat;
	}

	/**
	 * @param lat the m_lat to set
	 */
	public void setLat(double lat) {
		this.m_lat = lat;
	}
}
