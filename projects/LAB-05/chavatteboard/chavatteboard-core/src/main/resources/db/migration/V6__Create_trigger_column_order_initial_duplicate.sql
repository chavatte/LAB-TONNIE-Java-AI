CREATE TRIGGER column_order_initial_duplicate
BEFORE INSERT ON "column"
FOR EACH ROW
WHEN NEW.type = 'INITIAL'
AND (SELECT COUNT(*) FROM "column" WHERE board_id = NEW.board_id AND type = 'INITIAL') > 0
BEGIN
SELECT RAISE(ABORT, 'Já existe uma coluna do tipo INITIAL neste board.');
END;