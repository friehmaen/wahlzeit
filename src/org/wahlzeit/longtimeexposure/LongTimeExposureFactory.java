

package org.wahlzeit.longtimeexposure;

import java.sql.*;

import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.services.*;


public class LongTimeExposureFactory extends PhotoFactory {
	
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	private static LongTimeExposureFactory instance = null;
	
	/**
	 * Public singleton access method.
	 */
	public static synchronized PhotoFactory getInstance() {
		if (instance == null) {
			SysLog.logSysInfo("setting generic PhotoFactory");
			setInstance(new LongTimeExposureFactory());
		}
		
		return instance;
	}
	
	/**
	 * Method to set the singleton instance of PhotoFactory.
	 */
	protected static synchronized void setInstance(LongTimeExposureFactory photoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize PhotoFactory twice");
		}
		
		instance = photoFactory;
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
