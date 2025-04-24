SELECT * FROM empleado;
SELECT * FROM usuario;
SELECT * FROM productos;
SELECT * FROM categoria;

-- Tiene que crear los usuarios a patita sorry, debido a que el sistema encripta las contraseñas

INSERT INTO empleado (id_usuario, cargo, salario, departamento) VALUES (1, "empleado", 5000, "CS");
INSERT INTO empleado (id_usuario, cargo, salario, departamento) VALUES (2, "empleado", 5000, "CS");
INSERT INTO empleado (id_usuario, cargo, salario, departamento) VALUES (3, "Administrador", 5000, "CS");
INSERT INTO empleado (id_usuario, cargo, salario, departamento) VALUES (4, "Administrador", 5000, "CS");

INSERT INTO categorias (nombre, descripcion, estado) VALUES ("Celulares", "Dispositivo electronico movil", 1);
INSERT INTO categorias (nombre, descripcion, estado) VALUES ("Tablets", "Dispositivo electronico movil", 1);
INSERT INTO categorias (nombre, descripcion, estado) VALUES ("Laptops", "Dispositivo electronico movil", 1);

INSERT INTO productos (nombre, descipcion, id_categoria, precio, stock_actual, estado) VALUES ("Samsung", "telefono", 1, 1000, 500, 1);
INSERT INTO productos (nombre, descipcion, id_categoria, precio, stock_actual, estado) VALUES ("Apple", "telefono iPhone", 1, 1500, 500, 1);
INSERT INTO productos (nombre, descipcion, id_categoria, precio, stock_actual, estado) VALUES ("Xiaomi", "telefono xiaomi", 1, 800, 500, 1);
INSERT INTO productos (nombre, descipcion, id_categoria, precio, stock_actual, estado) VALUES ("Motorola", "telefono motorola", 1, 750, 500, 1);

INSERT INTO productos (nombre, descipcion, id_categoria, precio, stock_actual, estado) VALUES ("Apple", "iPad", 2, 2000, 500, 1);
INSERT INTO productos (nombre, descipcion, id_categoria, precio, stock_actual, estado) VALUES ("Samsung", "Tab S8", 2, 1600, 500, 1);
INSERT INTO productos (nombre, descipcion, id_categoria, precio, stock_actual, estado) VALUES ("Xiaomi", "redmi Pad s6", 2, 1200, 500, 1);

INSERT INTO productos (nombre, descipcion, id_categoria, precio, stock_actual, estado) VALUES ("Apple", "macBook air", 3, 5000, 500, 1);
INSERT INTO productos (nombre, descipcion, id_categoria, precio, stock_actual, estado) VALUES ("Lenovo", "ThinkPad", 3, 4500, 500, 1);
INSERT INTO productos (nombre, descipcion, id_categoria, precio, stock_actual, estado) VALUES ("Asus", "Asus vivobook", 3, 4800, 500, 1);



