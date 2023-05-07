UPDATE item
SET id = 11, name = 'uuwuu', price = 420.69, available_quantity = 1337
WHERE id = 10;

UPDATE item
SET price = 70.63
WHERE id = 7;

UPDATE cart
SET status = 'CLOSED', last_update = '2023-05-05 12:12:12'
WHERE id = 4;
