# DiaryAPI

## SetUP

### Postgres on Docker

 - Get Postgres Image: `docker pull postgres`
   - Check if Postgres image is stored on your computer: `docker images`


TODO: Fix password for DB (it is the old at the moment) 
 - Run the pulled Docker Image:\
   - `docker run --name postres-db-diary-api -e POSTGRES_USER=diaryapi -e POSTGRES_PASSWORD=D14rYA9iDb -p 5432:5432 -v /data:/var/lib/postgresql/data -d postgres`

   - `docker run --name postgres-db-transaction-api -e POSTGRES_USER=transactionapi -e POSTGRES_PASSWORD=Tr4N2aCT10nDb -p 5432:5432 -v /data:/var/lib/postgresql/data -d postgres`
   - Check if it's running: `docker ps`


