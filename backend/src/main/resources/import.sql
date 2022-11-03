INSERT INTO users(email, encrypted_password, first_name, last_name, date_of_birth, nationality, phone_no, pps_no) VALUES ('admin@findaroom.ie', '$2a$10$wdTCD4hUuRtmFtiJRcwzxOtrIyLxWj54bc8Ma6jrZ1KMAjLGt8wOq', 'Admin', 'Find A Room', '01/01/1989', 'Italian','+353 98 765 4321', 'ADM1N12345' );
INSERT INTO users(email, encrypted_password, first_name, last_name, date_of_birth, nationality, phone_no, pps_no) VALUES ('caio@findaroom.ie', '$2a$10$7OalG9bXixaSrXR4MUd49epCvjuoS4jWujCRgUnwMYvhl3lbNNIIG', 'Caio', 'T', '1999-01-01', 'Brazilian','+353 12 345 6789', '101A987' );
INSERT INTO users(email, encrypted_password, first_name, last_name, date_of_birth, nationality, phone_no, pps_no) VALUES ('user@test.com', '$2a$10$wtyDHirABc14ugsdxruw3e5Z4l0iK6091JGolk8t8p7NvVCLkjtLK', 'John', 'O''Reilly', '01/01/1989', 'Irish','+353 78 912 3456', '101C654' );

INSERT INTO user_preferences(email, room_type, parking, min_price, max_price) VALUES ('admin@findaroom.ie', 'single', true, 450, 900);
INSERT INTO user_preferences(email, room_type, parking, min_price, max_price) VALUES ('caio@findaroom.ie', 'double', false, 750, 1450);
INSERT INTO user_preferences(email, room_type, parking, min_price, max_price) VALUES ('user@test.com', 'shared', false, 350, 650);

INSERT INTO rooms(street_address, eir_code, room_type, ensuite_bathroom, heating, carpeted, lessor) VALUES ('12 Parnell Street', 'D1 1234', 'Single', false, true, false, 'caio@findaroom.ie');
INSERT INTO rooms(street_address, eir_code, room_type, ensuite_bathroom, heating, carpeted, lessor) VALUES ('24 Cork Rd', 'D2 2345', 'Double', true, true, true, 'caio@findaroom.ie');
INSERT INTO rooms(street_address, eir_code, room_type, ensuite_bathroom, heating, carpeted, lessor) VALUES ('34 Dame Street', 'D3 2345', 'Shared', false, true, true, 'caio@findaroom.ie');
INSERT INTO rooms(street_address, eir_code, room_type, ensuite_bathroom, heating, carpeted, lessor) VALUES ('56 St. Patrick Avenue', 'D4 3456', 'Single', false, true, false, 'user@test.com');
INSERT INTO rooms(street_address, eir_code, room_type, ensuite_bathroom, heating, carpeted, lessor) VALUES ('78 Guinness St', 'D5 4567', 'Shared', false, true, true, 'user@test.com');

INSERT INTO ads(room_id, lessor, rent, bills_included, owner_occupied, parking, pet_allowed, washing_machine, dryer, dish_washer, date) VALUES (1, 'caio@findaroom.ie', 900, false, true, false, false, true, true, false, '2022-10-3');
INSERT INTO ads(room_id, lessor, rent, bills_included, owner_occupied, parking, pet_allowed, washing_machine, dryer, dish_washer, date) VALUES (2, 'caio@findaroom.ie', 1600, true, true, false, false, true, true, false, '2022-10-15');
INSERT INTO ads(room_id, lessor, rent, bills_included, owner_occupied, parking, pet_allowed, washing_machine, dryer, dish_washer, date) VALUES (3, 'caio@findaroom.ie', 600, false, true, false, false, true, true, false, '2022-10-19');
INSERT INTO ads(room_id, lessor, rent, bills_included, owner_occupied, parking, pet_allowed, washing_machine, dryer, dish_washer, date) VALUES (4, 'user@test.com', 700, false, true, false, false, true, true, false, '2022-10-28');
INSERT INTO ads(room_id, lessor, rent, bills_included, owner_occupied, parking, pet_allowed, washing_machine, dryer, dish_washer, date) VALUES (5, 'user@test.com', 550, true, true, false, false, true, true, false, '2022-11-01');
