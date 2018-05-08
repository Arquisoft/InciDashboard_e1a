#language: es
Característica: Comprobación de agentes

  Escenario: Un agente tiene datos incorrectos
  	Dado que nos conectamos al dashboard
    Y se logea un agente con nombre "uo222222" y contraseña "123456" que no existe
    Entonces sigue en la misma de loging
