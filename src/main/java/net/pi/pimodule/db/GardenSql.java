package net.pi.pimodule.db;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import net.pi.pimodule.db.entity.GardenWorkerEntity;
import net.pi.pimodule.db.entity.SensorEntity;
import net.pi.pimodule.enums.SensorType;

public class GardenSql {

	private static final Logger logger = LogManager.getLogger(GardenSql.class);

	public void createTable() throws IOException, SQLException, ClassNotFoundException {
		DBConnection con = null;
		boolean exist = false;
		try {
			con = getConnection();

			DatabaseMetaData md = con.getConnection().getMetaData();
			ResultSet rs = md.getTables(null, null, GardenWorkerEntity.TBL_NAME.toUpperCase(), null);

			while (rs.next()) {
				exist = true;
			}

			logger.debug("Creating GardenWorkerEntity table. Teble exist: " + exist);

			if (!exist) {
				logger.info("SensorSql does not exist , creating");
				List<ColumnType> columns = new ArrayList<ColumnType>();					
				columns.add(new ColumnType(GardenWorkerEntity.ID, true).INT().setPKCriteria(new PkCriteria().autoIncrement()));
				columns.add(new ColumnType(GardenWorkerEntity.SCHED_TYPE).VarChar(25));
				columns.add(new ColumnType(GardenWorkerEntity.WATERING_DATE).TimeStamp());
				columns.add(new ColumnType(GardenWorkerEntity.SENSOR_ID).INT());
				columns.add(new ColumnType(GardenWorkerEntity.WATERING_DURATION).INT());
				columns.add(new ColumnType(GardenWorkerEntity.DESCRIPTION).VarChar(2000));

				con.createTable(GardenWorkerEntity.TBL_NAME, columns);	

			}
		}finally {
			if (con != null) {
				con.close();
			}
		}	
	}
	public GardenWorkerEntity findWorkerById(int workerId ) throws SQLException, ClassNotFoundException {

		DBConnection con = null;
		GardenWorkerEntity worker = null;
		try {
			con = getConnection();

			ResultSet rs = con.createSelectQuery("SELECT * FROM " + GardenWorkerEntity.TBL_NAME + " WHERE " +GardenWorkerEntity.ID + " = :workerId" )
					.setParameter("workerId", workerId)
					.getSelectResultSet();


			if (rs!=null) {
				while(rs.next()) {
					worker  = new GardenWorkerEntity(rs);
				}
			}

		}finally {
			if (con != null) {
				con.close();
			}
		}

		return worker;
	}
	public List<GardenWorkerEntity> getAllWorkers() throws SQLException, ClassNotFoundException {

		DBConnection con = null;
		List<GardenWorkerEntity> workers = new ArrayList<>();
		try {
			con = getConnection();

			ResultSet rs = con.createSelectQuery("SELECT * FROM " + GardenWorkerEntity.TBL_NAME )
					.getSelectResultSet();


			if (rs!=null) {
				while(rs.next()) {
					GardenWorkerEntity worker  = new GardenWorkerEntity(rs);

					workers.add(worker);
				}
			}

		}finally {
			if (con != null) {
				con.close();
			}
		}

		return workers;
	}
	public List<SensorEntity> getAllUnassignedWorkers() throws SQLException, ClassNotFoundException {

		DBConnection con = null;
		List<SensorEntity> unassignedSensors = new ArrayList<>();
		try {
			con = getConnection();

			ResultSet rs = con.createSelectQuery("SELECT * FROM " + SensorEntity.TBL_NAME + " WHERE " + SensorEntity.SENSOR_TYPE + " =:sensorType "
					+ "AND " + SensorEntity.ID + " not IN (select " + GardenWorkerEntity.SENSOR_ID + " FROM " + GardenWorkerEntity.TBL_NAME )
					.setParameter("sensorType", SensorType.GARDEN.name())
					.getSelectResultSet();

			if (rs!=null) {
				while(rs.next()) {
					SensorEntity sensor  = new SensorEntity(rs);
//
					unassignedSensors.add(sensor);
				}
			}

		}finally {
			if (con != null) {
				con.close();
			}
		}

		return unassignedSensors;
	}
	public int addWorker(GardenWorkerEntity worker) throws SQLException, ClassNotFoundException {
		DBConnection con = null;
		int pk = -1;
		try {
			con = getConnection();

			pk = con.buildAddQuery(GardenWorkerEntity.TBL_NAME)
					.setParameter(GardenWorkerEntity.DESCRIPTION, worker.getDescription())
					.setParameter(GardenWorkerEntity.SCHED_TYPE, worker.getScheduleType().name())
					.setParameter(GardenWorkerEntity.SENSOR_ID, worker.getSensorId())
					.setParameter(GardenWorkerEntity.WATERING_DATE, (worker.getWateringDate() != null ? Timestamp.valueOf(worker.getWateringDate()) : null) )
					.setParameter(GardenWorkerEntity.WATERING_DURATION, worker.getWateringDuration())
					.add();

		}finally {
			if (con != null) {
				con.close();
			}
		}

		return pk;
	}
	/**
	 * 
	 * @param worker
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void updateWorker(GardenWorkerEntity worker) throws SQLException, ClassNotFoundException {

		DBConnection con = null;
		try {
			con = getConnection();
			con.buildUpdateQuery(GardenWorkerEntity.TBL_NAME)
			.setParameter(GardenWorkerEntity.DESCRIPTION, worker.getDescription())
			.setParameter(GardenWorkerEntity.SCHED_TYPE, worker.getScheduleType().name())
			.setParameter(GardenWorkerEntity.SENSOR_ID, worker.getSensorId())
			.setParameter(GardenWorkerEntity.WATERING_DATE, (worker.getWateringDate() != null ? Timestamp.valueOf(worker.getWateringDate()) : null) )
			.setParameter(GardenWorkerEntity.WATERING_DURATION, worker.getWateringDuration())
			.addUpdWhereClause("Where "+GardenWorkerEntity.ID+" = :idValue", worker.getId()).update();

		}finally {
			if (con != null) {
				con.close();
			}
		}
	}

	private DBConnection getConnection() throws ClassNotFoundException, SQLException{

		Database db = new Database("jdbc:h2:" +Constants.DB_URL,Constants.DB_USER, Constants.DB_PASS.toCharArray(), DbClass.H2);
		return new DBConnection(db);

	}

}
