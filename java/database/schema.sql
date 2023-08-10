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
    username varchar(50) NOT NULL UNIQUE,
    CONSTRAINT PK_workout PRIMARY KEY (workout_id)
);

COMMIT TRANSACTION;
