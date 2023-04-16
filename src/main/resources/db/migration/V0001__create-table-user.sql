CREATE TABLE "user" (
   id           VARCHAR(255)                    NOT NULL,
   created_at   TIMESTAMP WITHOUT TIME ZONE,
   updated_at   TIMESTAMP WITHOUT TIME ZONE,
   email        VARCHAR(255)                    NOT NULL,
   password     VARCHAR(255)                    NOT NULL,
   name         VARCHAR(255)                    NOT NULL,
   phone        VARCHAR(20),
   avatar_url   VARCHAR(255),
   cpf          VARCHAR(14)                     NOT NULL,

   CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE "user" ADD CONSTRAINT uc_user_cpf   UNIQUE (cpf);
ALTER TABLE "user" ADD CONSTRAINT uc_user_email UNIQUE (email);