-- This file will run automatically when Postgres starts
CREATE TABLE IF NOT EXISTS dummy (
    id SERIAL PRIMARY KEY
);

INSERT INTO inventory(product_id, stock) VALUES ('p100', 20);
INSERT INTO inventory(product_id, stock) VALUES ('p200', 15);
