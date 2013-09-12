DROP TABLE IF EXISTS Students;
CREATE TABLE Students(name varchar(100), sid varchar(12) PRIMARY KEY, major varchar(30));

INSERT INTO Students values('Chris Wong', '1234567890', 'Computer Science');
INSERT INTO Students values('Amby', '0987654321', 'Computer Science');
INSERT INTO Students values('Sammi', '9999999999', 'Computer Science');
INSERT INTO Students values('Yogari', '888888888', 'English');