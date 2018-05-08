#language: es
Característica: Comprobación de incidencias

  Escenario: Una incidencia
  	Dado que nos conectamos al dashboard
   	Y se logea sesion con un agente con nombre "uo111111" y contraseña "123456"
    Cuando el agente se loguea en el sistema
    Entonces aparece en su vista la incidencia "normal1"