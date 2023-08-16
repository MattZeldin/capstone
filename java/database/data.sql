BEGIN TRANSACTION;

-- dummy data for users table
INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role, email, name, days, minutes) VALUES ('aschwarz','$2a$10$bjE6wUnNMZWcBVrtr2xuV.tSiRvQz5feT3jmGXujlbpM22YLeAa2O','ROLE_USER', 'aschwarz@gmail.com', 'Arnold Schwarzenegger', 7, 180);

-- dummy data for workouts table
INSERT INTO workouts (workout_type, exercise, workout_date, workout_duration_minutes, workout_notes, username) VALUES ('Cardio', 'Swim', '2023-08-15', 100, 'Swam 20 miles', 'aschwarz');
INSERT INTO workouts (workout_type, exercise, workout_date, workout_duration_minutes, workout_notes, username) VALUES ('Weightlifting', 'Bench Press', '2023-08-16', 120, '3 sets of 10 reps at 500 lbs', 'aschwarz');
INSERT INTO workouts (workout_type, exercise, workout_date, workout_duration_minutes, workout_notes, username) VALUES ('Weightlifting', 'Back Squat', '2023-08-17', 160, '4 sets of 8 reps at 1000 lbs', 'aschwarz');
INSERT INTO workouts (workout_type, exercise, workout_date, workout_duration_minutes, workout_notes, username) VALUES ('Cardio', 'Run', '2023-08-18', 100, 'Ran 10 miles', 'aschwarz');
INSERT INTO workouts (workout_type, exercise, workout_date, workout_duration_minutes, workout_notes, username) VALUES ('Weightlifting', 'Deadlift', '2023-08-19', 100, '2 sets of 4 reps at 1500 lbs', 'aschwarz');


-- dummy data for events table
INSERT INTO events (starts, ends, title, content, user_id) VALUES ('2023-08-16 09:00:00', '2023-08-16 10:30:00', 'Posing practice', '', 3);
INSERT INTO events (starts, ends, title, content, user_id) VALUES ('2023-08-19 13:00:00', '2023-08-19 17:00:00', 'Competition', '', 3);
INSERT INTO events (starts, ends, title, content, user_id) VALUES ('2023-08-14 11:00:00', '2023-08-14 13:00:00', 'Autograph signing', '', 3);



COMMIT TRANSACTION;
