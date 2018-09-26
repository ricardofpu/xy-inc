CREATE TABLE IF NOT EXISTS poi
(
  id text NOT NULL,
  name character varying(255) NOT NULL,
  coordinate_x integer NOT NULL CHECK (coordinate_x >= 0),
  coordinate_y integer NOT NULL CHECK (coordinate_y >= 0),
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  CONSTRAINT pk PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE poi
  OWNER TO postgres;