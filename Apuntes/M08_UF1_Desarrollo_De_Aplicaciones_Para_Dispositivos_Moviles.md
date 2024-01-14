# M08 UF1 Desarrollo De Aplicaciones Para Dispositivos Moviles

## Elementos de Android Studio

### Trazas

#### Errores de compilación

Indican problemas en el código fuente que impiden la generación exitosa del archivo ejecutable.

Para resolverlos, revisa la consola de compilación y corrige los errores señalados.

#### Errores de construcción (build)

Problemas durante la creación del proyecto, como dependencias faltantes o configuraciones incorrectas.

Verifica los archivos de configuración del proyecto y las dependencias para solucionar estos problemas.

#### Errores de instalación (run)

Ocurren al intentar ejecutar la aplicación en un dispositivo o emulador.

Asegúrate de que el dispositivo esté conectado correctamente y que la configuración de ejecución sea adecuada.

#### Errores de ejecución (crash)

Se producen durante la ejecución de la aplicación.

Analiza las trazas de errores y utiliza herramientas como el analizador de stack trace en Android Studio para identificar y corregir los problemas.

#### Debug

Permite examinar variables, establecer puntos de interrupción y evaluar expresiones en tiempo de ejecución.

Utiliza estas herramientas para identificar y corregir problemas en tu código durante la depuración.

### Project

#### Configuración

- **compileSdkVersion:** Versión de SDK utilizada para compilar.
- **minSdkVersion:** SDK mínimo compatible
- **targetSdkVersion:** SDK máximo compatible
- **buildTypes:** Información de la release.
- **dependences:** se especifican las dependencias externas que necesita tu aplicación.

#### Carpetas

- **Carpeta manifests:** Contiene el archivo AndroidManifest.xml que describe la estructura de tu aplicación, sus componentes y sus permisos.
- **Carpeta java:** Aquí se encuentran los archivos fuente de tu aplicación escritos en el lenguaje de programación Java.
- **Carpeta res:** Contiene recursos como imágenes, diseños y valores utilizados en tu aplicación.
- **Drawable:** En esta carpeta se almacenan los recursos gráficos, como imágenes y archivos XML de forma dibujable.
- **Layout:** Aquí se definen los archivos XML que representan la interfaz de usuario de tu aplicación.
- **Mipmap:** Similar a la carpeta Drawable, pero optimizada para almacenar íconos de la aplicación en diferentes densidades de pantalla.
- **Values:** Esta carpeta contiene archivos XML que definen valores constantes, como colores, cadenas y estilos, utilizados en toda la aplicación.
  - **Color:** Define los colores utilizados en la interfaz de usuario.
  - **String:** Contiene las cadenas de texto utilizadas en la aplicación.
  - **Styles:** Define los estilos de la interfaz de usuario, como el formato de texto y la apariencia de los componentes.

### Componentes de una aplicación

Una aplicación Android está compuesta por varios componentes que trabajan juntos para ofrecer funcionalidades. Aquí hay información adicional sobre algunos de los componentes clave:

#### Actividades

Las actividades representan las distintas pantallas con las que los usuarios interactúan en una aplicación. Puedes crear una nueva actividad extendiendo la clase Activity y definiendo su diseño en un archivo XML.

Para que la actividad sea reconocida por Android, hay que declararla en el archivo manifest.

Proceso:

1. Crear el archivo java de la actividad que extiende de AppCompatActivity
2. Crear un layout asociado a la clase java
3. Modificar el manifest

```java
public class PageEnd extends AppCompatActivity {
     @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endgame);
    }
}
```

En caso de ser la actividad prinicipal a mostrar:

```xml
<activity android:name="com.m08.example.PageEnd"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
</activity>
```

En caso de ser una actividad secundaria:

```xml
<activity android:name="com.m08.example.PageEnd"
            android:exported="true">
</activity>
```

#### Fragmentos

Los fragmentos son porciones modulares de la interfaz de usuario que pueden ser reutilizadas en varias actividades. Puedes añadir fragmentos a una actividad mediante el uso de la clase Fragment y gestionar su interfaz de usuario.

#### Servicios

Los servicios realizan operaciones en segundo plano sin tener una interfaz de usuario visible. Para crear un servicio, extiende la clase Service y define las tareas que deben realizarse en el fondo.

#### Proveedores de contenidos

Los proveedores de contenidos gestionan un conjunto estructurado de datos que pueden ser compartidos entre aplicaciones. Puedes implementar un proveedor de contenidos mediante la creación de una clase que extienda **ContentProvider**.

#### Receptores de emisiones

Los receptores de emisiones responden a mensajes de sistema o eventos personalizados. Para crear un receptor de emisiones, extiende la clase **BroadcastReceiver** y registra los eventos a los que debe responder en el archivo de manifiesto.

#### Intents

Los intents son mensajes que permiten a los componentes de una aplicación solicitar acciones de otros componentes del sistema. Puedes usar intents para iniciar actividades, servicios o enviar y recibir datos entre componentes.

En la actividad de origen podemos enviar información extra a otra actividad mediante **putExtra()**

```java
Intent intent = new Intent(this, ActividadDestino.class);
intent.putExtra("clave", "informacion a enviar");
startActivity(intent);
```

Y en la otra recivirla y transformarla

```java
Intent intent = getIntent();
String informacionRecibida = intent.getStringExtra("clave");
```

### Layouts

- **LinearLayout:** Organiza elementos en una sola dirección, ya sea vertical u horizontal.
- **RelativeLayout:** Permite posicionar elementos con respecto a otros, facilitando diseños más flexibles.
- **FrameLayout:** Diseñado para mostrar un solo elemento a la vez, útil para superponer vistas.
- **ConstraintLayout:** Ofrece flexibilidad y eficiencia al posicionar elementos mediante restricciones, ideal para interfaces complejas.
- **CoordinatorLayout:** Extiende FrameLayout, diseñado para coordinar comportamientos de vistas hijas, útil en interfaces con animaciones complejas.
- **ScrollView:** Permite desplazarse vertical u horizontalmente para mostrar contenido fuera de la pantalla.
- **TableLayout:** Organiza elementos en filas y columnas, útil para presentar datos tabulares.
- **GridLayout:** Similar a TableLayout, pero permite un mayor control sobre la ubicación y el tamaño de los elementos.

### Elementos

#### Elementos responsive

En Android Studio, puedes hacer elementos como botones y campos de texto responsivos utilizando ConstraintLayout. Define restricciones para que se ajusten automáticamente en diferentes tamaños de pantalla.

#### Etiquetas de texto (TextView)

Es un elemento de interfaz de usuario utilizado para mostrar texto.

Se usa para presentar información al usuario, como títulos, descripciones o cualquier tipo de texto.

```xml
<TextView
    android:id="@+id/myTextView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Hola, mundo!" />
```

Desde el código Java, puedes acceder y manipular un TextView utilizando su ID:

```java
// Obtén una referencia al TextView
TextView myTextView = findViewById(R.id.myTextView);

// Modifica el texto del TextView
myTextView.setText("Nuevo texto para el TextView");
```

Este ejemplo muestra cómo crear un TextView en el diseño XML y luego acceder y modificar su contenido desde el código Java.

#### Campos de texto editables (EditText/Plain Text)

Es un elemento de interfaz de usuario utilizado para que los usuarios ingresen y editen texto.

```xml
<EditText
    android:id="@+id/editText"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Escribe aquí" />
```

Desde el código Java, puedes acceder y manipular un EditText utilizando su ID:

```java
// Obtén una referencia al EditText
EditText myEditText = findViewById(R.id.myEditText);

// Obtén el texto ingresado por el usuario
String userInput = myEditText.getText().toString();
```

Este ejemplo muestra cómo crear un EditText en el diseño XML con un mensaje de sugerencia (hint) y luego acceder al texto ingresado por el usuario desde el código Java.

#### Botones (Button)

El elemento Button en Android Studio es un componente de interfaz de usuario utilizado para realizar acciones cuando el usuario hace clic en él

```xml
<Button
    android:id="@+id/myButton"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Haz clic" />
```

Desde el código Java, puedes acceder y manejar un Button utilizando su ID:

```java
// Obtén una referencia al Button
Button myButton = findViewById(R.id.myButton);

// Asigna un listener para manejar clics en el botón
myButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        // Acciones a realizar al hacer clic en el botón
        Toast.makeText(MainActivity.this, "¡Botón clickeado!", Toast.LENGTH_SHORT).show();
    }
});
```

Este ejemplo muestra cómo crear un Button en el diseño XML y cómo manejar eventos de clic desde el código Java.

#### Controles de selección (Spinner)

El elemento Spinner en Android Studio es utilizado para proporcionar a los usuarios una forma de seleccionar un valor de un conjunto.

```xml
<Spinner
    android:id="@+id/spinner"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```

Desde el código Java, puedes acceder y manipular un Spinner utilizando su ID y configurar un adaptador para llenar los datos:

```java
// Obtén una referencia al Spinner
Spinner mySpinner = findViewById(R.id.mySpinner);

// Define un array de datos
String[] datos = {"Opción 1", "Opción 2", "Opción 3"};

// Crea un adaptador y asigna los datos al Spinner
ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, datos);
mySpinner.setAdapter(adapter);
```

Este ejemplo muestra cómo crear un Spinner en el diseño XML y llenarlo con datos desde el código Java.

#### Lista de elementos desplazables (ListView)

El elemento ListView en Android Studio se utiliza para mostrar una lista de elementos desplazables.

```xml
<ListView
    android:id="@+id/myListView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

Desde el código Java, puedes acceder y manipular un ListView utilizando su ID y configurar un adaptador para llenar los datos:

```java
// Obtén una referencia al ListView
ListView myListView = findViewById(R.id.myListView);

// Define un array de datos
String[] datos = {"Elemento 1", "Elemento 2", "Elemento 3"};

// Crea un adaptador y asigna los datos al ListView
ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);
myListView.setAdapter(adapter);
```

Este ejemplo muestra cómo crear un ListView en el diseño XML y llenarlo con datos desde el código Java.

### Adaptadores própios

En Android, crear un adaptador propio para un ListView te permite personalizar la apariencia de cada elemento en la lista.

Pasos:

1. Crear un layout para cada elemento de la lista:

_Puedes definir cómo se verá cada elemento en el archivo XML de layout._

```xml
<!-- list_item_layout.xml -->
<TextView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/textViewListItem"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:textSize="18sp"
    android:padding="10dp"/>
```

2. Crear la clase del adaptador personalizado:

_Puedes extender la clase ArrayAdapter<> y personalizarla según tus necesidades._

```java
public class CustomAdapter extends ArrayAdapter<String> {
    private Context context;
    private String[] data;

    public CustomAdapter(Context context, String[] data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_layout, parent, false);
        }

        TextView textViewItem = convertView.findViewById(R.id.textViewListItem);
        textViewItem.setText(data[position]);

        return convertView;
    }
}
```

3. Usar el adaptador:

```java
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.myListView);
        String[] data = {"Elemento 1", "Elemento 2", "Elemento 3"};

        CustomAdapter adapter = new CustomAdapter(this, data);
        listView.setAdapter(adapter);
    }
}
```

### Permisos

#### Declarar permisos

Algunos permisos se declaran en el archivo de manifiesto para indicar las capacidades de la aplicación.

```xml
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.INTERNET" />
```

#### Pedir permisos

En situaciones donde se necesitan permisos en tiempo de ejecución, como el acceso a la cámara o ubicación, se deben solicitar explícitamente al usuario.

```java
if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
    // Permiso concedido, realizar acciones relacionadas con la ubicación.
} else {
    // Solicitar permiso al usuario.
    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION);
}
```

### Tareas asincronas para conexiones

Utilizando la clase Thread AsyncTask

- **onPreExecute():** Se ejecuta antes de iniciar la tarea.
  - Es útil para configurar cualquier elemento antes de la ejecución de la tarea.

```java
@Override
protected void onPreExecute() {
    super.onPreExecute();
    // Configuraciones previas a la ejecución, como mostrar un mensaje de carga.
}
```

- **doInBackground():** Se encarga de realizar una tarea en segundo plano.
  - Aquí se deben realizar las operaciones que podrían bloquear la interfaz de usuario.

```java
@Override
protected String doInBackground(Void... params) {
    // Realizar la tarea en segundo plano, como realizar una conexión.
    return resultado;
}
```

- **onProgressUpdate():** Permite mostrar una barra de progreso.
  - Se utiliza para actualizar la interfaz de usuario durante la ejecución de la tarea.

```java
@Override
protected void onProgressUpdate(Integer... values) {
    super.onProgressUpdate(values);
    // Actualizar la barra de progreso según los valores recibidos.
}
```

- **onPostExecute():** Se ejecuta una vez que la tarea en segundo plano ha finalizado.
  - Útil para manejar el resultado de la tarea y realizar cualquier acción posterior.

```java
@Override
protected void onPostExecute(String result) {
    super.onPostExecute(result);
    // Manejar el resultado de la tarea, como actualizar la interfaz de usuario.
}
```

Ejemplo:

```java
import android.os.AsyncTask;
import android.util.Log;

public class MiTareaAsincrona extends AsyncTask<Void, Void, String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Se ejecuta antes de iniciar la tarea. Configuraciones previas.
    }

    @Override
    protected String doInBackground(Void... params) {
        // Contiene el código principal de la tarea en segundo plano.
        // Por ejemplo, realizar una conexión a la red.
        return "Resultado de la tarea";
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        // Permite mostrar una barra de progreso.
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        // Se ejecuta una vez que la tarea en segundo plano ha finalizado.
        // Manejar el resultado, como actualizar la interfaz de usuario.
        Log.d("Resultado", result);
    }
}
```

### Base de datos SQLite

Implementa una base de datos SQLite para almacenar y recuperar datos localmente en la aplicación Android.

Proceso:

```java
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nombre y versión de la base de datos
    private static final String DATABASE_NAME = "miBaseDeDatos";
    private static final int DATABASE_VERSION = 1;

    // Sentencia SQL para crear la tabla
    private static final String CREATE_TABLE =
            "CREATE TABLE mitabla  (" +
                    "id INTEGER PRIMARY KEY," +
                    "nombre TEXT," +
                    "edad INTEGER" +
                    ")";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Método para crear la base de datos y la tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    // Métodos para realizar operaciones CRUD

    // Método para agregar un nuevo registro
    public void agregarRegistro(String nombre, int edad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, nombre);
        values.put(COLUMN_EDAD, edad);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Método para obtener todos los registros
    public Cursor obtenerRegistros() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    // Otros métodos CRUD: actualizarRegistro, eliminarRegistro, etc.

    // Método para actualizar un registro
    public void actualizarRegistro(int id, String nuevoNombre, int nuevaEdad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, nuevoNombre);
        values.put(COLUMN_EDAD, nuevaEdad);
        db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Método para eliminar un registro
    public void eliminarRegistro(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Otros métodos según sea necesario

    // Actualización de la base de datos (si hay cambios en la estructura)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Aquí puedes implementar la lógica para manejar actualizaciones de la base de datos
    }
}
```
