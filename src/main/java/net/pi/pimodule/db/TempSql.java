package net.pi.pimodule.db;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

		//		logger.debug("findCurrentTemp: " + recName);

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


	public void updateTemp(DBConnection con, TempEntity temp) throws SQLException {
		//		logger.debug("Update temperature");
		//		System.out.println("--upd--");

		int upd = con.buildUpdateQuery(TempEntity.TBL_NAME)
				.setParameter(TempEntity.BATT_LVL, temp.getBatteryLevel())
				.setParameter(TempEntity.HUMIDITY, temp.getHumidity())
				.setParameter(TempEntity.REC_DATE, temp.getRecordedDate())
				.setParameter(TempEntity.REC_NAME, temp.getRecorderName())
				.setParameter(TempEntity.TEMP, temp.getTempC())
				.addUpdWhereClause("Where "+TempEntity.ID+" = :idValue", temp.getId()).update();
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
				//				setTempProperties(temp,AA);
			}
			TempEntity BB = findCurrentTemp(con, TempRecName.BB.name());
			if (BB != null) {
				temp.setTmpSunUpdDt(temp.raw().format(BB.getRecordedDate()));
				temp.setTempSun(BB.getTempC() != null ? temp.tempFormat().format(Double.valueOf(BB.getTempC())) : "-90" );
				//				setTempProperties(temp,BB);
			}
			TempEntity pool = findCurrentTemp(con, TempRecName.pool.name());
			if (pool != null) {
				temp.setTmpPoolUpdDt(temp.raw().format(pool.getRecordedDate()));
				temp.setTempPool( (pool.getTempC() != null ? temp.tempFormat().format(Double.valueOf(pool.getTempC())) : "-90" ) );
				//				setTempProperties(temp,pool);
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
	private DBConnection getConnection() throws ClassNotFoundException, SQLException{

		Database db = new Database("jdbc:h2:" +Constants.DB_URL,Constants.DB_USER, Constants.DB_PASS.toCharArray(), DbClass.H2);
		return new DBConnection(db);
		//		Database db = new Database(Constants.DB_MYSQL,Constants.DB_USER, Constants.DB_PASS.toCharArray(), DbClass.Mysql);
		//				return  new DBConnection(Constants.DB_MYSQL, Constants.DB_USER, Constants.DB_PASS, DbClass.Mysql );
		//		return new DBConnec.tion(db);
	}

	//
	//	public void setTempProperties(Temperature temp, TempEntity t) {
	//		TempRecName rec = TempRecName.valueOf(t.getRecorderName());
	//
	//		if (rec == TempRecName.pool) {
	//			temp.setTmpPoolUpdDt(temp.sdf().format(t.getRecordedDate()));
	//			temp.setTempPool( (t.getTempC() != null ? temp.tempFormat().format(Double.valueOf(t.getTempC())) : "-90" ) );
	//
	//		}else if (rec == TempRecName.BB) {
	//
	//			temp.setTmpSunUpdDt(temp.sdf().format(t.getRecordedDate()));
	//			temp.setTempSun(t.getTempC() != null ? temp.tempFormat().format(Double.valueOf(t.getTempC())) : "-90" );
	//
	//		}else if (rec == TempRecName.AA) {
	//
	//			temp.setTmpShadeUpdDt(temp.sdf().format(t.getRecordedDate()));
	//			temp.setTempShade(t.getTempC() != null ? temp.tempFormat().format(Double.valueOf(t.getTempC())) : "-90" );
	//		}
	//	}
	public static void main (String args[]) throws ClassNotFoundException, SQLException, IOException {

		TempSql sql = new TempSql();
		sql.createTable();

		System.out.println("Get current stored temp");
		Temperature t1 = sql.getCurrentStoredTemperature();
		System.out.println("Stored temp: " + t1);
		System.out.println("BB record");
		TempEntity ent1 = new TempEntity();
		ent1.setRecordedDate(new Date());
		ent1.setRecorderName(TempRecName.BB.name());
		ent1.setTempC("33.5");

		sql.saveTemperature(ent1);
		t1 = sql.getCurrentStoredTemperature();
		System.out.println("Stored temp 2 : " + t1);

		System.out.println("BB record");
		ent1 = new TempEntity();
		ent1.setRecordedDate(new Date());
		ent1.setRecorderName(TempRecName.BB.name());
		ent1.setTempC("45");
		sql.saveTemperature(ent1);
		t1 = sql.getCurrentStoredTemperature();
		System.out.println("Stored temp 3 : " + t1);


		System.out.println("AA record");
		ent1 = new TempEntity();
		ent1.setRecordedDate(new Date());
		ent1.setRecorderName(TempRecName.AA.name());
		ent1.setTempC("45");
		sql.saveTemperature(ent1);
		t1 = sql.getCurrentStoredTemperature();
		System.out.println("Stored temp 4 : " + t1);

	}
}
