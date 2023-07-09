--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-04-02 16:23:39

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3345 (class 1262 OID 16446)
-- Name: phrase-api; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "phrase-api" ENCODING = 'UTF8';
    -- LOCALE = 'English_United States.1252';


ALTER DATABASE "phrase-api" OWNER TO postgres;

\connect -reuse-previous=on "dbname='phrase-api'"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 214 (class 1259 OID 16447)
-- Name: api_keys; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.api_keys (
                                 id character varying(255) NOT NULL,
                                 api_key character varying(255)
);


ALTER TABLE public.api_keys OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16454)
-- Name: jokes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jokes (
                              id integer,
                              phrase text
);


ALTER TABLE public.jokes OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16485)
-- Name: phrase_management; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.phrase_management (
                                          id integer NOT NULL,
                                          "userId" text NOT NULL,
                                          phrase text NOT NULL,
                                          author text
);


ALTER TABLE public.phrase_management OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16484)
-- Name: phrase_management_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.phrase_management_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.phrase_management_id_seq OWNER TO postgres;

--
-- TOC entry 3346 (class 0 OID 0)
-- Dependencies: 219
-- Name: phrase_management_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.phrase_management_id_seq OWNED BY public.phrase_management.id;


--
-- TOC entry 216 (class 1259 OID 16459)
-- Name: quotes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.quotes (
                               id integer,
                               author text,
                               phrase text
);


ALTER TABLE public.quotes OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16465)
-- Name: request_count; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.request_count (
                                      request_number integer NOT NULL,
                                      api_key character varying(255) NOT NULL
);


ALTER TABLE public.request_count OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16464)
-- Name: request_count_request_number_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.request_count_request_number_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.request_count_request_number_seq OWNER TO postgres;

--
-- TOC entry 3347 (class 0 OID 0)
-- Dependencies: 217
-- Name: request_count_request_number_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.request_count_request_number_seq OWNED BY public.request_count.request_number;


--
-- TOC entry 3191 (class 2604 OID 16488)
-- Name: phrase_management id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.phrase_management ALTER COLUMN id SET DEFAULT nextval('public.phrase_management_id_seq'::regclass);


--
-- TOC entry 3190 (class 2604 OID 16468)
-- Name: request_count request_number; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.request_count ALTER COLUMN request_number SET DEFAULT nextval('public.request_count_request_number_seq'::regclass);


--
-- TOC entry 3197 (class 2606 OID 16492)
-- Name: phrase_management phrase_management_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.phrase_management
    ADD CONSTRAINT phrase_management_pkey PRIMARY KEY (id);


--
-- TOC entry 3193 (class 2606 OID 16453)
-- Name: api_keys pk_api_keys; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.api_keys
    ADD CONSTRAINT pk_api_keys PRIMARY KEY (id);


--
-- TOC entry 3195 (class 2606 OID 16470)
-- Name: request_count pk_request_count; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.request_count
    ADD CONSTRAINT pk_request_count PRIMARY KEY (request_number);


-- Completed on 2023-04-02 16:23:39

--
-- PostgreSQL database dump complete
--


--update on 2023-07-09
ALTER TABLE IF EXISTS public.phrase_management
    ADD COLUMN date_submitted timestamp without time zone DEFAULT now();

ALTER TABLE IF EXISTS public.phrase_management
    ADD COLUMN date_modified_by_admin timestamp without time zone;

ALTER TABLE IF EXISTS public.phrase_management
    ALTER COLUMN date_submitted SET NOT NULL;