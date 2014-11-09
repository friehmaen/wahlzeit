package org.wahlzeit.location;

import java.util.ArrayList;
import java.util.List;

public class LocationFactory 
{
	public static Location create(String location)
	{
		if (location == null || location.isEmpty())
			return null;
		
		//generate a list of all location implementations
		List<Location> locationList = new ArrayList<Location>();
		locationList.add(new GpsLocation());
		locationList.add(new MapCodeLocation());
		
		//now try to create a valid location object
		for (Location loc: locationList)
		{
			if (loc.fromString(location))
				return loc;
		}
	
		return null;
	}
	
	public static GpsLocation create(double plat, double plong)
	{
		GpsLocation loc = new GpsLocation();
		loc.setLat(plat);
		loc.setLong(plong);
		
		return loc;
	}
	
	public static MapCodeLocation create(String territory, String code)
	{
		MapCodeLocation loc = new MapCodeLocation();
		loc.setTerritory(territory);
		loc.setCode(code);
		
		return loc;
	}
}
