# Project TODOs

## Backend

- [ ] File Processing

### API Endpoints

#### Data of File:

- [ ] PUT / POST:  Upload File
    - Check if there are the same number of transactions per day?
        - EQUAL:  Do nothing
        - !EQUAL: DELETE whole day and Insert it new
            - Why?
              > It's not easy to check which transaction has to updated,
              because a transaction must not be unique.
              And if there are two equal transaction,
              the app update two times the same transaction
              instead of insert a new one.

- [ ] GET: Last uploaded Data

- [ ] GET: Missing Data

#### Credit Balance:

- [ ] GET: Current Credit Balance


- [ ] GET: Week Credit Balance
    - arg: Week

- [ ] GET: Month Credit Balance
    - arg: month


- [ ] GET: Credit Balance Change
    - agr: time period => ('day', 'week', 'month')

#### Transactions

- [ ] GET: All Transactions

## Frontend


# Was soll auf der Website dargestellt werden?

- [ ] Datenstand
    - [X] Upload Field
    - [ ] Letzte Aktualisierung
    - [ ] Welche Daten noch fehlen (z.B. "Es fehlen Daten von 15.02.2022 - 05.06.2022")
- [ ] Aktueller Kontostand
- [X] Graph mit dem täglichen Kontostand
- [ ] Monatliche, wöchentliche und tägliche Änderung des Kontostandes
    - [ ] in Prozent
    - [ ] in Euro
- [X] Liste mit allen Transaktionen
- [ ] (Je nach Zeit noch mehr statistische Features)

