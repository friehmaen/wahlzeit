package org.wahlzeit.longtimeexposure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.services.SysLog;

public class LongTimeExposure extends Photo {
	
	private PhotoMetaData m_metaData = null;
	
	public LongTimeExposure() {	
		super();
		
		m_metaData = new PhotoMetaData();
		
		SysLog.logSysInfo("LTE", "creating a LongTimeExposure with default constructor");
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public LongTimeExposure(PhotoId myId) {
		super(myId);
		
		m_metaData = new PhotoMetaData();
		
		SysLog.logSysInfo("LTE", "creating a LongTimeExposure from PhotoId");
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public LongTimeExposure(ResultSet rset) throws SQLException {
		super(rset);
		
		SysLog.logSysInfo("LTE", "creating a LongTimeExposure from ResultSet");
		
	}

	public PhotoMetaData getMetaData() {
		return m_metaData;
	}

	public void setMetaData(PhotoMetaData metaData) {
		this.m_metaData = metaData;
	}
	
	protected void collectProperties(Map<String,String> properties)
	{
		super.collectProperties(properties);
		
		properties.put("Long Time Exposure", "This photo is a LongTimeExposure object.");
		
		if (m_metaData != null)
		{
			properties.put("Exposure Time", String.valueOf(m_metaData.getExposureTime()));
			properties.put("Exposure Type", m_metaData.getType().asString());
		}
	}

}
