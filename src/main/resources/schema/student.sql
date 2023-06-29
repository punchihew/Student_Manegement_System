


CREATE DATABASE Student;


CREATE TABLE Addmin (
                        Addmin_Id INT(10) NOT NULL ,
                        Addmin_Name VARCHAR(10),
                        Addmin_Contcat INT(10),
                        Addmin_Email VARCHAR(10)
);


CREATE TABLE Registation (
                             Reg_Id INT(10),
                             Reg_Name VARCHAR(10) NOT NULL,
                             Reg_Contcat INT(10),
                             Reg_Date DATE,
                             Reg_Address VARCHAR(10),
                             CONSTRAINT PRIMARY KEY (Reg_Id)
);




CREATE TABLE Student (
                         SId INT(10),
                         SName VARCHAR(10),
                         SNddress VARCHAR(10),
                         SContcat INT(10) NOT NULL,
                         SEmail VARCHAR(10) NOT NULL,
                         CONSTRAINT PRIMARY KEY (SId)
);


CREATE TABLE Payment (
                         SId INT(10),
                         Coure_Id INT(10),
                         Fee INT(10),
                         Pay_Date DATE NOT NULL,
                         CONSTRAINT FOREIGN KEY (SId) REFERENCES Student(SId),
                         CONSTRAINT FOREIGN KEY (Coure_Id) REFERENCES Course(Coure_Id)
);


CREATE TABLE Student_Attendence (
                                    SId INT(10),
                                    Date DATE NOT NULL,
                                    Time decimal(10,2),
                                    CONSTRAINT FOREIGN KEY (SId) REFERENCES Student(SId)
);


CREATE TABLE Teacher (
                         TId INT(10),
                         TName VARCHAR(10),
                         TNddress VARCHAR(10),
                         TContcat INT(10) NOT NULL,
                         TEmail VARCHAR(10) NOT NULL,
                         CONSTRAINT PRIMARY KEY (TId)
);



CREATE TABLE Teacher_Attendence (
                                    TId INT(10),
                                    Date DATE,
                                    Time Decimal(10,2),
                                    CONSTRAINT FOREIGN KEY (TId) REFERENCES Teacher(TId)
);


CREATE TABLE Course (
                        Coure_Id INT(10),
                        TId INT(10),
                        Coure_Name VARCHAR(10),
                        SId INT(10),
                        Coure_Date DATE,
                        CONSTRAINT PRIMARY KEY (Coure_Id),
                        CONSTRAINT FOREIGN KEY (SId) REFERENCES Student(SId),
                        CONSTRAINT FOREIGN KEY (TId) REFERENCES Teacher(TId)
);


CREATE TABLE Exam (
                      SId INT(10),
                      Coure_Id INT(10),
                      Name VARCHAR(10),
                      Subject VARCHAR(10),
                      Marks INT(10),
                      CONSTRAINT FOREIGN KEY (Coure_Id) REFERENCES Course (Coure_Id),
                      CONSTRAINT FOREIGN KEY (SId) REFERENCES Student(SId)
);


CREATE TABLE Salary (
                        TId INT(10),
                        Salary_Id INT(10) NOT NULL,
                        Fee Decimal(12,2),
                        SDate DATE NOT NULL,
                        CONSTRAINT FOREIGN KEY (TId) REFERENCES Teacher(TId)
);


CREATE TABLE Course_student_dis (
                                    Coure_Id INT(10),
                                    SId INT(10),
                                    CName VARCHAR(10),
                                    CDate DATE,
                                    CONSTRAINT FOREIGN KEY (SId) REFERENCES Student(SId),
                                    CONSTRAINT FOREIGN KEY (Coure_Id) REFERENCES Course(Coure_Id)
);


