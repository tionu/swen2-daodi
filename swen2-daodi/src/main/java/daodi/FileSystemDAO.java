package daodi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FileSystemDAO implements Persistence {

	// date format used to store date-objects as strings in file.
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private static final String FIELD_SEPERATOR = "|";

	private String storageFile;

	public FileSystemDAO(String storageFile) {
		this.storageFile = storageFile;
	}

	public void createPatient(Patient patient) {
		if (existsId(patient.getId())) {
			System.err.println("error creating patient in file: id exists.");
			return;
		}

		String textLineOut = patient.getId() + FIELD_SEPERATOR + patient.getNachname() + FIELD_SEPERATOR
				+ patient.getVorname() + FIELD_SEPERATOR + DATE_FORMAT.format(patient.getGebDat())
				+ System.getProperty("line.separator");
		try {
			Files.write(Paths.get(storageFile), textLineOut.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.err.println("error creating patient in file.");
			e.printStackTrace();
		}
	}

	public Patient readPatient(int id) {
		Patient patient = new Patient(id);
		String textLineIn;
		try (BufferedReader fileReader = new BufferedReader(new FileReader(storageFile))) {
			while ((textLineIn = fileReader.readLine()) != null) {
				String[] fields = textLineIn.split("\\" + FIELD_SEPERATOR);
				if (fields.length >= 4) {
					if (fields[0].equals(String.valueOf(id))) {
						patient.setNachname(fields[1]);
						patient.setVorname(fields[2]);
						patient.setGebDat(DATE_FORMAT.parse(fields[3]));
						return patient;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			System.err.println("error on parsing birthdate of patient.");
			e.printStackTrace();
		}
		return patient;
	}

	public void updatePatient(Patient patient) {
		if (!existsId(patient.getId())) {
			System.err.println("error updating patient in file: id does not exists.");
			return;
		}
		deletePatient(patient.getId());
		createPatient(patient);
	}

	public void deletePatient(int id) {
		if (!existsId(id)) {
			System.err.println("error deleting patient in file: id does not exists.");
			return;
		}

		String textLineIn;
		File inputFile = new File(storageFile);
		File tempFile = new File(storageFile + ".tmp");

		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
				BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
			while ((textLineIn = reader.readLine()) != null) {
				String[] fields = textLineIn.split("\\" + FIELD_SEPERATOR);
				if (fields.length >= 1) {
					if (!fields[0].equals(String.valueOf(id))) {
						writer.write(textLineIn + System.getProperty("line.separator"));
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		inputFile.delete();
		tempFile.renameTo(inputFile);
	}

	private boolean existsId(int patientId) {
		Patient presentPatient = readPatient(patientId);
		return presentPatient.getNachname() != null;
	}

}
