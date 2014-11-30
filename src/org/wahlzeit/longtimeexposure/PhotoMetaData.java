package org.wahlzeit.longtimeexposure;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.model.PhotoId;
import org.wahlzeit.services.DataObject;

public class PhotoMetaData extends DataObject {
	
	public final static String COL_ID      = "id";
	public final static String COL_TYPE    = "exposureType";
	public final static String COL_PHOTOID = "photoId";
	public final static String COL_TIME    = "exposureTime";
	public final static String TABLE       = "longTimeExposureMetaData";
	
	private int          m_id = -1;
	private PhotoId      m_photoId;
	private ExposureType m_type = ExposureType.UNSPECIFIED;
	private int          m_exposureTime = 0;
	
	@Override
	public String getIdAsString() {
		return String.valueOf(m_id);
	}

	@Override
	public void readFrom(ResultSet rset) throws SQLException {
		
		m_id = rset.getInt(COL_ID);
		m_photoId = PhotoId.getIdFromInt(rset.getInt(COL_PHOTOID));
		m_type = ExposureType.fromInt((rset.getInt(COL_TYPE)));
		m_exposureTime = rset.getInt(COL_TIME);
	}

	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		
		//rset.updateInt(COL_ID, m_id);
		rset.updateInt(COL_PHOTOID, m_photoId.asInt());
		rset.updateInt(COL_TYPE, m_type.asInt());
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
