CREATE TRIGGER column_order_initial_duplicate_must_match
BEFORE INSERT ON "column"
FOR EACH ROW
WHEN NEW.type = 'INITIAL'
AND NEW."order" != 'INITIAL'
BEGIN
SELECT RAISE(ABORT, 'A coluna do tipo INITIAL deve ter a ordem INITIAL.');
END;