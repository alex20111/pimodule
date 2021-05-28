package net.pi.pimodule.db;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import home.db.ColumnType;
import home.db.DBConnection;
import home.db.Database;
import home.db.DbClass;
import home.db.PkCriteria;
import net.pi.pimodule.common.Constants;
import net.pi.pimodule.db.entity.SensorEntity;
import net.pi.pimodule.db.entity.SensorLocation;
import net.pi.pimodule.enums.SensorType;

public class SensorLocSql {

	private static final Logger logger = LogManager.getLogger(SensorLocSql.class);

	public void createTable() throws IOException, SQLException, ClassNotFoundException {
		DBConnection con = null;
		boolean exist = false;
		try {
			con = getConnection();

			DatabaseMetaData md = con.getConnection().getMetaData();
			ResultSet rs = md.getTables(null, null, SensorLocation.TBL_NAME.toUpperCase(), null);

			while (rs.next()) {
				exist = true;
			}

			logger.debug("Creating SensorLocation table. Teble exist: " + exist);

			//			if (exist) {
			//				logger.debug("Dropping table !!!!!!!!!!!!!!!");
			//				//drop table
			//				String sqlDrop = "DROP TABLE " +SensorLocation.TBL_NAME + ";";
			//				con.createSelectQuery(sqlDrop);
			//				con.executeUpdate();
			//				exist = false;
			//			}


			if (!exist) {
				logger.info("SensorSql does not exist , creating");
				List<ColumnType> columns = new ArrayList<ColumnType>();					
				columns.add(new ColumnType(SensorLocation.ID, true).INT().setPKCriteria(new PkCriteria().autoIncrement()));
				columns.add(new ColumnType(SensorLocation.SENSOR_LOCATION).VarChar(20));
				columns.add(new ColumnType(SensorLocation.SENSOR_DESC).VarChar(1500));
				columns.add(new ColumnType(SensorLocation.SENSOR_ID_FK).INT());


				con.createTable(SensorLocation.TBL_NAME, columns);	

			}
		}finally {
			if (con != null) {
				con.close();
			}
		}	
	}
	public List<SensorLocation> getAllSensorLocation(boolean sensorName) throws SQLException, ClassNotFoundException {

		DBConnection con = null;
		List<SensorLocation> locations = new ArrayList<>();
		try {
			con = getConnection();

			ResultSet rs = con.createSelectQuery("SELECT * FROM " + SensorLocation.TBL_NAME )
					.getSelectResultSet();


			if (rs!=null) {
				while(rs.next()) {
					SensorLocation loc  = new SensorLocation(rs);


					if (sensorName && loc.getSensorIdFk() > 0) {
						ResultSet rs2 = con.createSelectQuery("SELECT "+SensorEntity.SENSOR_ID+","+SensorEntity.SENSOR_TYPE+""
								+ " FROM " + SensorEntity.TBL_NAME + " WHERE " + SensorEntity.ID + " = :sensorId")
								.setParameter("sensorId", loc.getSensorIdFk())
								.getSelectResultSet();

						while(rs2.next()) {
							SensorType t = SensorType.valueOf(rs2.getString(SensorEntity.SENSOR_TYPE));
							loc.setSensorName(t.name() + " " + rs2.getString(SensorEntity.SENSOR_ID));
						}
					}

					locations.add(loc);
				}
			}

		}finally {
			if (con != null) {
				con.close();
			}
		}

		return locations;
	}
	/**
	 * Get all sensors location that does not have a sensor associated to it,.
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<SensorLocation> getAllLocationWithoutSensors() throws SQLException, ClassNotFoundException {

		DBConnection con = null;
		List<SensorLocation> locations = new ArrayList<>();
		try {
			con = getConnection();

			ResultSet rs = con.createSelectQuery("SELECT * FROM " + SensorLocation.TBL_NAME +" WHERE " + SensorLocation.SENSOR_ID_FK + " < 0")
					.getSelectResultSet();


			if (rs!=null) {
				while(rs.next()) {
					SensorLocation loc  = new SensorLocation(rs);						
					locations.add(loc);
				}
			}

		}finally {
			if (con != null) {
				con.close();
			}
		}

		return locations;
	}
	public SensorLocation findLocationBySensorId(int sensorId) throws SQLException, ClassNotFoundException {
		DBConnection con = null;
		SensorLocation loc = null;
		try {
			con = getConnection();

			ResultSet rs = con.createSelectQuery("SELECT * FROM " + SensorLocation.TBL_NAME + " WHERE " + SensorLocation.SENSOR_ID_FK + " = :sensorId")
					.setParameter("sensorId", sensorId)
					.getSelectResultSet();


			if (rs!=null) {
				while(rs.next()) {
					loc  = new SensorLocation(rs);
				}
			}

		}finally {
			if (con != null) {
				con.close();
			}
		}

		return loc;
	}
	public SensorLocation findLocationById(int locId, boolean withSensor) throws SQLException, ClassNotFoundException {
		DBConnection con = null;
		SensorLocation loc = null;
		try {
			con = getConnection();

			ResultSet rs = con.createSelectQuery("SELECT * FROM " + SensorLocation.TBL_NAME + " WHERE " + SensorLocation.ID + " = :locId")
					.setParameter("locId", locId)
					.getSelectResultSet();


			if (rs!=null) {
				while(rs.next()) {
					loc  = new SensorLocation(rs);

					if (withSensor && loc.getSensorIdFk() > 0) {
						ResultSet rs2 = con.createSelectQuery("SELECT "+SensorEntity.SENSOR_ID+","+SensorEntity.SENSOR_TYPE+""
								+ " FROM " + SensorEntity.TBL_NAME + " WHERE " + SensorEntity.ID + " = :sensorId")
								.setParameter("sensorId", loc.getSensorIdFk())
								.getSelectResultSet();

						while(rs2.next()) {
							SensorType t = SensorType.valueOf(rs2.getString(SensorEntity.SENSOR_TYPE));
							loc.setSensorName(t.name() + " " + rs2.getString(SensorEntity.SENSOR_ID));
							loc.setSensorId(rs.getInt(SensorEntity.ID));
						}
					}
				}
			}

		}finally {
			if (con != null) {
				con.close();
			}
		}

		return loc;
	}
	public int addLocation(SensorLocation loc) throws SQLException, ClassNotFoundException {
		DBConnection con = null;
		int pk = -1;
		try {
			con = getConnection();

			pk = con.buildAddQuery(SensorLocation.TBL_NAME)
					.setParameter(SensorLocation.SENSOR_LOCATION, loc.getSensorLocation())
					.setParameter(SensorLocation.SENSOR_DESC, loc.getDescription())
					.setParameter(SensorLocation.SENSOR_ID_FK, loc.getSensorIdFk())					
					.add();

		}finally {
			if (con != null) {
				con.close();
			}
		}

		return pk;
	}

	public void updateLocation(SensorLocation loc) throws SQLException, ClassNotFoundException {
		//		logger.debug("Update temperature");
		//		System.out.println("--upd--");
		DBConnection con = null;
		try {
			con = getConnection();
			con.buildUpdateQuery(SensorLocation.TBL_NAME)
			.setParameter(SensorLocation.SENSOR_DESC, loc.getDescription())
			.setParameter(SensorLocation.SENSOR_ID_FK, loc.getSensorIdFk())
			.setParameter(SensorLocation.SENSOR_LOCATION, loc.getSensorLocation())

			.addUpdWhereClause("WHERE "+SensorLocation.ID+" = :idValue", loc.getId()).update();

		}finally {
			if (con != null) {
				con.close();
			}
		}
	}


	public void deleteLocation(int locId) throws ClassNotFoundException, SQLException {
		DBConnection con = null;
		try{
			con = getConnection();

			String query = "DELETE FROM " + SensorLocation.TBL_NAME + " where " + SensorLocation.ID + " = :id";

			con.createSelectQuery(query)
			.setParameter("id", locId)
			.delete();

		}finally{
			if (con!=null){
				con.close();
			}
		}
	}

	private DBConnection getConnection() throws ClassNotFoundException, SQLException{

		Database db = new Database("jdbc:h2:" +Constants.DB_URL,Constants.DB_USER, Constants.DB_PASS.toCharArray(), DbClass.H2);
		return new DBConnection(db);

	}
}
