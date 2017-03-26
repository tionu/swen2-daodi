package daodi;

public interface Persistence {

	public Patient readPatient(int id);
	public void addPatient(Patient patient);
	public void updatePatient(Patient patient);
	public void removePatient(int id);

}
