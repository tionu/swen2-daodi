package daodi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FileSystemDAO implements Persistence {

	// date format used to store date-objects as strings in file.
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	private String filePath;

	public FileSystemDAO(String filePath) {
		this.filePath = filePath;
	}

	public void createPatient(Patient patient) {
		// TODO Auto-generated method stub

	}

	public Patient readPatient(int id) {
		Patient patient = new Patient(id);
		String textLine;
		try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
			while ((textLine = fileReader.readLine()) != null) {
				String[] fields = textLine.split("\\|");
				if (fields.length >= 4) {
					if (Integer.parseInt(fields[0]) == id) {
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
		// TODO Auto-generated method stub

	}

	public void deletePatient(int id) {
		// TODO Auto-generated method stub

	}

}
