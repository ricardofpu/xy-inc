DROP TABLE IF EXISTS poi;

CREATE TABLE poi
(
  id text NOT NULL,
  coordenadax integer NOT NULL CHECK (coordenadax >= 0),
  coordenaday integer NOT NULL CHECK (coordenaday >= 0),
  name character varying(255) NOT NULL,
  CONSTRAINT poi_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE poi
  OWNER TO postgres;