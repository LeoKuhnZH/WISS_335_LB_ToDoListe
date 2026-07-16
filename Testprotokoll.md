# Testprotokoll - ToDoList

Dieses Protokoll dokumentiert die manuellen Akzeptanztests für die ToDoList-App gemäß den Anforderungen in der Modul-Leistungsbewertung 335.

---

### Test 1: Aufgabe hinzufügen
| Feld | Inhalt |
| :--- | :--- |
| **Titel** | Eine neue Aufgabe kann erfasst und in der Liste angezeigt werden |
| **User Story** | US-2 |
| **Vorbedingung** | Die App ist gestartet, die Liste ist leer oder enthält bestehende Aufgaben. |
| **Durchführung** | 1. Auf den Button "Aufgabe hinzufügen" klicken.<br>2. Titel, Beschreibung, Priorität und Datum eingeben.<br>3. Auf "Speichern" klicken. |
| **Erwartetes Ergebnis** | Die EditActivity schließt sich und die neue Aufgabe erscheint korrekt in der Liste auf der MainActivity. |
| **Tatsächliches Ergebnis** | [HIER EINTRAGEN] |
| **Bestanden** | Ja / Nein |

---

### Test 2: Aufgabe nach Neustart sichtbar
| Feld | Inhalt |
| :--- | :--- |
| **Titel** | Aufgabe bleibt nach Neustart erhalten |
| **User Story** | US-4 |
| **Vorbedingung** | Mindestens eine Aufgabe wurde bereits erfolgreich gespeichert. |
| **Durchführung** | 1. App vollständig schließen (Task-Manager).<br>2. App neu starten. |
| **Erwartetes Ergebnis** | Die zuvor gespeicherte Aufgabe wird weiterhin in der Liste angezeigt. |
| **Tatsächliches Ergebnis** | [HIER EINTRAGEN] |
| **Bestanden** | Ja / Nein |

---

### Test 3: Leere Eingabe verhindern
| Feld | Inhalt |
| :--- | :--- |
| **Titel** | Ein leerer Titel wird nicht gespeichert |
| **User Story** | US-2 |
| **Vorbedingung** | Die EditActivity ist geöffnet. |
| **Durchführung** | 1. Das Titelfeld leer lassen.<br>2. Auf "Speichern" klicken. |
| **Erwartetes Ergebnis** | Die App speichert nicht, zeigt eine Fehlermeldung (Toast oder Error am Feld) an und bleibt in der EditActivity. |
| **Tatsächliches Ergebnis** | [HIER EINTRAGEN] |
| **Bestanden** | Ja / Nein |

---

### Test 4: Aufgabe erledigen
| Feld | Inhalt |
| :--- | :--- |
| **Titel** | Eine Aufgabe kann als erledigt markiert werden |
| **User Story** | US-3 |
| **Vorbedingung** | In der Liste befindet sich mindestens eine offene Aufgabe. |
| **Durchführung** | 1. Den "Erledigt"-Button/Checkbox bei einer Aufgabe anklicken.<br>2. App neu starten, um Persistenz zu prüfen. |
| **Erwartetes Ergebnis** | Die Aufgabe wird als erledigt markiert (visuelle Kennzeichnung) und dieser Status bleibt auch nach einem Neustart erhalten. |
| **Tatsächliches Ergebnis** | [HIER EINTRAGEN] |
| **Bestanden** | Ja / Nein |

---

### Test 5 (Optional): Aufgabe löschen
| Feld | Inhalt |
| :--- | :--- |
| **Titel** | Eine Aufgabe kann gelöscht werden |
| **User Story** | - |
| **Vorbedingung** | In der Liste befindet sich mindestens eine Aufgabe. |
| **Durchführung** | 1. Auf den Löschen-Button der Aufgabe klicken.<br>2. Den Bestätigungsdialog mit "Delete" bestätigen. |
| **Erwartetes Ergebnis** | Die Aufgabe wird sofort aus der Liste entfernt und ist auch nach einem Neustart nicht mehr vorhanden. |
| **Tatsächliches Ergebnis** | [HIER EINTRAGEN] |
| **Bestanden** | Ja / Nein |
