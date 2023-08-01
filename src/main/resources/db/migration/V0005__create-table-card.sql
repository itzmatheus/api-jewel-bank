CREATE TABLE card (
   id               VARCHAR(255)                    NOT NULL,
   created_at       TIMESTAMP WITHOUT TIME ZONE,
   updated_at       TIMESTAMP WITHOUT TIME ZONE,
   name             VARCHAR(255)                    NOT NULL,
   number           VARCHAR(255)                    NOT NULL,
   expire_date      VARCHAR(6)                      NOT NULL,
   secure_number    VARCHAR(4)                      NOT NULL,
   flag             VARCHAR(255)                    NOT NULL,
   type             VARCHAR(255)                    NOT NULL,
   "limit"          DOUBLE PRECISION                NOT NULL,
   contactless      BOOLEAN                         NOT NULL,
   online_payment   BOOLEAN                         NOT NULL,
   atm_withdraws    BOOLEAN                         NOT NULL,
   fk_account_id    VARCHAR(255),

   CONSTRAINT pk_card PRIMARY KEY (id)
);

ALTER TABLE card ADD CONSTRAINT FK_CARD_ON_FK_ACCOUNT FOREIGN KEY (fk_account_id) REFERENCES account (id);