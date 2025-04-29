# Prueba técnica Inditex Core Platform

## Pre-requisitos

La prueba técnica consiste en el desarrollo de un servicio desde cero para cubrir la funcionalidad que se menciona en el enunciado. Es un caso hipotético que pretende simular situaciones del día a día, pero de forma parcial y focalizada en los aspectos que se quieren evaluar.

Como requisitos, se han de tener en cuenta:
* Uso de Java en la versión más moderna en la que tengas experiencia.
* Uso de maven o gradle.
* Uso de Springboot.

Además, se proporcionan las siguientes recomendaciones:
* Uso de una arquitectura concreta y consistente, que se refleje en su estructura.
* Use una arquitectura con la que tengas conocimientos previos, siendo valorable el uso de arquitecturas y paradigmas modernos (TDD, CleanCode, DDD, Hexagonal, etc.).
* Uso de Kafka si lo consideras necesario.
* Modelo de respuesta de errores consistente
* Uso de caché para optimización de peticiones
* Uso de circuitbrakers, u otras estrategias para mejorar rendimiento o resiliencia (o justificación para no hacerlo).
* Uso de lambdas, streams, etc.
* Es muy importante tener un README suficientemente completo y detallado. Cuanto más, mejor. Por ejemplo:
* Instrucciones de cómo acceder al swagger
* Explicar herramientas y versiones usadas
* Propuesta de mejoras o información a añadir en el enunciado de la prueba para evitar mal entendidos a futuro.
* Es muy importante el uso de tests. Cuantos más, mejor.
* Si intentas hacer un despliegue, no olvides de la posible observabilidad. Si no lo vas a desarrollar, al menos debería aparecer en una fase de “Próximos pasos” en el Readme.
* Aunque la prueba tiene un alcance concreto, imagina que en futuro cercano podrá crecer y por lo tanto su código tiene que marcar las bases para que lo haga de una forma mantenible y robusta.
* Ten en cuenta que el rendimiento y la resiliencia también son importantes.
* Usa un repositorio de código Git en el que luego pueda compartirnos tu código, y podamos ver el histórico de la evolución de la solución.
* Junto con la URL del código fuente, entrega un pequeño informe dónde explique el tiempo dedicado al día, y justifique las decisiones que ha tomado según consideres oportuno. 

## Descripción del reto

Dentro del sistema core del ecommerce de Inditex mantenemos una base de datos que almacena los precios de los productos para cada una de las marcas. Para gestionar esta información, utilizamos una tabla llamada "PRICES". Esta tabla contiene los datos esenciales de los precios de productos, como el precio final de venta (PVP), la tarifa a aplicar en un determinado rango de fechas y otros detalles relevantes. A continuación, se presenta un ejemplo de la estructura de la tabla junto con una breve descripción de sus campos:

- `BRAND_ID`: Este campo representa un identificador único para la marca a la que pertenece el producto (por ejemplo, "1" podría representar a la marca ZARA).

- `START_DATE` y `END_DATE`: Estos campos definen un rango de fechas durante el cual un precio y una tarifa son aplicables para un producto específico. Indican la fecha de inicio y la fecha de finalización de la validez del precio y la tarifa.

- `PRICE_LIST`: Aquí encontramos un identificador que corresponde a la tarifa específica que se aplica a un producto en un período determinado.

- `PRODUCT_ID`: Este campo almacena un identificador único para cada producto, permitiendo la identificación individual de los artículos en el catálogo.

- `PRIORITY`: Un valor numérico que se utiliza para desambiguar la aplicación de precios en caso de que dos tarifas coincidan en un rango de fechas. La tarifa con la prioridad más alta es la que aplica y por lo tanto la que se debe utilizar.

- `PRICE`: Indica el precio final de venta del producto en la moneda correspondiente.

- `CURR`: Aquí se almacena el código ISO de la moneda en la que se establece el precio.

**Objetivo:**

El objetivo principal es crear una aplicación o servicio utilizando el framework Spring Boot que ofrezca un punto de acceso REST para realizar consultas sobre esta base de datos de precios. Este endpoint REST deberá:

- Aceptar como parámetros de entrada la fecha de consulta (o aplicación), el identificador del producto y el identificador de la marca.

- Proporcionar como resultado el identificador del producto, el identificador de la marca, la tarifa que se aplica, el intervalo de fechas durante el cual se aplica el precio y el precio final que debe aplicarse.

**Base de Datos:**

La aplicación debe utilizar una base de datos en memoria del tipo H2, la cual debe ser inicializada con los datos de ejemplo proporcionados en **Tabla de Base de Datos** en el siguiente punto. Si es necesario, puedes modificar los nombres de los campos o agregar nuevos campos según consideres apropiado. Asegúrate de elegir los tipos de datos adecuados para cada campo.

**Tabla de Base de Datos (PRICES):**

| BRAND_ID | START_DATE            | END_DATE              | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|----------|-----------------------|-----------------------|------------|------------|----------|-------|------|
| 1        | 2020-06-14-00.00.00   | 2020-12-31-23.59.59   | 1          | 35455      | 0        | 35.50 | EUR  |
| 1        | 2020-06-14-15.00.00   | 2020-06-14-18.30.00   | 2          | 35455      | 1        | 25.45 | EUR  |
| 1        | 2020-06-15-00.00.00   | 2020-06-15-11.00.00   | 3          | 35455      | 1        | 30.50 | EUR  |
| 1        | 2020-06-15-16.00.00   | 2020-12-31-23.59.59   | 4          | 35455      | 1        | 38.95 | EUR  |

**Pruebas:**

Adicionalmente, se espera que desarrolles pruebas para el endpoint REST que validen las siguientes solicitudes al servicio utilizando los datos de ejemplo proporcionados:

1. Prueba 1: Realizar una petición a las 10:00 del día 14 para el producto 35455 y la marca 1 (ZARA).

2. Prueba 2: Realizar una petición a las 16:00 del día 14 para el producto 35455 y la marca 1 (ZARA).

3. Prueba 3: Realizar una petición a las 21:00 del día 14 para el producto 35455 y la marca 1 (ZARA).

4. Prueba 4: Realizar una petición a las 10:00 del día 15 para el producto 35455 y la marca 1 (ZARA).

5. Prueba 5: Realizar una petición a las 21:00 del día 16 para el producto 35455 y la marca 1 (ZARA).

**Comentarios adicionales:**

- Asegúrate de que el servicio pueda manejar solicitudes para diferentes productos y marcas, y devolver resultados precisos en función de los parámetros proporcionados.

- Puedes usar cualquier biblioteca o tecnología de pruebas que consideres adecuada para validar el comportamiento del servicio REST.