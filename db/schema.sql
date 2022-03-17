-- Courses App Database Schema
-- This is a prototype and subject to changes

CREATE DATABASE coursesapp;
USE coursesapp;

CREATE TABLE instructors (
	id INT NOT NULL AUTO_INCREMENT,
	username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    mail VARCHAR(255) UNIQUE,
    PRIMARY KEY (id)    
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE courses (
	id INT NOT NULL AUTO_INCREMENT,
	code VARCHAR(12) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    syllabus VARCHAR(255),
    year INT DEFAULT 0,
    semester VARCHAR(12),
    instructor_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (instructor_id) REFERENCES instructors(id) 
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE students (
	id INT NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    semester INT,
    registration_year INT,
    mail VARCHAR(255) UNIQUE,
    department VARCHAR(255),
    PRIMARY KEY (id)    
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table student_registrations (
	student_id INT NOT NULL,
	course_id INT NOT NULL,
    project_grade DOUBLE DEFAULT NULL,
    exam_grade DOUBLE DEFAULT NULL,
    total_grade DOUBLE DEFAULT NULL,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES students(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- Courses App Database Schema END


-- Temp SQL Queries (should be deleted at some point)
SELECT * FROM instructors;
SELECT * FROM courses;
SELECT * FROM students;
SELECT * FROM student_registrations;


SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE instructors;
TRUNCATE courses;
TRUNCATE students;
TRUNCATE student_registrations;
SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE instructors;
DROP TABLE courses;
DROP TABLE students;
DROP TABLE student_registrations;



DELETE FROM courses WHERE courses.id=1;
DELETE FROM students WHERE students.id=2323;