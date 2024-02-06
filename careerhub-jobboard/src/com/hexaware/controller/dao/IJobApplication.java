package com.hexaware.controller.dao;

import java.util.List;

import com.hexaware.model.entity.JobApplication;



public interface IJobApplication {
    void addJobApplication(JobApplication jobApplication);

    List<JobApplication> getApplicationsForJob(int jobID);

    List<JobApplication> getApplicationsForApplicant(int applicantID);

    
}
