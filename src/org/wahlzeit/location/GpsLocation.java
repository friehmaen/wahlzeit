package org.wahlzeit.location;

public class GpsLocation extends AbstractLocation {

	private double m_long;
	private double m_lat;
	
	@Override
	public String getMapLink() 
	{
		String slong = Double.toString(m_long);
		String slat = Double.toString(m_lat);
		
		return "http://www.openstreetmap.org/#map=15/" + slong + "/" + slat;
	}
	
	public static String getRegEx()
	{
		return "^[0-9]{2}\\.[0-9]+ [0-9]{2}\\.[0-9]+";
	}
	
	public String asString()
	{
		return Double.toString(m_long) + " " + Double.toString(m_lat);
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

	@Override
	public boolean isValid(String loc) 
	{
		if (loc.matches(GpsLocation.getRegEx()))
		{
			return true;
		}
		return false;
	}

	@Override
	protected void createFromString(String loc) 
	{
		String[] parts =  loc.split(" ");
		if (parts.length == 2)
		{
			setLong(Double.parseDouble(parts[0]));
			setLat(Double.parseDouble(parts[1]));
		}
	}
}
