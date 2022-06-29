# Banking Transaction Dashboard

## Requirements
 - Java 17 or higher
 - Browser with JavaScript enabled

## How to Use

### Start API
First start the API `Banking-Transaction-Dashboard/production/BankingTransactionAPI-0.0.1-SNAPSHOT.jar` with the following command:

`java -jar BankingTransactionAPI-0.0.1-SNAPSHOT.jar "."`

Instead of the `"."` you can insert a path where the database should be saved.

### Open the Frontend
Go to `Banking-Transaction-Dashboard/production/frontend` and open the `index.html` (You can do this by double-clicking).

### Upload the CSV
You can toggle the Upload context by pressing the `Upload` button.
Select a CSV file in the `Choose File` field or drag and drop it in this field.
Now press upload and the site will reload and show the graph and transaction infos.


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

