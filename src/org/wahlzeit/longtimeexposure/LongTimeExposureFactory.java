

package org.wahlzeit.longtimeexposure;

import java.sql.*;

import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.services.*;

/**
 * 
 * This is a factory. It should be used the create objects of the 
 * LongTimeExposure class
 * 
 * This class is part of the following collaborations:
 * - Factory collaboration
 */
public class LongTimeExposureFactory extends PhotoFactory {
	
	/**
	 * Public singleton access method.
	 */
	public static synchronized PhotoFactory getInstance() {
		if (doGetInstance() == null) {
			SysLog.logSysInfo("setting generic LongTimeExposureFactory");
			setInstance(new LongTimeExposureFactory());
		}
		
		return doGetInstance();
	}
	
	public static void initialize() {
		getInstance(); // drops result due to getInstance() side-effects
	}
	
	
	/**
	 * 
	 */
	protected LongTimeExposureFactory() {
		// do nothing
	}

	/**
	 * @methodtype factory
	 */
	public LongTimeExposure createPhoto() {
		return new LongTimeExposure();
	}
	
	/**
	 * 
	 */
	public LongTimeExposure createPhoto(PhotoId id) {
		return new LongTimeExposure(id);
	}
	
	/**
	 * 
	 */
	public LongTimeExposure createPhoto(ResultSet rs) throws SQLException {
		return new LongTimeExposure(rs);
	}
}
