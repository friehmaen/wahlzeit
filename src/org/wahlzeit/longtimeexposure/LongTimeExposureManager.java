package org.wahlzeit.longtimeexposure;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.model.PhotoId;
import org.wahlzeit.model.PhotoManager;
import org.wahlzeit.services.Persistent;
import org.wahlzeit.services.SysLog;

public class LongTimeExposureManager extends PhotoManager {
	
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
		
		result.setMetaData(getMetaDataFromPhotoId(result.getId()));
		
		
		return result;
	}
	
	PhotoMetaData getMetaDataFromPhotoId(PhotoId photoId)
	{
		PhotoMetaData result = null;
		
		try {
			PreparedStatement stmt = getReadingStatement("SELECT * FROM " + PhotoMetaData.TABLE + " WHERE photoId = ?");
			
			result = (PhotoMetaData) readObject(PhotoMetaData.class, stmt, photoId.asInt());
			
			if (result == null)
			{
				result = new PhotoMetaData();
				result.setPhotoId(photoId);
			}
			
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
		
		return result;
	}
	
	protected void updateDependents(Persistent obj) throws SQLException {
		super.updateDependents(obj);
		
		LongTimeExposure photo = (LongTimeExposure) obj;
		
		saveMetaData(photo.getMetaData());
	}
	
	protected void saveMetaData(PhotoMetaData meta)
	{
		try {
			PreparedStatement stmt = getUpdatingStatement("SELECT * FROM " + PhotoMetaData.TABLE + " WHERE id = ?");

			if (meta.isDirty()) {
				meta.writeId(stmt, 1);
				SysLog.logQuery(stmt);
				ResultSet rset = stmt.executeQuery();
				if (rset.next()) {
					meta.writeOn(rset);
					rset.updateRow();
					meta.resetWriteCount();
				} else {
					rset.moveToInsertRow();
					meta.writeOn(rset);
					rset.insertRow();
					meta.resetWriteCount();
					//SysLog.logSysError("trying to update non-existent object: " + meta.getIdAsString() + "(" + meta.toString() + ")");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	protected <T extends Persistent> Persistent readObject(Class<T> object, PreparedStatement stmt, int value) throws SQLException 
	{
		Persistent result = null;
		stmt.setInt(1, value);
		SysLog.logQuery(stmt);
		ResultSet rset = stmt.executeQuery();
		if (rset.next()) 
		{
			try {
				result = object.newInstance();
				result.readFrom(rset);
			} catch (InstantiationException | IllegalAccessException e) {
				result = null;
			}
		}
		
		return result;
	}
}
