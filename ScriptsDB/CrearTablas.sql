USE pulpeDB;

CREATE TABLE usuario (
	id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    password VARCHAR(255),
    estado BOOLEAN DEFAULT TRUE
);

CREATE TABLE clienteFrecuente (
	id_clienteFrecuente INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    puntosLealtad INT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE proveedor (
	id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombreEmpresa VARCHAR(100),
    estado BOOLEAN DEFAULT TRUE
);

CREATE TABLE categoria (
	id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion VARCHAR(255),
    estado BOOLEAN DEFAULT TRUE
);
CREATE TABLE productos (
	id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    id_categoria INT,
    id_proveedor INT,
    precio FLOAT,
    precio_promocional FLOAT,
    stock_actual INT,
    stock_minimo INT,
    estado BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria),
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
);

CREATE TABLE inventario (
	id_inventario INT PRIMARY KEY AUTO_INCREMENT,
    id_producto INT,
    cantidad INT,
    fecha_actualizacion DATE,
    tipo_movimiento VARCHAR(7),
    empleado_id INT,
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

CREATE TABLE carrito (
	id_carrito INT PRIMARY KEY AUTO_INCREMENT,
	id_usuario INT,
    fecha_creacion DATE,
    estado BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE Items_carrito (
	id_itemC INT PRIMARY KEY AUTO_INCREMENT,
    id_carrito INT,
    id_producto INT,
    precio_producto FLOAT,
    cantidad INT,
    FOREIGN KEY (id_carrito) REFERENCES carrito(id_carrito),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

CREATE TABLE pedidos (
	id_pedido INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    fecha_pedido DATE,
    precio_total FLOAT,
    estado_pedido VARCHAR(10),
    metodo_de_pago VARCHAR(10),
    direccion_envio VARCHAR(255),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE detalles_pedido (
	id_detalles_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT,
    id_producto INT,
    cantidad INT,
    producto_precion INT,
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido)
);

CREATE TABLE promociones (
	id_promociones INT AUTO_INCREMENT PRIMARY KEY,
    nombre_promociones VARCHAR(200),
    descripcion VARCHAR(255),
    tipo VARCHAR(15),
    valor_descuento FLOAT,
    fecha_inicio DATE,
    fecha_fin DATE,
    aplicable VARCHAR(20)
);

CREATE TABLE logs_sistema (
	id_logs INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    accion VARCHAR(100),
    fecha_hora DATETIME,
    ip VARCHAR(100),
    descripcion VARCHAR(100),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE contacto (
	id_contacto INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    telefono VARCHAR(20),
    correo VARCHAR(50),
    direccion VARCHAR(255),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE empleado (
	id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    cargo VARCHAR(100),
    fechaContrato DATE,
    salario FLOAT,
    departamento VARCHAR(100),
	FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);
	