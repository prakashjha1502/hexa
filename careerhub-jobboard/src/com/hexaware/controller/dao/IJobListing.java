package com.hexaware.controller.dao;


import java.util.List;

import com.hexaware.model.entity.JobListing;



public interface IJobListing {
    void insertJobListing(JobListing jobListing);

    List<JobListing> getJobListings();

    List<JobListing> getJobListingsForCompany(int companyID);

    JobListing getJobListingById(int jobID);

	List<JobListing> getJobListingsInRange(double from, double to);

   
}
