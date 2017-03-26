package daodi;

public interface Persistence {

	public void createPatient(Patient patient);
	public Patient readPatient(int id);
	public void updatePatient(Patient patient);
	public void deletePatient(int id);

}
