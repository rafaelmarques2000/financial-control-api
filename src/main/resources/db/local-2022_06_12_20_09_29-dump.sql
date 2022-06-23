--
-- PostgreSQL database dump
--

-- Dumped from database version 12.11 (Debian 12.11-1.pgdg110+1)
-- Dumped by pg_dump version 14.3 (Ubuntu 14.3-0ubuntu0.22.04.1)

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
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


--
-- Name: account_type; Type: TYPE; Schema: public; Owner: root
--

CREATE TYPE public.account_type AS ENUM (
    'CORRENTE',
    'POUPANCA',
    'DINHEIRO',
    'CARTAO_CREDITO',
    'INVESTIMENTO'
);


ALTER TYPE public.account_type OWNER TO root;

--
-- Name: service_recurrence_type; Type: TYPE; Schema: public; Owner: root
--

CREATE TYPE public.service_recurrence_type AS ENUM (
    'MONTHLY',
    'YEARLY'
);


ALTER TYPE public.service_recurrence_type OWNER TO root;

--
-- Name: service_status; Type: TYPE; Schema: public; Owner: root
--

CREATE TYPE public.service_status AS ENUM (
    'ACTIVE',
    'INACTIVE'
);


ALTER TYPE public.service_status OWNER TO root;

--
-- Name: user_status; Type: TYPE; Schema: public; Owner: root
--

CREATE TYPE public.user_status AS ENUM (
    'ACTIVE',
    'BLOCKED'
);


ALTER TYPE public.user_status OWNER TO root;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: cx_account_transaction; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.cx_account_transaction (
    id uuid NOT NULL,
    description character varying(255) NOT NULL,
    date date NOT NULL,
    value integer NOT NULL,
    extra_description text,
    account_id uuid NOT NULL,
    transaction_type_id uuid NOT NULL,
    transaction_category_id uuid NOT NULL,
    created_at timestamp without time zone DEFAULT now() NOT NULL,
    updated_at timestamp without time zone DEFAULT now() NOT NULL,
    deleted_at timestamp without time zone,
    service_reference uuid
);


ALTER TABLE public.cx_account_transaction OWNER TO root;

--
-- Name: cx_accounts; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.cx_accounts (
    id uuid NOT NULL,
    description character varying(255) NOT NULL,
    initial_amount integer NOT NULL,
    type public.account_type,
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone DEFAULT now(),
    deleted_at timestamp without time zone
);


ALTER TABLE public.cx_accounts OWNER TO root;

--
-- Name: cx_service; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.cx_service (
    id uuid NOT NULL,
    description character varying(200) NOT NULL,
    value integer NOT NULL,
    recurrence_type public.service_recurrence_type NOT NULL,
    user_id uuid NOT NULL,
    account_id uuid NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    deleted_at timestamp without time zone,
    status public.service_status NOT NULL
);


ALTER TABLE public.cx_service OWNER TO root;

--
-- Name: cx_transaction_category; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.cx_transaction_category (
    id uuid NOT NULL,
    description character varying(120),
    transaction_type_id uuid NOT NULL
);


ALTER TABLE public.cx_transaction_category OWNER TO root;

--
-- Name: cx_transaction_type; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.cx_transaction_type (
    id uuid NOT NULL,
    description character varying(100) NOT NULL
);


ALTER TABLE public.cx_transaction_type OWNER TO root;

--
-- Name: cx_user; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.cx_user (
    id uuid NOT NULL,
    login character varying(200) NOT NULL,
    password character varying(300) NOT NULL,
    view_name character varying(80),
    created_at date DEFAULT CURRENT_TIMESTAMP,
    updated_at date DEFAULT CURRENT_TIMESTAMP,
    status public.user_status NOT NULL
);


ALTER TABLE public.cx_user OWNER TO root;

--
-- Name: cx_user_accounts; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.cx_user_accounts (
    user_id uuid NOT NULL,
    account_id uuid NOT NULL,
    owner boolean DEFAULT false NOT NULL
);


ALTER TABLE public.cx_user_accounts OWNER TO root;

--
-- Name: cx_user_accounts account_indepontent_share; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cx_user_accounts
    ADD CONSTRAINT account_indepontent_share PRIMARY KEY (user_id, account_id);


--
-- Name: cx_account_transaction cx_account_transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cx_account_transaction
    ADD CONSTRAINT cx_account_transaction_pkey PRIMARY KEY (id);


--
-- Name: cx_accounts cx_accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cx_accounts
    ADD CONSTRAINT cx_accounts_pkey PRIMARY KEY (id);


--
-- Name: cx_service cx_service_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cx_service
    ADD CONSTRAINT cx_service_pkey PRIMARY KEY (id);


--
-- Name: cx_transaction_category cx_transaction_category_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cx_transaction_category
    ADD CONSTRAINT cx_transaction_category_pkey PRIMARY KEY (id);


--
-- Name: cx_transaction_type cx_transaction_type_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cx_transaction_type
    ADD CONSTRAINT cx_transaction_type_pkey PRIMARY KEY (id);


--
-- Name: cx_user cx_user_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cx_user
    ADD CONSTRAINT cx_user_pkey PRIMARY KEY (id);


--
-- Name: cx_account_transaction cx_account_transaction_account_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cx_account_transaction
    ADD CONSTRAINT cx_account_transaction_account_id_fkey FOREIGN KEY (account_id) REFERENCES public.cx_accounts(id);


--
-- Name: cx_account_transaction cx_account_transaction_transaction_category_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cx_account_transaction
    ADD CONSTRAINT cx_account_transaction_transaction_category_id_fkey FOREIGN KEY (transaction_category_id) REFERENCES public.cx_transaction_category(id);


--
-- Name: cx_account_transaction cx_account_transaction_transaction_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cx_account_transaction
    ADD CONSTRAINT cx_account_transaction_transaction_type_id_fkey FOREIGN KEY (transaction_type_id) REFERENCES public.cx_transaction_type(id);


--
-- Name: cx_user_accounts cx_credential_user_accounts_account_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cx_user_accounts
    ADD CONSTRAINT cx_credential_user_accounts_account_id_fkey FOREIGN KEY (account_id) REFERENCES public.cx_accounts(id);


--
-- Name: cx_user_accounts cx_credential_user_accounts_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cx_user_accounts
    ADD CONSTRAINT cx_credential_user_accounts_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.cx_user(id);


--
-- Name: cx_service cx_service_account_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cx_service
    ADD CONSTRAINT cx_service_account_id_fkey FOREIGN KEY (account_id) REFERENCES public.cx_accounts(id);


--
-- Name: cx_service cx_service_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cx_service
    ADD CONSTRAINT cx_service_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.cx_user(id);


--
-- Name: cx_transaction_category cx_transaction_category_transaction_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.cx_transaction_category
    ADD CONSTRAINT cx_transaction_category_transaction_type_id_fkey FOREIGN KEY (transaction_type_id) REFERENCES public.cx_transaction_type(id);


--
-- PostgreSQL database dump complete
--

