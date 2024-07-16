--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0 (Debian 16.0-1.pgdg120+1)
-- Dumped by pg_dump version 16.3 (Ubuntu 16.3-1.pgdg22.04+1)

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
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: status_enum; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.status_enum AS ENUM (
    'PENDING',
    'REJECTED',
    'ACCEPTED'
    );


ALTER TYPE public.status_enum OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: api_keys; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.api_keys (
                                 id character varying(255) NOT NULL,
                                 api_key character varying(255)
);


ALTER TABLE public.api_keys OWNER TO postgres;

--
-- Name: jokes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jokes (
                              id bigint NOT NULL,
                              phrase text,
                              author text,
                              phrase_management_id bigint,
                              "timestamp" timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.jokes OWNER TO postgres;

--
-- Name: jokes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.jokes ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.jokes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    );


--
-- Name: phrase_management; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.phrase_management (
                                          id bigint NOT NULL,
                                          user_id text NOT NULL,
                                          phrase text NOT NULL,
                                          author text,
                                          type text NOT NULL,
                                          status text DEFAULT 'PENDING'::text NOT NULL,
                                          date_submitted timestamp without time zone DEFAULT now() NOT NULL,
                                          date_modified_by_admin timestamp without time zone
);


ALTER TABLE public.phrase_management OWNER TO postgres;

--
-- Name: COLUMN phrase_management.status; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.phrase_management.status IS '"PENDING","ACCEPTED","REJECTED"';


--
-- Name: phrase_management_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.phrase_management_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.phrase_management_id_seq OWNER TO postgres;

--
-- Name: phrase_management_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.phrase_management_id_seq OWNED BY public.phrase_management.id;


--
-- Name: quotes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.quotes (
                               id bigint NOT NULL,
                               author text,
                               phrase text,
                               phrase_management_id bigint,
                               "timestamp" timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.quotes OWNER TO postgres;

--
-- Name: quotes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.quotes ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.quotes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    );


--
-- Name: request_counter; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.request_counter (
                                        api_key uuid NOT NULL,
                                        count bigint DEFAULT '1'::bigint NOT NULL,
                                        owner text NOT NULL
);


ALTER TABLE public.request_counter OWNER TO postgres;

--
-- Name: phrase_management id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.phrase_management ALTER COLUMN id SET DEFAULT nextval('public.phrase_management_id_seq'::regclass);


--
-- Name: jokes jokes_id_id1_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jokes
    ADD CONSTRAINT jokes_id_id1_key UNIQUE (id) INCLUDE (id);


--
-- Name: phrase_management phrase_management_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.phrase_management
    ADD CONSTRAINT phrase_management_pkey PRIMARY KEY (id);


--
-- Name: api_keys pk_api_keys; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.api_keys
    ADD CONSTRAINT pk_api_keys PRIMARY KEY (id);


--
-- Name: jokes pk_jokes; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jokes
    ADD CONSTRAINT pk_jokes PRIMARY KEY (id);


--
-- Name: quotes quotes_id_id1_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quotes
    ADD CONSTRAINT quotes_id_id1_key UNIQUE (id) INCLUDE (id);


--
-- Name: quotes quotes_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quotes
    ADD CONSTRAINT quotes_pk PRIMARY KEY (id);


--
-- Name: request_counter request_counter_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.request_counter
    ADD CONSTRAINT request_counter_pkey PRIMARY KEY (api_key);


--
-- Name: jokes jokes_phrasemanagement_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jokes
    ADD CONSTRAINT jokes_phrasemanagement_fk FOREIGN KEY (phrase_management_id) REFERENCES public.phrase_management(id) NOT VALID;


--
-- Name: quotes quotes_phrasemanagement_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quotes
    ADD CONSTRAINT quotes_phrasemanagement_fk FOREIGN KEY (phrase_management_id) REFERENCES public.phrase_management(id) NOT VALID;


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;


--
-- PostgreSQL database dump complete
--
