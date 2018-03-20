[![Codacy Badge](https://api.codacy.com/project/badge/Grade/2d1976960db9415892b85d741bb4a336)](https://www.codacy.com/app/jelabra/Agents_e5b?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/Agents_e5b&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/Arquisoft/Agents_e5b.svg?branch=master)](https://travis-ci.org/Arquisoft/Agents_e5b)
[![codecov](https://codecov.io/gh/Arquisoft/participants2a/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/Agents_e5b)


# InciDashboard_e5b

[![Join the chat at https://gitter.im/Arquisoft/participants2a](https://badges.gitter.im/Arquisoft/participants2a.svg)](https://gitter.im/Arquisoft/participants2a?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Skeleton of participants module

# Authors
## Autores
- Carlos Sanabria Miranda (@CarlosSanabriaM)
- Manuel Fernández Antuña (@uo2999)
- Adrián Pérez Carou (@adrycarou896)
- Mª Rosa Valdés Pire (@RosaValdesPire)


# Funcionamiento:

1. Arrancar HSQLDB
2. Arrancar Apache Zookeeper
3. Arrancar Apache Kafka

Para arrancarlo todo y que funcione, se debe ejecutar el siguiente comando, estando situado en la carpeta InciDashboard_e5b:
``mvn spring-boot:run``

   
## Interfaz HTML
  1. Escribir en el navegador: http://localhost:8090/ COMPLETAR!
  2. En el menu escoger entre identificarse o registrarse y hacer click.
  3. Al hacer click en el boton identificarse y si usted es operario deberá introducir su identifier y password.
  4. Si selecciona registrarse deberá introducir sus datos.
  5. Una vez identificado, aparecerán en el menu 3 botones inicio/incidencias/filtro.
  6. Inicio es la pagina principal.
  7. Incidencias se abre un submenu en el que podrá elegir 3 opciones Actuales/Asignadas a mí/Por categorías.
  8. Actuales muestra las incidencias ocurridas en tiempo real por todos los operarios.
  9. Asignadas a mí muestra las incidencias asignadas a el operario que ha iniciado sesión.
  10. Por categorías puede ver las incidencias según una determinada categoría.
  11. Filtro se puede actualizar el filtro seleccionando e introduciendo una serie de campos.
  
  
