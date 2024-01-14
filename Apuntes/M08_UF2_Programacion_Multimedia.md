# M08 UF2 Programación Multimedia

## Arquitectura

Una aplicación de contenido multimedia que reproduce audio o video suele tener dos partes:

- Un reproductor que toma el contenido multimedia y lo renderiza como audio o video.
- Una UI con controles de transporte para ejecutar el reproductor.

### Aplicacion audio

Puede ejecutarse como una tarea en segundo plano.
El usuario puede usar otra aplicación y seguir escuchando su contenido.

### Aplicacion video

Necesita una ventana para ver el contenido, la pantalla en la que aparece el video es parte de la actividad.

## Media Format

### Formatos de imagen

| Formato | Calidad   | Velocidad de Descarga |
| ------- | --------- | --------------------- |
| JPG     | Buena     | Buena                 |
| GIF     | Limitada  | Alta                  |
| PNG     | Excelente | Baja                  |
| BMP     | Excelente | Baja                  |

### Formatos de audio

Sin Compresión:

- WAV: Ampliamente utilizado, sin pérdida, pero archivos grandes.
- AIFF: Similar al WAV, común en entornos de Apple.
- AU: Menos común, pero sin pérdida y utilizado en sistemas Unix.

Compresión sin Pérdida:

- FLAC: Mantiene la calidad, pero con tamaños de archivo más pequeños que los sin compresión.

Compresión con Pérdida:

- MP3: Muy popular, pero sacrifica calidad para reducir el tamaño. Bueno para portabilidad.

### Formatos de video

En la compresión de archivos de video, distinguimos entre el formato contenedor (AVI, MPG, MOV, H264, WMV) que abarca video y audio, y el códec (H264, XVID, DIVX) responsable de la compresión y descompresión. La calidad del archivo comprimido depende del códec utilizado.

| Formato | Extensión | Compresión | Calidad   | Uso común                 |
| ------- | --------- | ---------- | --------- | ------------------------- |
| MP4     | .mp4      | Sí         | Buena     | Streaming, dispositivos   |
| AVI     | .avi      | No         | Alta      | Almacenamiento local      |
| MKV     | .mkv      | Sí         | Excelente | Contenido de alta calidad |
| MOV     | .mov      | Sí         | Muy buena | Edición de video          |
| WMV     | .wmv      | Sí         | Buena     | Windows Media Player      |

| Codec | Descripción                                              | Uso común                                |
| ----- | -------------------------------------------------------- | ---------------------------------------- |
| H.264 | Ofrece buena calidad con tamaños de archivo razonables.  | Streaming, grabación de video            |
| Xvid  | Codec de compresión de video de código abierto.          | Almacenamiento local, DVDs               |
| DivX  | Basado en el estándar MPEG-4, proporciona buena calidad. | Reproducción en dispositivos compatibles |

### Metadatos

Metadatos son detalles sobre los archivos.

| Tipo de Archivo | Ejemplos de Metadatos                                                                                                                                               |
| --------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Audio           | Título de la canción, artista, álbum, año de lanzamiento, género, duración, formato de archivo, tasa de bits, frecuencia de muestreo, intérpretes, compositor, etc. |
| Imagen          | Título de la imagen, fecha de creación, resolución, formato de archivo, información de la cámara (modelo, configuración), color, profundidad de bits, etc.          |
| Video           | Título del video, director, año de producción, duración, formato de archivo, resolución, tasa de fotogramas, códec de video, códec de audio, subtítulos, etc.       |

## URI

Una URI (Identificador de Recursos Uniforme) es un identificador que puede ser una URL o una URN.

### URN

Una URN (Uniform Resource Name) identifica un recurso por su nombre en un espacio de nombres.

Ejemplo: **urn:ietf:rfc:2648**
Ejemplo: **urn:isbn:9780007525546**

### URL

Una URL (Uniform Resource Locator) incluye la ubicación del recurso.

Ejemplo: **https://www.ejemplo.com/imagen.jpg**
Ejemplo: **ftp://ftp.rediris.es/**
Ejemplo: **telnet://telnet.wmflabs.org**

## Peticion permisos

1. Añadir los permisos necesarios en el manifest

```xml
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

2. Solicitar los permisos necesarios en el codigo (el codigo de permiso puede ser aleatorio)

```java
public class MainActivity extends AppCompactActivity {
    private static final int PERMISSION_CODE = 1;
    private static String[] PERMISSIONS = {
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    public void onRequestPermissionsResult(int PERMISSION_CODE, String[] PERMISSIONS, int[] grantResults) {
        super.onRequestPermissionsResult(PERMISSION_CODE, PERMISSIONS, grantResults);
        if (PERMISSION_CODE == REQUEST_CODE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // If permission is granted, do wathever
            } else {
                // Permission denied, show a message to the user
                Toast.makeText(this, "You must accept necessary permissions.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityMain);
        if (
            ContextCompat.checkSelfPermission(this, PERMISSIONS[0]) !=  PackageManager.PERMISSIONS_GRANTED ||
            ContextCompat.checkSelfPermission(this, PERMISSIONS[1]) != PackageManager.PERMISSIONS_GRANTED ||
            ) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_CODE);
        }
    }
}
```

## MediaPlayer

La clase MediaPlayer en Java se usa para manejar la reproducción de archivos multimedia, como audio y video desde diferentes fuentes:

- Desde un archivo en memoria interna _(de la carpeta /res/raw o /assets)_

```java
MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("cancion.mp3").toString()));
mediaPlayer.play();
```

- Desde un archivo en memoria externa _(una tarjeta de memoria SD)_

```java
MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("ruta/cancion.mp3").toURI().toString()));
mediaPlayer.play();
```

- Desde la web _(una dirección URL)_

```java
MediaPlayer mediaPlayer = new MediaPlayer(new Media("http://enlace.com/cancion.mp3"));
mediaPlayer.play();
```

### Estados

- **Idle (Inactivo):**

El MediaPlayer se encuentra en este estado justo después de ser creado.
_En este estado, el MediaPlayer no está configurado con ninguna fuente de datos._

- **Initialized (Inicializado):**

Se alcanza este estado después de llamar a setDataSource.
_El MediaPlayer está configurado con la fuente de datos, pero aún no está preparado para la reproducción._

- **Preparing (Preparando):**

El MediaPlayer se encuentra en este estado después de llamar a prepare() o prepareAsync().
_Durante este estado, el MediaPlayer está cargando y preparando los recursos para la reproducción._

- **Prepared (Preparado):**

Este estado se alcanza cuando la preparación (prepare() o prepareAsync()) ha terminado con éxito.
_El MediaPlayer está listo para comenzar o reanudar la reproducción._

- **Started (Iniciado):**

Se alcanza este estado después de llamar a start().
_El MediaPlayer está reproduciendo el contenido multimedia._

- **Paused (Pausado):**

Este estado se alcanza después de llamar a pause().
_La reproducción está en pausa, pero el MediaPlayer retiene su estado._

- **Stopped (Detenido):**

Este estado se alcanza después de llamar a stop().
_La reproducción se detiene y el MediaPlayer necesita ser preparado nuevamente para iniciar la reproducción._

- **Playback Completed (Reproducción completada):**

Este estado se alcanza cuando la reproducción ha llegado al final del contenido multimedia.
_Puede ser útil para tomar acciones específicas después de que la reproducción haya terminado._

- **End (Fin):**

Después de llamar a release(), el MediaPlayer entra en este estado.
_En este punto, el MediaPlayer está liberado y no se puede utilizar nuevamente._

### Inicialización

Podemos iniciar un archivo multimedia de las siguientes formas:

- Mediante **setDataSource()** y **prepare()**

```java
MediaPlayer mediaPlayer = new MediaPlayer();
mediaPlayer.setDataSource("ruta/cancion.mp3");
mediaPlayer.prepare();
mediaPlayer.start();
```

- Mediante **create()**

```java
String webUrl = "http://enlace.com/cancion.mp3";
MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), Uri.parse(webUrl));
mediaPlayer.start();
```

### Flow

1. Se configura la fuente multimedia a reproducir con **setDataSource()**.
2. Se ejecuta el método **prepare()** que deja el reproductor en estado Prepared, preparado para iniciar la reproducción.
3. Se ejecuta el método **start()** y comienza a reproducirse.
4. Pausamos la reproducción con **pause()**, para pararla en el momento actual.
5. Seguimos con la reproducción con **start()**.
6. Detenemos la reproducción con **stop()**, lo que detiene la reproducción y retrocede al inicio de la misma.
7. Preparamos el objeto otra vez con **prepare()** y volvemos a iniciar el contenido desde el principio con **start()**.
8. Liberamos el contenido multimedia asociado al objeto con **release()**.
9. Debemos de crear un nuevo objeto media player para configurar una nueva fuente multimedia.
