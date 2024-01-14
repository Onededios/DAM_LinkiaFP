# M09 UF1 Seguridad y criptografia

## Preguntas

```
Contenido del test:
- Apuntes UF4
    - Contenido de la parte de desarrollo:
- 3 ejercicios (2 puntos cada uno):
    - Creación de objetos.
    - Creación de tablas de objetos.
    - Nested Tables.
    - Integridad Referencial.
    - Insertar datos en tablas.
```

## Teoria

### Objetos

Para definir objetos en una BD objeto-relacional creamos nuevos tipos de
datos “complejos” (son un conjunto de tipos de datos simples y/ otros tipos
complejos):

#### Creacion

```sql
DROP TYPE objetoserie FORCE;

CREATE OR REPLACE TYPE objetoserie AS OBJECT (
        codigo VARCHAR2(255),
        titulo VARCHAR2(255),
        pais   VARCHAR2(100),
        genero VARCHAR2(50)
);
```

### Tablas

Para poder trabajar con objetos, es imprescindible crear una tabla. Oracle nos
proporciona dos tipos de tabla para trabajar con los objetos.

#### Tablas sin identidad de objeto

Tienen filas que provienen de la definición del objeto y filas definidas con tipos normales de SQL (number, varchar,…)

##### Creación

```sql
DROP TABLE tablaseries;

CREATE TABLE tablaseries (
    id NUMBER(3) PRIMARY KEY,
    serie objetoserie
);
```

##### Inserción

En los siguientes ejemplos se observa como se insertan objetos en una tabla, el articulo lo añadimos con el **objetoserie** delante.

```sql
INSERT INTO tablaseries values(
    0,
    objetoserie(
        '12adqweq1',
        'Game of Thrones',
        'USA',
        'Fantasy'
    )
);

INERT INTO tablaseries values(
    1,
    objetoserie(
        '128971239j',
        'Serial Experiments Lain',
        'JPN',
        'Sci-Fi'
    )
);
```

##### Consulta

Para acceder a los datos del objeto utilizamos el nombre de la tabla y el nombre del objeto.

```sql
SELECT serie.* FROM tablaseries;
SELECT s.serie.* FROM tablaseries s;
SELECT s.serie.codigo, s.serie.titulo, s.serie.pais, s.serie.genero FROM tablaseries s;
```

##### Modificiación

La modificación de datos se realiza mediante la instrucción UPDATE. Para referenciar el campo que queremos modificar utilizamos el mismo mecanismo de las consultas.

```sql
UPDATE tablaseries s
SET s.codigo = 2, s.serie.codigo = 'awoidjawiodj1'
WHERE s.codigo = 1;
```

##### Eliminación

```sql
DELETE FROM tablaseries s where s.serie.codigo = 2;
```

#### Tablas con identidad de objeto

Se utilizan como si de una tabla normal se tratara dónde cada una de sus filas es una fila del objeto.

##### Creación

```sql
DROP TABLE tablaseries;

CREATE TABLE tablaseries OF objetoserie (
    codigo PRIMARY KEY
);
```

##### Inserción

Las operaciones en estas tablas tienen la misma sintaxis que en las tablas relacionales.

Cada instancia de objeto se almacena como un registro y por tanto accedemos de la misma forma que lo hacíamos en SQL.

```sql
INSERT INTO tablaseries VALUES (
    '12adqweq1',
    'Game of Thrones',
    'USA',
    'Fantasy'
);

INSERT INTO tablaseries VALUES (
    '128971239j',
    'Serial Experiments Lain',
    'JPN',
    'Sci-Fi'
);
```

##### Consulta

```sql
SELECT * from tablaseries;
```

### Nested Tables

Las nested tables no tienen la limitación de saber el nombre maximo de elementos que puede tener.

Permiten representar una relación 1:N dentro de un objeto.

#### Creación

1. Creamos un nuevo tipo que define una tabla de objetos dirección que nos permita asociar varias direcciones a un cliente.
2. Se declara como AS TABLE OF (en lugar de AS OBJECT)

```sql
DROP TYPE episodio FORCE;

CREATE TYPE episodio AS OBJECT (
        temporada NUMBER(3),
        numero    NUMBER(3),
        nombre    VARCHAR2(255)
);

DROP TYPE objetotablaepisodio FORCE;

CREATE TYPE objetotablaepisodio AS TABLE OF episodio;
```

#### Inserción

Creamos una tabla para almacenar los episodios de cada serie.

```sql
DROP TABLE series;

CREATE TABLE series (
    codigo    VARCHAR(255),
    titulo    VARCHAR(255),
    pais      VARCHAR(100),
    genero    VARCHAR(50),
    episodios objetotablaepisodio
)
NESTED TABLE episodios STORE AS tablaepisodios;
```

Insertamos los datos de manera anidada.

```sql
INSERT INTO series VALUES (
    '12adqweq1',
    'Game of Thrones',
    'USA',
    'Fantasy',
    objetotablaepisodio(
        episodio(1, 0, 'Test with 0'),
        episodio(1, 1, 'Winter is coming'),
        episodio(1, 2, 'The kingsroad'),
        episodio(2, 1, 'The North Remembers')
    )
);
```

#### Consulta

Para realizar la consulta es necesaria una **función especial TABLE** que convierte los datos a formato de tabla.

```sql
SELECT
    s.titulo,
    e.*
FROM
    series                s,
    TABLE ( s.episodios ) e;


SELECT
    s.titulo,
    e.*
FROM
    series                s,
    TABLE ( s.episodios ) e
WHERE
    s.codigo = '12adqweq1'
ORDER BY
    e.temporada,
    e.numero ASC;
```

#### Modificación

Para modificar valores de la “nested table” debemos convertirla en formato "tabla” (utilizando la función TABLE) para acceder directamente a sus campos.

**El select del update solo puede devolver una fila.**

```sql
UPDATE TABLE (
    SELECT s.episodios FROM series s where s.codigo = '12adqweq1'
) e
SET e.nombrew = 'Capitulo de Mariano'
WHERE e.temporada = 1 AND e.numero = 0;
```

#### Eliminación

Para eliminar valores de la “nested table” debemos convertirla en formato “tabla” (utilizando la función TABLE).

```sql
DELETE TABLE (
    SELECT s.episodios FROM series s where s.codigo = '12adqweq1'
) e
WHERE e.temporada = 1 AND e.numero = 0;
```

### Integridad referencial

Trabajamos con los siguientes objetos:

- **objaddress:** almacena una dirección física.
- **objpersona:** almacena la información de una persona.

Y las siguientes tablas de objetos:

- **tbldirecciones:** almacena todos los objetos objaddress que asignaremos a personas.
- **tblpersonas:** almacena todos los objetos objpersona

Queremos limitar los valores de direcciones a los contenidos en la tabla de direcciones (tbldirecciones)

#### Creación

1. Creamos el objeto que almacena la dirección física

```sql
DROP TYPE objaddress FORCE;

CREATE OR REPLACE TYPE objaddress AS OBJECT (
    codigo NUMBER(3),
    calle VARCHAR2(20),
    cp VARCHAR2(5),
    poblacion VARCHAR2(20),
    provincia VARCHAR2(20)
);
```

2. Creamos la tabla que almacena las direcciones

```sql
DROP TABLE tbldirecciones;

CREATE TABLE tbldirecciones OF objaddress;
```

3. Creamos el tipo objpersona y su tabla correspondiente

Con la palabra “references” limitamos los valores posibles del atributo DOMICILIO a los varlores almacenados en la tabla de direcciones TBLDIRECCIONES.

En el caso de no incluir esta restricción, sería posible eliminar direcciones asociadas a personas. Estos registros reciben el nombre de DANGLING
REFERENCES.

```sql
DROP TYPE objpersona FORCE;

CREATE OR REPLACE TYPE objpersona AS OBJECT (
    dni VARCHAR2(9),
    nombre VARCHAR2(60),
    domicilio ref objaddress
);

DROP TABLE tblpersonas;

CREATE TABLE tblpersonas OF objpersona (
    PRIMARY KEY (dni), domicilio references tbldirecciones;
);
```

#### Inserción

1. Insertamos valores en la tabla de direcciones

```sql
INSERT INTO tbldirecciones VALUES (1,'adawdawd','12312','Blanes','Girona');
INSERT INTO tbldirecciones VALUES (2,'ouiahywduihaw','1124124','Lloret','Girona');
INSERT INTO tbldirecciones VALUES (3,'awdawdwda','124124','Calella','Barcelona');
INSERT INTO tbldirecciones VALUES (4,'adwawdwqe','6412312','Barcelona','Barcelona');
```

2. Insertamos valores en la tabla de personas

```sql
INSERT INTO tblpersonas SELECT '121212A', 'Antonio', REF(p) from tbldirecciones p where p.codigo = 1;
INSERT INTO tblpersonas SELECT '189271B', 'Juan', REF(p) from tbldirecciones p where p.codigo = 2;
INSERT INTO tblpersonas SELECT '876532C', 'Pedro', REF(p) from tbldirecciones p where p.codigo = 3;
```

#### Consulta

Para obtener la información hemos de acceder a la información almacenada por el enlace. En este caso utilizamos la instrucción DEREF y el campo al que queremos acceder.

```sql
SELECT t.dni, t.nombre, deref(domicilio).calle as calle, deref(domicilio).cp as cp, deref(domicilio).poblacion as poblacion from tblpersonas t;
```

#### Comprobación

Si intentamos borrar un registro de la tabla direcciones nos da un error de integridad referencial...

```sql
DELETE FROM tbldirecciones where codigo=1;
```
