/**
 * 
 */
package org.wahlzeit.longtimeexposure;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.model.PhotoId;
import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.services.Persistent;
import org.wahlzeit.services.SysLog;

/**
 * @author freeman
 *
 */
public class MetaDataManager extends ObjectManager {

	public PhotoMetaData getMetaDataForPhotoId(PhotoId photoId)
	{
		PhotoMetaData result = null;
		
		try {
			PreparedStatement stmt = getReadingStatement("SELECT * FROM " + PhotoMetaData.TABLE + " WHERE photoId = ?");
			
			result = (PhotoMetaData)readObject(stmt, photoId.asInt());
			
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
	
	public void saveMetaData(PhotoMetaData meta)
	{
		try {
			PreparedStatement stmt = getUpdatingStatement("SELECT * FROM " + PhotoMetaData.TABLE + " WHERE id = ?");

			if (meta.isDirty())
			{
				if (readObject(stmt, meta.getId()) != null) {
					updateObject(meta, stmt);
				} else {
					insertObject(meta, stmt);
				}
			}
		} catch (SQLException e) {
			SysLog.logThrowable(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.wahlzeit.services.ObjectManager#createObject(java.sql.ResultSet)
	 */
	@Override
	protected Persistent createObject(ResultSet rset) throws SQLException {
		PhotoMetaData md = new PhotoMetaData();
		md.readFrom(rset);
		
		return md;
	}
	
	protected void insertObject(PhotoMetaData obj, PreparedStatement stmt)
	{
		SysLog.logQuery(stmt);
		try {
			obj.writeId(stmt, 1);
			ResultSet rset = stmt.executeQuery();
			rset.moveToInsertRow();
			obj.writeOn(rset);
			rset.insertRow();
			obj.resetWriteCount();
			
		} catch (SQLException e) {
			SysLog.logThrowable(e);
		}
	}
}
