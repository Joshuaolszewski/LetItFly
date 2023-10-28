USE let_it_fly;

INSERT INTO user (email, password, first_name, last_name, birthdate, gender, address, phone, driver_license) VALUES
		("driveraccount2@gmail.com", "$2a$10$591ppVsEmSnbkbTCZDapmuytr//Txp5I7Zm9dfuDKfiI7W9gHcdIq", "George", "Billy", "2001-10-07", "male", "3rd Street, San Jose, CA 95121", "4095627864", "K6728892");
INSERT INTO user (email, password, first_name, last_name, birthdate, gender, address, phone) VALUES
		("passengeraccount2@gmail.com", "$2a$10$591ppVsEmSnbkbTCZDapmuytr//Txp5I7Zm9dfuDKfiI7W9gHcdIq", "Sally", "Jenny", "2003-06-07", "female", "4rd Street, San Jose, CA 95121", "4095627864");
        
INSERT INTO user (email, password, first_name, last_name, birthdate, gender, address, phone, driver_license) VALUES
		("driveraccount3@gmail.com", "$2a$10$591ppVsEmSnbkbTCZDapmuytr//Txp5I7Zm9dfuDKfiI7W9gHcdIq", "Bobby", "Henderson", "2001-10-07", "male", "3rd Street, San Jose, CA 95121", "4095627864", "K6728892");
INSERT INTO user (email, password, first_name, last_name, birthdate, gender, address, phone) VALUES
		("passengeraccount3@gmail.com", "$2a$10$591ppVsEmSnbkbTCZDapmuytr//Txp5I7Zm9dfuDKfiI7W9gHcdIq", "Hana", "Van", "2001-10-07", "female", "3rd Street, San Jose, CA 95121", "4095627864");
        
INSERT INTO user (email, password, first_name, last_name, birthdate, gender, address, phone) VALUES
		("dummypassenger1@gmail.com", "$2a$10$591ppVsEmSnbkbTCZDapmuytr//Txp5I7Zm9dfuDKfiI7W9gHcdIq", "Kimbel", "Sihk", "1995-11-07", "famale", "1st Street, San Jose, CA 95121", "4095627864"),
        ("dummypassenger2@gmail.com", "$2a$10$591ppVsEmSnbkbTCZDapmuytr//Txp5I7Zm9dfuDKfiI7W9gHcdIq", "Josh", "Ken", "1993-08-07", "male", "2nd Street, San Jose, CA 95121", "4095627864"),
        ("dummypassenger3@gmail.com", "$2a$10$591ppVsEmSnbkbTCZDapmuytr//Txp5I7Zm9dfuDKfiI7W9gHcdIq", "Thanh", "West", "1999-03-07", "female", "3rd Street, San Jose, CA 95121", "4095627864"); 
        
INSERT INTO payment (card_number, expiration, cvv, type, user_id, name, billing_address) VALUES
		("1000000000000003", "10/2024", 123, "debit", 10002, "Kevin Star", "5rd Street, San Jose, CA 95121"),
        ("1000000000000004", "10/2025", 123, "debit", 10003, "Sally Luu", "4rd Street, San Jose, CA 95121"),
        ("1000000000000005", "11/2025", 123, "debit", 10004, "Daniel Young", "5rd Street, San Jose, CA 95121"),
        ("1000000000000006", "11/2025", 123, "debit", 10005, "Emily Marta", "4rd Street, San Jose, CA 95121"),
        ("1000000000000007", "10/2024", 123, "debit", 10002, "Andy Viktor", "5rd Street, San Jose, CA 95121"),
        ("1000000000000008", "10/2024", 123, "debit", 10006, "Kimbel Sihk", "1st Street, San Jose, CA 95121"),
        ("1000000000000009", "10/2024", 123, "debit", 10007, "Josh Ken", "2nd Street, San Jose, CA 95121"),
        ("10000000000000010", "10/2024", 123, "debit", 10008, "Thanh West", "3rd Street, San Jose, CA 95121");
	
INSERT INTO user_role(user_id, role_id) VALUES
		(10002, 1),
        (10004, 1),
        (10003, 2),
        (10005, 2),
        (10006, 2),
        (10007, 2),
        (10008, 2);
		
INSERT INTO vehicle (license_plate, make, model, year, type, user_id) VALUES
		("5SHS555", "chevrolet", "corvette", 2023, "sedan", 10002),
        ("3MNM343", "chevrolet", "camaro", 2023, "sedan", 10004),
        ("4HHH4545", "nissan", "gtr", 2023, "sedan", 10002); 

INSERT INTO driver_status (user_id, dispatch, seat_available, cur_lat, cur_long) VALUES
		(10002, false, 4, 37.36888, -121.899999),
        (10004, false, 4, 37.35353, -121.901234); 
        
INSERT INTO history_log (driver_id, passenger_id, date, pickup_location, destination, distance, time_duration, cost) VALUES
		(10002, 10003, "2023-12-16", "9th Street, San Jose, CA 95100", "Airport Way, San Francisco, CA 95334", 23.8, "12 mins", 36.50),
        (10004, 10005, "2023-12-20", "Airport Way, San Francisco, CA 95334", "9th Street, San Jose, CA 95100", 23.8, "39 mins", 32.50);
        
-- Insert initial data for ride_request from dummypassenger1, dummypassenger2, dummypassenger3
INSERT INTO ride_request (cur_lat, cur_long, dest_lat, dest_long, passenger_id, date, time_request, duration, cost, start) VALUES
		(37.335436, -121.909424, 37.615223, -122.389977, 10006, "2023-10-26", "14:00", "20 mins", "36.20", FALSE),
        (37.349372, -121.896864, 37.363949, -121.928940, 10007, "2023-10-26", "14:12", "24 mins", "40.99", FALSE),
        (37.535419, -121.986426, 37.715907, -122.213771, 10008, "2023-10-26", "14:14", "15 mins", "25.50", FALSE);

