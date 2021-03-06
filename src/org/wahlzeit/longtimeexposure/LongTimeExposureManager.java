package org.wahlzeit.longtimeexposure;

import java.sql.SQLException;

import org.wahlzeit.model.PhotoId;
import org.wahlzeit.model.PhotoManager;
import org.wahlzeit.services.Persistent;

/**
 * This is the manager class for all LongTimeExposure objects.
 * All data handling will be done here. 
 * 
 * This class is part of the following collaborations:
 * - Manager collaboration
 * - Factory collaboration
 */
public class LongTimeExposureManager extends PhotoManager {
	
	private MetaDataManager m_metaManager = null;
	
	LongTimeExposureManager()
	{
		m_metaManager = new MetaDataManager();
	}
	
	public static PhotoManager createInstance()
	{
		return new LongTimeExposureManager();
	}
	
	
	public LongTimeExposure getPhotoFromId(PhotoId id) {
		if (id.isNullId()) {
			return null;
		}

		LongTimeExposure result = (LongTimeExposure)super.getPhotoFromId(id);
		
		if (result == null)
			return result;
		
		result.setMetaData(m_metaManager.getMetaDataForPhotoId(result.getId()));
		
		
		return result;
	}
	
	protected void updateDependents(Persistent obj) throws SQLException {
		super.updateDependents(obj);
		
		LongTimeExposure photo = (LongTimeExposure) obj;
		
		m_metaManager.saveMetaData(photo.getMetaData());
	}
}
