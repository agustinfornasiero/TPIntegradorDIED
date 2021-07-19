
CREATE TABLE tp_died.estacion
(
    nombre character varying(128) COLLATE pg_catalog."default" NOT NULL,
    id integer NOT NULL,
    hora_apertura time without time zone NOT NULL,
    hora_cierre time without time zone NOT NULL,
    estado character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Estacion_pkey" PRIMARY KEY (id),
    CONSTRAINT estado_chk CHECK (estado::text = ANY (ARRAY['OPERATIVA'::character varying, 'EN_MANTENIMIENTO'::character varying]::text[]))
)

TABLESPACE pg_default;

ALTER TABLE tp_died.estacion
    OWNER to postgres;

------------------------------

CREATE TABLE tp_died.tramo
(
    id integer NOT NULL,
    distancia_en_km double precision NOT NULL,
    duracion_viaje_en_min double precision NOT NULL,
    cantidad_maxima_pasajeros integer NOT NULL,
    estado character varying(10) COLLATE pg_catalog."default" NOT NULL,
    costo double precision NOT NULL,
    id_estacion_origen integer NOT NULL,
    id_estacion_destino integer NOT NULL,
    CONSTRAINT "Tramo_pkey" PRIMARY KEY (id),
    CONSTRAINT id_estacion_destino_fk FOREIGN KEY (id_estacion_destino)
        REFERENCES tp_died.estacion (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT id_estacion_origen_fk FOREIGN KEY (id_estacion_origen)
        REFERENCES tp_died.estacion (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT estado_chk CHECK (estado::text = ANY (ARRAY['ACTIVO'::character varying, 'INACTIVO'::character varying]::text[])) NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE tp_died.tramo
    OWNER to postgres;

------------------------------

CREATE TABLE tp_died.boleto
(
    id integer NOT NULL,
    correo_electronico_cliente character varying(128) COLLATE pg_catalog."default" NOT NULL,
    nombre_cliente character varying(128) COLLATE pg_catalog."default" NOT NULL,
    fecha_venta date NOT NULL,
    nombre_estacion_origen character varying(128) COLLATE pg_catalog."default" NOT NULL,
    nombre_estacion_destino character varying(128) COLLATE pg_catalog."default" NOT NULL,
    camino character varying(128)[] COLLATE pg_catalog."default" NOT NULL,
    costo double precision NOT NULL,
    CONSTRAINT boleto_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE tp_died.boleto
    OWNER to postgres;

------------------------------

CREATE TABLE tp_died.tarea_de_mantenimiento
(
    id integer NOT NULL,
    fecha_inicio date NOT NULL,
    fecha_fin date,
    observaciones character varying(512) COLLATE pg_catalog."default",
    id_estacion integer,
    CONSTRAINT tarea_de_mantenimiento_pkey PRIMARY KEY (id),
    CONSTRAINT id_estacion_fk FOREIGN KEY (id)
        REFERENCES tp_died.estacion (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE tp_died.tarea_de_mantenimiento
    OWNER to postgres;

------------------------------

CREATE TABLE tp_died.linea_de_transporte
(
    id integer NOT NULL,
    nombre character varying(128) COLLATE pg_catalog."default" NOT NULL,
    color character varying(64) COLLATE pg_catalog."default" NOT NULL,
    estado character varying(10) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT linea_de_transporte_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE tp_died.linea_de_transporte
    OWNER to postgres;

------------------------------

CREATE TABLE tp_died.estaciones_linea
(
    id_linea_de_transporte integer NOT NULL,
    id_estacion integer NOT NULL,
    CONSTRAINT estaciones_linea_pkey PRIMARY KEY (id_linea_de_transporte, id_estacion),
    CONSTRAINT id_estacion FOREIGN KEY (id_estacion)
        REFERENCES tp_died.estacion (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT id_linea_de_transporte_fk FOREIGN KEY (id_linea_de_transporte)
        REFERENCES tp_died.linea_de_transporte (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE tp_died.estaciones_linea
    OWNER to postgres;





