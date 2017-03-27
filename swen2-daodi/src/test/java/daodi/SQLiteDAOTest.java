package daodi;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import daodi.Patient;
import daodi.SQLiteDAO;

public class SQLiteDAOTest {

	private SQLiteDAO persist = new SQLiteDAO("./src/main/java/daodi/PatientDB.db");

	private int id;
	private String vorname;
	private String nachname;
	private Date geburtstag;

	@Before
	public void setUp() throws Exception {
		persist.purge();
		Random random = new Random();
		id = random.nextInt(4095);
		vorname = "Lecoi";
		nachname = "Dondu";
		geburtstag = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	@After
	public void tearDown() {
		persist.purge();
	}

	@Test
	public void testCreatePatient() {
		Patient patientCreate = new Patient(id);
		patientCreate.setVorname(vorname);
		patientCreate.setNachname(nachname);
		patientCreate.setGebDat(geburtstag);
		persist.createPatient(patientCreate);
	}

	@Test
	public void testReadPatient() {
		createDummy();
		Patient patientRead = persist.readPatient(id);
		assertEquals(id, patientRead.getId());
		assertEquals(vorname, patientRead.getVorname());
		assertEquals(nachname, patientRead.getNachname());
		assertEquals(geburtstag, patientRead.getGebDat());
	}

	@Test
	public void testUpdatePatient() {
		createDummy();
		Patient patientCreate = new Patient(id);
		patientCreate.setVorname(vorname);
		patientCreate.setNachname("Neuer");
		patientCreate.setGebDat(geburtstag);
		persist.updatePatient(patientCreate);
		Patient patientRead = persist.readPatient(id);
		assertEquals(id, patientRead.getId());
		assertEquals(vorname, patientRead.getVorname());
		assertEquals("Neuer", patientRead.getNachname());
		assertEquals(geburtstag, patientRead.getGebDat());
	}

	@Test
	public void testDeletePatient() {
		createDummy();
		persist.deletePatient(id);
	}

	private void createDummy() {
		Patient patientCreate = new Patient(id);
		patientCreate.setVorname(vorname);
		patientCreate.setNachname(nachname);
		patientCreate.setGebDat(geburtstag);
		persist.createPatient(patientCreate);
	}

}
