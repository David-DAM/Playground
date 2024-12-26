# Java Playground

Este proyecto es un conjunto de ejercicios y ejemplos prácticos en Java diseñados para explorar diversas características
del lenguaje y sus bibliotecas. Incluye ejercicios en la terminal que abarcan streams, manejo de archivos,
multithreading, bibliotecas populares, patrones de diseño, programación orientada a objetos (OOP) y más.

## Tabla de Contenidos

- [Descripción](#descripción)
- [Características](#características)
- [Requisitos Previos](#requisitos-previos)
- [Instalación](#instalación)
- [Ejercicios Disponibles](#ejercicios-disponibles)
- [Ejecución](#ejecución)
- [Contribuciones](#contribuciones)

---

## Descripción

El proyecto tiene como objetivo proporcionar un entorno práctico para aprender y practicar conceptos avanzados y básicos
de Java. Cada ejercicio está diseñado para ejecutarse desde la terminal, facilitando la experimentación y la comprensión
de temas clave del lenguaje.

## Características

- **Streams API**: Ejercicios para filtrar, mapear y reducir colecciones de datos.
- **Manejo de Archivos**: Lectura y escritura de archivos, incluyendo ejemplos de NIO y IO.
- **Multithreading**: Ejemplos prácticos de hilos, sincronización y concurrencia.
- **Bibliotecas Populares**: Uso de bibliotecas como Jackson.
- **Patrones de Diseño**: Implementaciones prácticas de patrones como Singleton, Factory, Observer y más.
- **OOP**: Ejercicios para practicar herencia, polimorfismo, encapsulación y abstracción.
- **Ejemplos de Código**: Código bien documentado para cada tema, con comentarios explicativos.

## Requisitos Previos

- **Java**: JDK 17 o superior.
- **Maven**: Para gestionar las dependencias y construir el proyecto.

## Instalación

1. Clona este repositorio:
   ```bash
   git clone https://github.com/DAVID-DAM/Playground.git
   cd Playground
   ```

2. Construye el proyecto con Maven:
   ```bash
   mvn clean install
   ```

## Ejercicios Disponibles

### Streams API

- Filtrado de listas.
- Agrupación y partición de datos.
- Operaciones terminales como `collect` y `reduce`.

### Manejo de Archivos

- Lectura y escritura de archivos de texto.
- Manejo de archivos binarios.
- Ejemplos con `Files` y `Paths` de NIO.

### Multithreading

- Ejercicios básicos con `Thread` y `Runnable`.
- Uso de `ExecutorService` y `Future`.
- Sincronización con `synchronized` y `ReentrantLock`.

### Patrones de Diseño

- Singleton.
- Factory.
- Observer.
- Strategy.
- Decorator.

### Programación Orientada a Objetos

- Herencia y polimorfismo con ejemplos prácticos.
- Ejercicios de encapsulación y abstracción.
- Implementación de interfaces y clases abstractas.

## Ejecución

1. Dirígete al directorio `target` después de compilar el proyecto:
   ```bash
   cd target
   ```

2. Ejecuta el programa principal desde la terminal:
   ```bash
   java -jar java-playground.jar
   ```

3. Navega por los ejercicios siguiendo el menú interactivo o ejecutando clases específicas:
   ```bash
   java com.ejemplo.playground.StreamsExample
   ```

## Contribuciones

¡Contribuciones son bienvenidas! Si tienes ideas para nuevos ejercicios o mejoras en los existentes, abre un issue o
envía un pull request.