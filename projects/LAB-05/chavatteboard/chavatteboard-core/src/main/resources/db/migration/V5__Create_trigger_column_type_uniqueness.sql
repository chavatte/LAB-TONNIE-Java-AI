CREATE TRIGGER column_type_uniqueness
BEFORE INSERT ON "column"
FOR EACH ROW
WHEN
NEW.type IN ('INITIAL', 'FINAL', 'CANCELED')
AND (SELECT COUNT(*) FROM "column" WHERE board_id = NEW.board_id AND type = NEW.type) > 0
BEGIN
SELECT RAISE(ABORT, 'Já existe uma coluna do mesmo tipo nesse board.');
END;