CREATE SCHEMA `SchoolManagementSystem`;
CREATE TABLE `SchoolManagementSystem`.`studentInfo` (
    `studentID` INT NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    `class` INT NOT NULL,
    `roll` INT NOT NULL,
    `section` VARCHAR(45) NULL,
    `fatherName` VARCHAR(100) NULL,
    `motherName` VARCHAR(100) NULL,
    `address` VARCHAR(100) NULL,
    `dateOfBirth` DATE NULL,
    `gender` VARCHAR(45) NULL,
    `contactNumber` VARCHAR(45) NULL,
    `religion` VARCHAR(100) NULL,
    `profilePicture` LONGBLOB NULL,
    PRIMARY KEY (`studentID`)
);
CREATE TABLE `SchoolManagementSystem`.`employeeInfo` (
    `employeeID` INT NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    `profession` VARCHAR(100) NOT NULL,
    `fatherName` VARCHAR(100) NULL,
    `motherName` VARCHAR(100) NULL,
    `subject` VARCHAR(100) NULL,
    `address` VARCHAR(100) NULL,
    `dateOfBirth` VARCHAR(100) NULL,
    `joiningDate` VARCHAR(100) NULL,
    `gender` VARCHAR(100) NULL,
    `contactNumber` VARCHAR(45) NULL,
    `religion` VARCHAR(100) NULL,
    `profilePicture` LONGBLOB NULL,
    PRIMARY KEY (`employeeID`)
);
CREATE TABLE `SchoolManagementSystem`.`notice` (
    `noticeID` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(255) NULL,
    `description` LONGTEXT NULL,
    `issueDate` DATE NULL,
    PRIMARY KEY (`noticeID`)
);
CREATE TABLE `SchoolManagementSystem`.`employeeSalary` (
    `employeeID` INT NULL,
    `baseSalary` INT NULL,
    `houseRent` INT NULL,
    `medical` INT NULL,
    `noOfChild` INT NULL,
    `receivedDate` DATE NULL,
    FOREIGN KEY (`employeeID`)
        REFERENCES `SchoolManagementSystem`.`employeeInfo` (`employeeID`)
        ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE `SchoolManagementSystem`.`studentFees` (
    `studentID` INT NULL,
    `feeAmount` INT NULL,
    `paymentDate` DATE NULL,
    FOREIGN KEY (`studentID`)
        REFERENCES `SchoolManagementSystem`.`studentInfo` (`studentID`)
        ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE `SchoolManagementSystem`.`loginInfo` (
    `ID` INT NULL,
    `userType` VARCHAR(45) NULL,
    `password` VARCHAR(45) NULL,
    FOREIGN KEY (`ID`)
        REFERENCES `SchoolManagementSystem`.`employeeInfo` (`employeeID`)
        ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE `SchoolManagementSystem`.`club` (
    `clubID` INT NOT NULL AUTO_INCREMENT,
    `clubName` VARCHAR(100) NULL,
    `president` INT NULL,
    `vicePresident` INT NULL,
    `generalSecretary` INT NULL,
    `treasurer` INT NULL,
    `clubModerator` INT NULL,
    `assistantGS` INT NULL,
    `publicRelations` INT NULL,
    `secretary` INT NULL,
    `executive_1` INT NULL,
    `executive_2` INT NULL,
    `executive_3` INT NULL,
    `fund` VARCHAR(100) NULL,
    PRIMARY KEY (`clubID`),
    FOREIGN KEY (`clubModerator`)
        REFERENCES `SchoolManagementSystem`.`employeeInfo` (`employeeID`)
        ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (`president`)
        REFERENCES `SchoolManagementSystem`.`studentInfo` (`studentID`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`vicePresident`)
        REFERENCES `SchoolManagementSystem`.`studentInfo` (`studentID`)
        ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (`generalSecretary`)
        REFERENCES `SchoolManagementSystem`.`studentInfo` (`studentID`)
        ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (`treasurer`)
        REFERENCES `SchoolManagementSystem`.`studentInfo` (`studentID`)
        ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (`assistantGS`)
        REFERENCES `SchoolManagementSystem`.`studentInfo` (`studentID`)
        ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (`publicRelations`)
        REFERENCES `SchoolManagementSystem`.`studentInfo` (`studentID`)
        ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (`secretary`)
        REFERENCES `SchoolManagementSystem`.`studentInfo` (`studentID`)
        ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (`executive_1`)
        REFERENCES `SchoolManagementSystem`.`studentInfo` (`studentID`)
        ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (`executive_2`)
        REFERENCES `SchoolManagementSystem`.`studentInfo` (`studentID`)
        ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (`executive_3`)
        REFERENCES `SchoolManagementSystem`.`studentInfo` (`studentID`)
        ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE `SchoolManagementSystem`.`clubMembers` (
    `studentID` INT NULL,
    `Business` TINYINT NOT NULL DEFAULT FALSE,
    `Science` TINYINT NOT NULL DEFAULT FALSE,
    `English` TINYINT NOT NULL DEFAULT FALSE,
    `Craft` TINYINT NOT NULL DEFAULT FALSE,
    FOREIGN KEY (`studentID`)
        REFERENCES `SchoolManagementSystem`.`studentInfo` (`studentID`)
        ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE SchoolManagementSystem.resultinfo_HalfYearly (
    ID INT NOT NULL,
    Bangla INT NULL,
    Bangla_1 INT NULL,
    Bangla_2 INT NULL,
    English INT NULL,
    English_1 INT NULL,
    English_2 INT NULL,
    Math INT NULL,
    Social_Science INT NULL,
    Science INT NULL,
    Religion INT NULL,
    Well_Being INT NULL,
    Craft INT NULL,
    History INT NULL,
    Logic INT NULL,
    Economics INT NULL,
    Business INT NULL,
    totalMarks INT NULL,
    gpa VARCHAR(45) NULL,
    FOREIGN KEY (ID)
        REFERENCES SchoolManagementSystem.studentInfo (studentID)
        ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE SchoolManagementSystem.resultinfo_YearFinal (
    ID INT NOT NULL,
    Bangla INT NULL,
    Bangla_1 INT NULL,
    Bangla_2 INT NULL,
    English INT NULL,
    English_1 INT NULL,
    English_2 INT NULL,
    Math INT NULL,
    Social_Science INT NULL,
    Science INT NULL,
    Religion INT NULL,
    Well_Being INT NULL,
    Craft INT NULL,
    History INT NULL,
    Logic INT NULL,
    Economics INT NULL,
    Business INT NULL,
    totalMarks INT NULL,
    gpa VARCHAR(45) NULL,
    FOREIGN KEY (ID)
        REFERENCES SchoolManagementSystem.studentInfo (studentID)
        ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE `SchoolManagementSystem`.`subjectInfo` (
	Class INT NOT NULL,
    sub1 VARCHAR(100) NULL,
    sub2 VARCHAR(100) NULL,
    sub3 VARCHAR(100) NULL,
    sub4 VARCHAR(100) NULL,
    sub5 VARCHAR(100) NULL,
    sub6 VARCHAR(100) NULL,
    sub7 VARCHAR(100) NULL,
    sub8 VARCHAR(100) NULL,
    sub9 VARCHAR(100) NULL,
    sub10 VARCHAR(100) NULL,
    sub11 VARCHAR(100) NULL,
    sub12 VARCHAR(100) NULL,
    sub13 VARCHAR(100) NULL,
    sub14 VARCHAR(100) NULL,
    PRIMARY KEY(class)
);
CREATE TABLE `SchoolManagementSystem`.`resultAlgo` (
    `demoID` INT NOT NULL,
    `halfYearlyWeight` INT NULL,
    `yearFinalWeight` INT NULL,
    `priority` VARCHAR(100) NULL,
    PRIMARY KEY (`demoID`)
);




INSERT INTO SchoolManagementSystem.subjectInfo (class, sub1, sub2, sub3, sub4, sub5) Values (1, "Bangla", "English", "Religion", "Science", "Math");
INSERT INTO SchoolManagementSystem.subjectInfo (class, sub1, sub2, sub3, sub4, sub5) Values (2, "Bangla", "English", "Religion", "Science", "Math");
INSERT INTO SchoolManagementSystem.subjectInfo (class, sub1, sub2, sub3, sub4, sub5) Values (3, "Bangla", "English", "Religion", "Science", "Math");
INSERT INTO SchoolManagementSystem.subjectInfo (class, sub1, sub2, sub3, sub4, sub5) Values (4, "Bangla", "English", "Religion", "Science", "Math");
INSERT INTO SchoolManagementSystem.subjectInfo (class, sub1, sub2, sub3, sub4, sub5) Values (5, "Bangla", "English", "Religion", "Science", "Math");
INSERT INTO SchoolManagementSystem.subjectInfo (class, sub1, sub2, sub3, sub4, sub5, sub6, sub7, sub8, sub9) Values (6, "Bangla_1", "Bangla_2", "English_1", "English_2", "Social_Science", "Science", "Math", "Well_Being", "Craft");
INSERT INTO SchoolManagementSystem.subjectInfo (class, sub1, sub2, sub3, sub4, sub5, sub6, sub7, sub8, sub9) Values (7, "Bangla_1", "Bangla_2", "English_1", "English_2", "Social_Science", "Science", "Math", "Well_Being", "Craft");
INSERT INTO SchoolManagementSystem.subjectInfo (class, sub1, sub2, sub3, sub4, sub5, sub6, sub7, sub8, sub9) Values (8, "Bangla_1", "Bangla_2", "English_1", "English_2", "Social_Science", "Science", "Math", "Well_Being", "Craft");
INSERT INTO SchoolManagementSystem.subjectInfo (class, sub1, sub2, sub3, sub4, sub5, sub6, sub7, sub8, sub9, sub10, sub11) Values (9, "Bangla_1", "Bangla_2", "English_1", "English_2", "Social_Science", "Science", "Math", "History", "Logic", "Economics", "Business");
INSERT INTO SchoolManagementSystem.subjectInfo (class, sub1, sub2, sub3, sub4, sub5, sub6, sub7, sub8, sub9, sub10, sub11) Values (10, "Bangla_1", "Bangla_2", "English_1", "English_2", "Social_Science", "Science", "Math", "History", "Logic", "Economics", "Business");

INSERT INTO SchoolManagementSystem.employeeInfo (employeeID, name, profession, fatherName, motherName, address, dateOfBirth, joiningDate, gender, contactNumber, religion) VALUES ('1111111', 'AdminName', 'Staff', 'a', 'b', 'c', '2023-04-01', '2023-04-01', 'Male', '11111111111', 'e');
INSERT INTO SchoolManagementSystem.loginInfo Values (1111111, "Admin", "a");