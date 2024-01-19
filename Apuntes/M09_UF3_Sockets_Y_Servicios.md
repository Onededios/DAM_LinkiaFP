# M09 UF3 Sockets y servicios

## Preguntas

```
Test (4 puntos)
• 10 preguntas. Estilo libro o Test UF.
• Cada pregunta 0,6 puntos. Las incorrectas no restan.
Una pregunta (6 puntos )sobre la teoria del libro y los pdf. (no hay código)
• Sistemas distribuidos
• Comunicaciones entre aplicaciones.
• Pila de protocolos IP
• Protocolos orientados y no orientados a la conexión.
• Sockets stream y sockets datagram. Funcionamiento Cliente-Servidor.
• Servicios. Protocolos con y sin estado.
• RMI. Elementos que intervienen.
• Clases y métodos más importantes.
• Como funciona …
```

## Teoría

### Sistemas distribuidos

Es un entorno informático que abarca múltiples dispositivos, coordinando sus esfuerzos para completar un trabajo de forma mucho más eficiente que si fuera con un solo dispositivo.

Los elementos que forman el sistema distribuido **no están sincronizados** y **están conectados a una red de comunicaciones**

Está formado por más de un elemento computacional distinto e independiente (un procesador dentro de una máquina, un ordenador dentro de una red, etc), que no comparte memoria con el resto.

### Comunicaciones entre aplicaciones

- **Mensaje:** Es la información que se intercambia entre las aplicaciones que se comunican.
- **Emisor:** Es la aplicación que envía el mensaje.
- **Receptor:** Es la aplicación que recibe el mensaje.
- **Paquete:** Es la unidad básica de información que intercambian dos dispositivos de comunicación.
- **Canal de comunicación:** Es el medio por el que se transmiten los paquetes, que conecta el emisor con el receptor.
- **Protocolo de comunicaciones:** Es el conjunto de reglas que fijan cómo se deben intercambiar paquetes entre los diferentes elementos que se comunican entre sí.

### Pila de protocolos IP

![alt](https://upload.wikimedia.org/wikipedia/commons/7/73/Suite_de_Protocolos_TCPIP.png)

#### Niveles

##### 1. Nivel de aplicación

Lo componen las aplicaciones que forman el sistema distribuido, que hacen uso de los niveles inferiores para poder transferir mensajes entre ellas.

**Ejemplo:** HTTP para la transferencia de páginas web.

##### 2. Nivel de transporte

Lo componen los elementos software cuya función es crear el canal de comunicación, descomponer el mensaje en paquetes y gestionar su transmisión entre el emisor y el receptor. Los dos protocolos de transporte fundamentales: TCP y UDP.

**Ejemplo:** TCP para garantizar la entrega de datos, como en la descarga de archivos.

##### 3. Nivel de Internet

Lo componen los elementos software que se encargan de dirigir los paquetes por la red, asegurándose de que lleguen a su destino.
También llamado nivel IP.

**Ejemplo:** IP (Internet Protocol) para enrutar datos entre redes, como enviar un paquete desde tu PC a un servidor.

##### 4. Nivel de red

Lo componen los elementos hardware de comunicaciones y sus controladores básicos. Se encarga de trasmitir los paquetes de información.

**Ejemplo:** Ethernet para la comunicación directa entre dispositivos en la misma red local.

#### Funcionamiento

1. La capa de aplicación aplicará un formato adecuado a los datos junto con la dirección de envío lo pasara a la capa de transporte.
2. La capa de transporte dividirá en la información en paquetes.
3. En la capa de internet se añaden las direcciones origen y destino del mensaje y calcula la ruta que deben seguir los paquetes para llegar al receptor.
4. La capa de red añade nuevos datos para conseguir que esta información llegue al nodo próximo situado en el camino activando todos los elementos físicos necesarios.
5. Las señales saltaran de nodo en nodo hasta llegar al dispositivo receptor, comprobando su dirección IP.

### Protocolos orientados a la conexión (TCP)

Es aquel en que el canal de comunicaciones entre dos aplicaciones permanece abierto durante un cierto tiempo, permitiendo enviar múltiples mensajes de manera fiable por el mismo.

#### Fases

1. Establecimiento de la conexión.
2. Envío de mensajes.
3. Cierre de la conexión.

#### Características

- Los datos no se pierden.
- Los mensajes llegarán en orden.

### Protocolos no orientados a la conexión (UDP)

El protocolo UDP es más rápido que el protocolo TCP, ya que no es necesario establecer conexiones, etc.

#### Características

- No garantiza que los mensajes lleguen siempre.
- No garantiza que los mensajes lleguen en el mismo orden que fueron enviados.
- Permite enviar mensajes de 64 KB como máximo.
- En UDP, los mensajes se denominan “datagramas” (datagrams en ingles).

### Sockets

Los sockets son el mecanismo de comunicación básico fundamental que se usa para realizar transferencias de información entre aplicaciones.

Proporcionan una abstracción de la pila de protocolos.

Un socket (en inglés, literalmente, un “enchufe”) representa el extremo de un canal de comunicación establecido entre un emisor y un receptor.

#### Sockets stream

Son orientados a conexión.
• Cuando operan sobre IP, emplean TCP.
• Un socket stream se utiliza para comunicarse siempre con el mismo receptor,
manteniendo el canal de comunicación abierto entre ambas partes hasta que se
termina la conexión.
• Una parte ejerce la función de **proceso cliente** y otra de **proceso servidor**.

##### Proceso cliente

1. Creación del socket.
2. Conexión del socket (*connect*).
3. Envío y recepción de mensajes.
4. Cierre de la conexión (*close*).

##### Proceso servidor

1. Creación del socket.
2. Asignación de dirección y puerto (*bind*).
3. Escucha (*listen*).
4. Aceptación de conexiones (*accept*). Esta operación implica la creación de un nuevo socket, que se usa para comunicarse con el cliente que se ha conectado.
5. Envío y recepción de mensajes.
6. Cierre de la conexión (*close*).

#### Sockets datagram

- Son no orientados a conexión.
- Cuando operan sobre IP, emplean UDP.
- Cuando se usan sockets datagram no existe diferencia entre proceso servidor y proceso cliente.

##### Pasos

1. Creación del socket.
2. Asignación de dirección y puerto (*bind*). Solo necesaria para poder recibir mensajes.
3. Envío y/o recepción de mensajes.
4. Cierre del socket.


### Servicios. Protocolos con y sin estado

### RMI (Remote Method Invocation)

Es la implementación de Java de RPC (Remote Procedure Call)
-  Permite invocar a métodos de objetos remotos a través del protocolo TCP/IP
-  En un programa orientado a objetos la invocación de un método se puede ver como un proceso de comunicación:
   - El objeto A envía un mensaje al B a modo de petición (invocación del método).
   - El objeto B procesa la petición (ejecución del método) contesta con un mensaje de respuesta (valor de retorno del método).
- La invocación de métodos remotos funciona de la misma forma, pero cada
objeto (A y B) se encuentran e máquinas distintas.

#### Cómo funciona

- **Objeto servidor:** El objeto cuyo método es invocado. También se le llama objeto remoto.
- **Objeto cliente:** El objeto que realiza la petición.
- **Método invocado:** El mensaje de petición, incluyendo los parámetros del método.
- **Valor de retorno:** El mensaje de respuesta.

Para que puedan llevarse a cabo invocaciones de métodos remotos, debe existir una infraestructura que capture las llamadas y genere el tráfico en la red. Esta infraestructura tiene dos componentes:

- **Objetos stub:** Sustituyen a los objetos remotos en el programa cliente. Convierten las invocaciones de métodos en mensajes y los transfieren por la red.
- **Registro de objetos remotos:** Es un servicio de nivel de aplicación que sirve para poder localizar objetos remotos.

#### Proceso

1. (_servidor_) Arranque del registro de objetos remotos
2. (_servidor_) Creación del objeto servidor.
3. (_servidor_) Inscripción del objeto servidor en el registro.
4. (_cliente_) Localización del objeto remoto y creación del objeto stub.
5. (_cliente_) Invocación del método del objeto stub.
6. Intercambio de mensajes entre servidor y cliente.
7. (_cliente_) Obtención del valor de retorno del método.

#### Arquitectura

![RMI](https://cdn.educba.com/academy/wp-content/uploads/2019/07/RMI-ArchitectureDone.jpg)

##### Interfaz remota

- Conjunto de métodos que serán implementados por el objeto remoto y que pueden ser accedidos por el cliente.
  - Especificación del nombre de los métodos y sus argumentos.
  - No se proporciona una implementación.
- La interfaz debe extender java.rmi.Remote y ser pública.
- Todos los métodos declarados deben poder lanzar la excepción java.rmi.RemoteException.

##### Implementación
- Es una clase Java Normal.
  - Extiende la clase java.rmi.server.UnicastRemoteObject.
  - Implementa el Interfaz definido anteriormente.
- Debe proporcionar una implementación para todos los métodos.
- En el constructor de esta clase hay que llamar al constructor de la clase UnicastRemoteObject.
  - Hay que usar super()

##### Stub y Skeleton

Cliente <-> Stub <-> Red <-> Skeleton <-> Servidor

- Son clases intermedias que abstraen de la comunicación por red entre cliente y servidor.
- Implementan la misma interfaz que “Interfaz”

##### Servidor

- Crea el objeto que será accedido remotamente.
  - Instancia el objeto que implementa la interfaz.
- Registra el objeto remoto (asignándole un nombre) en un servidor de nombre RMI.
  - Sin reemplazo Naming.bind()
  - Con reemplazo Naming.rebind()
- El servidor puede implementarse en una clase aparte o dentro de la clase Implementación.
  - La instancia y el registro habría que hacerlos en el main.

##### Cliente

- Obtiene una referencia al objeto remoto con el que desea conectar.
  - Realiza una petición a un servidor de Nombres RMI.
    - Método Naming.lookup() pasando como argumento el nombre con el que se registro el objeto remoto (en el mismo formato).
- Una vez recibido el objeto remoto, se pueden invocar sus métodos como si fuera un objeto local.
  - Los objetos pasados como argumento recibidos como valor de retorno son serializados ( conocido como marshalling)

##### Servidor de Nombres RMI

- Repositorio centralizado de objetos remotos.
  - Los servidores los registran.
  - Los clientes los recuperan.
- Puede iniciarse de dos maneras
  - Desde consola: rmiregistry [Puerto]
  - Desde el código del servidor LocateRegistry.createRegistry()
- Al crearlo hay que indicarle que puerto debe escuchar (por defecto, el 1099)
- El cliente puede acceder a él de dos formas.
  - A través de Naming.
  - Mediante una instancia del registro: LocateRegistry.getRegistry().

### Clases y métodos más importantes

#### Clase InetAddress

La clase InetAddress nos sirve para representar una dirección IP, las instancias de
esta clase no se crean llamando a un constructor, de hecho no tiene constructores,
si no que llamando a los siguientes métodos, los cuales devuelven un objeto de tipo
InetAddress

- **getLocalHost()** - Devuelve la dirección IP de la máquina donde se está ejecutando el programa.
- **getByName(host)** - Devuelve la dirección IP de la máquina que se especifica como parámetro.
- **getAllByName(host)** - Devuelve un array de objetos de tipo InetAddress.

#### Clase URL

Representa un puntero a un recurso de la Web. Un recurso, puede ser un fichero, un
directorio, una consulta a una base de datos o una búsqueda en internet.

- **getPort():** Devuelve el número de puerto de la URL, -1 si no se indica.
- **getHost():** Devuelve el nombre de la máquina.
- **getQuery():** Devuelve la cadena que se envía a una página para ser procesada (lo que sigue al signo ?).
- **getPath():** Devuelve una cadena con la ruta hacia el fichero desde el servidor y el nombre compelto del fichero.
- **getUserInfo():** Devuelve la parte con los datos del usuario de la dirección URL.

#### Clase URLConnection

Es una clase abstracta que contiene métodos que permiten la comunicación entre
aplicaciones y una URL. Para conseguir un objeto de este tipo se invoca al método
openConnection(), con ello obtenemos una conexión al objeto URL referenciado.

- **InputStream getInputStream():** - Devuelve un objeto InputStream para leer datos de esta conexión.
- **OutputStream getOutputStream():** - Devuelve un objeto OutputStream para escribir datos en esta conexión.
- **connect():** - Abre una conexión al recurso remoto.
- **getContentLength():** - Devuelve el valor del campo de cabecera content-length.
- **getContentType():** - Devuelve el valor del campo de cabecera content-type.
- **getDate():** - Devuelve el valor del campo de cabecera date.
- **getURL():** - Devuelve la dirección URL.

#### Clase Socket

#### Clase ServerSocket

#### Clase DatagramSocket


#### Clase LocateRegistry

- Antes vimos que el registro de nombres podía iniciarse desde la línea de
comandos con la orden: rmiregistry.
- También se puede crear desde un programa Java directamente.
- Clase LocateRegistry
  - Método createRegistry (int port)
    - Crea un servidor de nombres en el Puerto especificado.
- Método getRegistry (String host, int port)
  - Devuelve una referencia al registro localizado en ese host y puerto.
  - Si algún valor no se especifica, se usan valores por defecto (localhost y 1099)
  - Devuelve un objeto de tipo Registry (misma interfaz que Naming)

## Ejemplos en código

### Ejemplo sockets cliente-servidor

#### Cliente

1. Definición del Socket:
   - Se crea un objeto Socket llamado clientSocket.
2. Configuración de la Dirección del Socket:
   - Se instancia un objeto InetSocketAddress con la dirección "localhost" y el puerto 5678.
3. Establecimiento de la Conexión:
   - Se imprime un mensaje y se conecta el socket al servidor mediante clientSocket.connect(addr).
4. Envío de Datos al Servidor:
   - Se crea un PrintStream para enviar datos a través del socket.
   - Se utiliza BufferedReader para leer las líneas desde la consola.
   - Se imprime un mensaje de conexión exitosa.
   - Se lee la entrada del usuario y se envía al servidor a través del socket en un bucle hasta que la entrada sea "FIN".
   - Se cierra la conexión del socket.
5. Manejo de Excepciones:
   - En caso de excepciones de E/S, se imprime un mensaje de error y se muestra la traza de la excepción.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Cliente {
	private static Socket clientSocket;
	public static void main(String[] args) {
		String line;
		// definimos el socket
		clientSocket = new Socket();	
		// Creamos un Socket Adress para una  m�quina y numero de puerto
		InetSocketAddress addr = new InetSocketAddress("localhost", 5678);
		try {
			System.out.println("EJEMPLO SOCKETS - CLIENTE");  
			// Conectamos !!!
			clientSocket.connect(addr);
			// Envio la informacion a traves del canal de comunicacion establecido con el socket
			PrintStream ps = new PrintStream(clientSocket.getOutputStream(), true);
			//leemos lineas a trav�s de la consola
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
			System.out.println("Conectado !");
			line = in.readLine();
			while (!line.equals("FIN") && line != null) {
				// ponemos las lineas en el printStream que las envia al server a trav�s del socket
				ps.println(line);
				ps.flush();
	            line = in.readLine();
			}
			// cerramos conexion
			clientSocket.close();
		} catch (IOException e) {
            System.out.println("OOOPS !!!");
            e.printStackTrace();
		}
	}
}
```

#### Servidor

1. Definición del Socket del Servidor:
   - Se crea un objeto ServerSocket llamado serverSocket.
2. Configuración de la Dirección del Socket:
   - Se instancia un objeto InetSocketAddress con la dirección "localhost" y el puerto 5678.
3. Enlace y Espera de Conexión:
   - El servidor enlaza el serverSocket a la dirección y puerto especificados.
   - Se imprime un mensaje de espera de conexión.
   - Se acepta una conexión entrante mediante serverSocket.accept(), creando un nuevo Socket llamado socket.
4. Lectura y Muestra de Datos:
   - Se crea un BufferedReader para leer datos del socket.
   - Se lee línea por línea del socket y se muestra en la consola hasta que se recibe "FIN" o se produce una excepción NullPointerException.
5. Cierre de Conexión:
   - Se cierra el socket después de la transmisión de datos.
   - Se imprime un mensaje indicando el fin de la transmisión.
6. Manejo de Excepciones:
   - En caso de excepciones de E/S, se imprime un mensaje de error y se muestra la traza de la excepción.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	private static ServerSocket serverSocket;
	public static void main(String[] args) {
		String line;
		try {
			// Atenci�n! Utilizamos la clase ServerSocket en el servidor
			serverSocket = new ServerSocket();	
			System.out.println("EJEMPLO SOCKETS - SERVIDOR");  
			System.out.println("Esperando Conexion...");  
			// Creamos un Socket Adress para una  m�quina y numero de puerto
			InetSocketAddress addr = new InetSocketAddress("localhost", 5678);
			// Enlaza el serverSocket a una direccion especifica (IP  y numero de puerto).
	        serverSocket.bind(addr);
            // Estamos a la escucha de una connexi�n a este socket y la aceptamos
	        Socket socket = serverSocket.accept();
			System.out.println("Establecida la conexion !...");
			System.out.println();
			// Definimos la lectura de datos que vendran por el socket, muy similar a la actividad1
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));			
			try { 
				line = br.readLine();
				while (!line.equals("FIN") && line != null) {
	                System.out.println(line);
					line = br.readLine();
				}				
			} catch (NullPointerException ex) {
                System.out.println("Finalizada transmision...");				
			}
			// cerramos conexion
			socket.close();
            System.out.println("Fin.");							
			
		} catch (IOException e) {
            System.out.println("OOOPS !!!");
            e.printStackTrace();
		}
	}

}
```

### Ejemplo RMI cliente-servidor

#### Interfaz

```java
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculator extends Remote {
    String getMenu() throws RemoteException;
    String getIntToBin(int num) throws RemoteException;
    String getIntIsPrimeOrOdd(int num) throws RemoteException;
    String getIntToFactorial(int num) throws RemoteException;
    String getIntSumStartingBy1(int num) throws RemoteException;
    String getIntAllPossibleDivisors(int num) throws RemoteException;
}
```

#### Implementación

```java
import java.rmi.RemoteException;

public class CalculatorImpl implements ICalculator {
    @Override
    public String getMenu() throws RemoteException {
        return  "\nChoose the operation to perform:"+
                "\n (B)inary    - Convert the given integer number to binary"+
                "\n (P)rime     - Assert if the given integer is prime or odd"+
                "\n (F)actorial - Calculate the factorial of the given integer"+
                "\n (S)um       - Calculates the result of the sum among all the numbers between 1 and the given integer"+
                "\n (D)ivisors  - Get all possible divisors from 1 to the given integer"+
                "\n (E)nd       - Closes the connection with the server"
                ;
    }

    @Override
    public String getIntToBin(int num) throws RemoteException {
        return " -> The integer "+num+" in binary equals to "+Integer.toBinaryString(num);
    }

    @Override
    public String getIntIsPrimeOrOdd(int num) throws RemoteException {
        return " -> The integer "+num+" is an "+(num % 2 == 0 ? "odd" : "prime" )+" number";
    }

    @Override
    public String getIntToFactorial(int num) throws RemoteException {
        int factorial = 1;
        for (int i = 1; i <= num; i++) factorial *= i;
        return " -> The factorial of the integer "+num+" is "+factorial;
    }

    @Override
    public String getIntSumStartingBy1(int num) throws RemoteException {
        int accumulated = 0;
        for (int i = 1; i <= num; i++) accumulated += i;
        return " -> The result of the sum of all the numbers between 1 and "+num+" is "+accumulated;
    }

    @Override
    public String getIntAllPossibleDivisors(int num) throws RemoteException {
        String result = " -> All possible divisors of the integer are:";
        for (int i = 1; i <= num; i++) if(num % i == 0) result += " "+i;
        return result;
    }
}
```

#### Cliente

1. Obtiene el registro RMI en el localhost y puerto 8069.
2. Busca en el registro el objeto remoto "Calculator" implementando la interfaz "ICalculator".
3. Configura la lectura de entrada del usuario.
4. En un bucle do-while:
   - Muestra el menú del calculador.
   - Solicita la selección del usuario.
   - Realiza acciones según la selección del usuario (convertir a binario, verificar si es primo u impar, calcular factorial, sumar desde 1, obtener divisores o salir).
   - Continúa hasta que el usuario elige salir (E).
5. Maneja excepciones de RMI y entrada/salida.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private static BufferedReader userReader;
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8069);
            ICalculator calculator = (ICalculator) registry.lookup("Calculator");
            userReader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
            String action;
            do {
                System.out.print(calculator.getMenu());
                System.out.print("\nYour selection: ");
                switch ((action = userReader.readLine().toUpperCase())) {
                    case "B": System.out.println(calculator.getIntToBin(askForInteger()));
                        break;
                    case "P": System.out.println(calculator.getIntIsPrimeOrOdd(askForInteger()));
                        break;
                    case "F": System.out.println(calculator.getIntToFactorial(askForInteger()));
                        break;
                    case "S": System.out.println(calculator.getIntSumStartingBy1(askForInteger()));
                        break;
                    case "D": System.out.println(calculator.getIntAllPossibleDivisors(askForInteger()));
                        break;
                    case "E": System.out.println("Bye!");
                        break;
                    default: System.out.println("Error. That's not an available action.");
                        break;
                }

            } while(!action.equals("E"));
        } catch (NotBoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int askForInteger() throws IOException {
        System.out.println("Perfect, give me an integer.");
        Integer userInt;
        do {
            System.out.print("Your answer: ");
            try {
                userInt = Integer.parseInt(userReader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Error. You've not introduced an integer!");
                userInt = null;
            }
        }
        while(userInt == null);
        return userInt;
    }
}
```

#### Servidor

1. Crea una instancia de "CalculatorImpl" llamada "calculator".
2. Exporta el objeto "calculator" como un objeto remoto con el puerto 8069.
3. Crea un registro RMI en el puerto 8069.
4. Vincula el objeto remoto ("stub") al registro con el nombre "Calculator".
5. Imprime en la consola "Server online!" si todo es exitoso.
6. Captura y maneja excepciones, imprimiendo mensajes de error.

```java
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static java.rmi.registry.LocateRegistry.createRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            ICalculator calculator = new CalculatorImpl();
            ICalculator stub = (ICalculator) UnicastRemoteObject.exportObject(calculator, 8069);
            Registry registry = createRegistry(8069);
            registry.rebind("Calculator", stub);
            System.err.println("Server online!");
        } catch (Exception e) {
            System.err.println("Server error: " + e);
            e.printStackTrace();
        }
    }
}
```