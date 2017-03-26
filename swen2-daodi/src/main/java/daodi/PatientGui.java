package daodi;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PatientGui implements ActionListener {

	private JButton readButton;
	private JButton addButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JTextField idTextField;
	private JTextField nachnameTextField;
	private JTextField vornameTextField;
	private JTextField gebDatTextField;

	public void show() {
		JPanel dataPanel = new JPanel(new GridLayout(4, 2));
		JPanel controlPanel = new JPanel(new FlowLayout());
		JLabel idLabel = new JLabel("ID");
		JLabel nachnameLabel = new JLabel("Nachname");
		JLabel vornameLabel = new JLabel("Vorname");
		JLabel gebDatLabel = new JLabel("Geburtsdatum");
		idTextField = new JTextField();
		nachnameTextField = new JTextField();
		vornameTextField = new JTextField();
		gebDatTextField = new JTextField();
		readButton = new JButton("Laden");
		addButton = new JButton("Hinzufügen");
		updateButton = new JButton("Aktualisieren");
		deleteButton = new JButton("Entfernen");
		readButton.setActionCommand("READ");
		addButton.setActionCommand("ADD");
		updateButton.setActionCommand("UPDATE");
		deleteButton.setActionCommand("DELETE");
		readButton.addActionListener(this);
		addButton.addActionListener(this);
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
		controlPanel.add(addButton);
		controlPanel.add(updateButton);
		controlPanel.add(deleteButton);

		JFrame frame = new JFrame("Patient");
		frame.setLayout(new BorderLayout());
		frame.add(dataPanel, BorderLayout.CENTER);
		frame.add(controlPanel, BorderLayout.SOUTH);
		frame.setSize(500, 250);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("READ")) {
			// TODO Code ergänzen, um Patient zu laden
		} else if (e.getActionCommand().equals("ADD")) {
			// TODO Code ergänzen, um Patient hinzuzufügen
		} else if (e.getActionCommand().equals("UPDATE")) {
			// TODO Code ergänzen, um Patient zu aktualsieren
		} else if (e.getActionCommand().equals("DELETE")) {
			// TODO Code ergänzen, um Patient zu löschen
		}

	}

}
