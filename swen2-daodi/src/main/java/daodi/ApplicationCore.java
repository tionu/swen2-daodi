package daodi;

public class ApplicationCore {

	public static void main(String[] args) {
		Persistence storageProvider = new SQLiteDAO("./src/main/java/daodi/storage/PatientDB.db");
		PatientGui gui = new PatientGui(storageProvider);
		gui.show();

	}

}
