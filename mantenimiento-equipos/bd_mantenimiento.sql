-- Evaluacion 03 - Laboratorio 08: Gestion de mantenimiento de equipos
-- Cada microservicio tiene su propia base de datos.
-- En el perfil dev las tablas tambien se crean solas (ddl-auto: update).

-- 1) Servicio de clientes (puerto 8091)
CREATE DATABASE IF NOT EXISTS bd_cliente_mant;
USE bd_cliente_mant;
CREATE TABLE IF NOT EXISTS cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    documento  VARCHAR(11)  NOT NULL UNIQUE,
    nombres    VARCHAR(100) NOT NULL,
    telefono   VARCHAR(9),
    correo     VARCHAR(100),
    direccion  VARCHAR(150)
);

-- 2) Servicio de equipos (puerto 8092)
CREATE DATABASE IF NOT EXISTS bd_equipo;
USE bd_equipo;
CREATE TABLE IF NOT EXISTS equipo (
    id_equipo  INT AUTO_INCREMENT PRIMARY KEY,
    codigo     VARCHAR(20) NOT NULL UNIQUE,
    tipo       VARCHAR(50) NOT NULL,
    marca      VARCHAR(50) NOT NULL,
    modelo     VARCHAR(50) NOT NULL,
    id_cliente INT NOT NULL            -- referencia por ID al servicio de clientes
);

-- 3) Servicio de ordenes de servicio (puerto 8093)
CREATE DATABASE IF NOT EXISTS bd_orden_servicio;
USE bd_orden_servicio;
CREATE TABLE IF NOT EXISTS orden_servicio (
    id_orden       INT AUTO_INCREMENT PRIMARY KEY,
    id_equipo      INT NOT NULL,       -- referencia por ID al servicio de equipos
    fecha_ingreso  DATE NOT NULL,
    descripcion    VARCHAR(300) NOT NULL,
    costo_estimado DECIMAL(10,2) NOT NULL,
    estado         ENUM('RECIBIDO','EN_REPARACION','LISTO','ENTREGADO') NOT NULL DEFAULT 'RECIBIDO'
);
