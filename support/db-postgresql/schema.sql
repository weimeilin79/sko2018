DROP TABLE IF EXISTS reservationlist;

CREATE TABLE reservationlist (
	ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	candidatename varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	seatname varchar(10) NOT NULL,
	seatno varchar(10) NOT NULL
	
);

