DROP TABLE IF EXISTS member;

CREATE TABLE IF NOT EXISTS member
(
		id serial not null
	,	name character varying(20) not null
	,	primary key (id)
);

-- シーケンスの初期化
-- SELECT setval('member_id_seq', 1, false);
