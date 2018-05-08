#language: es
Característica: Comprobación de agentes

  Escenario: Un agente tiene datos correctos
  	Dado que nos conectamos al dashboard
    Y se logea sesion con un agente con nombre "uo111111" y contraseña "123456"
    Cuando el agente se loguea en el sistema
    Entonces ve las incidencias asignadas
