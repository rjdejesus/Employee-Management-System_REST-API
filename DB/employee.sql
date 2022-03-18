--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2
-- Dumped by pg_dump version 14.2

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
-- Name: employee; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.employee (
    id bigint NOT NULL,
    email character varying(255),
    first_name character varying(255),
    hire_date date,
    last_name character varying(255),
    phone bigint
);


--
-- Name: employee_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.employee_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: user_accounts; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.user_accounts (
    id bigint NOT NULL,
    password character varying(255),
    role character varying(255),
    user_name character varying(255)
);


--
-- Name: user_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.user_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.employee (id, email, first_name, hire_date, last_name, phone) FROM stdin;
1	mike.hunt@gmail.com	Mike	2005-08-23	Hunt	12345678
2	ethan@gmail.com	Ethan	2004-04-02	Gold	98765432
3	lucas.brown@gmail.com	Lucas	2019-07-17	Brown	51212365
4	francine.hill@gmail.com	Francine	2021-12-01	Hill	12345678
5	john.doe@gmail.com	John	2015-12-08	Doe	12345678
6	dafo.george@gmail.com	George	2018-04-02	Dafo	12365678
7	william.green@gmail.com	William	2017-09-22	Green	88832199
8	alexa.jones@gmail.com	Alexa	2018-11-30	Jones	72312986
9	ezio.auditore@gmail.com	Ezio	2013-11-11	Auditore	81233190
20	robert.dejesus@gmail.com	Robert	2020-08-23	De Jesus	12345678
21	kylecruz@gmail.com	Kyle	2020-08-23	Cruz	12345678
22	karen.white@gmail.com	Karen	2017-08-23	White	65112407
\.


--
-- Data for Name: user_accounts; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.user_accounts (id, password, role, user_name) FROM stdin;
2	$2a$10$.hwGOQkkmFrYwkhUjYhRSO29JXfbeRJwv6oB96q1oAgoN.c2vYMOG	ROLE_USER	mike
4	$2a$10$MtGEeUdqkv7s4r5DAdhHqesqkslsoFzVH7OMKx4NsQAsSyd6vW9A2	ROLE_USER	john.doe
1	$2a$10$Yo41MmKPyCjfqKTAF1I1a.kt62ckhbiPgAYLwMkIOfft4Rd53z9Ya	ROLE_ADMIN	robert_admin
7	$2a$10$MAW8ghH0aonh6GqpIoDYX.CtxuT/1b7tSLjqHe.L44X1Scd9EoPEi	ROLE_USER	jack
6	$2a$10$B5GAV1k3WiIvCKiOI5MJre3yDQi/mnLB4hpoeetjNdPRHHjc.Ncne	ROLE_HR	karen_hr
5	$2a$10$C2nhI.XlD9P9O248OfOKTu3Iw..95r.h7OOZAS4N6ybGzboH7Xqai	ROLE_HR	julia
\.


--
-- Name: employee_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.employee_sequence', 22, true);


--
-- Name: user_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.user_sequence', 10, true);


--
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);


--
-- Name: user_accounts user_accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_accounts
    ADD CONSTRAINT user_accounts_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

