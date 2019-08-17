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
# CREATE TABLE product (
      id                 BIGSERIAL                   NOT NULL PRIMARY KEY,
      name               VARCHAR(255)                NOT NULL);
# \q      
```

To run docker if it was create:

```
$ docker start local-postgres
```


### MySql database
# MySQL

Create a new docker instance of mysql with name local-mysql, listen in 3306 port (once)

```
$ docker run -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root --name local-mysql -d mysql
```

Connect and create the database

```
$ docker exec -it local-mysql mysql -uroot -proot
# create database devcave_multitenancy;
# use devcave_multitenancy;
# CREATE TABLE product (
      id                 int                         NOT NULL AUTO_INCREMENT,
      name               VARCHAR(255)                NOT NULL,
      PRIMARY KEY (id));

```

Start container next time:
```
$ docker start local-mysql
```

### Run

Run project and access to swagger in http://localhost:8080. 

In all endpoints we can write the tenantId. If we don't fill, the app uses `postgres`. Otherwise, attempt to use the passed tenantId

