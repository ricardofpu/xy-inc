INSERT INTO poi (id, "name", coordinate_x, coordinate_y)
    VALUES (md5(random()::text || clock_timestamp()::text)::uuid, 'Lanchonete', 27, 12);
    
INSERT INTO poi (id ,"name", coordinate_x, coordinate_y)
    VALUES (md5(random()::text || clock_timestamp()::text)::uuid, 'Posto', 31, 18);
    
INSERT INTO poi (id, "name", coordinate_x, coordinate_y)
    VALUES (md5(random()::text || clock_timestamp()::text)::uuid, 'Joalheria', 15, 12);

INSERT INTO poi (id, "name", coordinate_x, coordinate_y)
    VALUES (md5(random()::text || clock_timestamp()::text)::uuid, 'Floricultura', 19, 21);
    
INSERT INTO poi (id, "name", coordinate_x, coordinate_y)
    VALUES (md5(random()::text || clock_timestamp()::text)::uuid, 'Pub', 12, 8);
    
INSERT INTO poi (id, "name", coordinate_x, coordinate_y)
    VALUES (md5(random()::text || clock_timestamp()::text)::uuid, 'Supermercado', 23, 6);
    
INSERT INTO poi (id, "name", coordinate_x, coordinate_y)
    VALUES (md5(random()::text || clock_timestamp()::text)::uuid, 'Churrascaria', 28, 2);