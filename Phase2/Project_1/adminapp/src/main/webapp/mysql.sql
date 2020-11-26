CREATE SCHEMA `academyadmin` ;

create table academyadmin.students
(
 stdId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 stdName varchar(100) not null,
 stdClassId INT NOT NULL
) engine = InnoDB;

create table academyadmin.classes
(
 classId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 className varchar(100) not null
) engine = InnoDB;

create table academyadmin.subjects
(
 subjId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 subjName varchar(100) not null
) engine = InnoDB;

create table academyadmin.teachers
(
 teacherId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 teacherName varchar(100) not null
) engine = InnoDB;

create table academyadmin.teacherclasssubj
(
 teacherId INT NOT NULL,
 classId INT NOT NULL,
 subjId INT NOT NULL,
 FOREIGN KEY (teacherId) REFERENCES TEACHERS(teacherId),
 FOREIGN KEY (classId) REFERENCES CLASSES(classId), 
 FOREIGN KEY (subjId) REFERENCES SUBJECTS(subjId)
) engine = InnoDB;