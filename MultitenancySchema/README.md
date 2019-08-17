# Multitenancy separate in schemas

### Postgres database

To up a Postgres container, run the next instruction:
```
$ docker run --name local-postgres -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres
``` 

To connect to postgres and create a database:

```
$ docker exec -it local-postgres psql -U postgres
# create database devcave_multitenancy;
# \c devcave_multitenancy;
# create schema schema1;
# create schema schema2;
# CREATE TABLE schema1.product (
      id                 BIGSERIAL                   NOT NULL PRIMARY KEY,
      name               VARCHAR(255)                NOT NULL);
# CREATE TABLE schema2.product (
      id                 BIGSERIAL                   NOT NULL PRIMARY KEY,
      name               VARCHAR(255)                NOT NULL);
# \q      
```

To run docker if it was create:

```
$ docker start local-postgres
```

### Run

Run project and access to swagger in http://localhost:8080. 

In all endpoints we can write the tenantId. If we don't fill, the app uses `schema1`. Otherwise, attempt to use the passed tenantId



