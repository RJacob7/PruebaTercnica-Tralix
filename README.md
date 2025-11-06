# Tienda API REST - Prueba Técnica Tralix

API REST desarrollada con Spring Boot 3 y Java 17 para gestionar productos, ventas y obtener analíticas usando Java Streams.

## 🚀 Tecnologías

- Java 17
- Spring Boot 3
- Maven
- Docker & Docker Compose
- JUnit 5 & Mockito

## 📋 Requisitos Previos

- Docker Desktop instalado
- Puerto 8080 disponible

## 🐳 Ejecución con Docker

### Opción 1: Usar Docker Compose (Recomendado)

```bash
# Construir y levantar el contenedor
docker-compose up --build

# Para ejecutar en segundo plano
docker-compose up -d --build

# Para detener
docker-compose down
```

### Opción 2: Usar Docker directamente

```bash
# Construir la imagen
docker build -t tienda-apirest .

# Ejecutar el contenedor
docker run -p 8080:8080 --name tienda-api tienda-apirest

# Detener el contenedor
docker stop tienda-api
docker rm tienda-api
```

## 📡 Endpoints Disponibles

### Productos

- **POST** `/productos` - Crear producto
- **GET** `/productos` - Listar todos los productos

#### Ejemplo POST /productos:
```json
{
  "nombre": "Laptop Dell",
  "categoria": "Electronica",
  "precio": 15000.50
}
```

### Ventas

- **POST** `/ventas` - Registrar venta
- **GET** `/ventas` - Listar todas las ventas

#### Ejemplo POST /ventas:
```json
{
  "productoId": 1,
  "cantidad": 2
}
```

### Analíticas

- **GET** `/analytics/total` - Total de ventas
- **GET** `/analytics/top?n=3` - Top N productos vendidos
- **GET** `/analytics/categorias` - Ventas agrupadas por categoría
- **GET** `/analytics/promedio` - Promedio de venta por producto

## ✅ Validaciones

### Producto:
- **nombre**: Obligatorio, entre 3 y 50 caracteres
- **categoria**: Debe ser una de: Electronica, Ropa, Alimentos, Hogar, Deportes
- **precio**: Entre 0.01 y 1,000,000

### Venta:
- **productoId**: Obligatorio, mayor a 0
- **cantidad**: Entre 1 y 1000 unidades

## 🧪 Ejecutar Tests

```bash
# Con Maven
mvn test

# Con Maven Wrapper (si está disponible)
./mvnw test
```

## 📦 Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/tralix/pruebatecnica/tiendaapirest/
│   │   ├── Controller/      # Controladores REST
│   │   ├── Service/         # Lógica de negocio
│   │   ├── Repository/      # Acceso a datos (en memoria)
│   │   ├── Entities/        # Entidades del dominio
│   │   ├── DTO/             # Data Transfer Objects
│   │   ├── Validator/       # Validadores personalizados
│   │   └── Exception/       # Manejo de excepciones
│   └── resources/
│       └── application.properties
└── test/
    └── java/                # Tests unitarios
```

## 🔧 Configuración

La aplicación utiliza almacenamiento en memoria con datos de prueba precargados:
- 20 productos de muestra
- Categorías: Electronica, Ropa, Alimentos, Hogar, Deportes

## 📝 Notas

- La API corre en el puerto **8080**
- Los datos se reinician al reiniciar la aplicación (almacenamiento en memoria)
- Se incluyen validaciones robustas y manejo de excepciones
- Arquitectura en capas siguiendo mejores prácticas

## 👨‍💻 Autor

Jacob - Prueba Técnica para Tralix
