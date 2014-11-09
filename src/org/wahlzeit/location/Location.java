package org.wahlzeit.location;

public interface Location {
	
	public String asString();
	public boolean fromString(String loc);
	public String getRegEx();
	public boolean isValid(String loc);
	
	public String getDescription();
	public void setDescription(String desc);
	
	public String getMapLink();
	
	GpsLocation toGps();
	MapCodeLocation toMapCode();
}
