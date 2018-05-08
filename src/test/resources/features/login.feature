#language: es
Característica: Comprobación de agentes

  Escenario: Un agente tiene datos correctos
    Dado un agente con nombre "uo111111", contraseña "123456" se loguea
    Cuando el agente se loguea en el sistema
    Entonces ve las incidencias asignadas
