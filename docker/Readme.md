
## Start PostgreSQL containers
With Powershell:
```
docker network create backend

docker compose -p dbmoviepg-empty up -d

docker compose -p dbmoviepg-ddl `
    --env-file .env-ddl `
    -f docker-compose.yml `
    -f docker-compose.ddl.yml `
    up -d
    
docker compose -p dbmoviepg-ddldata `
    --env-file .env-ddldata `
    -f docker-compose.yml `
    -f docker-compose.ddldata.yml `
    up -d
```

## Start PgAdmin4 container
With Powershell:
```
docker compose -p pgadmin `
    --env-file .env-gui `
    -f docker-compose.pgadmin.yml `
    up -d
```
