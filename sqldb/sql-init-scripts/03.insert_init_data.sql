USE let_it_fly;

-- Insert initial data for user table
-- first user will have id=10000, and other users will have id auto incremented by 1
INSERT INTO user (email, password, first_name, last_name, birthdate, gender, address, phone, date_join, driver_license) VALUES
		("driveraccount@gmail.com", "$2a$10$591ppVsEmSnbkbTCZDapmuytr//Txp5I7Zm9dfuDKfiI7W9gHcdIq", "Johnny", "West", "2000-12-06", "male", "3rd Street, San Jose, CA 95121", "4095627864", "2023-09-30", "Y1237849");
INSERT INTO user (email, password, first_name, last_name, birthdate, gender, address, phone, date_join) VALUES
		("passengeraccount@gmail.com", "$2a$10$591ppVsEmSnbkbTCZDapmuytr//Txp5I7Zm9dfuDKfiI7W9gHcdIq", "Emily", "Marta", "2003-10-06", "female", "4rd Street, San Jose, CA 95121", "4095627864", "2023-09-30");
        

-- Insert initial data for payment table
INSERT INTO payment (card_number, expiration, cvv, type, user_id, name, billing_address) VALUES
		("1000000000000001", "12/2024", 123, "debit", 10000, "Daniel Young", "5rd Street, San Jose, CA 95121"),
        ("1000000000000002", "10/2025", 123, "debit", 10001, "Emily Marta", "4rd Street, San Jose, CA 95121");
        

-- Insert initial data for role table
INSERT INTO role (name) VALUES
		("ROLE_DRIVER"),
		("ROLE_PASSENGER");
        
-- Insert initial data for user_role table
INSERT INTO user_role(user_id, role_id) VALUES
		(10000, 1),
        (10001, 2);
        
-- Insert initial data for vehicle table
INSERT INTO vehicle (license_plate, make, model, year, type, user_id) VALUES
		("1ABC234", "subaru", "outback", 2008, "sedan", 10000); 
        
-- Insert initial data for driver_status table
INSERT INTO driver_status (user_id, dispatch, seat_available, cur_lat, cur_long) VALUES
		(10000, FALSE, 4, 37.355555, -121.900000); 
        
-- Insert initial data for history_log table
INSERT INTO history_log (driver_id, passenger_id, date, pickup_location, destination, distance, time_duration, cost) VALUES
		(10000, 10001, "2023-12-06", "9th Street, San Jose, CA 95100", "Airport Way, San Francisco, CA 95334", 23.8, "45 mins", 36.50),
        (10000, 10001, "2023-12-15", "Airport Way, San Francisco, CA 95334", "9th Street, San Jose, CA 95100", 23.8, "25 mins", 32.50);
        