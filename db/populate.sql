-- Populate instructors
INSERT INTO instructors 
(username, password, mail)
VALUES
('user123', 'pass123', 'jack@gmail.com'),
('user1', 'pass1', 'max1@yahoo.gr');


-- Populate courses
INSERT INTO courses 
(code, name, syllabus, year, semester, instructor_id)
VALUES
('MYY100', 'Databases', 'Introduction to Database Management Systems.', 4, 'Winter', 1),
('MYY101', 'Calculus I', 'Functions of one variable (monotonic, inverse, algebraic, logarithmic functions).', 1, 'Winter', 2),
('MYY102', 'Software Engineering', 'Best practices for software development.', 4, 'Spring', 1);


-- Populate students
INSERT INTO students 
(id, full_name, semester, registration_year, mail, department)
VALUES
(2641, 'Antoniou Chris', 8, 2020, 'ant@yahoo.com', 'Dept. Computer Science'),
(2626, 'John John', 2, 2022, 'jj@gmail.com', 'Dept. Computer Science'),
(3354, 'Aggeliki Tsiouri', 8, 2020, 'agg@gmail.com', 'Dept. Computer Science'),
(2323, 'Sandy Sand', 2, 2022, 'sand@gmail.com', 'Dept. Computer Science');


-- Populate student registrations
INSERT INTO student_registrations 
(student_id, course_id)
VALUES
(2641, 2),
(2626, 1),  (2626, 2), (2626, 3),
(3354, 1), (3354, 2),
(2323, 1), (2323, 2), (2323, 3);