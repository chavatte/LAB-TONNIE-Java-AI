CREATE TRIGGER column_order_canceled_duplicate
BEFORE INSERT ON "column"
FOR EACH ROW
WHEN NEW.type = 'CANCELED'
AND (SELECT COUNT(*) FROM "column" WHERE board_id = NEW.board_id AND type = 'CANCELED') > 0
BEGIN
SELECT RAISE(ABORT, 'Já existe uma coluna do tipo CANCELED neste board.');
END;