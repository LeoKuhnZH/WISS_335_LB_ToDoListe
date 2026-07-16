# ToDoList - Aufgabenverwaltung

## Projektinformationen
*   **Name:** Leo Kuhn
*   **Klasse:** IFZK-2424-016
*   **Projektname:** ToDoList

## Beschreibung
Diese Android-App ermöglicht es Benutzern, ihre täglichen Aufgaben effizient zu verwalten. Sie wurde im Rahmen der Modul-Leistungsbewertung 335 entwickelt und nutzt moderne Android-Technologien wie die Room Persistence Library für die lokale Datenspeicherung.

## Umgesetzte Funktionen
*   **Aufgaben anzeigen (F1):** Übersichtliche Liste aller gespeicherten Aufgaben beim App-Start.
*   **Aufgaben erfassen (F2):** Formular zum Hinzufügen neuer Aufgaben mit Titel (Pflicht), Beschreibung, Priorität und Fälligkeitsdatum.
*   **Persistenz (F3):** Lokale Speicherung der Daten mit Room/SQLite, sodass Aufgaben nach einem Neustart erhalten bleiben.
*   **Aufgabe erledigen (F4):** Markieren von Aufgaben als erledigt direkt in der Liste.
*   **Validierung (F5):** Verhindert das Speichern von Aufgaben ohne Titel und gibt dem Benutzer eine Rückmeldung.
*   **Datumsauswahl:** Integrierter DatePicker für eine benutzerfreundliche Eingabe des Fälligkeitsdatums.
*   **Löschfunktion:** Ermöglicht das Entfernen von Aufgaben aus der Liste (Bestätigungsdialog inklusive).

## Bekannte Probleme oder nicht umgesetzte Punkte
*   **Bearbeiten:** Die Funktion zum nachträglichen Bearbeiten einer bestehenden Aufgabe wurde als Bonusfunktion eingestuft und ist in der aktuellen Version noch nicht implementiert.
*   **Sortierung/Filter:** Eine Filterung nach Status oder eine Sortierung nach Priorität/Datum ist derzeit nicht vorhanden.

## Startanleitung
1.  **Projekt öffnen:** Starten Sie Android Studio und wählen Sie `Open`. Navigieren Sie zum Projektordner `ToDoList` und bestätigen Sie.
2.  **Gradle Sync:** Warten Sie, bis der Gradle-Synchronisierungsvorgang abgeschlossen ist.
3.  **App starten:** Wählen Sie einen Emulator (z.B. Pixel 5 mit API 30+) aus und klicken Sie auf den grünen `Run` Button (Play-Symbol) in der Toolbar.

## Verwendete Hilfsmittel
*   **Android Studio:** Entwicklungsumgebung
*   **Java:** Programmiersprache
*   **Room Persistence Library:** Für die lokale SQLite-Datenbankanbindung
*   **Material Components:** Für das UI-Design und die Eingabeelemente
