INSERT INTO shop_user (name, login, password, role)
VALUES ('Guest', '', '', 'GUEST'),
        ('Customer', 'customer', 'customer', 'CUSTOMER'),
        ('Manager', 'manager', 'manager', 'MANAGER'),
        ('Admin', 'admin', 'admin', 'ADMIN');

INSERT INTO cart (user_id, status, last_update)
VALUES (1, 'OPEN', '2023-05-05 10:00:00'),
        (2, 'OPEN', '2023-05-05 10:00:00'),
        (3, 'OPEN', '2023-05-05 10:00:00'),
        (4, 'OPEN', '2023-05-05 10:00:00');

INSERT INTO item (name, price, available_quantity)
VALUES ('cvxhv', 79.71, 6),
        ('airyc', 91.88, 7),
        ('amgwz', 58.98, 5),
        ('eoslu', 21.10, 9),
        ('gskkn', 66.10, 4),
        ('lagxg', 32.49, 2),
        ('xzpjw', 63.70, 6),
        ('fvdyy', 65.05, 8),
        ('szvqi', 76.09, 7),
        ('bstxd', 76.02, 8);
