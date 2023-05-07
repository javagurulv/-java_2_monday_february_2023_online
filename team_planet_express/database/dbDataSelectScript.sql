SELECT *
FROM item;

SELECT name
FROM item;

SELECT *
FROM item
WHERE id = 4;

SELECT *
FROM item
WHERE available_quantity = 9;

SELECT *
FROM item
WHERE price < 50 AND available_quantity > 5;

SELECT name, price
FROM item
WHERE name = 'eoslu' OR price > 90;

SELECT *
FROM item
WHERE price BETWEEN 30 AND 90;

SELECT *
FROM item
WHERE price < 30 OR price > 90;

SELECT name, price
FROM item
ORDER BY price ASC;

SELECT *
FROM item
LIMIT 3;

SELECT *, price * available_quantity AS sum
FROM item;

SELECT MIN(price) as min_price, MAX(price) as max_price
FROM item;

SELECT status, COUNT(id) as cart_count
FROM cart
GROUP BY status;

SELECT name
FROM user
JOIN cart ON user.id = cart.user_id
WHERE cart.status = 'CLOSED';
