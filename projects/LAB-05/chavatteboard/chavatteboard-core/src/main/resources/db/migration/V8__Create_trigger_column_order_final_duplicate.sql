CREATE TRIGGER column_order_final_duplicate
BEFORE INSERT ON "column"
FOR EACH ROW
WHEN NEW.type = 'FINAL'
AND (SELECT COUNT(*) FROM "column" WHERE board_id = NEW.board_id AND type = 'FINAL') > 0
BEGIN
SELECT RAISE(ABORT, 'Já existe uma coluna do tipo FINAL neste board.');
END;