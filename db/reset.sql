DROP TABLE IF EXISTS Students;
CREATE TABLE Students(name varchar(100), sid varchar(12) PRIMARY KEY, major varchar(30));

INSERT INTO Students values('Chris Wong', '1234567890', 'Computer Science');
INSERT INTO Students values('Amby', '0987654321', 'Computer Science');
INSERT INTO Students values('Sammi', '9999999999', 'Computer Science');
INSERT INTO Students values('Yogari', '888888888', 'English');

DROP TABLE IF EXISTS Courses;
CREATE TABLE Courses(courseCode varchar(100) PRIMARY KEY, availableSeat int, waitListCapacity int);

INSERT INTO Courses values('CSCI3230', 100, 10);
INSERT INTO Courses values('CSCI3280', 80, 20);
INSERT INTO Courses values('CSCI4180', 50, 20);
INSERT INTO Courses values('CSCI4140', 100, 20);
INSERT INTO Courses values('TRAN3260', 50, 10);