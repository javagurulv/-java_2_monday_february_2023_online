INSERT INTO `user` (name, login, password, role)
VALUES ('Guest', '', '', 'GUEST'),
        ('Customer', 'customer', 'customer', 'CUSTOMER'),
        ('Manager', 'manager', 'manager', 'MANAGER'),
        ('Admin', 'admin', 'admin', 'ADMIN');

INSERT INTO cart (user_id, status)
VALUES (1, 'OPEN'),
        (2, 'OPEN'),
        (3, 'OPEN'),
        (4, 'OPEN');

INSERT INTO item (name, price, available_quantity)
VALUES ('Stop-and-Drop Suicide Booth', 1000.00, 1),
        ('Good News', 7.00, 7),
        ('Lightspeed Briefs', 249.99, 3),
        ('Moms Old-Fashioned Robot Oil', 9.99, 150),
        ('Slurm', 4.99, 30),
        ('Morbo on Management', 24.99, 70),
        ('Blank Robot', 17.50, 10),
        ('iObey', 0.01, 1000),
        ('Captains Handbook', 2.50, 5),
        ('Popplers', 1.00, 0);
