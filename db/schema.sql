-- Courses App Database Schema
-- This is a prototype and subject to changes

CREATE DATABASE coursesapp;
USE coursesapp;

CREATE TABLE instructors (
	id INT NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    mail VARCHAR(255),
    PRIMARY KEY (id)    
);

CREATE TABLE courses (
	id INT NOT NULL AUTO_INCREMENT,
	code VARCHAR(12) NOT NULL,
    name VARCHAR(255) NOT NULL,
    syllabus VARCHAR(255),
    year INT DEFAULT 0,
    semester VARCHAR(12),
    instructor_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (instructor_id) REFERENCES instructors(id)
);

CREATE TABLE students (
	id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    semester INT,
    year_of_studies INT,
    department VARCHAR(255),
    mail VARCHAR(255),
    PRIMARY KEY (id)    
);

create table student_registrations (
	student_id INT NOT NULL,
	course_id INT NOT NULL,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);
-- Courses App Database Schema END



-- Temp SQL Queries (should be deleted at some point)
SELECT * FROM instructors;
SELECT * FROM courses;
SELECT * FROM students;
SELECT * FROM student_registrations;

ALTER TABLE courses CHANGE COLUMN year year int DEFAULT 0;

-- Add some dummy data 
INSERT INTO courses 
(code, name, syllabus, year, semester)
VALUES
('MYY100', 'CLASS 1', 'GGG', 5, 'WINTER'),
('MYY102', 'CLASS 2', 'AAA', 5, 'WINTER'),
('MYY103', 'CLASS 3', 'BBB', 5, 'SUMMER'),
('MYY101', 'CLASS 4', 'XXX', 5, 'WINTER'),
('MYY100', 'CLASS 5', 'WWW', 5, 'WINTER');