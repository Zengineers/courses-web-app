-- Courses App Database Schema

CREATE DATABASE coursesapp;
USE coursesapp;

CREATE TABLE instructors (
	id BIGINT NOT NULL AUTO_INCREMENT,
	username VARCHAR(55) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    mail VARCHAR(55) UNIQUE,
    PRIMARY KEY (id)    
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE courses (
	id BIGINT NOT NULL AUTO_INCREMENT,
	code VARCHAR(12) UNIQUE NOT NULL,
    name VARCHAR(55) NOT NULL,
    syllabus VARCHAR(500),
    year INT DEFAULT 0,
    semester VARCHAR(12),
    instructor_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (instructor_id) REFERENCES instructors(id) 
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE students (
	id BIGINT NOT NULL AUTO_INCREMENT,
    registration_number INT UNIQUE NOT NULL,
    full_name VARCHAR(55) NOT NULL,
    semester INT,
    registration_year INT,
    mail VARCHAR(55) UNIQUE,
    department VARCHAR(55),
    PRIMARY KEY (id)    
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table student_registrations (
	student_id BIGINT NOT NULL,
	course_id BIGINT NOT NULL,
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

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE instructors;
TRUNCATE courses;
TRUNCATE students;
TRUNCATE student_registrations;
SET FOREIGN_KEY_CHECKS = 1;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE instructors;
DROP TABLE courses;
DROP TABLE students;
DROP TABLE student_registrations;
SET FOREIGN_KEY_CHECKS = 1;