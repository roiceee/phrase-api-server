-- SCHEMA: public

-- DROP SCHEMA IF EXISTS public ;

CREATE SCHEMA IF NOT EXISTS public
    AUTHORIZATION postgres;

COMMENT ON SCHEMA public
    IS 'standard public schema';

GRANT ALL ON SCHEMA public TO PUBLIC;

GRANT ALL ON SCHEMA public TO postgres;

-- Table: public.api_keys

-- DROP TABLE IF EXISTS public.api_keys;

CREATE TABLE IF NOT EXISTS public.api_keys
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    api_key character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT pk_api_keys PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.api_keys
    OWNER to postgres;


-- Table: public.jokes

-- DROP TABLE IF EXISTS public.jokes;

CREATE TABLE IF NOT EXISTS public.jokes
(
    id integer,
    phrase text COLLATE pg_catalog."default"
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.jokes
    OWNER to postgres;

-- Table: public.quotes

-- DROP TABLE IF EXISTS public.quotes;

CREATE TABLE IF NOT EXISTS public.quotes
(
    id integer,
    author text COLLATE pg_catalog."default",
    phrase text COLLATE pg_catalog."default"
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.quotes
    OWNER to postgres;

-- Table: public.request_count

-- DROP TABLE IF EXISTS public.request_count;

CREATE TABLE IF NOT EXISTS public.request_count
(
    request_number serial NOT NULL,
    api_key character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_request_count PRIMARY KEY (request_number)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.request_count
    OWNER to postgres;