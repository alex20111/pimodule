package net.pi.pimodule.db;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import home.db.ColumnType;
import home.db.DBConnection;
import home.db.Database;
import home.db.DbClass;
import home.db.PkCriteria;
import net.pi.pimodule.common.Constants;
import net.pi.pimodule.temperature.TempRecName;
import net.pi.pimodule.temperature.Temperature;


public class TempSql {

	private static final Logger logger = LogManager.getLogger(TempSql.class);



	public void createTable() throws IOException, SQLException, ClassNotFoundException {
		DBConnection con = null;
		boolean exist = false;
		try {
			con = getConnection();

			DatabaseMetaData md = con.getConnection().getMetaData();
			ResultSet rs = md.getTables(null, null, TempEntity.TBL_NAME.toUpperCase(), null);

			while (rs.next()) {
				exist = true;
			}

			System.out.println("Exist: " + exist);
			if (!exist) {
				logger.info("User table does not exist , creating");
				List<ColumnType> columns = new ArrayList<ColumnType>();					
				columns.add(new ColumnType(TempEntity.ID, true).INT().setPKCriteria(new PkCriteria().autoIncrement()));
				columns.add(new ColumnType(TempEntity.BATT_LVL, false).VarChar(10));
				columns.add(new ColumnType(TempEntity.HUMIDITY, false).VarChar(10));
				columns.add(new ColumnType(TempEntity.REC_DATE, false).TimeStamp());
				columns.add(new ColumnType(TempEntity.REC_NAME, false).VarChar(10));
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
	public int addTemp(DBConnection con, TempEntity temp) throws SQLException {
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

	public void updateTemp(DBConnection con, TempEntity temp) throws SQLException {
		logger.debug("Update temperature");
		System.out.println("--upd--");

		int upd = con.buildUpdateQuery(TempEntity.TBL_NAME)
				.setParameter(TempEntity.BATT_LVL, temp.getBatteryLevel())
				.setParameter(TempEntity.HUMIDITY, temp.getHumidity())
				.setParameter(TempEntity.REC_DATE, temp.getRecordedDate())
				.setParameter(TempEntity.REC_NAME, temp.getRecorderName())
				.setParameter(TempEntity.TEMP, temp.getTempC())
				.addUpdWhereClause("Where "+TempEntity.ID+" = :idValue", temp.getId()).update();
	}

	public void saveTemperature(TempEntity entity) throws ClassNotFoundException, SQLException {
		logger.debug("Saving temp : " + entity);

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

	public Temperature getCurrentStoredTemperature() throws SQLException, ClassNotFoundException {
		DBConnection con = null;
		Temperature temp ;
		try {
			con = getConnection();

			temp = new Temperature();

			TempEntity AA = findCurrentTemp(con, TempRecName.AA.name());
			if (AA != null) {
				temp.setProperties(AA);
			}
			TempEntity BB = findCurrentTemp(con, TempRecName.BB.name());
			if (BB != null) {
				temp.setProperties(BB);
			}
			TempEntity pool = findCurrentTemp(con, TempRecName.Pool.name());
			if (pool != null) {
				temp.setProperties(pool);
			}

		}finally {
			if (con != null) {
				con.close();
			}
		}	

		return temp;
	}
	private DBConnection getConnection() throws ClassNotFoundException, SQLException{

		Database db = new Database("jdbc:h2:" +Constants.DB_URL,Constants.DB_USER, Constants.DB_PASS.toCharArray(), DbClass.H2);
		return new DBConnection(db);
		//		Database db = new Database(Constants.DB_MYSQL,Constants.DB_USER, Constants.DB_PASS.toCharArray(), DbClass.Mysql);
		//				return  new DBConnection(Constants.DB_MYSQL, Constants.DB_USER, Constants.DB_PASS, DbClass.Mysql );
		//		return new DBConnec.tion(db);
	}
	
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
