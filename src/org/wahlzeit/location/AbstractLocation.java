package org.wahlzeit.location;


public abstract class AbstractLocation implements Location {

	private String m_description;
	
	public void setDescription(String desc)
	{
		m_description = desc;
	}
	
	public String getDescription()
	{
		return m_description;
	}
	
	@Override
	public boolean isValid(String loc) 
	{
		if (loc.matches(getRegEx()))
		{
			return true;
		}
		return false;
	}
	
	public boolean fromString(String loc)
	{
		if (isValid(loc))
		{
			createFromString(loc);
			return true;
		}
		return false;
	}
	
	protected abstract void createFromString(String loc);
}
