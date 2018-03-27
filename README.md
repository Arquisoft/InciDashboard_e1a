[![Build Status](https://travis-ci.org/Arquisoft/InciDashboard_e1a.svg?branch=master)](https://travis-ci.org/Arquisoft/InciDashboard_e1a)

# InciDashboard_e1a
InciDashboard e1a

# Authors (2017/2018)
- Ángel Puerta Díaz (UO246998)
- Sergio Domínguez Cabrero (UO250788)
- César Álvarez Rubio (UO251069)
- Alvaro Alonso Quijada (UO251322)

El proyecto actual gestiona las dependencias con [Maven](https://maven.apache.org/)

Para inciar el proyecto se necesitará ejecutar los siguientes pasos:
1. Iniciar base de datos:
  * La base de datos va a ser local, utilizando [HSQLDB](http://hsqldb.org/).
  * Puede iniciarla a través de ```/data/hsqldb/bin/runServer```
2. El proyecto utiliza [Apache Kafka](https://kafka.apache.org/), nos dirigimos hacia la carpeta del proyecto Kafka.
  * Para su despliegue, necesitamos arrancar Zookeeper:
  - En sistemas Unix : ```bin/zookeeper-server-start.sh config/zookeeper.properties```
  - En sistemas Windows : ```bin\windows\zookeeper-server-start.bat config\zookeeper.properties```
  * A continuación podremos iniciar el servidor Kafka:
  - En sistemas Unix :  ```bin/kafka-server-start.sh config/server.properties ```
  - En sistemas Windows :  ``` bin\windows\kafka-server-start.bat config\server.properties ```
3. Podremos arrancarlo :
  * Desde consola con : ``` spring-boot:run ```
  * Desde un IDE como Eclipse.
  
  
