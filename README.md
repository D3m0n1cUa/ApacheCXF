# ApacheCXF

The ApacheCXF is a simple RESTFULL app. The following technologies:

Java 1.8
Apache CXF
Spring
Hibernate
JUnit
Jackson
Maven

DB: MySQL
IDE: Eclipse Neon
Web-server: Tomcat 8

The working example is here: http://igalkin.info/ApacheCXF

GET request to receive all persons http://igalkin.info/ApacheCXF/services/person/getall
GET request to receive person by NIF http://igalkin.info/ApacheCXF/services/person/get/nif/{nif}
GET request to receive all person with name http://igalkin.info/ApacheCXF/services/person/get/name/{name}
POST request to add person to DB http://igalkin.info/ApacheCXF/services/person/add
DELETE request to remove person from DB http://igalkin.info/ApacheCXF/services/person/delete/{nif}

All data transfered in JSON. 
