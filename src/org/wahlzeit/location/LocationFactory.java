package org.wahlzeit.location;

import java.util.ArrayList;
import java.util.List;

public class LocationFactory 
{
	public static Location create(String location)
	{
		Location l = null;
		
		if (location == null || location.isEmpty())
			return l;
		
		List<Location> locationList = new ArrayList<Location>();
		
		locationList.add(new GpsLocation());
		locationList.add(new MapCodeLocation());
		
		for (Location loc: locationList)
		{
			if (loc.fromString(location))
				return loc;
		}
		
		/*
		 * 
		if (location.matches(GpsLocation.getRegEx()))
		{
			l = new GpsLocation();
			l.fromString(location);
		}
		else if (location.matches(MapCodeLocation.getRegEx()))
		{
			l = new MapCodeLocation();
			l.fromString(location);
		}
		*/
		
		return l;
	}

}
