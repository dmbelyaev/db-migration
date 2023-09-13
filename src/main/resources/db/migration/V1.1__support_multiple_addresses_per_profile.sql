ALTER TABLE address
    ADD COLUMN IF NOT EXISTS profile_id VARCHAR(36);
ALTER TABLE address
    ADD COLUMN IF NOT EXISTS main BOOLEAN NOT NULL DEFAULT TRUE;

ALTER TABLE address
    ADD CONSTRAINT address_customer_profile_fkey
        FOREIGN KEY (profile_id) REFERENCES customer_profile (id);

CREATE INDEX IF NOT EXISTS address_profile_id_idx ON address (profile_id);
