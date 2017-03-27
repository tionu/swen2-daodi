package daodi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class FileSystemDAOTest {

	private static final String STORAGE_FILE = "./src/test/java/daodi/storage/Patienten.file";

	private FileSystemDAO persist = new FileSystemDAO(STORAGE_FILE);

	private int id;
	private String vorname;
	private String nachname;
	private Date geburtstag;

	@Before
	public void setUp() throws Exception {

		// purge storage test file
		PrintWriter writer = new PrintWriter(new File(STORAGE_FILE));
		writer.close();

		Random random = new Random();
		id = random.nextInt(4095);
		vorname = "Lecoi";
		nachname = "Dondu";
		geburtstag = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	@Test
	public void testCreateAndReadPatient() {
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
		Patient patientRead = persist.readPatient(id);
		assertEquals(id, patientRead.getId());
		assertNull(patientRead.getVorname());
		assertNull(patientRead.getNachname());
		assertNull(patientRead.getGebDat());
	}

	private void createDummy() {
		Patient patientCreate = new Patient(id);
		patientCreate.setVorname(vorname);
		patientCreate.setNachname(nachname);
		patientCreate.setGebDat(geburtstag);
		persist.createPatient(patientCreate);
	}

}
