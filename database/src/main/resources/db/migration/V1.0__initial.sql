CREATE TABLE customer_profile (
    id          VARCHAR(36) NOT NULL,
    first_name  VARCHAR(255) NULL,
    last_name   VARCHAR(255) NULL,
    email       VARCHAR(255) NOT NULL,
    address_id  VARCHAR(36),
    PRIMARY KEY(id)
);

CREATE TABLE address (
    id          VARCHAR(36) NOT NULL,
    street      VARCHAR(255) NOT NULL,
    city        VARCHAR(255) NOT NULL,
    zip_code    VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

ALTER TABLE customer_profile
    ADD CONSTRAINT customer_profile_address_fkey
    FOREIGN KEY (address_id) REFERENCES address (id);
CREATE INDEX customer_profile_address_id_idx ON customer_profile (address_id);
