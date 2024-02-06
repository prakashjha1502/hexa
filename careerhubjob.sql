USE joblistingnew;


CREATE TABLE JobListings (
    JobID INT PRIMARY KEY AUTO_INCREMENT,
    CompanyID INT,
    JobTitle VARCHAR(255),
    JobDescription TEXT,
    JobLocation VARCHAR(255),
    Salary DECIMAL(10, 2),
    JobType VARCHAR(50),
    PostedDate DATE,
    FOREIGN KEY (CompanyID) REFERENCES Companies(CompanyID)
);


CREATE TABLE Companies (
    CompanyID INT PRIMARY KEY AUTO_INCREMENT,
    CompanyName VARCHAR(255),
    Location VARCHAR(255)
);

INSERT into cOMPANIES


CREATE TABLE Applicants (
    ApplicantID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(100),
    LastName VARCHAR(100),
    Email VARCHAR(255),
    Phone VARCHAR(20),
    Resume VARCHAR(255) 
);


CREATE TABLE JobApplications (
    ApplicationID INT PRIMARY KEY AUTO_INCREMENT,
    JobID INT,
    ApplicantID INT,
    ApplicationDate DATE,
    CoverLetter VARCHAR(255),
    FOREIGN KEY (JobID) REFERENCES JobListings(JobID),
    FOREIGN KEY (ApplicantID) REFERENCES Applicants(ApplicantID)
);


select * from JobListings;
select * from Companies;
select * from Applicants;
select * from JobApplications;