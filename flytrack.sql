/*
Modelo: FlyTrack
Base de datos: PostgreSQL 8.3
*/

---- Creación de tablas

-- Tabla Parametro

create table Parametro (
	idParametro Serial not null,
	Valor Text,
	ValorUnico Text,
	Tipo Text,
	idPadre Integer
);

SELECT pg_catalog.setval('parametro_idparametro_seq', 65, true);

INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (3, 'Argentina', 'ARG', 'PAIS', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (12, 'Activo', 'ACTV', 'ESTADO_ESCALA', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (14, 'Programado', 'PROG', 'ESTADO_VUELO', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (15, 'Retrasado', 'RET', 'ESTADO_VUELO', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (16, 'En vuelo', 'VUE', 'ESTADO_VUELO', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (17, 'Finalizado', 'FIN', 'ESTADO_VUELO', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (18, 'Cancelado', 'CAN', 'ESTADO_VUELO', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (13, 'Inactivo', 'INCTV', 'ESTADO_ESCALA', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (19, 'Activo', 'ACTV', 'ESTADO_TARIFA', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (20, 'Inactivo', 'INCTV', 'ESTADO_TARIFA', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (21, 'Despegue', 'DES', 'ESTADO_INCIDENCIA', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (22, 'Aterrizaje', 'ATE', 'ESTADO_INCIDENCIA', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (23, 'Retraso', 'RET', 'ESTADO_INCIDENCIA', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (24, 'Cancelación', 'CAN', 'ESTADO_INCIDENCIA', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (25, 'En almacén', 'ALM', 'ESTADO_ENVIO', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (26, 'En vuelo', 'VUE', 'ESTADO_ENVIO', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (27, 'Activo', 'ACTV', 'ESTADO_AEROPUERTO', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (28, 'Inactivo', 'INCTV', 'ESTADO_AEROPUERTO', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (29, 'Brasil', 'BRA', 'PAIS', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (30, 'Colombia', 'COL', 'PAIS', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (31, 'Ecuador', 'ECU', 'PAIS', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (32, 'Chile', 'CHI', 'PAIS', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (33, 'Paraguay', 'PAR', 'PAIS', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (34, 'Uruguay', 'URU', 'PAIS', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (35, 'Venezuela', 'VZLA', 'PAIS', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (36, 'Bolivia', 'BOL', 'PAIS', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (39, 'Perú', 'PER', 'PAIS', NULL);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (41, 'Lima', 'LIMA', 'CIUDAD', 39);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (42, 'Cusco', 'CUSCO', 'CIUDAD', 39);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (43, 'Trujillo', 'TRUJILLO', 'CIUDAD', 39);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (44, 'Buenos Aires', 'BAIRES', 'CIUDAD', 3);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (45, 'Sao Paulo', 'SAOPAULO', 'CIUDAD', 29);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (46, 'Rio de Janeiro', 'RIOJ', 'CIUDAD', 29);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (47, 'Bogotá', 'BOGOTA', 'CIUDAD', 30);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (48, 'Quito', 'QUITO', 'CIUDAD', 31);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (49, 'Santiago de Chile', 'SANTIAGO', 'CIUDAD', 32);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (50, 'Asunción', 'ASUNCION', 'CIUDAD', 33);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (51, 'Montevideo', 'MONTEVIDEO', 'CIUDAD', 34);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (52, 'Caracas', 'CARACAS', 'CIUDAD', 35);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (53, 'Santa Cruz', 'STACRUZ', 'CIUDAD', 36);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (55, 'Loreto', 'LORETO', 'CIUDAD', 39);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (56, 'Guayaquil', 'GUAYAQUIL', 'CIUDAD', 31);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (60, 'Córdoba', 'CORDOBA', 'CIUDAD', 3);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (58, 'La Paz', 'LAPAZ', 'CIUDAD', 36);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (59, 'Maracaibo', 'MARACAIBO', 'CIUDAD', 35);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (61, 'Medellín', 'MEDELLIN', 'CIUDAD', 30);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (62, 'Cartagena', 'CARTAGENA', 'CIUDAD', 30);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (57, 'Brasilia', 'BRASILIA', 'CHEVERIDAD', 29);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (63, 'Valparaíso', 'VALPARAISO', 'CIUDAD', 32);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (64, 'Concepción', 'CONCEPCION', 'CIUDAD', 32);
INSERT INTO parametro (idparametro, valor, valorunico, tipo, idpadre) VALUES (65, 'Sucre', 'SUCRE', 'CIUDAD', 36);

alter table Parametro add constraint KeyParametro primary key (idParametro);
alter table Parametro add constraint ValorUnico unique (valorUnico, tipo);

-- Tabla Aeropuerto

create table Aeropuerto (
	idAeropuerto Serial not null,
	Nombre Text,
	Ciudad Text,
	Almacen Integer,
	AlmacenUsado Integer,
	Estado Integer,
	CoordX Integer,
	CoordY Integer
);

alter table Aeropuerto add constraint KeyAeropuerto primary key (idAeropuerto);

-- Tabla Cliente

create table Cliente (
	idCliente Serial not null,
	Nombres Text,
	Apellidos Text,
	TipoDoc Integer,
	NumDoc Text,
	Telefono Text,
	eMail Text
);

alter table Cliente add constraint KeyCliente primary key (idCliente);
alter table Cliente add constraint PersonaUnica unique (NumDoc, TipoDoc);

-- Tabla Vuelo

create table Vuelo (
	idVuelo Serial not null,
	idOrigen Integer,
	idDestino Integer,
	FechaSalida Timestamp,
	FechaLlegada Timestamp,
	Capacidad Integer,
	CapacidadUsada Integer,
	Alquiler Real,
	Estado Integer
);

alter table Vuelo add Constraint KeyVuelo primary key (idVuelo);

-- Tabla Incidencia

create table Incidencia (
	idIncidencia Serial not null,
	idVuelo Integer,
	Tipo Integer,
	Fecha Timestamp,
	Descripcion Text
);

alter table Incidencia add constraint KeyIncidencia primary key (idIncidencia);

-- Tabla Envio

create table Envio (
	idEnvio Serial not null,
	idRemitente Integer,
	idDestinatario Integer,
	FechaEntrada Timestamp,
	FechaSalida Timestamp,
	NumPaquetes Integer,
	Moneda Integer,
	Monto Real,
	TipoDocVenta Integer,
	NumDocVenta Text,
	idOrigen Integer,
	idDestino Integer,
	idActual Integer,
	Estado Integer
);

alter table Envio add constraint KeyEnvio primary key (idEnvio);

-- Tabla Tarifa

create table Tarifa (
	idTarifa Serial not null,
	idOrigen Integer,
	idDestino Integer,
	Moneda Integer,
	Monto Real,
	Estado Integer,
	FechaActivacion Timestamp,
	FechaDesctivacion Timestamp
);

alter table Tarifa add constraint KeyTarifa primary key (idTarifa);

-- Tabla Escala

create table Escala (
	idEscala Serial not null,
	idVuelo Integer,
	idEnvio Integer,
	FechaInicio Timestamp,
	Estado Integer
);

alter table Escala add constraint KeyEscala primary key (idEscala);
alter table Escala add constraint EscalaUnica unique (idEnvio, idVuelo);

---- Creación de relaciones

alter table Parametro add constraint fKeyPadreParametro foreign key (idPadre) references Parametro (idParametro);
alter table Vuelo add constraint fKeyOrigenVuelo foreign key (idOrigen) references Aeropuerto (idAeropuerto);
alter table Vuelo add constraint fKeyDestinoVuelo foreign key (idDestino) references Aeropuerto (idAeropuerto);
alter table Incidencia add constraint fKeyVueloIncidencia foreign key (idVuelo) references Vuelo (idVuelo);
alter table Tarifa add constraint fKeyOrigenTarifa foreign key (idOrigen) references Aeropuerto (idAeropuerto);
alter table Tarifa add constraint fKeyDestinoTarifa foreign key (idDestino) references Aeropuerto (idAeropuerto);
alter table Envio add constraint fKeyOrigenEnvio foreign key (idOrigen) references Aeropuerto (idAeropuerto);
alter table Envio add constraint fKeyDestinoEnvio foreign key (idDestino) references Aeropuerto (idAeropuerto);
alter table Envio add constraint fKeyActualEnvio foreign key (idActual) references Aeropuerto (idAeropuerto);
alter table Envio add constraint fKeyRemitenteEnvio foreign key (idRemitente) references Cliente (idCliente);
alter table Envio add constraint fKeyDestinatarioEnvio foreign key (idDestinatario) references Cliente (idCliente);
alter table Escala add constraint fKeyEnvioEscala foreign key (idEnvio) references Envio (idEnvio);
alter table Escala add constraint fKeyVueloEscala foreign key (idVuelo) references Vuelo (idVuelo);


