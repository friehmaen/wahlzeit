package org.wahlzeit.location;

public class LocationFactory 
{
	public static Location create(String loc)
	{
		Location l = null;
		
		if (loc == null || loc.isEmpty())
			return l;
		
		if (loc.matches(GpsLocation.getRegEx()))
		{
			l = new GpsLocation();
			l.fromString(loc);
		}
		else if (loc.matches(MapCodeLocation.getRegEx()))
		{
			l = new MapCodeLocation();
			l.fromString(loc);
		}
		
		return l;
	}

}
