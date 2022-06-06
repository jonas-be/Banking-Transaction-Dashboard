# Banking Transaction Dashboard


### Beschreibung

Mein Plan wäre es ein Web Dashboard zu erstellen,
welches verschiedene Informationen zu Transaktionen auf einem Konto anzeigt.

Die Daten kommen dafür aus einer CSV Datei,
die man im Online-Banking meiner Bank exportieren kann.
Die csv beinhaltet Transaktionen, aus einem vom Nutzer bestimmten Zeitraum.

Damit die Daten nicht jedes Mal alle neu hochgeladen werden müssen,
sollen die Transaktionsdaten in einer Datenbank gespeichert werden.


### Was soll auf der Website dargestellt werden?

- Datenstand
    - Letzte Aktualisierung
    - Welche Daten noch fehlen (z.B. "Es fehlen Daten von 15.02.2022 - 05.06.2022")
- Aktueller Kontostand
- Graph mit dem täglichen Kontostand
- Monatliche, wöchentliche und tägliche Änderung des Kontostandes
    - in Prozent
    - in Euro
- Liste mit allen Transaktionen
- (Je nach Zeit noch mehr statistische Features)


### Wie können Sie das später testen?

Ich werde Test CSV Dateien mit abgeben mit abgeänderten Kontodaten,
welche zum Testen des Dashboards verwendet werden können.

Falls Ihre Bank das gleiche Format anbietet,
können sie natürlich auch ihre Daten verwenden.
Die Daten werden nicht nach aussen gegeben da das Tool Lokal läuft.


### Voraussichtlich genutzte Technologien

#### Backend

- Java / Kotlin

#### Frontend

- HTML
- CSS
- Java Script
    - (evtl. Vue.js)

#### DatenBank

- Postgres
    - in einem Docker Container

