CREATE TABLE IF NOT EXISTS lord_test
(
    id serial NOT NULL,
    name character varying(256) NOT NULL,
    age integer,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS planet_test
(
    id serial NOT NULL,
    name character varying(256) NOT NULL,
    lord_id serial,
    PRIMARY KEY (id),
    CONSTRAINT lord_id FOREIGN KEY (lord_id)
        REFERENCES lord (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
