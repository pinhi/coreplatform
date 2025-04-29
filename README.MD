# CORE PLATFORM
Some generic description of the project.

## Comentarios
Antes de nada, suelo programar en inglés todo, pero escribir los comentarios creo que es una tonteria para este caso. Además quiero expresarme libremente.

Siendo sincero, agradezco esta oportunidad. Me he tomado la prueba como una oportunidad para aprender. Hay cosas que no domino totalmente y las iré comentando. Sé que esto juega en mi contra, en el proceso de selección, pero prefiero ser sincero.

### Planificacion
* Primero, con Spring initializer genero el proyecto y adapto los sistemas de carpetas a ports & adapters. Creo los clases para el sistema, pero no hago mas.
* Segundo, creo el modelo de datos, el insert, que es obligatorio y la configuracion
* Tercero, creo el acceso desde el controlador a la base de datos, los mappers para dto - domain - entity y hago DDD. Red, green, refactor. Para este caso no haria falta, ya que todo es básico, pero por practicar. Esta podria subdividirla en 2 tranquilamente, pero prefiero hacerlo todo junto. Aqui creo ademas las pruebas pedidas.
* Cuarto, implementar circuit breakers.
* Quinto, para lo que se me pudiese olvidar, mejorar o aprovecho para aprender. Aunque dudo que haga los comentarios adicionales de la prueba. No quiero dedicarle tanto tiempo.

### Decisiones
* Arquitectura: Hexagonal, relativamente nueva para mi. Estoy aprendiendola en mi actual proyecto. Decisión, como comentáis que puede ser MUY escalable, pensé en usar Vertical SLICING, que sería lo mejor para aplicaciones más grandes, pero no lo veo necesario ahora mismo.
* DDD: Me parece que va de la mano de la arquitectura hexagonal. Novato en ello.
* TDD: He trabajado muy poco con esto, porque en la mayoria de trabajos hay que entregar todo ayer. Me gusta. Puede verse la intencion en el history de git.
* Git flow: Con lo que más cómodo me siento trabajando.
* Open Api: Usaré Swagger, porque me gusta como documenta y lo facil que se lee, pero usaré un proyecto de Postman para consumirlo.
* : 
* Interface: 

### Tiempo

* Pre: Ni idea de cuanto tiempo estuve mirando documentación. Supongo que 2 h o así. Revisé: arquitectura hexagonal, DDD, vertical slicing, escoger mejores atributos de entidad.
* 1º commit: +-1h
* 2º commit: 30m (quise crear un converter porque las fechas de la tabla dada, no coinciden con H2, pensé en añadir un converter, pero creo que me llevaría mas timpo del que tengo ahora mismo)
* 3* commit: +-2h