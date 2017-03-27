package daodi;

public class ApplicationCore {

	// defines a default path for storage file in case no arguments are given
	private static final String DEFAULT_STORAGE_PROVIDER = System.getProperty("java.io.tmpdir") + "daodi_storage.tmp";

	public static void main(String[] args) {
		String storagePath = DEFAULT_STORAGE_PROVIDER;

		// process start params
		if (!(args.length == 0)) {
			storagePath = args[0];
		}

		// create storage provider for injection
		Persistence storageProvider;
		String storagePathExtension = storagePath.substring(storagePath.lastIndexOf('.')).toLowerCase();

		switch (storagePathExtension) {
		case ".db":
			storageProvider = new SQLiteDAO(storagePath);
			break;
		case ".file":
		default:
			storageProvider = new FileSystemDAO(storagePath);
			break;
		}

		// inject storage provider and run gui
		PatientGui gui = new PatientGui(storageProvider);
		gui.show();

	}

}
