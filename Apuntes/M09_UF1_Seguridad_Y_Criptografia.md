# M09 UF1 Seguridad y criptografia

## Preguntas

```
Test (4 puntos)
• 10 preguntas. Estilo libro o Test UF.
• Cada pregunta 0,4 puntos. Las incorrectas no restan.
Una pregunta (6 puntos )sobre la teoria del libro y los pdf. (no hay código)
• Criptografía y sus aplicaciones.
• Características de los sistemas de seguridad.
• Criptografía simétrica y asimétrica.
• Métodos de criptografía híbridos.
• Saber de que tipo es cada algoritmo RSA,DES,…
• Certificados digitales, Firma digital, Funciones hash resumen.
• SSL y TLS.
• Clases y métodos más importantes vistos en los ejemplos y ejercicios de clase:
Cipher,MessageDigest, Signature, KeyPar, ….. doFinal, init, sign, etc.
• Como funciona …
```

## Teoria

### Criptografía y sus aplicaciones

#### Que es la criptografía?

Acción de proteger la información mediante su modificación utilizando una clave. 

#### Aplicaciones de la criptografía

- **Identificación y autentificación:** Identificar a un individuo o validar el acceso a un servidor.
- **Certificación:** Esquema mediante el cual agentes fiables validan la identidad de agentes desconocidos (como usuarios reales).
- **Seguridad de las comunicaciones:** Permite establecer canales seguros para aplicaciones que operan sobre redes que no son seguras.
- **Comercio electrónico:** El empleo de canales seguros y mecanismos de identificación posibilita permite que las empresas y los usuarios tengan garantías de que las operaciones no van a ser espiadas ni modificadas, reduciéndose el riesgo de fraudes.

### Características de los sistemas de seguridad

- **Confidencialidad:** se trata de asegurar que la comunicación solo pueda ser vista por los usuarios autorizados, evitando que ningún otro pueda leer el mensaje. Suele ir acompañada de la autenticación de los usuarios que participan en la misma.
- **Integridad de la información:** se trata de asegurar que el mensaje no haya sido modificado de ningún modo por terceras personas durante su transmisión.
- **Autenticación:** se trata de asegurar el origen, autoría y propiedad de la información de quien envía el mensaje.
- **No repudio:** se trata de evitar que la persona que envía el mensaje o realiza una acción niegue haberlo hecho ante terceros. Al igual que la característica de confidencialidad, necesita de la autenticación del origen de la información.

### Criptografía simétrica y asimétrica

#### Claves simétricas

- Las claves de cifrado y descifrado son la misma.
- El problema es cómo transmitir la clave para que el emisor (que cifra la información) y el receptor de la información (descifra) tengan ambos la misma clave.
- Dan lugar a lo que se denomina **modelo de clave privada**.

##### Modelo clave privada

- Tanto el emisor como el receptor del mensaje utilizan la misma clave.
- Buen rendimiento.
- Una sola clave y su validez se pone en entredicho a medida que se va utilizando.

#### Claves asimétricas

- Las claves de cifrado y descifrado son diferentes y están relacionadas entre sí de algún modo.
- Dan lugar al **modelo de clave pública**.

##### Metodo clave pública

Diseñado para evitar el problema de la distribución de las claves entre emisor y receptor.
Dos claves diferentes:
- **Clave privada Kc:** que solo el usuario conoce.
– **Clave pública Kp:** publicada para todo el mundo que lo desee.
– Relacionadas entre sí por una función de sentido único. Debe ser muy difícil calcular una clave a partir de la otra.
- Si alguien desea enviar un mensaje, buscará la clave pública de aquel al que desea enviárselo, y lo cifrará con dicha clave.
- La única forma de desencriptarlo es utilizando la clave privada del receptor.


### Métodos de criptografía híbridos

El sistema simétrico es inseguro y el asimétrico es lento, por lo que este es el proceso para usar un sistema hibrido:

1. Generar una clave pública y otra privada (en el receptor).
2. Cifrar un archivo de forma síncrona.
3. El receptor nos envía su clave pública.
4. Ciframos la clave que hemos usado para encriptar el archivo con la clave pública del receptor.
5. Enviamos el archivo cifrado (síncronamente) y la clave del archivo cifrada (asíncronamente y solo puede ver el receptor).

### Tipos de algoritmos de criptografia y características

#### Algoritmos de cifrado simétrico

##### DES

Cifra un texto de una longitud fija en otro texto cifrado de la misma longitud.
Tres fases:
1. Permutación inicial: para dotar de confusión y difusión al algoritmo.
2. Sustitución mediante cajas-S previamente definidas que comprimen la
información, la permutan y la sustituyen (16 etapas).
3. Permutación final inversa a la inicial.
**Clave de 56 bits** para modificar las transformaciones realizadas.

##### AES

- Sustituto del DES
- Estándar de cifrado por el Gobierno de EEUU desde el año 2000.
- Su desarrollo se llevó a cabo de forma pública y abierta
- Sistema de cifrado por bloques
- Maneja longitudes de clave y de bloque variables, entre 128 y 256 bits.
- Rápido y fácil de implementar. 


#### Algoritmos de cifrado asimétrico

##### RSA

- Una clave pública, conocida por todos.
- Una clave otra privada, guardada en secreto por su propietario.
- La relación de claves se basa en el producto de dos números primos muy grandes elegidos al azar.
- No hay maneras rápidas conocidas de factorizar un número grande en sus factores primos. 

### Certificados digitales, Firma digital y Funciones hash

#### Función hash

- Una función hash es un algoritmo matemático que resume el contenido de un mensaje en una cantidad de información fija menor.

- Sirven para proporcionar pruebas de la integridad de la transferencia de información. 

##### Requisitos

- La descripción de la función de descifrado debe ser pública.
- El texto en claro puede tener una longitud arbitraria.
- Sin embargo, el resultado debe tener una longitud fija (resumen).
- Resistente a colisiones.
- Función de un único sentido: dado uno de estos valores cifrados, debe ser imposible producir un documento con sentido que dé lugar a ese hash.
- Aun cuando se conozca un gran número de pares (texto en claro, resumen) debe ser difícil determinar la clave.
- Rápida de calcular

#### Certificado digital

Un certificado digital o certificado electrónico es un documento firmado electrónicamente por alguien en quien se confía, que confirma la identidad vinculándolo con su correspondiente clave pública.

Permite a los usuarios y servicios identificarse, estando confirmado por una autoridad de certificación.
- Una autoridad de certificación (CA por sus siglas en ingles, Certification Authority) es
una entidad de confianza, responsable de emitir y revocar los certificados electrónicos que se emplean en la criptografía de clave pública.
  - El certificado procede de la CA.
  - Responde de que el nombre corresponde con quien dice que es.
  - Afirma la relación de obligación existente entre el nombre y la clave pública.
  - El certificado más usado es el que emite el departamento del CERES de la Fábrica Nacional de Moneda y Timbre

- Sirve para firmar digitalmente para garantizar la integridad de los datos trasmitidos y su procedencia, y autenticar la identidad del usuario de forma electrónica ante terceros.
- Sirve para cifrar datos para que solo el destinatario del documento pueda acceder a su contenido.

#### Firma digital

La firma digital es un mecanismo criptográfico que se utiliza para que el receptor del mensaje firmado digitalmente pueda identificar al emisor del mismo y confirmar que el mensaje no se ha sido modificado desde que fue firmado.
- **Autentificación:** Identificar al firmante de forma inequívoca.
- **Integridad:** Asegura que el documento firmado es exactamente igual que el original y no ha sufrido ni alteraciones ni modificaciones.
- **No repudio:** Los datos que se utilizan para el firmado son únicos, por tanto el autor de la firma no puede decir que no ha firmado el documento.

La firma electrónica permite verificar la autenticidad del emisor y la integridad de los datos utilizando un procedimiento electrónico y dejando registro de la fecha y hora de la misma. La principal diferencia con la digital es que la electrónica tiene validez legal.

##### Proceso

1. El emisor dispone de un documento que desea firmar.
2. Se aplica una función hash al documento y se obtiene el resumen.
3. Con la clave privada del emisor se cifra el resumen y obtenemos la firma
digital.

### Servicios en red seguros

El proceso es seguro gracias a un modelo de clave mixta, en el que se emplea clave asimétrica para establecer la sesión, y cifrado simétrico para las comunicaciones posteriores.

SSL y TLS agregan las siguientes características de seguridad:
-  Uso de criptografía asimétrica para el intercambio de claves de sesión.
-  Uso de criptografía simétrica para asegurar la confidencialidad de la sesión.
-  Uso de códigos de autenticación de mensajes (resúmenes) para garantizar la integridad de los mensajes.

- **Autentificación de servidor:** El servidor dispone de un certificado que emplea para que los
clientes puedan confiar en que se están conectando al elemento adecuado.
- El cliente genera la clave de sesión simétrica y la envía al servidor cifrada con la clave pública del servidor. Esto garantiza que solo el servidor pueda recibirla y utilizarla.
- **Confidencialidad:** La privacidad de las comunicaciones está garantizada gracias a la clave de sesión simétrica.
- **Integridad:** Mediante el cálculo de resúmenes se garantiza que los mensajes que se intercambian no se pueden alterar de ninguna forma.

### Clases y metodos mas importantes vistos en los ejemplos

#### Clase Cipher

La clase javax.crypto.Cipher encapsula un sistema criptográfico que puede cifrar o descifrar datos y puede ser aplicado tanto a algoritmos simétricos como asimétricos.

- Obtener una instancia usando el método getInstance.
- Inicializar el sistema criptográfico para cifrar o descifrar datos usando el método init(), especificando el modo de cifrado y la llave secreta. Los modos más habituales son Cipher.ENCRIPT_MODE para encriptar y Cipher.DECRIPT_MODE para desencriptar.
- Cifrar o descifrar utilizando el método doFinal.

#### Interfaz Key

La interface java.security.Key encapsula una llave criptográfica, que se puede usar par funciones de cifrado y descifrado.


En Java toda clase que represente una clave debe implementar esta interfaz y tener tres partes
fundamentales:

*Una llave se expresa como un byte [] utilizando un formato específico*

- **El algoritmo:** El nombre de la función de cifrado y descifrado.
  - **public String getAlgorithm()** - Retorna el nombre del algoritmo criptográfico para el será usada la llave.
- **La forma codificada:** Es una representación de la clave.
  - **public byte[] getEncoded()** - El método getEncoded() permite obtener el valor codificado de la llave.
- **El formato:** Es la forma en la que se encuentra codificada la clave.
  - **public String getFormat()** - Éste método retorna el nombre del formato utilizado para codificar la llave

#### Clase KeyGenerator
- **Generadores de claves:** Se utilizan para crear objetos de clases que implementan la interfaz Key, es decir, para crear claves nuevas.
- **Factorías de claves:** Se emplean para obtener versiones transparentes (KeySpec) a partir de de claves opacas (Key) y viceversa.
- **Clave opaca (Key):** Se usan de forma directa en los algoritmos de cifrado y no proporcionan acceso a los componentes de la clave.
- **Clave transparente(KeySpec):** Ofrecen acceso a sus componentes para que se puedan intercambiar entre diferentes entidades, usuarios o aplicaciones.

*La longitud de la llave se especifica en el proceso de inicialización. Después de inicializar el generador de llaves, es posible obtener una llave mediante el método **generateKey()**.*

#### Clase MessageDigest

##### Pasos

1. Obtención de una instancia de la clase MessageDigest.
2. Introducción de datos en la instancia.
3. Cómputo del resumen.

##### Métodos importantes

- **getInstance( String Algorithm)** - Método estático para crear objetos de clase MessageDigest. Recibe como parámetro el nombre del algoritmo de resumen que se desea emplear.
- **update(byte[] input)** - Actualiza el contenido del objeto, incluyendo la información pasada como parámetro.
- **reset()** - Reinicia el objeto, eliminando toda la información introducida.
- **digest()** - Realiza la operación de resumen sobre toda la información almacenada.

## Ejemplos en código

### Cifrado y descifrado asimétrico utilizando el algoritmo RSA

- **Generación de Claves:** Utiliza KeyPairGenerator para generar un par de claves RSA (pública y privada) con un tamaño de 512 bits.

- **Obtención de Claves:** Extrae la clave privada y pública del par generado.

- **Mostrar Claves (opcional):** Imprime las claves generadas, aunque esto no es recomendado en producción.

- **Texto Plano:** Define un mensaje de texto plano, en este caso, "Este es el mensaje secreto".

- **Cifrado con Clave Pública:** Utiliza la clave pública para cifrar el mensaje mediante el objeto Cipher en modo de cifrado. Imprime el mensaje cifrado.

- **Descifrado con Clave Privada:** Configura el cifrador en modo de descifrado utilizando la clave privada.

- **Mostrar Texto Descifrado:** Realiza el descifrado del mensaje cifrado utilizando la clave privada e imprime el resultado.

```java
import javax.crypto.*;
import java.security.*;

public class CifradoAsimetrico {
   
    public static void main(String[] args) {

        try {
            // Crear e imprimir clave pública y privada
            System.out.println("Crear clave pública y privada");
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(512);  // Tamaño de la clave
            KeyPair clavesRSA = keyGen.generateKeyPair();

            // Obtener claves
            PrivateKey clavePrivada = clavesRSA.getPrivate();
            PublicKey clavePublica = clavesRSA.getPublic();

            // Mostrar claves (no recomendado en producción)
            System.out.println("clavePublica: " + clavePublica);
            System.out.println("clavePrivada: " + clavePrivada);

            // Texto plano
            byte[] mensaje = "Este es el mensaje secreto".getBytes();

            // Cifrar con clave pública utilizando RSA
            Cipher cifrador = Cipher.getInstance("RSA");
            cifrador.init(Cipher.ENCRYPT_MODE, clavePublica);
            System.out.println("Cifrar con clave pública el Texto:" + new String(mensaje));

            // Realizar cifrado del texto plano
            byte[] mensajeCifrado = cifrador.doFinal(mensaje);
            System.out.println("Texto CIFRADO:" + new String(mensajeCifrado));

            // Desencriptar utilizando la clave privada
            cifrador.init(Cipher.DECRYPT_MODE, clavePrivada);
            System.out.println("Descifrar con clave privada");

            // Obtener y mostrar texto descifrado
            byte[] mensajeDescifrado = cifrador.doFinal(mensajeCifrado);
            System.out.println("Texto DESCIFRADO:" + new String(mensajeDescifrado));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### Cifrado Simétrico con AES

- **Generación de Clave Secreta:** Utiliza KeyGenerator para generar una clave secreta con el algoritmo AES y un tamaño de clave de 128 bits.

- **Creación de Objeto Cipher:** Crea un objeto Cipher con el algoritmo AES.

- **Cifrado de la Información:** Inicializa el cifrador en modo de cifrado (Cipher.ENCRYPT_MODE) utilizando la clave secreta generada. Realiza el cifrado del texto plano con el método doFinal() y muestra el resultado encriptado.

- **Configuración para Desencriptación:** Configura el cifrador en modo de desencriptación (Cipher.DECRYPT_MODE) utilizando la misma clave secreta.

- **Desencriptación del Texto Cifrado:** Utiliza el método doFinal() para desencriptar el texto cifrado y muestra el resultado desencriptado.

```java
import javax.crypto.*;

public class CifradoSimetrico {
   
   public static void main(String[] args) {
      try {
    	//Creamos la clave secreta usando el algoritmo AES y deﬁnimos un tamaño de clave de 128 bits
          KeyGenerator kg = KeyGenerator.getInstance("AES");
          kg.init (128);
          SecretKey clave = kg.generateKey();
         
          //Creamos un objeto Cipher con el algoritmo AES,
          Cipher c = Cipher.getInstance("AES");
          c.init(Cipher.ENCRYPT_MODE, clave);
         
          //Realizamos el cifrado de la información con el método doFinal()
          byte textoPlano[] = "Esto es un Texto Plano".getBytes();
          byte textoCifrado[] = c.doFinal(textoPlano);
          System.out.println("Encriptado: "+ new String(textoCifrado));
         
          //Conﬁguramos el objeto Cipher en modo desencriptación con la clave anterior 
          //para desencriptar el texto, usamos el método doFinal()
          c.init(Cipher.DECRYPT_MODE, clave);
          byte desencriptado[] = c.doFinal(textoCifrado);
          System.out.println("Desencriptado: "+ new String(desencriptado));
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
```

### Cifrado Hash

#### Pasos para calcular

1. Se crea un FileOutputStream para el archivo "DATOS.DAT" y un ObjectOutputStream para escribir en él.
2. Se utiliza la instancia de MessageDigest con el algoritmo SHA.
3. Se define un string 'datos' con contenido de texto.
4. Se convierte 'datos' a bytes y se actualiza el MessageDigest con estos bytes.
5. Se calcula el resumen (hash) y se escriben los datos y el resumen en el archivo.
6. Se cierran los flujos y se imprime el contenido de 'datos' junto con un mensaje de éxito.

#### Pasos para verificar:

1. Se crea un FileInputStream para leer el archivo "DATOS.DAT" y un ObjectInputStream para procesar los objetos.
2. Se lee el primer objeto como un String ('datos').
3. Se lee el segundo objeto como un array de bytes ('resumenOriginal').
4. Se vuelve a calcular el hash del 'datos' leído.
5. Se compara el hash calculado con el hash original y se imprime si los datos son válidos o no.
6. Se cierran los flujos.

```java
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CalcularVerificarHash {
    public static void main(String args[]) {
        // Calcular Hash y Guardar en Archivo
        try {
            FileOutputStream fileout = new FileOutputStream("DATOS.DAT");
            ObjectOutputStream dataOS = new ObjectOutputStream(fileout);
            MessageDigest md = MessageDigest.getInstance("SHA");
            String datos = "Episodio IV: Una nueva esperanza.\n"
                    + "Hace mucho tiempo en una galaxia muy, muy lejana...\n"
                    + "Nos encontramos en un periodo de guerra civil. Las naves espaciales rebeldes,\n"
                    + "atacando desde una base oculta, han logrado su primera victoria contra el\n"
                    + "malvado Imperio Galáctico. ";
            byte dataBytes[] = datos.getBytes();
            md.update(dataBytes); // TEXTO A RESUMIR
            byte resumen[] = md.digest(); // SE CALCULA EL RESUMEN
            dataOS.writeObject(datos); // se escriben los datos
            dataOS.writeObject(resumen); // Se escribe el resumen
            dataOS.close();
            fileout.close();
            System.out.println("Datos:" + datos);
            System.out.println("\nResumen creado con éxito");
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // Verificar Hash desde Archivo
        try {
            FileInputStream fileout = new FileInputStream("DATOS.DAT");
            ObjectInputStream dataOS = new ObjectInputStream(fileout);
            Object o = dataOS.readObject();

            // Primera lectura, se obtiene el String
            String datos = (String) o;
            System.out.println("Datos: " + datos);

            // Segunda lectura, se obtiene el resumen
            o = dataOS.readObject();
            byte resumenOriginal[] = (byte[]) o;

            MessageDigest md = MessageDigest.getInstance("SHA");
            // Se calcula el resumen del String leído del fichero
            md.update(datos.getBytes()); // TEXTO A RESUMIR
            byte resumenActual[] = md.digest(); // SE CALCULA EL RESUMEN

            // Se comprueban los dos resúmenes
            if (MessageDigest.isEqual(resumenActual, resumenOriginal))
                System.out.println("\nDATOS VÁLIDOS");
            else
                System.out.println("\nDATOS NO VÁLIDOS");
            dataOS.close();
            fileout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

