package net.pi.pimodule.db;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import home.common.data.Temperature;
import home.common.data.Temperature.TempRecName;
import home.db.ColumnType;
import home.db.DBConnection;
import home.db.Database;
import home.db.DbClass;
import home.db.PkCriteria;
import net.pi.pimodule.common.Constants;
import net.pi.pimodule.enums.SensorType;


public class TempSql {

	private static final Logger logger = LogManager.getLogger(TempSql.class);


	public void createTable() throws IOException, SQLException, ClassNotFoundException {
		logger.debug("Creating Temperature table. Teble exist: " );
		DBConnection con = null;
		boolean exist = false;
		try {
			con = getConnection();

			DatabaseMetaData md = con.getConnection().getMetaData();
			ResultSet rs = md.getTables(null, null, TempEntity.TBL_NAME.toUpperCase(), null);

			while (rs.next()) {
				exist = true;
			}

			logger.debug("Creating Temperature table. Teble exist: " + exist);

			if (!exist) {
				logger.info("User table does not exist , creating");
				List<ColumnType> columns = new ArrayList<ColumnType>();					
				columns.add(new ColumnType(TempEntity.ID, true).INT().setPKCriteria(new PkCriteria().autoIncrement()));
				columns.add(new ColumnType(TempEntity.BATT_LVL, false).VarChar(10));
				columns.add(new ColumnType(TempEntity.HUMIDITY, false).VarChar(10));
				columns.add(new ColumnType(TempEntity.REC_DATE, false).TimeStamp());
				columns.add(new ColumnType(TempEntity.REC_NAME, false).VarChar(30));
				columns.add(new ColumnType(TempEntity.TEMP, false).VarChar(10));

				con.createTable(TempEntity.TBL_NAME, columns);	

			}
		}finally {
			if (con != null) {
				con.close();
			}
		}	
	}


	public TempEntity findCurrentTemp(DBConnection con, String recName) throws SQLException {

		TempEntity ent = null;

		ResultSet rs = con.createSelectQuery("SELECT * FROM " + TempEntity.TBL_NAME + " WHERE " + TempEntity.REC_NAME + " = :recName" )
				.setParameter("recName", recName)
				.getSelectResultSet();


		if (rs!=null) {
			while(rs.next()) {
				ent  = new TempEntity(rs);
			}
		}

		return ent;
	}

	/** find the current temperature by sensor location
	 * 
	 * @param loc
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public TempEntity findCurrentTempByLocation(SensorLocation loc) throws SQLException, ClassNotFoundException {
		DBConnection con = null;
		TempEntity ent  = null;
		try {
			con = getConnection();
			if (loc.getSensorIdFk() > 0) {
				ent = currentMaxTemp(con, loc.getSensorIdFk());
			}
		}finally {
			if (con != null) {
				con.close();
			}
		}
		
		return ent;
	}


	public TempEntity findCurrentTempByLocationName(DBConnection con, String location) throws SQLException {

		//1st find location..
		TempEntity ent = null;

		ResultSet rsLoc = con.createSelectQuery("SELECT "+SensorLocation.SENSOR_ID_FK+" FROM " + SensorLocation.TBL_NAME + " WHERE " + SensorLocation.SENSOR_LOCATION + " = :locationName" )
				.setParameter("locationName", location)
				.getSelectResultSet();
		int sensorIdFk = -1;


		while(rsLoc.next()) {
			sensorIdFk = rsLoc.getInt(SensorLocation.SENSOR_ID_FK);
		}

		//got a sensor id from the location .. go find the sensor
		if (sensorIdFk > 0) {			
			ent = currentMaxTemp(con, sensorIdFk);
		}else {
			logger.info("findCurrentTempByLocationName::No location found by the name: " + location);
		}

		return ent;
	}


	public int updateTemp(DBConnection con, TempEntity temp) throws SQLException {

		int upd = con.buildUpdateQuery(TempEntity.TBL_NAME)
				.setParameter(TempEntity.BATT_LVL, temp.getBatteryLevel())
				.setParameter(TempEntity.HUMIDITY, temp.getHumidity())
				.setParameter(TempEntity.REC_DATE, temp.getRecordedDate())
				.setParameter(TempEntity.REC_NAME, temp.getRecorderName())
				.setParameter(TempEntity.TEMP, temp.getTempC())
				.addUpdWhereClause("Where "+TempEntity.ID+" = :idValue", temp.getId()).update();
		
		return upd;
	}

	public void saveTemperature(TempEntity entity) throws ClassNotFoundException, SQLException {
		logger.debug("Saving temp on database : " + entity);

		DBConnection con = null;
		try {
			con = getConnection();
			TempEntity dbEntity = findCurrentTemp(con, entity.getRecorderName());

			if(dbEntity != null) {
				//update
				dbEntity.setBatteryLevel(entity.getBatteryLevel());
				dbEntity.setHumidity(entity.getHumidity());
				dbEntity.setRecordedDate(entity.getRecordedDate());
				dbEntity.setRecorderName(entity.getRecorderName());
				dbEntity.setTempC(entity.getTempC());

				updateTemp(con, dbEntity);
			}else {
				//add
				int pk = addTemp(con, entity);
				entity.setId(pk);;
			}

		}finally {
			if (con != null) {
				con.close();
			}
		}	
	}
	/**
	 * Add temperature to DB 
	 * @param temp
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int addTemp(TempEntity temp) throws SQLException, ClassNotFoundException {
		DBConnection con = null;
		int pk = -1;
		try {
			con = getConnection();
			pk = addTemp(con, temp);
		}finally {
			if (con != null) {
				con.close();
			}
		}

		return pk;
	}

	/**
	 * Get the current stored temperature.. passing a list to get the one required o
	 * @param rec
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Temperature getCurrentStoredTemperature(TempRecName... rec) throws SQLException, ClassNotFoundException {
		DBConnection con = null;
		Temperature temp ;
		try {
			con = getConnection();

			temp = new Temperature();

			TempEntity AA = findCurrentTemp(con, TempRecName.AA.name());
			if (AA != null) {
				temp.setTmpShadeUpdDt(temp.raw().format(AA.getRecordedDate()));
				temp.setTempShade(AA.getTempC() != null ? temp.tempFormat().format(Double.valueOf(AA.getTempC())) : "-90" );
			}
			TempEntity BB = findCurrentTemp(con, TempRecName.BB.name());
			if (BB != null) {
				temp.setTmpSunUpdDt(temp.raw().format(BB.getRecordedDate()));
				temp.setTempSun(BB.getTempC() != null ? temp.tempFormat().format(Double.valueOf(BB.getTempC())) : "-90" );
			}
			TempEntity pool = findCurrentTempByLocationName(con, TempRecName.pool.name());
			if (pool != null) {
				temp.setTmpPoolUpdDt(temp.raw().format(pool.getRecordedDate()));
				temp.setTempPool( (pool.getTempC() != null ? temp.tempFormat().format(Double.valueOf(pool.getTempC())) : "-90" ) );
			}
			//add new
			for(TempRecName r : TempRecName.getNewSensors()) {
				TempEntity tempSensor = findCurrentTemp(con, r.name());

				if (tempSensor != null) {
					Map<TempRecName, String> dt = temp.getTempDateMap();
					dt.put(r, temp.raw().format(tempSensor.getRecordedDate() ));

					Map<TempRecName, String> tempMap = temp.getTempMap();
					tempMap.put(r, tempSensor.getTempC());
				}
			}

		}finally {
			if (con != null) {
				con.close();
			}
		}	

		return temp;
	}

	/** new
	 * Clean up the temperature db.. 
	 * 
	 * @param cleanUpFrom
	 * 			- Give a date to clean up to. So if we want to clean up the last month, the 'cleanUpFrom' would be
	 * 				 Current date (Now is 2021-04-14) - 1 month = cleanUpFrom (2021-03-14) .  So everything before that will be deleted.
	 * 			
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int cleanUpTempDbByDate(LocalDateTime cleanUpFrom) throws ClassNotFoundException, SQLException {
		DBConnection con = null;
		int deleted = 0;
		try {
			con = getConnection();
			String query = "DELETE FROM " + TempEntity.TBL_NAME + " where " + TempEntity.REC_DATE + " <= :date";

			deleted = con.createSelectQuery(query)
			.setParameter("date", cleanUpFrom)
			.delete();

		}finally {
			if (con != null) {
				con.close();
			}

		}	
		
		return deleted;

	}
	private int addTemp(DBConnection con, TempEntity temp) throws SQLException {
		//		logger.debug("add temperature");

		int pk = con.buildAddQuery(TempEntity.TBL_NAME)
				.setParameter(TempEntity.BATT_LVL, temp.getBatteryLevel())
				.setParameter(TempEntity.HUMIDITY, temp.getHumidity())
				.setParameter(TempEntity.REC_DATE, temp.getRecordedDate())
				.setParameter(TempEntity.REC_NAME, temp.getRecorderName())
				.setParameter(TempEntity.TEMP, temp.getTempC())
				.add();

		return pk;
	}

	/**
	 * Private method to get the current most recent temperature for a sensor.
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	private TempEntity currentMaxTemp(DBConnection con, int sensorId) throws SQLException {

		TempEntity ent = null;

		ResultSet rsSensor = con.createSelectQuery("SELECT " +SensorEntity.SENSOR_TYPE+", "+SensorEntity.SENSOR_ID+" FROM " + SensorEntity.TBL_NAME + " WHERE " + SensorEntity.ID + " = :sensorId" )
				.setParameter("sensorId", sensorId)
				.getSelectResultSet();

		//sensor found , fetch the current temperature.
		if (rsSensor!=null) {
			while(rsSensor.next()) {

				String sensorName  = SensorType.valueOf(rsSensor.getString(SensorEntity.SENSOR_TYPE)).name() + rsSensor.getString(SensorEntity.SENSOR_ID);
				System.out.println("Sensor name: " + sensorName);

				ResultSet rsTemp = con.createSelectQuery("SELECT * FROM " + TempEntity.TBL_NAME + " WHERE " + TempEntity.REC_DATE +"=(Select MAX("+TempEntity.REC_DATE+") FROM " + TempEntity.TBL_NAME + " WHERE " + TempEntity.REC_NAME + " = :sensorName  ) " )
						.setParameter("sensorName", sensorName)
						.getSelectResultSet();


				while(rsTemp.next()) {
					ent = new TempEntity(rsTemp);
				}
			}
		}

		return ent;
	}

		private DBConnection getConnection() throws ClassNotFoundException, SQLException{
	
			Database db = new Database("jdbc:h2:" +Constants.DB_URL,Constants.DB_USER, Constants.DB_PASS.toCharArray(), DbClass.H2);
			return new DBConnection(db);
		}








//
//	private DBConnection getConnection() throws ClassNotFoundException, SQLException{
//
//		Database db = new Database("jdbc:h2:" +DB_URL,DB_USER, DB_PASS.toCharArray(), DbClass.H2);
//		return new DBConnection(db);
//	}

	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		TempSql sql = new TempSql();
//		Temperature t = sql.getCurrentStoredTemperature2();

//		System.out.println(t);
		  LocalDateTime now = LocalDateTime.now().minusWeeks(2);
		
	        int deleted = sql.cleanUpTempDbByDate(now);
	        
	        System.out.println("Date used: " + now + "  Deleted: " + deleted);

				sql.selectAll();

	}
	public static final String DB_URL = "c:\\temp\\piModule";
	public static final String DB_USER = "piModuleUser";
	public static final String DB_PASS = "109256";
	public  void selectAll() throws ClassNotFoundException, SQLException {
		DBConnection con = null;

		try {
			con = getConnection();
			String query = "SELECT * FROM temperature ";

			ResultSet rs = con.createSelectQuery(query)
					.getSelectResultSet();

			display(rs);

		}finally {
			if (con != null) {
				con.close();
			}

		}


	}
	private  void display(ResultSet rs) throws SQLException {
		int nbr = 0;
		if (rs!=null) {
			while(rs.next()) {
				nbr ++;
//				System.out.println("--------------- sTART ---------------");
//				System.out.println("ID: " + rs.getInt("id"));
//				System.out.println("TEmp: " + rs.getString("temp_c"));
				System.out.println("REc Date: " + rs.getTimestamp("recorded_date") + ",");
//				System.out.println("Rec name: " +  rs.getString("recorder_name"));
//				System.out.println("Batt Level: " +  rs.getString("battery_level"));
//				System.out.println("humidity: " +  rs.getString("humidity"));
			}
		}
		
		System.out.println("\nNumber of record on the DB: " + nbr);
	}
}
