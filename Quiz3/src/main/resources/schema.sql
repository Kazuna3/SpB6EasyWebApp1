DROP TABLE IF EXISTS quiz;

CREATE TABLE IF NOT EXISTS quiz
(
		id serial not null
	,	question text not null
	,	answer boolean not null
	,	author character varying(20) not null
	,	primary key (id)
);

-- シーケンスの初期化
-- SELECT setval('quiz_id_seq', 1, false);
