Sie schreiben ein Programm zur Verwaltung von Patientendaten. Das Programm soll die Daten der Patienten speichern und laden k�nnen. Da Sie nicht sicher sein k�nnen, ob die Kunden, die Ihr Programm einsetzen, eine Datenbank einsetzen oder die Daten im Filesystem speichern, schreiben Sie ein Programm, das beide Varianten bedienen kann und offen ist f�r Erweiterungen der  Speicherm�glichkeiten (z.B. REST-Service, Schnittstellen�bertragung)

F�r die Implementierung ben�tigen Sie eine Klasse Patient. Diese finden Sie bereits fertig im Anhang. Ebenso finden Sie das Interface Persistence im Anhang. Dieses Interface m�ssen alle Klassen implementieren, die Patientendaten speichern bzw. abrufen k�nnen.

Erstellen Sie zwei Klassen, die  das Interface Persistence implementieren. Eine Klasse soll die Interaktion mit der Datenbank realisieren und eine Klasse die Speicherung in und das Auslesen aus einer einfachen Textdatei. Schreiben Sie au�erdem Unittests f�r die erstellten Klassen.

Passen Sie dar�berhinaus die Klasse PatientGui so an, dass sie ihre Aufgabe vollst�ndig erledigt und dabei dem Designpattern Dependency Injection entspricht und flexibel mit verschiedenen Persistence-Klassen umgehen kann.

Erstellen Sie schlie�lich noch eine Starter-Klasse, die das Programm startet und abh�ngig von den �bergebenen String-Parametern das Programm so konfiguriert, dass die Daten in der Datenbank oder einer Textdatei gespeichert bzw. ausgelesen werden.

Zur Arbeit mit SQLite steht Ihnen der Datenbanktreiber sqlite-jdbc-3.16.1.jar zur Verf�gung. Binden Sie diesen in Ihr Projekt ein. 
Die SQLite-Datenbank befindet sich in der Datei Patient.db
Falls Sie sich den Inhalt der Datei anschauen wollen, steht ein DB-Browser zur Verf�gung, den Sie herunterladen k�nnen.
https://github.com/sqlitebrowser/sqlitebrowser/releases
Die Datei zum Speichern der Patientendaten als Textdatei ist die Datei Patient.file.