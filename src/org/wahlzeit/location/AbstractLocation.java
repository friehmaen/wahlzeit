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
	
	public void fromString(String loc)
	{
		if (isValid(loc))
		{
			createFromString(loc);
		}
	}
	
	protected abstract void createFromString(String loc);
}
