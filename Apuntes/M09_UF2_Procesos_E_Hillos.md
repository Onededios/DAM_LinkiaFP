# M09 UF2 Procesos e hilos

## Preguntas

```
Test (4 puntos)
• 10 preguntas. Estilo libro o Test UF.
• Cada pregunta 0,4 puntos. Las incorrectas no restan.
Una pregunta (6 puntos )sobre la teoria del libro y los pdf. (no hay código)
• Programación concurrente, multiprogramación, programación distribuida.
• Estados de los procesos.
• Planificación de procesos.
• Creación de procesos y comunicación entre ellos (clases).
• Hilos, creación de hilos, métodos más importantes.
• Sincronización y problemas de sincronización.
• Sección crítica y como solucionarla.
• Métodos y sentencias sincronizadas.
• Semáforos.
• Clases y métodos más importantes.
```

## Teoría

### Programación concurrente

Se basa en ejecutar múltiples tareas al mismo tiempo, pero no en paralelo y de forma no secuencial.

En un programa concurrente las tareas puede continuar sin la necesidad que otras comiencen o finalicen.

Ejemplo:

```sql
Un programa que utiliza varios hilos para realizar tareas
simultáneamente, como procesar solicitudes de usuarios en un servidor web.
```

### Programación paralela

La programación paralela es una técnica de programación en la que muchas instrucciones se ejecutan simultáneamente.

Las tareas se realizarán de forma simultánea, comenzarán y finalizarán sin interrupciones.

Ejemplo:

```sql
Dividir una tarea en partes y realizar esas partes simultáneamente en
varios núcleos de un procesador para acelerar el proceso.
```

### Multiprogramación

Es una técnica de procesamiento que permite que varias aplicaciones se ejecuten de forma simultánea en una misma computadora, con la particularidad de que se utilizan los recursos de forma compartida y coordinada.

Ejemplo:

```sql
Cuando hay varias aplicaciones abiertas al mismo tiempo, como un
navegador web, un reproductor de música y una aplicación de procesamiento de
texto. Permite alternar entre estas aplicaciones y realizar tareas en cada una de ellas
sin necesidad de cerrar una antes de abrir la siguiente.
```

### Programación distribuida

Es un metódo en el que las tareas y los recursos de un programa se distribuyen en múltiples dispositivos o sistemas interconectados en una red.

Ejemplo:

```sql
La construcción de aplicaciones web, donde el servidor y los clientes se
comunican a través de una red para intercambiar datos y realizar tareas.
```

### Hilo (Thread)

- Unidad básica de utilización de la CPU, y más concretamente de un core del procesador.
- Secuencia de código que está en ejecución, pero dentro del contexto de un proceso.
- Los hilos se ejecutan dentro del contexto de un proceso.
- Dependen de un proceso para ejecutarse.
- Los procesos son independientes y tienen espacios de memoria diferentes.
- Dentro de un mismo proceso pueden coexistir varios hilos ejecutándose que compartirán la memoria de dicho proceso.

#### Multitarea

Es la ejecución simultanea de varios hilos:
- **Capacidad de respuesta:** Los hilos permiten a los procesos continuar atendiendo peticiones del usuario aunque alguna de las tareas (hilo) que esté realizando el programa sea muy larga.
- **Compartición de recursos:** Por defecto, los threads comparten la memoria y todos los recursos del proceso al que pertenecen.
- La creación de nuevos hilos no supone ninguna reserva adicional de memoria por parte del sistema operativo.
- **Paralelismo real:** La utilización de threads permite aprovechar la existencia de más de un núcleo en el sistema en arquitecturas multicore. 

#### Recursos compartidos

- Los procesos mantienen su propio espacio de direcciones y recursos de ejecución mientras que los hilos dependen del proceso.
- Comparten con otros hilos la sección de código, datos y otros recursos.
- Cada hilo tiene su propio contador de programa, conjunto de registros de la CPU y pila para indicar por dónde se está ejecutando.

#### Estados

Los hilos pueden cambiar de estado a lo largo de su ejecución.

- **Nuevo:** el hilo está preparado para su ejecución pero todavía no se ha realizado la llamada correspondiente en la ejecución del código del programa.
- **Listo:** el proceso no se encuentra en ejecución aunque está preparado para hacerlo. El sistema operativo no le ha asignado todavía un procesador para ejecutarse.
- **Runnable:** el hilo está preparado para ejecutarse y puede estar ejecutándose.
- **Bloqueado:** el hilo está bloqueado por diversos motivos esperando que el suceso suceda para volver al estado Runnable.
- **Terminado:** el hilo ha finalizado su ejecución.

#### Gestión

- **Creación y arranque de hilos:** Cualquier programa a ejecutarse es un proceso que tiene un hilo de ejecución principal. Este hilo puede a su vez crear nuevos hilos que ejecutarán código diferente o tareas, es decir el camino de ejecución no tiene por qué ser el mismo.
- **Espera de hilos:** Como varios hilos comparten el mismo procesador, si alguno no tiene trabajo que hacer, es bueno suspender su ejecución para que haya un mayor tiempo de procesador disponible.

#### Creación
- **Implementando la interfaz Runnable:** 
  - La interfaz Runnable proporciona la capacidad de añadir la funcionalidad de un hilo a una clase simplemente implementando dicha interfaz. La interfaz Runnable debería ser utilizada si la clase solamente va a utilizar la funcionalidad run de los hilos.

```java
class RunThread implements Runnable {
    Thread t;

    RunThread() {
        t = new Thread(this, "Nuevo Thread");
        System.out.println("Creado hilo: " + t);
        t.start(); // Arranca el nuevo hilo de ejecución. Ejecuta run
    }

    public void run() {
        System.out.println("Hola desde el hilo creado!");
        System.out.println("Hilo finalizando.");
    }

    public static void main(String args[]) {
        new RunThread(); // Crea un nuevo hilo de ejecución
        System.out.println("Hola desde el hilo principal!");
        System.out.println("Proceso acabando.");
    }
}
```  

- **Extendiendo de la clase Thread mediante la creación de una subclase:**
  - La clase Thread es responsable de producir hilos funcionales para otras clases e implementa la interfaz Runnable Creación y Arranque de hilos

```java
class HelloHilo extends Thread {

    public void run() {
        // Your implementation of the run method goes here
        // It defines the code that will be executed in the new thread
    }
}

 public class HelloThread {
    public static void main(String args[]) {
       HelloHilo hl = new HelloHilo(); // Creates a new thread
       hl.start(); // Starts the new thread's execution, which will invoke the run method

       System.out.println("Hola desde el hilo creado!");
       System.out.println("Hola desde el hilo principal!");
       System.out.println("Proceso acabando.");
   }
}
```

- El método run() implementa la operación create conteniendo el código a ejecutar por el hilo. Dicho método contendrá el hilo de ejecución.
- La clase Thread define también el método start() para implementar la operación create. Este método es que comienza la ejecución del hilo de la clase correspondiente.

#### Espera

- **Join:** Si un Thread necesita esperar a que otro termine (por ejemplo el Thread padre espera a que termine el hijo) puede usar el método join(). Este método detiene el hilo actual hasta que termine el hilo sobre el que se llama join().

```java
public class EjemploJoin extends Thread {
    private String mensaje;

    EjemploJoin(String msg) {
        mensaje = msg;
    }

    public void run() {
        for (int i = 1; i <= 5; i++)
            System.out.println(mensaje + i);
        System.out.println("Fin Hilo " + mensaje);
    }

    public static void main(String args[]) {
        System.out.println("Inicio programa principal");
        EjemploJoin h1 = new EjemploJoin("Alumno Joan");
        EjemploJoin h2 = new EjemploJoin("Alumno Ana");
        System.out.println("Inicio Alumno Joan");
        h1.start();
        System.out.println("Inicio Alumno Ana");
        h2.start();
        try {
            h1.join();
            h2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Fin programa principal");
    }
}
```

- **Sleep:** duerme un hilo por un período especificado.

```java
class RunnableDemo implements Runnable {
    private Thread t;
    private String threadName;

    // constructor -> initialization
    RunnableDemo(String name) {
        threadName = name;
        System.out.println("Creando " + threadName);
    }

    // code executed by threads
    public void run() {
        System.out.println("Running " + threadName);
        try {
            for (int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", Count: " + i);
                // sleep the thread
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrumpido.");
        }
        System.out.println("Thread " + threadName + " finalizado.");
    }

    public void start() {
        System.out.println("Iniciando " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}
```

Ambas operaciones de espera pueden ser interrumpidas, si otro hilo interrumpe al hilo actual mientras está suspendido por dichas llamadas.

- Una interrupción es una indicación a un hilo que debería dejar de hacer lo que esté haciendo para hacer otra cosa.
- Un hilo envía una interrupción mediante la invocación del método interrupt() en el objeto del hilo que se quiere interrumpir.

**isAlive():** comprueba si el hilo no ha finalizado su ejecución antes de trabajar con él.

#### Prioridades

**setPriority( int )**
- El método setPriority() asigna al hilo la prioridad indicada por el valor pasado como parámetro. Hay bastantes constantes predefinidas para la prioridad, definidas en la clase Thread, tales como MIN_PRIORITY, NORM_PRIORITY y MAX_PRIORITY, que toman los valores 1, 5 y 10, respectivamente.
- Como guía aproximada de utilización, se puede establecer que la mayor parte de los procesos a nivel de usuario deberían tomar una prioridad en torno a NORM_PRIORITY. Las tareas en segundo plano, como una entrada/salida a red o el nuevo dibujo de la pantalla, deberían tener una prioridad cercana a MIN_PRIORITY.
- Con las tareas a las que se fije la máxima prioridad, en torno a MAX_PRIORITY, hay que ser especialmente cuidadosos, porque si no se hacen llamadas a sleep() o yield(), se puede provocar que el intérprete Java quede totalmente fuera de control.

**getPriority()**
- Este método devuelve la prioridad del hilo de ejecución en curso, que es un valor comprendido entre uno y diez

```java
public class Serie extends Thread {
    private double sup;
    private double inf;
    private double i;
    private double suma = 0;

    Serie(double a, double b) {
        inf = a;
        sup = b;
        System.out.println(inf);
        System.out.println(sup);
    }

    public void run() {
        for (i = inf; i <= sup; i++) {
            suma = suma + i;
            System.out.println("Hilo--> " + this.getName() + " i=" + i);
        }
    }

    public static void main(String args[]) {
        Serie h1 = new Serie(2, 5);
        h1.setName("Uno");

        Serie h2 = new Serie(5, 10);
        h2.setName("Dos");

        h1.start();
        h1.setPriority(MAX_PRIORITY);

        h2.start();
        h2.setPriority(MIN_PRIORITY);

        System.out.println("Suma=" + suma);
    }
}
```

#### Método yield()

- El método yield() tiene la función de hacer que un hilo que se está ejecutando regrese al estado de “preparado” para permitir que otros hilos de la misma prioridad puedan ejecutarse.
- Sin embargo, el funcionamiento de este método (al igual que de los hilos en general) no está garantizado, puede que después de que se establezca un hilo por medio del método yield() a su estado “preparado”, éste vuelva a ser elegido para ejecutarse.
- El método yield() nunca causará que un hilo pase a estado de espera/bloqueado/dormido, simplemente pasa de ejecutándose (running) a “preparado”.

Añadiendo el método yield() la ejecución de los hilos cambia considerablemente, siendo mucho mas equitativa.

#### Comunicación

Los hilos pueden comunicarse entre sí mediante el uso de los métodos wait(), notify() y notifyAll() de la clase Object (son métodos finales).
Estos métodos sólo pueden ser llamados desde métodos sincronizados.

- **wait()** dice al hilo llamante que deje el monitor y que pase a estado suspendido (dormido) hasta que otro hilo entre en el mismo monitor y llame a **notify()**.
- **notify()** despierta el primer hilo que llamó a **wait()** sobre el mismo objeto.
- **notifyAll()** despierta todos los hilos que llamaron a **wait()** sobre el mismo objeto.

El método wait() hace que el thread actual espere hasta que otro thread se lo notifique o a que cambie un condición. Se utiliza el método wait() en conjunción con el método notify() para coordinar la actividad de varios threads que utilizan los mismos recursos.

### Sincronización 

- Los threads se comunican principalmente mediante el intercambio de información a través de variables y objetos en memoria.
  - Los threads pertenecen al mismo proceso, y pueden acceder a toda la memoria asignada a dicho proceso utilizando las variables y objetos del mismo para compartir información, siendo este el método de comunicación más eficiente.
- Cuando varios hilos manipulan a la vez objetos compartidos, pueden ocurrir diferentes problemas.

#### Condición de carrera

Se da cuando dos o más hilos pueden acceder a los datos compartidos e intentan cambiarlos al mismo tiempo.

Ejemplo:

```sql
Imagina un programa con dos hilos que intentan incrementar una variable compartida llamada contador. Ambos hilos realizan la operación contador += 1. Si no hay sincronización, podría ocurrir una condición de carrera. Aquí hay un ejemplo en pseudo código:

# Hilo 1
leer contador
incrementar contador
guardar contador

# Hilo 2
leer contador
incrementar contador
guardar contador
Si ambos hilos leen el valor actual del contador al mismo tiempo, podrían ambos incrementarlo sin tener en cuenta el cambio realizado por el otro hilo. Esto podría llevar a resultados incorrectos, ya que el valor final del contador dependería del orden en que los hilos ejecuten sus operaciones.
```

#### Inconsistencia de memoria

Se produce cuando diferentes hilos tienen una visión diferente de lo que debería ser el mismo dato.

Ejemplo:

```sql
Supongamos que dos hilos, A y B, comparten una variable no volátil contador y ejecutan la siguiente secuencia de operaciones de manera concurrente:

Hilo A:

Lee el valor actual de contador: contador = 0
Incrementa contador: contador = contador + 1
Guarda el nuevo valor de contador: contador = 1
Hilo B:

Lee el valor actual de contador: contador = 0
Incrementa contador: contador = contador + 1
Guarda el nuevo valor de contador: contador = 1
De manera ideal, después de que ambos hilos ejecuten sus operaciones, el valor de contador debería ser 2. Sin embargo, debido a la falta de sincronización y la inconsistencia de memoria, podría ocurrir una situación en la que ambos hilos lean simultáneamente el valor original de contador (0), lo incrementen y guarden el mismo valor, resultando en un valor final de contador igual a 1.

Este escenario ilustra cómo la inconsistencia de memoria, en ausencia de mecanismos de sincronización, puede llevar a resultados no deseados en la manipulación de variables compartidas entre hilos.
```

#### Inanición

Cuando un proceso nunca llega a tomar el control de un recurso debido a que el resto
siempre toman el control antes que él por diferentes motivos.

```sql
Imaginemos un sistema con tres hilos: A, B y C. Todos compiten por un recurso compartido y siguen el siguiente patrón:

Hilo A:

Intenta acceder al recurso.
Si no puede, espera.
Hilo B:

Intenta acceder al recurso.
Si no puede, espera.
Hilo C:

Intenta acceder al recurso.
Si no puede, espera.
En este escenario, si el recurso está ocupado continuamente por un flujo constante de nuevos hilos, los hilos A, B y C pueden quedar atrapados en un estado de espera perpetua. Ningún hilo logra acceder al recurso, y todos permanecen en un estado de inanición.

La inanición ocurre cuando los hilos no pueden avanzar en su ejecución debido a la incapacidad de acceder a un recurso necesario, generando un bloqueo perpetuo.
```

#### Interbloqueo

Se produce cuando dos o más procesos o hilos están esperando indefinidamente por
un evento que solo puede generar un proceso o hilo bloqueado. 

Requiere de intervención externa.

Ejemplo:

```sql
Imaginemos un sistema con tres recursos (R1, R2, R3) y tres procesos (P1, P2, P3). Cada proceso puede tener asignados recursos y solicitar recursos adicionales. El interbloqueo ocurre cuando los procesos esperan indefinidamente por recursos que son retenidos por otros procesos.

Asignación inicial de recursos:

P1 tiene R1 asignado.
P2 tiene R2 asignado.
P3 tiene R3 asignado.
Solicitudes y asignaciones adicionales:

P1 solicita R2.
P2 solicita R3.
P3 solicita R1.
Estado actual:

P1 espera a R2 que está asignado a P2.
P2 espera a R3 que está asignado a P3.
P3 espera a R1 que está asignado a P1.
En este punto, cada proceso está esperando un recurso que posee otro proceso, creando un círculo de dependencia mutua. Ningún proceso puede liberar sus recursos actuales porque está esperando uno que está retenido por otro. Esta situación conduce al interbloqueo, donde los procesos quedan atrapados indefinidamente, sin posibilidad de avanzar.
```

#### Bloqueo activo

Es similar a un interbloqueo, excepto que el estado de los dos procesos envueltos en
el bloqueo activo cambia constantemente con respecto al otro.

Ejemplo:

```sql
Imagina dos corredores compitiendo hacia una meta. 
Si no se establecen mecanismos adecuados para garantizar que un corredor termine antes de que otro comience, podrían llegar simultáneamente, generando incertidumbre sobre quién realmente ganó.
```



### Sección crítica y como solucionarla

Se denomina sección crítica a una región de código en la cual se accede de forma ordenada a variables y recursos compartidos, de forma que se puede diferenciar de aquellas zonas de código que se pueden ejecutar de forma asíncrona. Este concepto se puede aplicar tanto a hilos como a procesos concurrentes, la única condición es que compartan datos o recursos.

Cuando un proceso está ejecutando su sección crítica, ningún otro proceso puede ejecutar su correspondiente sección crítica, ordenando de esta forma la ejecución concurrente.

El problema de la sección crítica (condición de carrera) se puede solucionar mediante la sincronización empleando monitores, que son estrucutras que encapsulan datos y operaciones.

### Métodos y sentencias sincronizadas

Cuando dos o más hilos necesitan acceder a un recurso compartido, entonces necesitan alguna forma de asegurar que el recurso se usa sólo por un hilo al mismo tiempo: **Sincronización**.

Un **monitor** es un objeto usado como un cerrojo de exclusión mutua. Sólo un hilo puede poseer el monitor en un momento determinado.

Cuando un hilo entra en el monitor, los demás hilos que intentan entrar en él, quedan suspendidos hasta que el primer hilo lo deja.

#### Métodos sincronizados

Mecanismo para construir una sección crítica de forma sencilla. La ejecución por parte de
un hilo de un método sincronizado de un objeto en Java imposibilita que se ejecute a la vez
otro método sincronizado del mismo objeto por parte de otro hilo.
Para crear un método sincronizado, solo es necesario añadir la palabra clave synchronized
en la declaración del método, sabiendo que los constructores ya son síncronos por defecto.

```java
public synchronized void método_a_sincronizar () {
    // CODE
}
```

Todos los objetos de Java tienen asociado su propio monitor implícito.

Para entrar en el monitor de un objeto sólo hay que llamar a un método synchronized.
Cuando un hilo está ejecutando un método sincronizado, todos los demás hilos que
intenten ejecutar cualquier método sincronizado del mismo objeto tendrán que
esperar.

#### Sentencias sincronizadas

Permiten una sincronización de grano fino al sincronizar únicamente una región específica de código.
A veces la solución de sincronizar un método no es posible porque no tenemos el código fuente de la clase, y no podemos hacer que el método sea sincronizado.

En ese caso podemos usar la sentencia **synchronized**:

```java
synchronized(objeto){
    // sentencias que se sincronizan
}
```

**objeto** es el objeto que sincronizamos.
Esta sentencia hace que cualquier llamada a algún método de objeto se ejecutará después de que el hilo actual entre en el monitor de objeto.

### Semáforos

Los semáforos se pueden utilizar para controlar el acceso a un determinado recurso formado por un número finito de instancias. Se representa como una variable entera donde su valor representa el número de instancias libres o disponibles en el recurso compartido y una cola donde se almacenan los procesos o hilos bloqueados esperando para usar el recurso. En la inicialización se proporciona un valor inicial igual al número de recursos disponibles.

- **wait (espera):** Un proceso disminuye el número de instancias disponibles en uno, ya que se supone que lo va a utilizar.
- **Signal (señal):** Un proceso, cuando termina de usar la instancia del recurso compartido, avisa de su liberación mediante esta operación. Aumenta el valor de instancias disponibles en el semáforo. Si hay varios procesos esperando uno de ellos pasará a Runnable.

#### Semáforos binarios

- Un semáforo binario o mutex (MUTual EXclusion, Exclusión mutua) es un indicador de condición que registra si un único recurso está disponible o no.
- Un mutex solo puede tomar los valores 0 y 1. Si el semáforo vale 1, entonces el recurso esta disponible, y se puede acceder a la zona de compartición del recurso. Si el valor del semáforo es 0, el hilo debe esperar.

### Clases y métodos más importantes

#### Clase Thread

La clase Thread en Java se utiliza para trabajar con hilos de ejecución dentro de un programa.

- **start():** Método que inicia la ejecución del hilo. Se llama al método run() del hilo.
- **run():** Define el código que será ejecutado por el hilo cuando se inicia mediante start().
- **sleep(long milliseconds):** Hace que el hilo actual duerma durante el tiempo especificado.
- **join():** Hace que un hilo espere hasta que el hilo en el que se llama haya completado su ejecución.
- **getId():** Devuelve el ID del hilo.
- **setName(String name):** Establece el nombre del hilo.
- **getState():** Devuelve el estado del hilo, como NEW, RUNNABLE, BLOCKED, etc.

#### Interfaz Runnable

La interfaz Runnable en Java proporciona una forma alternativa de trabajar con hilos sin extender directamente la clase Thread.

- **run():** Define el código que será ejecutado por el hilo cuando se inicia mediante start().

#### Clase Semaphore

Se utiliza para controlar el acceso a recursos compartidos mediante el uso de un contador.

- Semaphore(int valor) Indica el valor inicial del semáforo antes de comenzar su ejecución.
- acquire() implementa la operación wait.
- release() implementa la operación signal.
  - Desbloquea un hilo que esté esperando en el método wait().

## Ejemplos en código

### Simulacion de un supermercado

- Cada hilo de cajera procesa los artículos del cliente uno por uno, simulando el tiempo de procesamiento de los artículos usando Thread.sleep.
- La salida en la consola muestra los tiempos de inicio, procesamiento y finalización para cada cliente.

#### Clase cajera (hilo)

- La clase define un hilo para una cajera en un supermercado.
- Tiene atributos para el nombre de la cajera (`nombre`), el cliente asociado (`cliente`), y un tiempo inicial (`initialTime`).
- Los constructores permiten establecer valores iniciales.
- Métodos getter y setter para `nombre`, `initialTime`, y `cliente`.
- El método `run` imprime información de procesamiento, incluyendo el tiempo de inicio, procesando cada artículo en el carrito de compras del cliente y el tiempo de finalización.

```java
class CajeraThread extends Thread {

	private String nombre;

	private Cliente cliente;

	private long initialTime;


	public CajeraThread() {
	}

	public CajeraThread(String nombre, Cliente cliente, long initialTime) {
		this.nombre = nombre;
		this.cliente = cliente;
		this.initialTime = initialTime;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getInitialTime() {
		return initialTime;
	}

	public void setInitialTime(long initialTime) {
		this.initialTime = initialTime;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public void run() {

		System.out.println("La cajera " + this.nombre + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE " 
					+ this.cliente.getNombre() + " EN EL TIEMPO: " 
					+ (System.currentTimeMillis() - this.initialTime) / 1000 
					+ "seg");

		for (int i = 0; i < this.cliente.getCarroCompra().length; i++) {
			// Se procesa el pedido en X segundos
			this.esperarXsegundos(cliente.getCarroCompra()[i]);
			System.out.println("Procesado el producto " + (i + 1) 
						+ " del cliente " + this.cliente.getNombre() + "->Tiempo: " 
						+ (System.currentTimeMillis() - this.initialTime) / 1000 
						+ "seg");
		}

		System.out.println("La cajera " + this.nombre + " HA TERMINADO DE PROCESAR " 
						+ this.cliente.getNombre() + " EN EL TIEMPO: " 
						+ (System.currentTimeMillis() - this.initialTime) / 1000 
						+ "seg");
	}

	private void esperarXsegundos(int segundos) {
		try {
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

}
```

#### Clase Cliente

- Representa a un cliente con un nombre (nombre) y un array que representa los artículos en su carrito de compras (carroCompra).
- Los constructores permiten establecer valores iniciales.
- Métodos getter y setter para nombre y carroCompra.

```java
class Cliente {

	private String nombre;
	private int[] carroCompra;

	public Cliente() {
	}

	public Cliente(String nombre, int[] carroCompra) {
		this.nombre = nombre;
		this.carroCompra = carroCompra;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int[] getCarroCompra() {
		return carroCompra;
	}

	public void setCarroCompra(int[] carroCompra) {
		this.carroCompra = carroCompra;
	}

}
```

#### Clase Supermercado (Principal)

- Crea tres instancias de clientes con diferentes carritos de compras (cliente1, cliente2, cliente3).
- Inicializa el tiempo inicial.
- Crea tres hilos de cajeras (cajera1, cajera2, cajera3) asociados a los respectivos clientes.
- Inicia los hilos de las cajeras, simulando el procesamiento paralelo de clientes por parte de las cajeras.

```java
public class SupermercadoHilos {
	public static void main(String[] args) {

		Cliente cliente1 = new Cliente("Cliente 1", new int[] { 2, 2, 1, 5, 2, 3 });
		Cliente cliente2 = new Cliente("Cliente 2", new int[] { 1, 3, 5, 1, 1 });
		Cliente cliente3 = new Cliente("Cliente 3", new int[] { 4, 2, 3, 2, 4,5,1 });
		// Tiempo inicial de referencia
		long initialTime = System.currentTimeMillis();
		CajeraThread cajera1 = new CajeraThread("Cajera 1", cliente1, initialTime);
		CajeraThread cajera2 = new CajeraThread("Cajera 2", cliente2, initialTime);
		CajeraThread cajera3 = new CajeraThread("Cajera 3", cliente3, initialTime);

		cajera1.start();
		cajera2.start();
		cajera3.start();
	}
	
}
```

### Semaforo

1. Se define una clase Semaforo que implementa la interfaz Runnable y utiliza un semáforo (Semaphore) llamado S1 con una capacidad de 2 procesos simultáneos.
2. Cada instancia de la clase Semaforo tiene un nombre asignado.
3. En el método run de la clase Semaforo, se solicita el semáforo S1 mediante acquire(), se muestra un mensaje indicando que el proceso duerme por 5 segundos, se simula una pausa de 5 segundos con Thread.sleep(5000), se muestra un mensaje indicando que el proceso ha finalizado y luego se libera el semáforo con release().
4. La clase EjemploSemaforo crea y ejecuta 10 instancias de la clase Semaforo, cada una representando un proceso con un nombre único.

```java
import java.util.concurrent.Semaphore;
 class Semaforo implements Runnable {
    // N�mero de  procesos que se pueden ejecutar al tiempo.
    private static final Semaphore S1 = new Semaphore(2);
    //nombre del proceso
    private final String nombre;
     
    public Semaforo(String nombre) {
        this.nombre = nombre;
    }

    public void run() {
        try {
            // Solicita S1.
            S1.acquire();
             System.out.println( this.nombre + " duerme 5 segundos.");
             Thread.sleep(5000);
             System.out.println( this.nombre + " finaliza.");
             // Libera S1.
            S1.release();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
public class EjemploSemaforo {
	public static void main(String[] args) {
        // Ejecutamos 10 procesos.
        for (int i = 0; i < 10; i++) {
            new Thread(new Semaforo("Proceso #" + i)).start();
        }      
    }
}

```