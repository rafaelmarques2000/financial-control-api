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
-- Data for Name: cx_account_transaction; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.cx_account_transaction (id, description, date, value, extra_description, account_id, transaction_type_id, transaction_category_id, created_at, updated_at, deleted_at, service_reference) FROM stdin;
a152c013-3978-495a-b414-3bfd692a74c0	netflix updated	2022-06-01	8000	Lançado automaticamente pelo sistema	022cec48-5c7f-4e3e-90a4-3a90ccd822dc	b607761c-7c57-4b0e-804b-dfccea1a2a95	dccf7735-b74e-46ef-ac01-a30a5ed4f3d5	2022-06-08 00:37:01.029872	2022-06-08 00:37:01.029895	\N	981c1f4d-456f-40f0-8adf-c04aed14df67
9f070092-fdac-46d2-90d7-c899784319e9	netflix	2022-06-01	5990	Lançado automaticamente pelo sistema	022cec48-5c7f-4e3e-90a4-3a90ccd822dc	b607761c-7c57-4b0e-804b-dfccea1a2a95	dccf7735-b74e-46ef-ac01-a30a5ed4f3d5	2022-06-08 00:37:01.032244	2022-06-08 00:37:01.032254	\N	5c2073c9-9ede-45b0-a6a8-0f392a7ac09b
\.


--
-- Data for Name: cx_accounts; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.cx_accounts (id, description, initial_amount, type, created_at, updated_at, deleted_at) FROM stdin;
20e3c88d-ddf9-4768-9b89-0a22dfb9669c	CONTA A VISTA	50000	DINHEIRO	2022-06-02 00:26:28.284081	2022-06-02 00:26:28.284105	\N
00f6187a-c45f-4dcd-a973-f2462d31d08d	CONTA A VISTA 500	5000	DINHEIRO	2022-06-02 00:00:33.564195	2022-06-02 14:56:50.08163	\N
022cec48-5c7f-4e3e-90a4-3a90ccd822dc	CONTA A VISTA 500	5000	DINHEIRO	2022-06-02 00:10:16.799166	2022-06-02 15:17:39.683619	\N
92969c39-3557-47d3-a73c-b803f98bb301		50000	DINHEIRO	2022-06-02 23:11:11.962858	2022-06-02 23:11:11.962881	\N
a97c203e-ad17-4f58-a5fa-d5762995d5b0	CONTA A VISTA	50000	DINHEIRO	2022-06-02 23:12:02.988917	2022-06-02 23:12:02.988945	\N
28c5f5ec-25db-4a8e-9f2f-c1384cd36367	CONTA A VISTA	50000	CARTAO_CREDITO	2022-06-02 23:20:26.479098	2022-06-02 23:20:26.47912	\N
\.


--
-- Data for Name: cx_service; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.cx_service (id, description, value, recurrence_type, user_id, account_id, created_at, updated_at, deleted_at, status) FROM stdin;
5c2073c9-9ede-45b0-a6a8-0f392a7ac09b	netflix	5990	MONTHLY	93fe8eca-de1a-11ec-9d64-0242ac120002	022cec48-5c7f-4e3e-90a4-3a90ccd822dc	2022-06-07 22:43:32.474034	2022-06-07 22:43:32.474046	\N	ACTIVE
981c1f4d-456f-40f0-8adf-c04aed14df67	netflix updated	8000	MONTHLY	93fe8eca-de1a-11ec-9d64-0242ac120002	022cec48-5c7f-4e3e-90a4-3a90ccd822dc	2022-06-07 22:29:14.781991	2022-06-07 22:44:18.853047	\N	ACTIVE
\.


--
-- Data for Name: cx_transaction_category; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.cx_transaction_category (id, description, transaction_type_id) FROM stdin;
fdd2af04-498e-437a-a71b-e5a178b5f9b3	Saldo Anterior	1498ad9e-f42e-4070-a08a-37b220699228
24963a06-1c98-46ae-895a-5e34c5dbc556	Salário líquido	1498ad9e-f42e-4070-a08a-37b220699228
a797af55-6929-4498-8011-666648f69c0e	Venda feita	1498ad9e-f42e-4070-a08a-37b220699228
4676d5e2-5007-44e2-8c5e-7ea430d4c803	Serviço prestado	1498ad9e-f42e-4070-a08a-37b220699228
0b903045-9c24-43c8-bdd4-c8d952b85384	Reembolso	1498ad9e-f42e-4070-a08a-37b220699228
0d481e71-a99d-42c0-9744-15ae38ed4da8	Saldo Anterior	b607761c-7c57-4b0e-804b-dfccea1a2a95
9e2ce5f8-2762-459b-b3fc-5e108e81a2df	Alimentação e refeição	b607761c-7c57-4b0e-804b-dfccea1a2a95
12aad5b8-3db6-4fdb-831f-062a7a5687b5	Saúde	b607761c-7c57-4b0e-804b-dfccea1a2a95
8bc56d97-1d2b-413d-94e5-01202da3883e	Contas da casa	b607761c-7c57-4b0e-804b-dfccea1a2a95
2073e2da-9302-4bee-890f-7a1a0e6f68bb	Compras para a casa	b607761c-7c57-4b0e-804b-dfccea1a2a95
989e2a97-fc3e-4660-90a4-18813690e127	Internet	b607761c-7c57-4b0e-804b-dfccea1a2a95
dd0e77de-dbfd-45b6-9bce-bb03a11b822f	Educação	b607761c-7c57-4b0e-804b-dfccea1a2a95
3f0f252d-0d7d-46e0-a2c7-504c26efba52	Vestuário e acessórios	b607761c-7c57-4b0e-804b-dfccea1a2a95
ffd2b6ab-de6d-4eb7-9e1e-e4c45570e5be	Aparelhos e acessórios tecnológicos	b607761c-7c57-4b0e-804b-dfccea1a2a95
dccf7735-b74e-46ef-ac01-a30a5ed4f3d5	Assinaturas e streamings	b607761c-7c57-4b0e-804b-dfccea1a2a95
be49b1d7-1dc5-47db-99a9-c3e0f068fee0	Empréstimos, juros, multas e taxas	b607761c-7c57-4b0e-804b-dfccea1a2a95
\.


--
-- Data for Name: cx_transaction_type; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.cx_transaction_type (id, description) FROM stdin;
1498ad9e-f42e-4070-a08a-37b220699228	RECEITA
b607761c-7c57-4b0e-804b-dfccea1a2a95	DESPESA
\.


--
-- Data for Name: cx_user; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.cx_user (id, login, password, view_name, created_at, updated_at, status) FROM stdin;
93fe8eca-de1a-11ec-9d64-0242ac120002	steff	123456	Steffani aurerilo	2022-05-27	2022-05-27	ACTIVE
08beb51b-e65a-4867-b58f-5889b93962ae	teste	123456	Teste	2022-05-29	2022-05-29	ACTIVE
84cf0158-daf2-11ec-9d64-0242ac120002	rpaixao	$2a$10$gnyDqPOK1ta5es2knNgvQe/1Ph9SZF6fTsZXxJjCcfFSXR8Dmn2LO	Rafael Marques	2022-05-23	2022-05-23	ACTIVE
\.


--
-- Data for Name: cx_user_accounts; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.cx_user_accounts (user_id, account_id, owner) FROM stdin;
84cf0158-daf2-11ec-9d64-0242ac120002	00f6187a-c45f-4dcd-a973-f2462d31d08d	t
93fe8eca-de1a-11ec-9d64-0242ac120002	00f6187a-c45f-4dcd-a973-f2462d31d08d	f
93fe8eca-de1a-11ec-9d64-0242ac120002	022cec48-5c7f-4e3e-90a4-3a90ccd822dc	t
84cf0158-daf2-11ec-9d64-0242ac120002	022cec48-5c7f-4e3e-90a4-3a90ccd822dc	f
93fe8eca-de1a-11ec-9d64-0242ac120002	20e3c88d-ddf9-4768-9b89-0a22dfb9669c	t
93fe8eca-de1a-11ec-9d64-0242ac120002	92969c39-3557-47d3-a73c-b803f98bb301	t
93fe8eca-de1a-11ec-9d64-0242ac120002	a97c203e-ad17-4f58-a5fa-d5762995d5b0	t
93fe8eca-de1a-11ec-9d64-0242ac120002	28c5f5ec-25db-4a8e-9f2f-c1384cd36367	t
\.


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

