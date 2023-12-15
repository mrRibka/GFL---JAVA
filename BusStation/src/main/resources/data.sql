INSERT INTO City (Name) VALUES ('Lviv');
INSERT INTO City (Name) VALUES ('Kiev');
INSERT INTO City (Name) VALUES ('Poltava');
INSERT INTO City (Name) VALUES ('Kharkiv');
INSERT INTO City (Name) VALUES ('Ternopil');
INSERT INTO City (Name) VALUES ('Odesa');
INSERT INTO City (Name) VALUES ('Dnipro');

INSERT INTO Route (Name, Bus_Type, Cost) VALUES ('R1', 'LARGE', 300);
INSERT INTO Route (Name, Bus_Type, Cost) VALUES ('R2', 'MEDIUM', 250);
INSERT INTO Route (Name, Bus_Type, Cost) VALUES ('R3', 'SMALL', 150);
INSERT INTO Route (Name, Bus_Type, Cost) VALUES ('R4', 'LARGE', 300);
INSERT INTO Route (Name, Bus_Type, Cost) VALUES ('R5', 'MEDIUM', 350);
INSERT INTO Route (Name, Bus_Type, Cost) VALUES ('R6', 'SMALL', 200);

INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (1, 1, '2023-12-16 12:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (2, 1, '2023-12-16 14:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (4, 1, '2023-12-16 16:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (5, 1, '2023-12-16 18:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (3, 1, '2023-12-16 20:00:00');

INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (3, 2, '2023-12-17 08:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (1, 2, '2023-12-17 10:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (5, 2, '2023-12-17 12:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (7, 2, '2023-12-17 14:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (6, 2, '2023-12-17 16:00:00');

INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (2, 3, '2023-12-18 09:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (4, 3, '2023-12-18 11:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (6, 3, '2023-12-18 13:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (5, 3, '2023-12-18 15:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (7, 3, '2023-12-18 17:00:00');

INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (1, 4, '2023-12-19 10:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (2, 4, '2023-12-19 12:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (5, 4, '2023-12-19 14:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (7, 4, '2023-12-19 16:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (3, 4, '2023-12-19 18:00:00');

INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (3, 5, '2023-12-20 11:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (6, 5, '2023-12-20 13:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (4, 5, '2023-12-20 15:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (2, 5, '2023-12-20 17:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (7, 5, '2023-12-20 19:00:00');

INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (1, 6, '2023-12-21 10:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (7, 6, '2023-12-21 12:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (6, 6, '2023-12-21 14:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (5, 6, '2023-12-21 16:00:00');
INSERT INTO Stop (City_Id, Route_Id, Stop_Time) VALUES (4, 6, '2023-12-21 18:00:00');

