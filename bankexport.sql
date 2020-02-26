--------------------------------------------------------
--  File created - Wednesday-February-26-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence SEQ_ACCOUNT
--------------------------------------------------------

   CREATE SEQUENCE  "BANK_ADMIN"."SEQ_ACCOUNT"  MINVALUE 0 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 40 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_AUDIT
--------------------------------------------------------

   CREATE SEQUENCE  "BANK_ADMIN"."SEQ_AUDIT"  MINVALUE 0 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 60 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_TRANSFER
--------------------------------------------------------

   CREATE SEQUENCE  "BANK_ADMIN"."SEQ_TRANSFER"  MINVALUE 0 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 40 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_USER
--------------------------------------------------------

   CREATE SEQUENCE  "BANK_ADMIN"."SEQ_USER"  MINVALUE 0 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 40 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table ACCOUNTS
--------------------------------------------------------

  CREATE TABLE "BANK_ADMIN"."ACCOUNTS" 
   (	"ACCOUNT_ID" NUMBER(5,0), 
	"BALANCE" NUMBER(20,2), 
	"USER_ID" NUMBER(5,0), 
	"ACCOUNT_NAME" VARCHAR2(20 BYTE), 
	"IS_APPLICATION" NUMBER(1,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table AUDITS
--------------------------------------------------------

  CREATE TABLE "BANK_ADMIN"."AUDITS" 
   (	"TRANSACTION_ID" NUMBER, 
	"TYPE" VARCHAR2(20 BYTE), 
	"ACCOUNT_FROM" NUMBER, 
	"ACCOUNT_TO" NUMBER, 
	"AMOUNT" NUMBER, 
	"TRANSACTION_DATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table TRANSFERS
--------------------------------------------------------

  CREATE TABLE "BANK_ADMIN"."TRANSFERS" 
   (	"TRANSFER_ID" NUMBER(5,0), 
	"ACCOUNT_FROM_ID" NUMBER(5,0), 
	"ACCOUNT_TO_ID" NUMBER(5,0), 
	"AMOUNT" NUMBER(16,2), 
	"STATUS" VARCHAR2(20 BYTE), 
	"OWNER_ID" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table USERS
--------------------------------------------------------

  CREATE TABLE "BANK_ADMIN"."USERS" 
   (	"USER_ID" NUMBER(5,0), 
	"USERNAME" VARCHAR2(20 BYTE), 
	"PASSWORD" VARCHAR2(20 BYTE), 
	"USER_TYPE" NUMBER(1,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into BANK_ADMIN.ACCOUNTS
SET DEFINE OFF;
Insert into BANK_ADMIN.ACCOUNTS (ACCOUNT_ID,BALANCE,USER_ID,ACCOUNT_NAME,IS_APPLICATION) values (1,450,2,'checking',0);
Insert into BANK_ADMIN.ACCOUNTS (ACCOUNT_ID,BALANCE,USER_ID,ACCOUNT_NAME,IS_APPLICATION) values (2,2250.75,2,'saving',0);
Insert into BANK_ADMIN.ACCOUNTS (ACCOUNT_ID,BALANCE,USER_ID,ACCOUNT_NAME,IS_APPLICATION) values (3,1500,2,'investment',0);
Insert into BANK_ADMIN.ACCOUNTS (ACCOUNT_ID,BALANCE,USER_ID,ACCOUNT_NAME,IS_APPLICATION) values (4,900,2,'salary',1);
Insert into BANK_ADMIN.ACCOUNTS (ACCOUNT_ID,BALANCE,USER_ID,ACCOUNT_NAME,IS_APPLICATION) values (5,1000,7,'checking',0);
Insert into BANK_ADMIN.ACCOUNTS (ACCOUNT_ID,BALANCE,USER_ID,ACCOUNT_NAME,IS_APPLICATION) values (6,1510,7,'savings',0);
Insert into BANK_ADMIN.ACCOUNTS (ACCOUNT_ID,BALANCE,USER_ID,ACCOUNT_NAME,IS_APPLICATION) values (20,2024.25,20,'checking',0);
Insert into BANK_ADMIN.ACCOUNTS (ACCOUNT_ID,BALANCE,USER_ID,ACCOUNT_NAME,IS_APPLICATION) values (21,500,20,'savings',0);
REM INSERTING into BANK_ADMIN.AUDITS
SET DEFINE OFF;
Insert into BANK_ADMIN.AUDITS (TRANSACTION_ID,TYPE,ACCOUNT_FROM,ACCOUNT_TO,AMOUNT,TRANSACTION_DATE) values (14,'DEPOSIT',4,null,300,to_date('25-FEB-20','DD-MON-RR'));
Insert into BANK_ADMIN.AUDITS (TRANSACTION_ID,TYPE,ACCOUNT_FROM,ACCOUNT_TO,AMOUNT,TRANSACTION_DATE) values (8,'DEPOSIT',2,null,500,to_date('24-FEB-20','DD-MON-RR'));
Insert into BANK_ADMIN.AUDITS (TRANSACTION_ID,TYPE,ACCOUNT_FROM,ACCOUNT_TO,AMOUNT,TRANSACTION_DATE) values (9,'WITHDRAW',1,null,-50,to_date('24-FEB-20','DD-MON-RR'));
Insert into BANK_ADMIN.AUDITS (TRANSACTION_ID,TYPE,ACCOUNT_FROM,ACCOUNT_TO,AMOUNT,TRANSACTION_DATE) values (10,'WITHDRAW',2,null,-100,to_date('24-FEB-20','DD-MON-RR'));
Insert into BANK_ADMIN.AUDITS (TRANSACTION_ID,TYPE,ACCOUNT_FROM,ACCOUNT_TO,AMOUNT,TRANSACTION_DATE) values (11,'DEPOSIT',1,null,100,to_date('24-FEB-20','DD-MON-RR'));
Insert into BANK_ADMIN.AUDITS (TRANSACTION_ID,TYPE,ACCOUNT_FROM,ACCOUNT_TO,AMOUNT,TRANSACTION_DATE) values (12,'TRANSFER',2,1,100,to_date('24-FEB-20','DD-MON-RR'));
Insert into BANK_ADMIN.AUDITS (TRANSACTION_ID,TYPE,ACCOUNT_FROM,ACCOUNT_TO,AMOUNT,TRANSACTION_DATE) values (13,'DEPOSIT',1,null,10,to_date('25-FEB-20','DD-MON-RR'));
Insert into BANK_ADMIN.AUDITS (TRANSACTION_ID,TYPE,ACCOUNT_FROM,ACCOUNT_TO,AMOUNT,TRANSACTION_DATE) values (17,'DEPOSIT',1,null,5.5,to_date('25-FEB-20','DD-MON-RR'));
Insert into BANK_ADMIN.AUDITS (TRANSACTION_ID,TYPE,ACCOUNT_FROM,ACCOUNT_TO,AMOUNT,TRANSACTION_DATE) values (18,'WITHDRAW',1,null,-1,to_date('25-FEB-20','DD-MON-RR'));
Insert into BANK_ADMIN.AUDITS (TRANSACTION_ID,TYPE,ACCOUNT_FROM,ACCOUNT_TO,AMOUNT,TRANSACTION_DATE) values (19,'DEPOSIT',5,null,20,to_date('25-FEB-20','DD-MON-RR'));
Insert into BANK_ADMIN.AUDITS (TRANSACTION_ID,TYPE,ACCOUNT_FROM,ACCOUNT_TO,AMOUNT,TRANSACTION_DATE) values (20,'WITHDRAW',5,null,-10,to_date('25-FEB-20','DD-MON-RR'));
Insert into BANK_ADMIN.AUDITS (TRANSACTION_ID,TYPE,ACCOUNT_FROM,ACCOUNT_TO,AMOUNT,TRANSACTION_DATE) values (21,'WITHDRAW',6,null,-490,to_date('25-FEB-20','DD-MON-RR'));
Insert into BANK_ADMIN.AUDITS (TRANSACTION_ID,TYPE,ACCOUNT_FROM,ACCOUNT_TO,AMOUNT,TRANSACTION_DATE) values (22,'DEPOSIT',5,null,490,to_date('25-FEB-20','DD-MON-RR'));
Insert into BANK_ADMIN.AUDITS (TRANSACTION_ID,TYPE,ACCOUNT_FROM,ACCOUNT_TO,AMOUNT,TRANSACTION_DATE) values (23,'TRANSFER',6,5,490,to_date('25-FEB-20','DD-MON-RR'));
Insert into BANK_ADMIN.AUDITS (TRANSACTION_ID,TYPE,ACCOUNT_FROM,ACCOUNT_TO,AMOUNT,TRANSACTION_DATE) values (15,'DEPOSIT',4,null,200,to_date('25-FEB-20','DD-MON-RR'));
Insert into BANK_ADMIN.AUDITS (TRANSACTION_ID,TYPE,ACCOUNT_FROM,ACCOUNT_TO,AMOUNT,TRANSACTION_DATE) values (16,'DEPOSIT',1,null,20,to_date('25-FEB-20','DD-MON-RR'));
Insert into BANK_ADMIN.AUDITS (TRANSACTION_ID,TYPE,ACCOUNT_FROM,ACCOUNT_TO,AMOUNT,TRANSACTION_DATE) values (40,'DEPOSIT',20,null,50,to_date('26-FEB-20','DD-MON-RR'));
Insert into BANK_ADMIN.AUDITS (TRANSACTION_ID,TYPE,ACCOUNT_FROM,ACCOUNT_TO,AMOUNT,TRANSACTION_DATE) values (41,'WITHDRAW',20,null,-25.75,to_date('26-FEB-20','DD-MON-RR'));
Insert into BANK_ADMIN.AUDITS (TRANSACTION_ID,TYPE,ACCOUNT_FROM,ACCOUNT_TO,AMOUNT,TRANSACTION_DATE) values (42,'WITHDRAW',21,null,-1500,to_date('26-FEB-20','DD-MON-RR'));
Insert into BANK_ADMIN.AUDITS (TRANSACTION_ID,TYPE,ACCOUNT_FROM,ACCOUNT_TO,AMOUNT,TRANSACTION_DATE) values (43,'DEPOSIT',20,null,1500,to_date('26-FEB-20','DD-MON-RR'));
Insert into BANK_ADMIN.AUDITS (TRANSACTION_ID,TYPE,ACCOUNT_FROM,ACCOUNT_TO,AMOUNT,TRANSACTION_DATE) values (44,'TRANSFER',21,20,1500,to_date('26-FEB-20','DD-MON-RR'));
REM INSERTING into BANK_ADMIN.TRANSFERS
SET DEFINE OFF;
REM INSERTING into BANK_ADMIN.USERS
SET DEFINE OFF;
Insert into BANK_ADMIN.USERS (USER_ID,USERNAME,PASSWORD,USER_TYPE) values (6,'banking','123',1);
Insert into BANK_ADMIN.USERS (USER_ID,USERNAME,PASSWORD,USER_TYPE) values (10,'tester','testing',1);
Insert into BANK_ADMIN.USERS (USER_ID,USERNAME,PASSWORD,USER_TYPE) values (1,'george','password',2);
Insert into BANK_ADMIN.USERS (USER_ID,USERNAME,PASSWORD,USER_TYPE) values (2,'firstCustomer','1234',1);
Insert into BANK_ADMIN.USERS (USER_ID,USERNAME,PASSWORD,USER_TYPE) values (3,'firstEmployee','emp',2);
Insert into BANK_ADMIN.USERS (USER_ID,USERNAME,PASSWORD,USER_TYPE) values (9,'tester','testing',1);
Insert into BANK_ADMIN.USERS (USER_ID,USERNAME,PASSWORD,USER_TYPE) values (11,'tester','testing',1);
Insert into BANK_ADMIN.USERS (USER_ID,USERNAME,PASSWORD,USER_TYPE) values (13,'tester','testing',1);
Insert into BANK_ADMIN.USERS (USER_ID,USERNAME,PASSWORD,USER_TYPE) values (14,'tester','testing',1);
Insert into BANK_ADMIN.USERS (USER_ID,USERNAME,PASSWORD,USER_TYPE) values (15,'firstTimeBanker','new',1);
Insert into BANK_ADMIN.USERS (USER_ID,USERNAME,PASSWORD,USER_TYPE) values (12,'tester','testing',1);
Insert into BANK_ADMIN.USERS (USER_ID,USERNAME,PASSWORD,USER_TYPE) values (7,'ryan','ryan',1);
Insert into BANK_ADMIN.USERS (USER_ID,USERNAME,PASSWORD,USER_TYPE) values (20,'rob','demo',1);
--------------------------------------------------------
--  DDL for Index AUDITS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BANK_ADMIN"."AUDITS_PK" ON "BANK_ADMIN"."AUDITS" ("TRANSACTION_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index pk_account_id
--------------------------------------------------------

  CREATE UNIQUE INDEX "BANK_ADMIN"."pk_account_id" ON "BANK_ADMIN"."ACCOUNTS" ("ACCOUNT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index pk_transfer_id
--------------------------------------------------------

  CREATE UNIQUE INDEX "BANK_ADMIN"."pk_transfer_id" ON "BANK_ADMIN"."TRANSFERS" ("TRANSFER_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index pk_user_id
--------------------------------------------------------

  CREATE UNIQUE INDEX "BANK_ADMIN"."pk_user_id" ON "BANK_ADMIN"."USERS" ("USER_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger AUDIT_ACTION
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "BANK_ADMIN"."AUDIT_ACTION" 
BEFORE UPDATE ON accounts
FOR EACH ROW
DECLARE
    l_type VARCHAR2(20);
    l_bal_change NUMBER;
BEGIN
    IF updating('balance') THEN
        l_bal_change := :NEW.balance - :OLD.balance;
        IF l_bal_change > 0 THEN 
            l_type := 'DEPOSIT';
        ELSE 
            l_type := 'WITHDRAW';
        END IF;
        INSERT INTO audits (transaction_id, type, account_from, amount, transaction_date)
        VALUES (seq_audit.NEXTVAL, l_type, :OLD.account_id, l_bal_change, SYSDATE);
    END IF;
END;
/
ALTER TRIGGER "BANK_ADMIN"."AUDIT_ACTION" ENABLE;
--------------------------------------------------------
--  DDL for Trigger AUDIT_TRANSFER
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "BANK_ADMIN"."AUDIT_TRANSFER" 
BEFORE DELETE ON transfers
FOR EACH ROW
BEGIN
    INSERT INTO audits (transaction_id, type, account_from, account_to, amount, transaction_date) 
    VALUES (seq_audit.NEXTVAL, 'TRANSFER', :OLD.account_from_id, :OLD.account_to_id, :OLD.amount, SYSDATE);
END;
/
ALTER TRIGGER "BANK_ADMIN"."AUDIT_TRANSFER" ENABLE;
--------------------------------------------------------
--  DDL for Procedure ACCOUNT_INSERT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "BANK_ADMIN"."ACCOUNT_INSERT" (
    in_balance IN NUMBER, 
    in_account_name IN VARCHAR2,
    in_owner IN NUMBER, 
    in_application IN NUMBER)
IS
BEGIN
    INSERT INTO accounts (account_id, balance, account_name, user_id, is_application)
    VALUES (seq_account.NEXTVAL, in_balance, in_account_name, in_owner, in_application);
    COMMIT;
END;

/
--------------------------------------------------------
--  DDL for Procedure ACCOUNT_UPDATE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "BANK_ADMIN"."ACCOUNT_UPDATE" (
    in_account_id IN NUMBER, 
    in_amount IN NUMBER)
IS
BEGIN
    UPDATE accounts SET balance=in_amount 
    WHERE account_id=in_account_id;
END;

/
--------------------------------------------------------
--  DDL for Procedure DISABLE_TRIGGERS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "BANK_ADMIN"."DISABLE_TRIGGERS" 
AS
BEGIN
    EXECUTE IMMEDIATE 'ALTER TRIGGER audit_action DISABLE';
    EXECUTE IMMEDIATE 'ALTER TRIGGER audit_transfer DISABLE';
END;

/
--------------------------------------------------------
--  DDL for Procedure ENAABLE_TRIGGERS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "BANK_ADMIN"."ENAABLE_TRIGGERS" 
AS
BEGIN
    EXECUTE IMMEDIATE 'ALTER TRIGGER audit_action ENABLE';
    EXECUTE IMMEDIATE 'ALTER TRIGGER audit_transfer ENABLE';
END;

/
--------------------------------------------------------
--  DDL for Procedure PROCESS_TRANSFER
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "BANK_ADMIN"."PROCESS_TRANSFER" (
    in_transfer_id IN NUMBER, 
    in_verdict IN NUMBER)
IS
    l_transfer_amount NUMBER;
    l_account_from_id NUMBER;
    l_account_to_id NUMBER;
    l_from_balance NUMBER;
    l_to_balance NUMBER;
    l_new_from_bal NUMBER;
    l_new_to_bal NUMBER;
BEGIN
    CASE in_verdict
    WHEN 0 THEN
        -- transfer is denied. do nothing.
        UPDATE transfers SET status='denied' WHERE transfer_id=in_transfer_id;
    WHEN 1 THEN
        -- transfer is accepted, process withdraw and deposit
        SELECT amount, account_from_id, account_to_id 
        INTO l_transfer_amount, l_account_from_id, l_account_to_id
        FROM transfers WHERE transfer_id=in_transfer_id;
        SELECT balance INTO l_from_balance FROM accounts WHERE account_id=l_account_from_id;
        SELECT balance INTO l_to_balance FROM accounts WHERE account_id=l_account_to_id;
        l_new_from_bal := l_from_balance - l_transfer_amount;
        l_new_to_bal := l_to_balance + l_transfer_amount;
        account_update(l_account_from_id, l_new_from_bal);
        account_update(l_account_to_id, l_new_to_bal);
        DELETE FROM transfers WHERE transfer_id=in_transfer_id;
    END CASE;
END;

/
--------------------------------------------------------
--  DDL for Procedure TRANSFER_INSERT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "BANK_ADMIN"."TRANSFER_INSERT" (
    in_account_from IN NUMBER, 
    in_account_to IN NUMBER,
    in_amount IN NUMBER,
    in_owner IN NUMBER)
IS
BEGIN
    INSERT INTO transfers (transfer_id, account_from_id, account_to_id, amount, status, owner_id)
    VALUES (seq_transfer.NEXTVAL, in_account_from, in_account_to, in_amount, 'PENDING', in_owner);
END;

/
--------------------------------------------------------
--  DDL for Procedure USER_INSERT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "BANK_ADMIN"."USER_INSERT" (
    in_username IN VARCHAR2, 
    in_password IN VARCHAR2,
    in_type IN NUMBER)
IS
BEGIN
    INSERT INTO users (user_id, username, password, user_type)
    VALUES (seq_user.NEXTVAL, in_username, in_password, in_type);
    COMMIT;
END;

/
--------------------------------------------------------
--  Constraints for Table ACCOUNTS
--------------------------------------------------------

  ALTER TABLE "BANK_ADMIN"."ACCOUNTS" MODIFY ("ACCOUNT_ID" NOT NULL ENABLE);
 
  ALTER TABLE "BANK_ADMIN"."ACCOUNTS" MODIFY ("USER_ID" NOT NULL ENABLE);
 
  ALTER TABLE "BANK_ADMIN"."ACCOUNTS" MODIFY ("IS_APPLICATION" NOT NULL ENABLE);
 
  ALTER TABLE "BANK_ADMIN"."ACCOUNTS" ADD CONSTRAINT "pk_account_id" PRIMARY KEY ("ACCOUNT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table AUDITS
--------------------------------------------------------

  ALTER TABLE "BANK_ADMIN"."AUDITS" ADD CONSTRAINT "AUDITS_PK" PRIMARY KEY ("TRANSACTION_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "BANK_ADMIN"."AUDITS" MODIFY ("TRANSACTION_ID" NOT NULL ENABLE);
 
  ALTER TABLE "BANK_ADMIN"."AUDITS" MODIFY ("TYPE" NOT NULL ENABLE);
 
  ALTER TABLE "BANK_ADMIN"."AUDITS" MODIFY ("ACCOUNT_FROM" NOT NULL ENABLE);
 
  ALTER TABLE "BANK_ADMIN"."AUDITS" MODIFY ("AMOUNT" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table TRANSFERS
--------------------------------------------------------

  ALTER TABLE "BANK_ADMIN"."TRANSFERS" MODIFY ("TRANSFER_ID" NOT NULL ENABLE);
 
  ALTER TABLE "BANK_ADMIN"."TRANSFERS" MODIFY ("ACCOUNT_FROM_ID" NOT NULL ENABLE);
 
  ALTER TABLE "BANK_ADMIN"."TRANSFERS" MODIFY ("ACCOUNT_TO_ID" NOT NULL ENABLE);
 
  ALTER TABLE "BANK_ADMIN"."TRANSFERS" MODIFY ("AMOUNT" NOT NULL ENABLE);
 
  ALTER TABLE "BANK_ADMIN"."TRANSFERS" ADD CONSTRAINT "not_self_transfer" CHECK (account_from_id <> account_to_id) ENABLE;
 
  ALTER TABLE "BANK_ADMIN"."TRANSFERS" ADD CONSTRAINT "pk_transfer_id" PRIMARY KEY ("TRANSFER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table USERS
--------------------------------------------------------

  ALTER TABLE "BANK_ADMIN"."USERS" MODIFY ("USER_ID" NOT NULL ENABLE);
 
  ALTER TABLE "BANK_ADMIN"."USERS" MODIFY ("USER_TYPE" NOT NULL ENABLE);
 
  ALTER TABLE "BANK_ADMIN"."USERS" ADD CONSTRAINT "USERS_UK1" UNIQUE ("USERNAME") DISABLE;
 
  ALTER TABLE "BANK_ADMIN"."USERS" ADD CONSTRAINT "pk_user_id" PRIMARY KEY ("USER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ACCOUNTS
--------------------------------------------------------

  ALTER TABLE "BANK_ADMIN"."ACCOUNTS" ADD CONSTRAINT "fk_owner" FOREIGN KEY ("USER_ID")
	  REFERENCES "BANK_ADMIN"."USERS" ("USER_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table AUDITS
--------------------------------------------------------

  ALTER TABLE "BANK_ADMIN"."AUDITS" ADD CONSTRAINT "AUDITS_FK1" FOREIGN KEY ("ACCOUNT_FROM")
	  REFERENCES "BANK_ADMIN"."ACCOUNTS" ("ACCOUNT_ID") ENABLE;
 
  ALTER TABLE "BANK_ADMIN"."AUDITS" ADD CONSTRAINT "AUDITS_FK2" FOREIGN KEY ("ACCOUNT_TO")
	  REFERENCES "BANK_ADMIN"."ACCOUNTS" ("ACCOUNT_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table TRANSFERS
--------------------------------------------------------

  ALTER TABLE "BANK_ADMIN"."TRANSFERS" ADD CONSTRAINT "FK_OWNER" FOREIGN KEY ("OWNER_ID")
	  REFERENCES "BANK_ADMIN"."USERS" ("USER_ID") ENABLE;
 
  ALTER TABLE "BANK_ADMIN"."TRANSFERS" ADD CONSTRAINT "fk_from" FOREIGN KEY ("ACCOUNT_FROM_ID")
	  REFERENCES "BANK_ADMIN"."ACCOUNTS" ("ACCOUNT_ID") ENABLE;
 
  ALTER TABLE "BANK_ADMIN"."TRANSFERS" ADD CONSTRAINT "fk_to" FOREIGN KEY ("ACCOUNT_TO_ID")
	  REFERENCES "BANK_ADMIN"."ACCOUNTS" ("ACCOUNT_ID") ENABLE;
