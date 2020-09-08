# ETL - SAKILA

## ABOUT
Universidad Nacional de Colombia <br/>
Bases de datos avanzadas <br/>
By. Edwar A. Rojas Blanco <br/>
edrojasb@unal.edu.co - edwar.red@gmail.com <br/>

## PREREQUISITES
 - Java SDK 8
 - Apache Maven 3.6.0 or later
 - Mysql 10.4.11-MariaDB or later 

## INSTALL DB
```sh
cd etl-sakila/sakila-db
mysql 
mysql>
mysql> create database sakila
mysql> exit

$ mysql sakila < ./sakila-db/sakila-schema.sql
$ mysql sakila < ./sakila-db/sakila-data.sql
```

## CONFIG. DB CONNECTION

Open file, and change data base connection properties
```sh
etl-sakila/src/main/resources/META-INF/persistence.xml
```
```xml
....
    <properties>
      <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/sakila?zeroDateTimeBehavior=convertToNull"/>
      <property name="javax.persistence.jdbc.user" value="root"/>
      <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver"/>
      <property name="javax.persistence.jdbc.password" value=""/>
    </properties>
  </persistence-unit>
</persistence>
```

## INSTALL APP
```sh
cd etl-sakila/
mvn clean install
```

## RUN APP
```sh
cd etl-sakila/target
java -jar  
```
