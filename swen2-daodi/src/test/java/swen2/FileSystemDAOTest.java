package swen2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import daodi.FileSystemDAO;
import daodi.Patient;

public class FileSystemDAOTest {

	private FileSystemDAO persist = new FileSystemDAO("./src/main/java/daodi/Patienten.file");

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreatePatient() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadPatient() {
		Patient patientRead = persist.readPatient(3);
		assertEquals(3, patientRead.getId());
		assertEquals("Gudrun", patientRead.getVorname());
		assertEquals("Beier", patientRead.getNachname());
//		assertEquals(geburtstag, patientRead.getGebDat());
	} 

	@Test
	public void testUpdatePatient() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeletePatient() {
		fail("Not yet implemented");
	}

}
