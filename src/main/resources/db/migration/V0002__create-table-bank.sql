CREATE TABLE bank (
   id           VARCHAR(255)                    NOT NULL,
   created_at   TIMESTAMP WITHOUT TIME ZONE,
   updated_at   TIMESTAMP WITHOUT TIME ZONE,
   number       VARCHAR(255)                    NOT NULL,
   name         VARCHAR(255)                    NOT NULL,

   CONSTRAINT pk_bank PRIMARY KEY (id)
);
