package daodi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SQLiteDAO implements Persistence {

	// date format used to store date-objects as strings in database.
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	private String dBasePath;
	private Connection dbConnection = null;

	public SQLiteDAO(String dBasePath) {
		this.dBasePath = dBasePath;
		try {
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:" + this.dBasePath);
			System.out.println("dBase opened: " + dBasePath);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(0);
		}
	}

	public void createPatient(Patient patient) {
		try {
			PreparedStatement statement = dbConnection
					.prepareStatement("INSERT INTO PaPatient (PaId, PaNachname, PaVorname, PaGebDat) VALUES (?,?,?,?)");
			statement.setInt(1, patient.getId());
			statement.setString(2, patient.getNachname());
			statement.setString(3, patient.getVorname());
			statement.setString(4, DATE_FORMAT.format(patient.getGebDat()));
			statement.execute();
			statement.close();
			System.out.println("patient created in db.");
		} catch (SQLException e) {
			String message = "error creating patient in db.";
			System.err.println(message);
			e.printStackTrace();
		}
	}

	public Patient readPatient(int id) {
		Patient patient = new Patient(id);
		try {
			ResultSet queryResult = dbConnection.createStatement()
					.executeQuery("SELECT PaNachname, PaVorname, PaGebDat FROM PaPatient WHERE PaId = " + id);
			while (queryResult.next()) {
				patient.setNachname(queryResult.getString("PaNachname"));
				patient.setVorname(queryResult.getString("PaVorname"));
				patient.setGebDat(DATE_FORMAT.parse(queryResult.getString("PaGebDat")));
				System.out.println("patient read from db.");
			}
		} catch (SQLException e) {
			System.err.println("error reading patient from db.");
			e.printStackTrace();
		} catch (ParseException e) {
			System.err.println("error on parsing birthdate of patient.");
			e.printStackTrace();
		}
		return patient;
	}

	public void updatePatient(Patient patient) {
		try {
			PreparedStatement statement = dbConnection.prepareStatement(
					"UPDATE PaPatient  SET PaNachname = ?, PaVorname = ?, PaGebDat = ? WHERE PaId = ?");
			statement.setInt(4, patient.getId());
			statement.setString(1, patient.getNachname());
			statement.setString(2, patient.getVorname());
			statement.setString(3, DATE_FORMAT.format(patient.getGebDat()));
			statement.execute();
			statement.close();
			System.out.println("patient updated in db.");
		} catch (SQLException e) {
			System.err.println("error creating patient in db.");
			e.printStackTrace();
		}
	}

	public void deletePatient(int id) {
		try {
			dbConnection.createStatement().execute("DELETE FROM PaPatient WHERE PaId = " + id);
			System.out.println("patient deleted in db.");
		} catch (SQLException e) {
			System.err.println("error deleting patient in db.");
			e.printStackTrace();
		}
	}

	public void purge() {
		try {
			dbConnection.createStatement().execute("DELETE FROM PaPatient");
			System.out.println("db purged.");
		} catch (SQLException e) {
			System.err.println("error purging db.");
			e.printStackTrace();
		}
	}

}
