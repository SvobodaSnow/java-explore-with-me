DROP TABLE If EXISTS endpoint_hit;

CREATE TABLE IF NOT EXISTS endpoint_hit (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    app VARCHAR(255) NOT NULL,
    uri VARCHAR(128) NOT NULL,
    ip VARCHAR(16) NOT NULL,
    created_on TIMESTAMP NOT NULL
);