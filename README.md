# ABOUT
Universidad Nacional de Colombia
Bases de datos avanzadas
By. Edwar A. Rojas Blanco
edrojasb@unal.edu.co - edwar.red@gmail.com

# PREREQUISITES
 - Java SDK 8
 - Apache Maven 3.6.0 or later

# INSTALL DB
```sh
cd etl-sakila/sakila-db
./mysql sakila < ./sakila-db/sakila-schema.sql
./mysql sakila < ./sakila-db/sakila-data.sql
```

#CONFIG. DB CONNECTION

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

# INSTALL APP
```sh
cd etl-sakila/
mvn clean install
```

# RUN APP
```sh
cd etl-sakila/target
java -jar  
```
