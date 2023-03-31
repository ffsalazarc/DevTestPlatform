# Proyecto: API de consulta de precios para productos de comercio electrónico

Este proyecto consiste en una aplicación/servicio en SpringBoot que provee una API REST que permite consultar los precios finales de venta de productos de una cadena de comercio electrónico en función de la fecha de aplicación, el identificador del producto y el identificador de la cadena.

La información de precios se obtiene de una tabla llamada PRICES que se encuentra en una base de datos en memoria H2, que se inicializa con los datos del ejemplo proporcionado.

# Tabla PRICES
La tabla PRICES contiene la siguiente información:

| Campo       | Descripción                                                                                   |
|-------------|------------------------------------------------------------------------------------------------|
| BRAND_ID    | Identificador de la cadena del grupo (1 = ZARA).                                               |
| START_DATE  | Fecha y hora de inicio del rango de fechas en el que aplica el precio tarifa indicado.         |
| END_DATE    | Fecha y hora de fin del rango de fechas en el que aplica el precio tarifa indicado.            |
| PRICE_LIST  | Identificador de la tarifa de precios aplicable.                                               |
| PRODUCT_ID  | Identificador del código de producto.                                                         |
| PRIORITY    | Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rango de fechas se aplica la de mayor prioridad (mayor valor numérico). |
| PRICE       | Precio final de venta.                                                                         |
| CURR        | Código ISO de la moneda.                                                                       |


# API REST
La API REST proporciona el siguiente endpoint:

Consultar precio final de venta de un producto

*GET /prices?appliedDate={appliedDate}&productId={productId}&brandId={brandId}*

# Parámetros de entrada
| Parámetro    | Tipo   | Descripción                                                  |
| ------------ | ------ | ------------------------------------------------------------ |
| appliedDate  | String | Fecha y hora de aplicación del precio en formato ISO 8601 (yyyy-MM-dd'T'HH:mm:ss) |
| productId    | Long   | Identificador del código de producto.                        |
| brandId      | Long   | Identificador de la cadena del grupo (1 = ZARA).             |

# Datos de salida
| Campo        | Descripción                                                                                   |
| ------------ | --------------------------------------------------------------------------------------------- |
| PRODUCT_ID   | Identificador del código de producto.                                                        |
| BRAND_ID     | Identificador de la cadena del grupo (1 = ZARA).                                             |
| START_DATE   | Fecha y hora de inicio del rango de fechas en el que aplica el precio tarifa indicado.        |
| END_DATE     | Fecha y hora de fin del rango de fechas en el que aplica el precio tarifa indicado.           |
| PRICE_LIST   | Identificador de la tarifa de precios aplicable.                                              |
| PRIORITY     | Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rango de fechas se aplica la de mayor prioridad (mayor valor numérico). |
| PRICE        | Precio final de venta.                                                                        |
