# Practica 6: Testing de una aplicacion de Spring Boot

## Objetivo de la práctica


[![](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/BlancadePedro/Practica6)

Dado un desarrollo de Spring Boot, es necesario anhadir tests a las siguientes clases:

- DNI & Telefono (Unit Tests) (Cada clase tiene un metodo y varias casuisticas para probar)
- ProcessController (E2E Tests) (2 endpoints)


## Pruebas jUnit

En la carpeta "model" del directorio correspondiente a los Test se contrarán dos ficheros: "DNITest.java" y "TelefonoTest.java", donde se han desarrollado las pruebas convenientes.

# Pruebas DNI
Se van a realizar cinco pruebas con cinco DNIs distintos. El primero cumple todos los requisitos que debe tener (ocho números y el último una letra), el segundo es erróneo ya que son solo números ("123456789") y los tres últimos corresponden a los modelos inválidos. Mediante la función validar() se comprueba que solo el primero devuelva true, mientras que el resto false.

# Pruebas Teléfono
Se van a realizar varias pruebas con distintos números de teléfono para verificar si se aceptan números tanto con prefijo como sin prefijo. A su vez, se han introducido dos ejemplos que no cumplen con las especificaciones ya que contienen una letra o son demasiado largos. Para estos últimos la función validar() devuelve false, mientras que los ejemplos que se suponen validos dicha función dvuelve true, confirmando lo esperado.


## Pruebas End to End

Se va a validar que la clase ProcessControllerTest.java, localizada en la carpeta "controller" del directorio correspondiente a los Test, funcione adecuadaente. Para ello, se va a verificar el status y el cuerpo de la respuesta correspondiente a cada caso. Se han realizado dos tipos de pruebas distintas, comprobando si la respuesta es correcta (ok) o no (ko), teniendo un total de cuatro @Test.

- Se comprueba que al enviar unos datos supuestos correctos la respuesta obtenida es "OK", tanto del cuerpo como de Status.
- A su vez, se introducen un DNI y un número de teléfono erróneos al no cumplir con las especificaciones del jUnit, pero que no son nulos. Por ello, el cuerpo que se va a obtener es igual a KO, pero el status es OK.
- Por otro lado, se va comprobar que al enviar una serie de datos el mensaje HTML que se reciba es el correspondiente, mediante la función .contains(). 
- Finalemnte, para obtener un status BAD_REQUEST se ha enviado un MultiValueMap vacío. 


## References

- https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/boot-features-testing.html
- https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/web/client/TestRestTemplate.html
- https://www.urlencoder.org/
