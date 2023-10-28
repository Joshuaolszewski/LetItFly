USE let_it_fly;

CREATE TABLE user(
		id INT PRIMARY KEY AUTO_INCREMENT,
        email VARCHAR(100) NOT NULL UNIQUE,
        password VARCHAR(100) NOT NULL,
        first_name VARCHAR(100),
        last_name VARCHAR(100),
        birthdate DATE,
        gender VARCHAR(50),
        address VARCHAR(150),
        phone VARCHAR(50),
        date_join DATE,
        driver_license VARCHAR(50),
        active BOOLEAN DEFAULT TRUE,
        verified BOOLEAN DEFAULT FALSE
        )AUTO_INCREMENT = 10000;

-- type: type of card (debit/credit)
-- name: person's name on the card
-- expiration: 12/2024
CREATE TABLE payment(
		id INT PRIMARY KEY AUTO_INCREMENT,
		card_number VARCHAR(50),
        expiration VARCHAR(50),
        cvv INT,
        type VARCHAR(50), 
        user_id INT,
        name VARCHAR(100),
        billing_address VARCHAR(150),
        in_use BOOLEAN DEFAULT true,
        balance DOUBLE DEFAULT 0.00, 
        CONSTRAINT payment_user_fk FOREIGN KEY (user_id) REFERENCES user(id));

CREATE TABLE driver_status(
		id INT PRIMARY KEY AUTO_INCREMENT,
		user_id INT,
        dispatch BOOLEAN, 
        seat_available INT,
        cur_lat DECIMAL(10, 8),
        cur_long DECIMAL(11, 8),
        CONSTRAINT driver_status_user_fk FOREIGN KEY (user_id) REFERENCES user(id));

CREATE TABLE vehicle(
		id INT PRIMARY KEY AUTO_INCREMENT,
		license_plate VARCHAR(50),
        make VARCHAR(50),
        model VARCHAR(50),
        year INT,
        type VARCHAR(50),
        in_use BOOLEAN DEFAULT TRUE,
        user_id INT,
        CONSTRAINT vehicle_user_fk FOREIGN KEY (user_id) REFERENCES user(id));
        
        
CREATE TABLE role(
		id INT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(50) NOT NULL UNIQUE); 


CREATE TABLE user_role(
		user_id INT,
        role_id INT,
        PRIMARY KEY(user_id, role_id),
        CONSTRAINT user_role_user_fk FOREIGN KEY (user_id) REFERENCES user(id), 
        CONSTRAINT user_role_role_fk FOREIGN KEY (role_id) REFERENCES role(id));
        

-- time duration: "45 mins"
CREATE TABLE history_log(
		id INT PRIMARY KEY AUTO_INCREMENT,
        driver_id INT,
        passenger_id INT,
        date DATE,
        pickup_location VARCHAR(150),
        destination VARCHAR(150),
        distance DOUBLE,
        time_duration VARCHAR(50),
        cost DOUBLE,
        CONSTRAINT history_log_user_fk1 FOREIGN KEY (driver_id) REFERENCES user(id),
        CONSTRAINT history_log_user_fk2 FOREIGN KEY (passenger_id) REFERENCES user(id));
        
CREATE TABLE ride_request(
		id INT PRIMARY KEY AUTO_INCREMENT,
        cur_lat DECIMAL(10, 8),
        cur_long DECIMAL(11, 8),
        dest_lat DECIMAL(10, 8),
        dest_long DECIMAL(11, 8),
        passenger_id INT,
        driver_id INT,
        date DATE,
        time_request TIME,
        duration VARCHAR(50),
        cost DOUBLE,
        pickup_location VARCHAR(255),
        destination VARCHAR(255),
        start BOOLEAN,
        CONSTRAINT ride_request_user_fk1 FOREIGN KEY (passenger_id) REFERENCES user(id),
        CONSTRAINT ride_request_user_fk2 FOREIGN KEY (driver_id) REFERENCES user(id)
        );