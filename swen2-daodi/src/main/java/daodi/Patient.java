package daodi;

import java.util.Date;

public class Patient {
	
	private int id;
	private String nachname;
	private String vorname;
	private Date gebDat;
	
	public Patient(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public Date getGebDat() {
		return gebDat;
	}

	public void setGebDat(Date gebDat) {
		this.gebDat = gebDat;
	}	

}
