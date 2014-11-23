package org.wahlzeit.longtimeexp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.model.PhotoId;
import org.wahlzeit.services.DataObject;

public class PhotoMetaData extends DataObject {
	
	public enum ExposureType
	{
		Darkness, NeutralFilter, OverExposed, Unspecified;
	}
	
	public final static String COL_ID      = "id";
	public final static String COL_TYPE    = "exposureType";
	public final static String COL_PHOTOID = "photoId";
	public final static String COL_TIME    = "exposureTime";
	public final static String TABLE       = "longTimeExposureMetaData";
	
	private int          m_id = -1;
	private PhotoId      m_photoId;
	private ExposureType m_type = ExposureType.Unspecified;
	private int          m_exposureTime = 0;
	
	@Override
	public String getIdAsString() {
		return String.valueOf(m_id);
	}

	@Override
	public void readFrom(ResultSet rset) throws SQLException {
		
		m_id = rset.getInt(COL_ID);
		m_photoId = PhotoId.getIdFromInt(rset.getInt(COL_PHOTOID));
		m_type = getTypeFromString(rset.getString(COL_TYPE));
		m_exposureTime = rset.getInt(COL_TIME);
	}

	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		
		rset.updateInt(COL_ID, m_id);
		rset.updateInt(COL_PHOTOID, m_photoId.asInt());
		rset.updateString(COL_TYPE, m_type.name());
		rset.updateInt(COL_TIME, m_exposureTime);
	}

	@Override
	public void writeId(PreparedStatement stmt, int pos) throws SQLException {
		stmt.setInt(pos, m_id);	
	}
	
	public void setType(ExposureType type) {
		this.m_type = type;
		
		incWriteCount();
	}
	
	public ExposureType getType() {
		return m_type;
	}
	
	public String getTypeAsString() {
		return m_type.name();
	}
	
	public static ExposureType getTypeFromString(String type)
	{
		ExposureType t;
		
		try {
			t = ExposureType.valueOf(type);
		}
		catch (IllegalArgumentException ex)
		{
			t = ExposureType.Unspecified;
		}
		
		return t;
	}

	public PhotoId getPhotoId() {
		return m_photoId;
	}

	public void setPhotoId(PhotoId photoId) {
		this.m_photoId = photoId;
	}

	public int getId() {
		return m_id;
	}

	public void setId(int id) {
		this.m_id = id;
	}

	public int getExposureTime() {
		return m_exposureTime;
	}

	public void setExposureTime(int exposureTime) {
		this.m_exposureTime = exposureTime;
		incWriteCount();
	}

}
