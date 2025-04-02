use mydb;
 
SHOW TABLES;

SELECT * FROM productos;
SELECT * FROM categoria;
SELECT productos.nombre, categoria.nombre
                FROM productos 
                INNER JOIN categoria ON productos.id_categoria = categoria.id_categoria;




SELECT * FROM usuario;