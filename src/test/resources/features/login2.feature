#language: es
Característica: Comprobación de agentes

  Escenario: Un agente tiene datos incorrectos
    Dado un agente con nombre "uo222222", contraseña "123456" se loguea
    Y no autorizado en el sistema como "uo222222"
    Cuando el agente se loguea en el sistema
    Entonces sigue en la misma de loging
