#language: es
Característica: Comprobación de incidencias

  Escenario: Una incidencia
    Dado un agente con nombre "uo111111", contraseña "123456" se loguea
    Cuando se le asigna una incidencia "normal1" a "uo111111"
    Entonces aparece en su vista la incidencia "normal1"
    Y  modifica "nomal1" a cerrada