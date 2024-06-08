# JAVA STREAMS

## CARACTERISTICAS
- Introducido con el JDK 8
- El cambio más importante de Java
- Cambio de paradigma con el uso de programación funcional
- Mejora la comprensión del código, rendimiento y reusabilidad 

## QUE ES EL PARADIGMA FUNCIONAL?

Es una forma no imperativa (la forma tradicional) de declarar que queremos hacer.
una similitud sería el lenguaje SQL, ya que en este le decimos que información queremos
que nos traiga, con que filtrado pero no de que manera lo hago, nos abstraemos de ese punto

## ERES UN CANDIDATO PARA USAR STREAMS? 

- Has **RECORRIDO** una lista?
- Has tenido que usar una condición para **FILTRAR**? 
- HAS **ASIGNADO** (MAPPING) un nuevo valor?
- U realizado agrupaciones, medías, maximos, minimos, etc...?

## FELICIDADES!!

Eres un candidatos válido 

## MERECE LA PENA REALMENTE?

P: Si ya se usár mis bucles for y démas que me quieres traer aquí?

D: Y si te digo que puedes hacer todo lo anterior en una sola línea de código? no me creees?

``` java
List<Integer> numberList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

List<Integer> filteredListWithPlus1 = numberList.stream()
        .filter(x -> x > 5)
        .map(x -> x + 1)
        .collect(Collectors.toList());
```

## PERO COMO FUNCIONA ESTO?

Los Streams diseñan un flujo de trabajo que se ejecuta de forma unitaria item a item.

Es decir que por cada función que encadenamos al stream lo realiza en la misma iteración del item

<picture>
  <source media="(prefers-color-scheme: dark)" srcset="https://media.geeksforgeeks.org/wp-content/uploads/20230705133732/Stream-in-Java-768.png">
  <source media="(prefers-color-scheme: light)" srcset="https://media.geeksforgeeks.org/wp-content/uploads/20230705133732/Stream-in-Java-768.png">
  <img alt="" src="https://media.geeksforgeeks.org/wp-content/uploads/20230705133732/Stream-in-Java-768.png">
</picture>

Es probable que este tipo de programación os sea similar al patrón Builder ya que encadenamos que queremos que haga y por eso resulta muy cómodo de realizar y es muy visual

## QUE SON ESTAS OPERACIONES?

- Las operaciones intermedias son las operaciones que como su nombre dicen se realizan durante el flujo de trabajo
- Las operaciones terminales se realizan unicamente al final para **RECOLECTAR** la información procesada de la forma que queramos

## DE QUE SE COMPONEN ESTAS OPERACIONES?

Al final todas estas operaciones que vemos son lambdas

Las lambdas son funciones anonimas que pueden o no tener parametros de entrada, llevado a java son interfaces llamadas funcionales, 
porque estas interfaces tienen un solo método abstracto que podemos implementar

Principalmene de Predicates, no son más que condiciones que declaramos, devuelven un booleano
```` java
Predicate<Integer> predicate = x -> x > 5;
predicate.test(1);
````

Funciones que al recibir un genérico pueden tener cualquier input y output

````java
Function<Integer,Integer> function = x -> x + 1;
function.apply(1);
````

Supplier que se encargan de proporcionar algún tipo de dato
```` java
 Supplier<List<Integer>> supplier = () -> new ArrayList<>();
 supplier.get();
````

Collectors para obtener la información agrupandola de distintas maneras
```` java
Collectors.toList();
````

Comparators para realizar distintas comparaciones
```` java
Comparator.comparingInt(x -> (int) x);
````

Y varias más...

## MEJOR PONERNOS MANOS A LA OBRA