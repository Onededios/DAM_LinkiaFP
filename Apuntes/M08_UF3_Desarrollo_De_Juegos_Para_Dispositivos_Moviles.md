# M08 UF3 Desarrollo De Juegos Para Dispositivos Moviles

## Animación

### Tipos de animaciones

Una animación es una secuencia de imágenes que crea la ilusión de movimiento.

Se logra al mostrar imágenes consecutivas a una velocidad suficientemente rápida.

Una animación de **24 fotogramas por segundo** se considera animación.

### Técnicas de animación

- **Stop Motion:** Se capturan movimientos de objetos inanimados manualmente con una cámara fotográfica y después se unen y editan en una computadora.
- **Rotoscopia:** Se copian los fotogramas de una grabación real y se sustituyen con dibujos creados digitalmente a partir de la imagen real.
- **Animación digital 2D:** Utiliza software para crear movimientos en un espacio bidimensional.
- **Animación digital 3D:** Se basa en la creación de modelos tridimensionales en entornos digitales

## Sprites o texturas

Un sprite es la combinación de una imagen gráfica y su información geométrica.

La parte gráfica de un sprite viene determinada por una textura, una imagen descomprimida y guardada en la memoria del procesador gráfico.

Al Sprite se le aplica el movimiento y las colisiones, a la textura no.

### Mapeo

El mapeo de texturas consiste en calcular en qué lugar de la pantalla y con qué forma se va a dibujar la textura.

## Renderizar

Proceso de generar digitalmente una imagen o animación a partir de un modelo 2D o 3D.

Requiere de un alto nivel de procesamiento gráfico.

## Colisiones

Las técnicas de detección de colisiones en los videojuegos se utilizan para controlar el movimiento de los sprites.

Permiten:
- Evitar que los sprites se salgan de los **límites de la pantalla**.
- Evitar que los sprites **atraviesen obstáculos u otros sprites** que son considerados como sólidos.
- Detectar el momento en el que los sprites **entran en contacto** con objetos del juego.
- Simular el **comportamiento de proyectiles**.

### Detecciones

La detección de colisiones se basa en la posición y el estado actual de los objetos en un juego o simulación.

Si la frecuencia de renderización es baja, es posible que se pierdan colisiones entre fotogramas.

#### Detección geométrica

La simplificación de la geometría del sprite es la clave para poder detectar colisiones.

Una ténica muy sencilla consiste en envolver los sprites en figuras geométricas simples (como rectangulos o redondas) para de esa forma detectar si colisionan o no.

Ejemplo:

```text
Imagina que estás creando un juego con naves espaciales representadas por sprites. 
En lugar de verificar colisión pixel por pixel, utilizamos una técnica más simple:

1. Envolver en Figuras Geométricas:

Envolvemos cada nave espacial con un rectángulo que se ajusta a su forma.
Estos rectángulos son figuras geométricas simples y representan de manera aproximada la forma de las naves.

2. Detección de Colisión:

En lugar de comparar cada pixel, simplemente verificamos si los rectángulos que envuelven las naves se solapan.
Si hay solapamiento, asumimos que las naves han colisionado.
```

#### Detección mediante proyecciones

Esta técnica sirve para detectar el solapamiento de dos sprites y consiste en proyectar geométricamente los objetos sobre un plano.

Si tenemos dos figuras convexas y encontramos un eje en el cual la proyección de las dos figuras no se solapa, entonces los dos sprites no han colisionado.

Esta técnica reduce la complejidad a un problema de una dimensión, acelerando de esta manera los cálculos.

Ejemplo:

```text
Supongamos que tienes dos autos representados como rectángulos en un juego. 
Queremos saber si estos autos han chocado. 

1. Proyección en el eje X:

Proyectamos ambos rectángulos en el eje X (horizontal).
Si las proyecciones no se solapan en este eje, los autos no han colisionado.

2. Proyección en el eje Y:

Proyectamos ambos rectángulos en el eje Y (vertical).
Si las proyecciones no se solapan en este eje, los autos no han colisionado.

Al combinar ambas proyecciones, si no hay solapamiento en ninguna dimensión, podemos concluir que no hay colisión entre los dos autos.
```

## Como diseñar un videojuego en Android

### Proceso

1. Crear un nuevo proyecto
2. Añadir una clase Java que herede de la clase View (es donde se realizará el render)
3. En el onDraw dibujamos la escena

```java
public class CustomViewGame extends View {
    public CustomViewGame(Context context_JOO) {
        super(context_JOO);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas_JOO) {
        super.onDraw(canvas_JOO);
        Paint p = new Paint();
        p.setColor(Color.RED);
        canvas.drawOval(new RectF(0, 0, 100, 100), p);
    }
}
```

4. En el layout colocamos un elemento de tipo View

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <com.ejemplo.CustomViewGame
        android:id="@+id/CustomViewGame"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</RelativeLayout>
```

La función que se utiliza para llamar al onDraw() y actualizar así la pantalla es **invalidate()** y se debe de llamar dentro del Timer que ejecuta el main loop del juego.