CREATE TABLE IF NOT EXISTS lord
(
    id bigint NOT NULL,
    name character varying(256) NOT NULL,
    age integer,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS planet
(
    id bigint NOT NULL,
    name character varying(256) NOT NULL,
    lord_id bigint,
    PRIMARY KEY (id),
    CONSTRAINT lord_id FOREIGN KEY (lord_id)
        REFERENCES lord (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
