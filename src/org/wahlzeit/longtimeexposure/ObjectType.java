package org.wahlzeit.longtimeexposure;

import java.util.HashMap;
import java.util.Map;

public class ObjectType  {
	
	public static final String DELIMITER = ";";
	
	private String name;
	private ObjectType subType = null;
	private Map<String,String> attributes;
	
	public ObjectType(String name)
	{
		this.name = name;
		attributes = new HashMap<String,String>();
	}
	
	public String asString()
	{
		String out = getName();
		
		if (subType != null)
			out += DELIMITER + subType.asString();
		
		return out;
	}
	
	public void fromString(String t)
	{
		if (t.length() == 0)
			throw new IllegalArgumentException("Invalid ObjectType");
		
		String[] typeSplit = t.split(DELIMITER);
		if (typeSplit.length == 1)
			setName(t);
		else
		{
			setName(typeSplit[0]);
			 subType = new ObjectType(typeSplit[1]);
			 subType.fromString(t.substring(t.indexOf(DELIMITER)+1));
		}
	}
	
	public String getAttribute(String name)
	{
		asserValidAttribute(name);
		
		return attributes.get(name);
	}
	
	public void setAttribute(String name, String value)
	{
		attributes.put(name, value);
	}
	
	private void asserValidAttribute(String name)
	{
		if (!attributes.containsKey(name))
			throw new IllegalArgumentException("Attribute does not exist (" + name + ")");
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ObjectType getSubType() {
		return subType;
	}

	public void setSubType(ObjectType subType) {
		this.subType = subType;
	}

	public Map<String,String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String,String> attributes) {
		this.attributes = attributes;
	}
}
