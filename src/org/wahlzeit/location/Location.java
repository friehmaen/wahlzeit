package org.wahlzeit.location;

public interface Location {
	
	public String asString();
	public void fromString(String loc);
	public String getDescription();
	public void setDescription(String desc);
	public String getMapLink();
	public boolean isValid(String loc);
	
}
