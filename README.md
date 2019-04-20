This commands should be run in Oracle DB.


CREATE TABLE Characters (
   id         NUMBER PRIMARY KEY,
   name       VARCHAR(100),
   heroclass      VARCHAR(30),
   race     VARCHAR(40),
   exlevel      NUMBER,
   hp        NUMBER
);

CREATE SEQUENCE characters_sequence;

CREATE OR REPLACE TRIGGER characters_on_insert
  BEFORE INSERT ON characters
  FOR EACH ROW
BEGIN
  SELECT characters_sequence.nextval
  INTO :new.id
  FROM dual;
END;

