package com.hexaware.model.entity;





public class JobApplication {
    private int applicationID;
    private int jobID;
    private int applicantID;
    private java.sql.Date applicationDate;
    private String coverLetter;

    

  

    public int getApplicationID() {
		return applicationID;
	}



	public void setApplicationID(int applicationID) {
		this.applicationID = applicationID;
	}



	public int getJobID() {
		return jobID;
	}



	public void setJobID(int jobID) {
		this.jobID = jobID;
	}



	public int getApplicantID() {
		return applicantID;
	}



	public void setApplicantID(int applicantID) {
		this.applicantID = applicantID;
	}



	public java.sql.Date getApplicationDate() {
		return applicationDate;
	}



	public void setApplicationDate(java.sql.Date applicationDate) {
		this.applicationDate = applicationDate;
	}



	public String getCoverLetter() {
		return coverLetter;
	}



	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}




}

