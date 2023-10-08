CREATE TABLE users
(
    id          BIGSERIAL PRIMARY KEY,
    email       VARCHAR(64) NOT NULL UNIQUE,
    password    VARCHAR(64) NOT NULL,
    is_verified BOOLEAN     NOT NULL,
    created_on  TIMESTAMP   NOT NULL,
    updated_on  TIMESTAMP   NOT NULL
);

CREATE TABLE verification_codes
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT      NOT NULL,
    code       VARCHAR(64) NOT NULL,
    created_on TIMESTAMP   NOT NULL,
    CONSTRAINT verification_code_user_fkey FOREIGN KEY (user_id) REFERENCES users (id)
);