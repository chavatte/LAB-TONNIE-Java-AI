CREATE TRIGGER column_order_canceled_order
BEFORE INSERT ON "column"
FOR EACH ROW
WHEN NEW.type = 'CANCELED'
AND NEW."order" != 'CANCELED'
BEGIN
SELECT RAISE(ABORT, 'A coluna do tipo CANCELED deve ter a ordem CANCELED.');
END;