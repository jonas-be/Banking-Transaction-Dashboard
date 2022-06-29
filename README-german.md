# Banking Transaction Dashboard

## Anforderungen
 - Java 17 oder höher
 - Browser mit JavaScript aktiviert
 - CSV Export (SWIFT StandardFormat: CSV-MT940)

## How to Use

### Start API
Als Erstes wird die API `Banking-Transaction-Dashboard/production/BankingTransactionAPI-0.0.1-SNAPSHOT.jar` mit dem folgenden Command gestartet:

`java -jar BankingTransactionAPI-0.0.1-SNAPSHOT.jar "."`

Anstatt `"."` kann mann auch einen eigenen Pfad angeben, wo die DatenBank gespeichert werden soll.

### Frontend öffnen
Gehe zu `Banking-Transaction-Dashboard/production/frontend` und öffne die Datei `index.html` (Dies geht meist auch mit einem Doppelklick).

### CSV hochladen
Der Upload Kontext kann mit Drücken des `Upload` Buttons angezeigt und ausgeblendet werden.
Wähle die CSV Datei in dem `Choose File` Feld oder ziehe sie per drag and drop in da Feld.
Jetzt presse upload und die Seite wird sich neu laden und den Graphen und die Transactions-Infos anzeigen.


## Tech-Stack

### Frontend

- JavaScript
- React
- Recharts
- TailwindCss

### Backend

- Kotlin
- Spring Boot

### DataBase

- SQLLite

