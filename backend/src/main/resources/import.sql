INSERT INTO users(email, password, first_name, last_name, date_of_birth, nationality, phone_no, pps_no) VALUES ('test@gmail.com', 'FJaifahfafgFHau', 'Caio', 'Tayota', '01/01/1989', 'italian','+353891234567', '101A987' );
INSERT INTO users(email, password, first_name, last_name, date_of_birth, nationality, phone_no, pps_no) VALUES ('landlord@gmail.com', 'FJaifahfafgFHau', 'Land', 'Lord', '01/01/1989', 'italian','+353891234567', '101A987' );

INSERT INTO user_preferences(email, room_type, parking, min_price, max_price) VALUES ('test@gmail.com', 'single', false, 450, 750);

INSERT INTO properties(street_address, eir_code, washing_machine, dryer, dish_washer) VALUES ('Nome da rua', 'D1 1234', true, true, false);

INSERT INTO rooms(property_id, room_type, ensuite_bathroom, landlord) VALUES (1, 'Single', false, 'landlord@gmail.com');

INSERT INTO ads(property_id, room_id, landlord, pet_allowed, parking, owner_occupied, rent, date) VALUES (1, 1, 'landlord@gmail.com', true, false, false, 800, '2022-06-01');
