package net.pi.pimodule.db;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

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

public class SensorSql {

	private static final Logger logger = LogManager.getLogger(SensorSql.class);

	public void createTable() throws IOException, SQLException, ClassNotFoundException {
		DBConnection con = null;
		boolean exist = false;
		try {
			con = getConnection();

			DatabaseMetaData md = con.getConnection().getMetaData();
			ResultSet rs = md.getTables(null, null, SensorEntity.TBL_NAME.toUpperCase(), null);

			while (rs.next()) {
				exist = true;
			}

			logger.debug("Creating SensorSql table. Teble exist: " + exist);


			if (!exist) {
				logger.info("SensorSql does not exist , creating");
				List<ColumnType> columns = new ArrayList<ColumnType>();					
				columns.add(new ColumnType(SensorEntity.ID, true).INT().setPKCriteria(new PkCriteria().autoIncrement()));
				columns.add(new ColumnType(SensorEntity.BATT_LVL).VarChar(8));
				columns.add(new ColumnType(SensorEntity.LAST_TRANSMIT).TimeStamp());
				columns.add(new ColumnType(SensorEntity.LAST_UPDATED).TimeStamp());
				columns.add(new ColumnType(SensorEntity.POWER_SAVE).Boolean());
				columns.add(new ColumnType(SensorEntity.POWER_SAVE_END).INT());
				columns.add(new ColumnType(SensorEntity.POWER_SAVE_ST).INT());
				columns.add(new ColumnType(SensorEntity.POWER_SAVE_TRANS_FREQ).INT());
				columns.add(new ColumnType(SensorEntity.SENSOR_ID).VarChar(5));
				columns.add(new ColumnType(SensorEntity.SENSOR_TYPE).VarChar(20));
				columns.add(new ColumnType(SensorEntity.TRANS_FREQ).INT());
				columns.add(new ColumnType(SensorEntity.CONFIGURED).Boolean());
				columns.add(new ColumnType(SensorEntity.DESCRIPTION).VarChar(2000));
				columns.add(new ColumnType(SensorEntity.ERROR_FIELD).VarChar(500));

				con.createTable(SensorEntity.TBL_NAME, columns);	

			}
		}finally {
			if (con != null) {
				con.close();
			}
		}	
	}

	public SensorEntity findSensor(SensorType type, String sensorId ) throws SQLException, ClassNotFoundException {

		DBConnection con = null;
		SensorEntity sensor = null;
		try {
			con = getConnection();

			ResultSet rs = con.createSelectQuery("SELECT * FROM " + SensorEntity.TBL_NAME + " WHERE " + SensorEntity.SENSOR_TYPE + " = :sensorType AND " +SensorEntity.SENSOR_ID + " = :sensorId" )
					.setParameter("sensorType", type.name())
					.setParameter("sensorId", sensorId)
					.getSelectResultSet();


			if (rs!=null) {
				while(rs.next()) {
					sensor  = new SensorEntity(rs);
				}
			}

		}finally {
			if (con != null) {
				con.close();
			}
		}

		return sensor;
	}
	/**
	 * Get all sensors	
	 * @param withLocation - also get the location fort he sensor if the sensor has a location
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<SensorEntity> getAllSensors(boolean withLocation) throws SQLException, ClassNotFoundException {

		DBConnection con = null;
		List<SensorEntity> sensors = new ArrayList<>();
		try {
			con = getConnection();

			ResultSet rs = con.createSelectQuery("SELECT * FROM " + SensorEntity.TBL_NAME )
					.getSelectResultSet();


			if (rs!=null) {
				while(rs.next()) {
					SensorEntity sensor  = new SensorEntity(rs);

					if (withLocation) {

						ResultSet rs2 = con.createSelectQuery("SELECT * FROM " + SensorLocation.TBL_NAME + " WHERE " + SensorLocation.SENSOR_ID_FK + " = :senId")
								.setParameter("senId", sensor.getId())
								.getSelectResultSet();

						while(rs2.next()) {
							SensorLocation sl = new SensorLocation(rs2);
							sensor.setSensorLocation(sl);
						}
						sensors.add(sensor);
					}
				}
			}

		}finally {
			if (con != null) {
				con.close();
			}
		}

		return sensors;
	}
	/**
	 * Get sensor that has no location attached.
	 */
	public List<SensorEntity> getAllSensorsWithNoLocation() throws SQLException, ClassNotFoundException {

		DBConnection con = null;
		List<SensorEntity> sensors = new ArrayList<>();
		try {
			con = getConnection();

			ResultSet rs = con.createSelectQuery("SELECT * FROM " + SensorEntity.TBL_NAME )
					.getSelectResultSet();


			if (rs!=null) {
				while(rs.next()) {
					SensorEntity sensor  = new SensorEntity(rs);

					ResultSet rs2 = con.createSelectQuery("SELECT "+SensorLocation.ID+"  FROM " + SensorLocation.TBL_NAME + " WHERE " + SensorLocation.SENSOR_ID_FK + " = :senId")
							.setParameter("senId", sensor.getId())
							.getSelectResultSet();
					boolean noLocationFound = true;

					while(rs2.next()) {
						noLocationFound = false;
					}

					if (noLocationFound) {
						sensors.add(sensor);
					}
				}
			}

		}finally {
			if (con != null) {
				con.close();
			}
		}

		return sensors;
	}
	/**
	 * only get the sensors that has a location
	 */
	public List<SensorEntity> getAllSensorsWithLocation() throws SQLException, ClassNotFoundException {

		DBConnection con = null;
		List<SensorEntity> sensors = new ArrayList<>();
		try {
			con = getConnection();

			ResultSet rs = con.createSelectQuery("SELECT * FROM " + SensorEntity.TBL_NAME )
					.getSelectResultSet();


			if (rs!=null) {
				while(rs.next()) {
					SensorEntity sensor  = new SensorEntity(rs);

					ResultSet rs2 = con.createSelectQuery("SELECT *  FROM " + SensorLocation.TBL_NAME + " WHERE " + SensorLocation.SENSOR_ID_FK + " = :senId")
							.setParameter("senId", sensor.getId())
							.getSelectResultSet();


					while(rs2.next()) {
						SensorLocation sl = new SensorLocation(rs2);
						sensor.setSensorLocation(sl);
						sensors.add(sensor);
					}
				}
			}

		}finally {
			if (con != null) {
				con.close();
			}
		}

		return sensors;
	}

	public SensorEntity findSensorById(int id, boolean fetchLocation) throws SQLException, ClassNotFoundException {

		DBConnection con = null;
		SensorEntity sensor = null;
		try {
			con = getConnection();

			ResultSet rs = con.createSelectQuery("SELECT * FROM " + SensorEntity.TBL_NAME + " WHERE " + SensorEntity.ID + " = :sensorId")
					.setParameter("sensorId", id)
					.getSelectResultSet();


			if (rs!=null) {
				while(rs.next()) {
					sensor  = new SensorEntity(rs);

					if (fetchLocation) {
						logger.debug("findSensorById: fetchLocation: " + fetchLocation);

						ResultSet rs2 = con.createSelectQuery("SELECT * FROM " + SensorLocation.TBL_NAME + " WHERE " + SensorLocation.SENSOR_ID_FK + " = :senId")
								.setParameter("senId", id)
								.getSelectResultSet();

						while(rs2.next()) {
							SensorLocation sl = new SensorLocation(rs2);
							sensor.setSensorLocation(sl);
							//							logger.debug("setSensorLocation: sl: " + sl);
						}
					}

				}
			}

		}finally {
			if (con != null) {
				con.close();
			}
		}

		return sensor;
	}

	public String getNextSensorId(SensorType t) throws SQLException, ClassNotFoundException {
		DBConnection con = null;
		String nextId = "000";
		try {
			con = getConnection();

			ResultSet rs = con.createSelectQuery("SELECT * FROM " + SensorEntity.TBL_NAME + " WHERE " + SensorEntity.SENSOR_TYPE + " = :sensorType")
					.setParameter("sensorType", t.name())
					.getSelectResultSet(); 
			TreeSet<String> sensors = new TreeSet<>();

			if (rs!=null) {
				while(rs.next()) {
					SensorEntity sensor  = new SensorEntity(rs);
					sensors.add(sensor.getSensorId());
				}
			}

			//			logger.debug("getNextSensorId: " + t + " sensors: " + sensors + "  get last: " + (sensors.isEmpty() ? "empty" : sensors.last()));

			if (!sensors.isEmpty()) {
				try {
					int currId = Integer.parseInt(sensors.last());
					currId ++;
					nextId  = String.format("%03d", currId); 
				}catch(NumberFormatException nfx) {
					//					logger.error("Current sensor ID not numeric. " + sensors.last());
					throw new SQLException("Current Sensor ID not numeric: " + sensors.last());
				}
			}else {
				nextId = "001";
			}

		}finally {
			if (con != null) {
				con.close();
			}
		}

		return nextId;
	}

	public int addSensor(SensorEntity sensor) throws SQLException, ClassNotFoundException {
		DBConnection con = null;
		int pk = -1;
		try {
			con = getConnection();

			pk = con.buildAddQuery(SensorEntity.TBL_NAME)
					.setParameter(SensorEntity.BATT_LVL, sensor.getBattLvl())
					.setParameter(SensorEntity.LAST_TRANSMIT, sensor.getLastTransmit())
					.setParameter(SensorEntity.LAST_UPDATED, sensor.getLastUpdated())
					.setParameter(SensorEntity.POWER_SAVE, sensor.isPowerSave())
					.setParameter(SensorEntity.POWER_SAVE_END, sensor.getPwSaveEnd())
					.setParameter(SensorEntity.POWER_SAVE_ST, sensor.getPwSaveStart())
					.setParameter(SensorEntity.POWER_SAVE_TRANS_FREQ, sensor.getPwSaveTransFreq())
					.setParameter(SensorEntity.SENSOR_ID, sensor.getSensorId())
					.setParameter(SensorEntity.SENSOR_TYPE, sensor.getSensorType().name())
					.setParameter(SensorEntity.TRANS_FREQ, sensor.getTransFreq())
					.setParameter(SensorEntity.CONFIGURED, sensor.isConfigured())
					.setParameter(SensorEntity.DESCRIPTION, sensor.getDescription())
					.setParameter(SensorEntity.ERROR_FIELD, sensor.getErrorField())
					.add();

		}finally {
			if (con != null) {
				con.close();
			}
		}

		return pk;
	}

	public void updateSensor(SensorEntity sensor) throws SQLException, ClassNotFoundException {
		//		logger.debug("Update temperature");
		//		System.out.println("--upd--");
		DBConnection con = null;
		try {
			con = getConnection();
			con.buildUpdateQuery(SensorEntity.TBL_NAME)
			.setParameter(SensorEntity.BATT_LVL, sensor.getBattLvl())
			.setParameter(SensorEntity.LAST_TRANSMIT, sensor.getLastTransmit())
			.setParameter(SensorEntity.LAST_UPDATED, sensor.getLastUpdated())
			.setParameter(SensorEntity.POWER_SAVE, sensor.isPowerSave())
			.setParameter(SensorEntity.POWER_SAVE_END, sensor.getPwSaveEnd())
			.setParameter(SensorEntity.POWER_SAVE_ST, sensor.getPwSaveStart())
			.setParameter(SensorEntity.POWER_SAVE_TRANS_FREQ, sensor.getPwSaveTransFreq())
			.setParameter(SensorEntity.SENSOR_ID, sensor.getSensorId())
			.setParameter(SensorEntity.SENSOR_TYPE, sensor.getSensorType().name())
			.setParameter(SensorEntity.TRANS_FREQ, sensor.getTransFreq())
			.setParameter(SensorEntity.CONFIGURED, sensor.isConfigured())
			.setParameter(SensorEntity.DESCRIPTION, sensor.getDescription())
			.setParameter(SensorEntity.ERROR_FIELD, sensor.getErrorField())
			.addUpdWhereClause("Where "+SensorEntity.ID+" = :idValue", sensor.getId()).update();

		}finally {
			if (con != null) {
				con.close();
			}
		}
	}

	public void updateLastTransmit(int id, Date lastTransmit, boolean cleanErrorField) throws SQLException, ClassNotFoundException {
		logger.debug("updateLastTransmit. id: " + id + " LastTransmit: " +  lastTransmit);
		DBConnection con = null;
		try {
			con = getConnection();
			con.buildUpdateQuery(SensorEntity.TBL_NAME)
			.setParameter(SensorEntity.LAST_TRANSMIT, lastTransmit);
			if (cleanErrorField) {
				con.setParameter(SensorEntity.ERROR_FIELD, "");
			}
			con.addUpdWhereClause("Where "+SensorEntity.ID+" = :idValue", id).update();

		}finally {
			if (con != null) {
				con.close();
			}
		}
	}

	public void updateLastUpdateAndTransmit(int id, Date lastUpdate) throws SQLException, ClassNotFoundException {
		logger.debug("updateLastUpdateAndTransmit. id: " + id + " lastUpdate: " +  lastUpdate);
		DBConnection con = null;
		try {
			con = getConnection();
			con.buildUpdateQuery(SensorEntity.TBL_NAME)
			.setParameter(SensorEntity.LAST_TRANSMIT, lastUpdate)
			.setParameter(SensorEntity.LAST_UPDATED, lastUpdate)
			.addUpdWhereClause("Where "+SensorEntity.ID+" = :idValue", id).update();

		}finally {
			if (con != null) {
				con.close();
			}
		}
	}

	public void deleteSensor(SensorEntity sensor, boolean deleteLocation) throws ClassNotFoundException, SQLException {
		DBConnection con = null;
		try{
			con = getConnection();

			String query = "DELETE FROM " + SensorEntity.TBL_NAME + " where " + SensorEntity.ID + " = :id";

			int deleted = con.createSelectQuery(query)
					.setParameter("id", sensor.getId())
					.delete();

			//if there is a sensor location related to the sensor, remove the sensor from it.
			if (deleteLocation && sensor.getSensorLocation() != null) {
				SensorLocSql locSql = new SensorLocSql();

				SensorLocation loc = locSql.findLocationBySensorId(sensor.getId());

				if (loc != null) {
					loc.setSensorIdFk(-1);
					locSql.updateLocation(loc);
				}
			}

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
