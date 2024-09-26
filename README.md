# Proyecto de Automatización de Pruebas con Selenium

## Introducción

Este proyecto forma parte de la asignatura **Tópicos Avanzados de Software** y tiene como objetivo automatizar el proceso de compra en el sitio web [SauceDemo](https://www.saucedemo.com/) utilizando **Selenium WebDriver** y **JUnit**. 

### Alumnos

- Sara Herrera Ramírez
- Julio Franco Ardila

## Descripción del Proyecto

El propósito de este proyecto es simular una compra exitosa en la tienda **SauceDemo** y verificar el total de la compra, incluyendo impuestos. La clase principal del proyecto es `PurchaseTest`, que cubre el siguiente flujo:

1. Iniciar sesión con un usuario válido.
2. Seleccionar múltiples productos y añadirlos al carrito.
3. Proceder al checkout.
4. Verificar el precio total de los productos, incluidos los impuestos.
5. Finalizar el proceso de compra y verificar que se muestre el mensaje de confirmación correcto.

### Funcionalidades Automatizadas

- **Login**: Validar el acceso de un usuario registrado.
- **Añadir productos al carrito**: Permitir agregar una lista de productos especificados al carrito.
- **Checkout**: Llenar el formulario de checkout con información del usuario.
- **Verificación del total**: Comparar los precios de los productos añadidos con el total mostrado en la pantalla, incluyendo impuestos.
- **Finalización del proceso**: Verificar que el mensaje final de la compra sea correcto.

## Requisitos

Para ejecutar el proyecto, necesitas tener instalados los siguientes componentes:

- **Java JDK** (versión 8 o superior)
- **Gradle**
- Este proyecto utiliza **WebDriverManager** para gestionar automáticamente las dependencias del navegador, por lo que no es necesario configurar manualmente **ChromeDriver** ni otros drivers de navegador.


## Configuración

1. **Clonar el repositorio**:

   ```bash
   git clone https://github.com/schr95/selenium-project.git
   ```
2. Instalar las dependencias:

- Utiliza Gradle para gestionar las dependencias. Ejecuta el siguiente comando para compilar el proyecto y descargar las dependencias necesarias:

```bash
   gradle build
   ```

3. **Configurar el entorno de desarrollo**:
   - Utiliza un entorno de desarrollo integrado (IDE) como **IntelliJ IDEA** o **Eclipse**, y asegúrate de que las dependencias de **JUnit** y **Selenium WebDriver** estén correctamente configuradas.

## Estructura del Proyecto

El proyecto contiene la siguiente estructura principal:

```bash
/src
  └── /test
    └── LoginTest.java 
    └── PurchaseTest.java    // Clase principal que contiene las pruebas de automatización
```

### Descripción de la Clase `PurchaseTest`

La clase `PurchaseTest` implementa los siguientes métodos:

- `login(String username, String password)`: Inicia sesión en la aplicación con el usuario especificado.
- `addProductsToCart(List<String> itemNames)`: Añade productos al carrito basándose en una lista de nombres de productos.
- `checkout(String firstName, String lastName, String postalCode)`: Procede al checkout, llenando los datos de envío.
- `verifyTotal(List<Double> productPrices)`: Verifica que el total de los productos y los impuestos sean correctos.
- `finishProcess(String expectedMessage)`: Finaliza el proceso de compra y valida que se muestre el mensaje de confirmación correcto.

## Ejecución de las Pruebas

- Para compilar y ejecutar las pruebas:

```bash
gradle build clean test
```

- Revisar los resultados:

Si todas las pruebas son exitosas, recibirás un mensaje de éxito en la consola. Si alguna prueba falla, el mensaje de error detallará los valores esperados y los valores reales.

## Notas Adicionales

- Si la estructura del sitio web de **SauceDemo** cambia, puede ser necesario ajustar los **XPath** utilizados en las pruebas.
- Este proyecto fue desarrollado con fines educativos.

