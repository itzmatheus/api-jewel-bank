CREATE TABLE account (
   id               VARCHAR(255)                    NOT NULL,
   created_at       TIMESTAMP WITHOUT TIME ZONE,
   updated_at       TIMESTAMP WITHOUT TIME ZONE,
   fk_bank_user_id  VARCHAR(255),
   fk_bank_id       VARCHAR(255),
   fk_agency_id     VARCHAR(255),
   number           VARCHAR(8)                      NOT NULL,

   CONSTRAINT pk_account PRIMARY KEY (id)
);

ALTER TABLE account ADD CONSTRAINT FK_ACCOUNT_ON_FK_AGENCY FOREIGN KEY (fk_agency_id) REFERENCES agency (id);

ALTER TABLE account ADD CONSTRAINT FK_ACCOUNT_ON_FK_BANK FOREIGN KEY (fk_bank_id) REFERENCES bank (id);

ALTER TABLE account ADD CONSTRAINT FK_ACCOUNT_ON_FK_BANK_USER FOREIGN KEY (fk_bank_user_id) REFERENCES bank_user (id);