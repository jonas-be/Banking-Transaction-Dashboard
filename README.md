# DiaryAPI

## SetUP

### Postgres on Docker

 - Get Postgres Image: `docker pull postgres`
   - Check if Postgres image is stored on your computer: `docker images`


 - Run the pulled Docker Image:\
`docker run --name postres-db-diary-api -e POSTGRES_USER=diaryapi -e POSTGRES_PASSWORD=D14rYA9iDb -p 5432:5432 -v /data:/var/lib/postgresql/data -d postgres`
   - Check if it's running: `docker ps`


