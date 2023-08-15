BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	email varchar(50),
	name varchar(50),
	days int, 
	minutes int, 
	CONSTRAINT PK_user PRIMARY KEY (user_id), 
	CHECK(days > 0 and days < 8)
);

CREATE TABLE workouts (
    workout_id SERIAL,
    workout_type varchar(50) NOT NULL,
    exercise varchar(50),
    workout_date date,
    workout_duration_minutes int,
    workout_notes varchar(500),
    username varchar(50) NOT NULL,
    CONSTRAINT PK_workout PRIMARY KEY (workout_id)
);

CREATE TABLE events (
	event_id SERIAL, 
	starts timestamp, 
	date date, 
	ends timestamp,   
	title varchar(50),
	content varchar(500),
	class varchar(50),
<<<<<<< HEAD
<<<<<<< HEAD
	user_id int,
	CONSTRAINT PK_events PRIMARY KEY (event_id),
	CONSTRAINT FK_user FOREIGN KEY(user_id) REFERENCES users(user_id)
	
=======
	user_id int, 
	CONSTRAINT PK_events PRIMARY KEY (event_id),
	CONSTRAINT FK_user FOREIGN KEY(user_id) REFERENCES users(user_id)
>>>>>>> 7dcbac2452b717d76ae19b7da76342dbd909d22a
=======
	user_id int, 
	CONSTRAINT PK_events PRIMARY KEY (event_id),
	CONSTRAINT FK_user FOREIGN KEY(user_id) REFERENCES users(user_id)
>>>>>>> d1c9d17164e5d4e7971ea2d6432a3411e50fad9a
);
COMMIT TRANSACTION;
