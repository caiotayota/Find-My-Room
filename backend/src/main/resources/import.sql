INSERT INTO properties(street_address, eir_code, washing_machine, dryer, dish_washer) VALUES ('Nome da rua', 'D1 1234', true, true, false);

INSERT INTO rooms(room_type, ensuite_bathroom, usuario, property_id) VALUES ('Single', false, 'Caio', 1);

INSERT INTO ads(property_id, room_id, pet_allowed, parking, owner_occupied, rent, date) VALUES (1, 1, true, false, false, 800, '2022-06-01');