package daodi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PatientGui implements ActionListener {

	// date format
	private static final String DATE_STRING = "dd.MM.yyyy";

	private Persistence storageProvider;
	private DateFormat dateFormat;

	private JButton readButton;
	private JButton createButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JTextField idTextField;
	private JTextField nachnameTextField;
	private JTextField vornameTextField;
	private JTextField gebDatTextField;
	private JLabel status;

	public PatientGui(Persistence storageProvider) {
		super();
		this.storageProvider = storageProvider;
		this.dateFormat = new SimpleDateFormat(DATE_STRING);
		;
	}

	public void show() {
		JPanel dataPanel = new JPanel(new GridLayout(4, 2));
		JPanel controlPanel = new JPanel(new FlowLayout());
		JPanel statusPanel = new JPanel(new FlowLayout());
		JLabel idLabel = new JLabel("ID");
		JLabel nachnameLabel = new JLabel("Nachname");
		JLabel vornameLabel = new JLabel("Vorname");
		JLabel gebDatLabel = new JLabel("Geburtsdatum");
		status = new JLabel(" ");
		status.setForeground(Color.RED);
		idTextField = new JTextField();
		nachnameTextField = new JTextField();
		vornameTextField = new JTextField();
		gebDatTextField = new JTextField();
		readButton = new JButton("Laden");
		createButton = new JButton("Hinzufügen");
		updateButton = new JButton("Aktualisieren");
		deleteButton = new JButton("Entfernen");
		readButton.setActionCommand("READ");
		createButton.setActionCommand("ADD");
		updateButton.setActionCommand("UPDATE");
		deleteButton.setActionCommand("DELETE");
		readButton.addActionListener(this);
		createButton.addActionListener(this);
		updateButton.addActionListener(this);
		deleteButton.addActionListener(this);

		dataPanel.add(idLabel);
		dataPanel.add(idTextField);
		dataPanel.add(nachnameLabel);
		dataPanel.add(nachnameTextField);
		dataPanel.add(vornameLabel);
		dataPanel.add(vornameTextField);
		dataPanel.add(gebDatLabel);
		dataPanel.add(gebDatTextField);
		controlPanel.add(readButton);
		controlPanel.add(createButton);
		controlPanel.add(updateButton);
		controlPanel.add(deleteButton);
		statusPanel.add(status);

		JFrame frame = new JFrame("Patient");
		frame.setLayout(new BorderLayout());
		frame.add(dataPanel, BorderLayout.CENTER);
		frame.add(controlPanel, BorderLayout.SOUTH);
		frame.add(statusPanel, BorderLayout.NORTH);
		frame.setSize(500, 250);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		status.setText(" ");
		if (e.getActionCommand().equals("READ")) {
			if (idTextField.getText().length() == 0) {
				status.setText("Bitte ID eingeben.");
				return;
			}

			int patientID;
			if (validateId(idTextField.getText())) {
				patientID = Integer.parseInt(idTextField.getText());
			} else {
				status.setText("ID muss eine Zahl sein.");
				return;
			}

			Patient patient = storageProvider.readPatient(patientID);
			if (!(patient.getNachname() == null)) {
				nachnameTextField.setText(patient.getNachname());
				vornameTextField.setText(patient.getVorname());
				gebDatTextField.setText(dateFormat.format(patient.getGebDat()));
			} else {
				status.setText("ID nicht gefunden.");
			}
		} else if (e.getActionCommand().equals("ADD")) {
			if (idTextField.getText().length() == 0 || nachnameTextField.getText().length() == 0
					|| vornameTextField.getText().length() == 0 || gebDatTextField.getText().length() == 0) {
				status.setText("Bitte alle Felder ausfüllen.");
				return;
			}

			int patientID;
			if (validateId(idTextField.getText())) {
				patientID = Integer.parseInt(idTextField.getText());
			} else {
				status.setText("ID muss eine Zahl sein.");
				return;
			}

			Date geburtsDatum;
			try {
				geburtsDatum = dateFormat.parse(gebDatTextField.getText());
			} catch (ParseException e1) {
				status.setText("Format für Geburtstdatum: " + DATE_STRING);
				return;
			}

			Patient existingPatient = storageProvider.readPatient(patientID);
			if (!(existingPatient.getNachname() == null)) {
				status.setText("ID bereits vorhanden. Bitte 'Aktualisieren' benutzen.");
				return;
			}

			Patient createPatient = new Patient(patientID);
			createPatient.setNachname(nachnameTextField.getText());
			createPatient.setVorname(vornameTextField.getText());
			createPatient.setGebDat(geburtsDatum);
			storageProvider.createPatient(createPatient);
		} else if (e.getActionCommand().equals("UPDATE")) {
			if (idTextField.getText().length() == 0 || nachnameTextField.getText().length() == 0
					|| vornameTextField.getText().length() == 0 || gebDatTextField.getText().length() == 0) {
				status.setText("Bitte alle Felder ausfüllen.");
				return;
			}

			int patientID;
			if (validateId(idTextField.getText())) {
				patientID = Integer.parseInt(idTextField.getText());
			} else {
				status.setText("ID muss eine Zahl sein.");
				return;
			}

			Date geburtsDatum;
			try {
				geburtsDatum = dateFormat.parse(gebDatTextField.getText());
			} catch (ParseException e1) {
				status.setText("Format für Geburtstdatum: " + DATE_STRING);
				return;
			}

			Patient existingPatient = storageProvider.readPatient(patientID);
			if ((existingPatient.getNachname() == null)) {
				status.setText("ID nicht vorhanden. Bitte 'Hinzufügen' benutzen.");
				return;
			}

			Patient createPatient = new Patient(patientID);
			createPatient.setNachname(nachnameTextField.getText());
			createPatient.setVorname(vornameTextField.getText());
			createPatient.setGebDat(geburtsDatum);
			storageProvider.updatePatient(createPatient);

		} else if (e.getActionCommand().equals("DELETE")) {
			if (idTextField.getText().length() == 0) {
				status.setText("Bitte ID eingeben.");
				return;
			}

			int patientID;
			if (validateId(idTextField.getText())) {
				patientID = Integer.parseInt(idTextField.getText());
			} else {
				status.setText("ID muss eine Zahl sein.");
				return;
			}

			storageProvider.deletePatient(patientID);

			idTextField.setText("");
			nachnameTextField.setText("");
			vornameTextField.setText("");
			gebDatTextField.setText("");

		}

	}

	private boolean validateId(String patientId) {
		try {
			Integer.parseInt(patientId);
		} catch (NumberFormatException e1) {
			return false;
		}
		return true;
	}

}
