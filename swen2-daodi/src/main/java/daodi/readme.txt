Sie schreiben ein Programm zur Verwaltung von Patientendaten. Das Programm soll die Daten der Patienten speichern und laden können. Da Sie nicht sicher sein können, ob die Kunden, die Ihr Programm einsetzen, eine Datenbank einsetzen oder die Daten im Filesystem speichern, schreiben Sie ein Programm, das beide Varianten bedienen kann und offen ist für Erweiterungen der  Speichermöglichkeiten (z.B. REST-Service, Schnittstellenübertragung)

Für die Implementierung benötigen Sie eine Klasse Patient. Diese finden Sie bereits fertig im Anhang. Ebenso finden Sie das Interface Persistence im Anhang. Dieses Interface müssen alle Klassen implementieren, die Patientendaten speichern bzw. abrufen können.

Erstellen Sie zwei Klassen, die  das Interface Persistence implementieren. Eine Klasse soll die Interaktion mit der Datenbank realisieren und eine Klasse die Speicherung in und das Auslesen aus einer einfachen Textdatei. Schreiben Sie außerdem Unittests für die erstellten Klassen.

Passen Sie darüberhinaus die Klasse PatientGui so an, dass sie ihre Aufgabe vollständig erledigt und dabei dem Designpattern Dependency Injection entspricht und flexibel mit verschiedenen Persistence-Klassen umgehen kann.

Erstellen Sie schließlich noch eine Starter-Klasse, die das Programm startet und abhängig von den übergebenen String-Parametern das Programm so konfiguriert, dass die Daten in der Datenbank oder einer Textdatei gespeichert bzw. ausgelesen werden.

Zur Arbeit mit SQLite steht Ihnen der Datenbanktreiber sqlite-jdbc-3.16.1.jar zur Verfügung. Binden Sie diesen in Ihr Projekt ein. 
Die SQLite-Datenbank befindet sich in der Datei Patient.db
Falls Sie sich den Inhalt der Datei anschauen wollen, steht ein DB-Browser zur Verfügung, den Sie herunterladen können.
https://github.com/sqlitebrowser/sqlitebrowser/releases
Die Datei zum Speichern der Patientendaten als Textdatei ist die Datei Patient.file.