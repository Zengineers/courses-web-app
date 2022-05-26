# Courses Management Web Application
 
 Courses is a Spring Boot Java Web App developed during the [Software Engineering](https://www.cs.uoi.gr/course/software-engineering/?lang=en) course [@cse.uoi.gr](https://www.cs.uoi.gr/).<br><br>


## Summary

The objective of the project was to develop a Web App that allows an instructor to manage the grading of the courses that he teaches. The objective was to be fulfilled using a Scrum approach to resolve organizational issues and technologies such as the Spring Boot Framework and MySQL to satisfy functional requirements. Non-functional requirements were specified in the form of design quality and best coding practices. Incomplete class diagrams describing the application architecture was the starting point. Testing was also among the requirements. The development process has been documented in detail under the report.pdf as well as in the description of the commits.

<br>

## Technologies

This section contains a brief overview of the technologies used:
*	[MySQL](https://www.mysql.com/) & [MySQL Workbench](https://www.mysql.com/products/workbench/) as a DBMS and database visual tool respectively.
*	[GitHub](https://github.com/) for version control. Project repo [here](https://github.com/Zengineers/courses-web-app).
*	[Trello](https://trello.com/) for project management and team organization. Project board [here](https://trello.com/b/aYWeaLxz/zengineers-coursesmanagementapp). The project board contains cards with the user stories and any other task regarding the implementation of the project as well as various useful information in the form of links we came across during the development process.
*	[Figma](https://www.figma.com/) as an application wireframe design tool. All the application’s pages were first designed here along with a rough sketch of their navigation functionality. Later, the Figma wireframe was used as a guideline to design the actual application using CSS and HTML. Wireframe link [here](https://www.figma.com/file/PYsLldOQx0acj7uvoIks2C/Courses-Management-App-Wireframe?node-id=0%3A1). A presentation can be instantiated by clicking on the present button on the top right.
*	[Spring Boot](https://spring.io/projects/spring-boot) as a general framework for creating the application with Java 11 as the programming language and [Maven](https://maven.apache.org/) as a build tool. Some notable dependencies were [Thymeleaf](https://www.thymeleaf.org/) as a template engine, Spring Data JPA and MySQL Driver to implement the connection with the MySQL database and Spring Boot DevTools to auto-reload the project when the source code is changed while the application is running.
*	[Eclipse](https://www.eclipse.org/ide/) as an IDE.
*	[JUnit 5](https://junit.org/junit5/) for testing.
*	[ObjectAid](https://marketplace.eclipse.org/content/objectaid-uml-explorer) (Eclipse plugin) to generate UML package and class diagrams.
*	[Discord](https://discord.com/) as a communication tool.

<br>

## Features

Instructors can:
-   Login to the application with a username and password.
-   Browse the list of courses they are teaching.
-   Add a course in the list, by giving info such as course id, name, syllabus, etc.
-   Remove a course from the list.
-   Update the info of a course.
-   Browse the list of students that enrolled to a particular course.
-   Add a student to the list of a particular course, by giving info such as student id, name, semester, etc.
-   Remove a student from the list.
-   Update the info of a student.
-   Browse the list of students' grades that enrolled to a particular course.
-   Register the grades of a student.
-   Calculate overall grades for students that enrolled in a particular course

<br>

## Usage

An executable jar can be found in releases.
<br>
Alternatively, the application can also be imported into Eclipse IDE as a Maven project.

<br>

## Demo Images

- Courses page - Displays the courses the authenticated instructor is teaching
  ![](/demo/images/courses.png)
  <br>

- Add/Update course page - A selected couse is updated with the specified info
  ![](/demo/images/addCourse.png)
  <br>

- Student Registrations page -  Displays the students enrolled in the selected course
  ![](/demo/images/studentRegistrations.png)
  <br>


## Credits

<p align="center">
  <img src="https://avatars.githubusercontent.com/u/94444618?s=400&u=665ca7ded45c1a0edc43742040bd8bf5813083c9&v=4" alt="Zengineers Logo" width="205" height="205">
  <br>
  <br>
  Tsiouri Angeliki
  <br>
  Antoniou Christodoulos
</p>
