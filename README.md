# library-control

Este repositorio comprende el entregable correspondiente al RA6. Para realizarlo, el alumno deberá **hacer un fork** del repositorio y trabajar desde su usuario. La entrega se realizará suministrando **el enlace al repositorio del alumno**.

## Descripción del problema

La biblioteca central de una gran ciudad ha implantado un sistema automático de control de accesos. Cada vez que un usuario entra o sale del edificio, el sistema registra el evento en un fichero de texto.

Debido al gran volumen de datos generado diariamente, no es posible analizar los registros de forma manual. Además, el sistema puede contener errores, como salidas sin entrada previa o entradas duplicadas.

La dirección de la biblioteca necesita un programa que procese estos registros y obtenga información útil sobre el uso real del edificio.

Para ello se proporciona un fichero de texto que contiene varios miles de líneas. Cada línea representa un evento registrado por el sistema de control de accesos. El formato de cada línea del fichero:

    ID_USUARIO;EVENTO

Donde:

* **ID_USUARIO**: cadena alfanumérica sin espacios (por ejemplo, U0042).
* **EVENTO**: puede tomar uno de los siguientes valores: ENTRADA o SALIDA.

El programa aceptará como argumento la ruta al fichero de texto con los registros:

    NombrePrograma <Ruta fichero>

### Carácterísticas del fichero de entrada

* Contiene al menos 1000 registros.
* Incluye cientos de usuarios distintos.
* Pueden existir registros inconsistentes:
    * Salidas sin entrada previa -> Deben descartarse
    * Entradas duplicadas -> Cuentan como solo una entrada.


## Trabajo por hacer

El alumno debe completar las funciones con //TODO e implementar las interfaces propuestas. Puede añadir además todos los métodos que considere necesarios para completar el resto del código.

Para facilitar el problema, el método main ya está implementado.

### Ejemplo de ejecución:

El siguiente ejemplo es únicamente orientativo para comprender el formato de los datos. El fichero real utilizado en el ejercicio será mucho más grande.

Ejemplo de entrada:

    U001;ENTRADA
    U002;ENTRADA
    U001;SALIDA
    U001;SALIDA
    U003;ENTRADA
    U002;ENTRADA
    U004;SALIDA
    U002;SALIDA
    U002;ENTRADA

Ejemplo de salida:

    Usuarios actualmente dentro de la biblioteca:
    U002
    U003

    Número de entradas por usuario:
    U001 -> 1
    U002 -> 2
    U003 -> 1

    Usuario(s) con más entradas:
    U002

