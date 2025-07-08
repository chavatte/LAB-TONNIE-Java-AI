CREATE TRIGGER column_order_final_order
BEFORE INSERT ON "column"
FOR EACH ROW
WHEN NEW.type = 'FINAL'
AND NEW."order" != 'FINAL'
BEGIN
SELECT RAISE(ABORT, 'A coluna do tipo FINAL deve ter a ordem FINAL.');
END;