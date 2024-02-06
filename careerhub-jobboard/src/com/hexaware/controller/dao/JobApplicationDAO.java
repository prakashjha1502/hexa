package com.hexaware.controller.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.model.entity.JobApplication;
import com.hexaware.util.DBConnUtil;



public class JobApplicationDAO implements IJobApplication {

    @Override
    public void addJobApplication(JobApplication jobApplication) {
        try (Connection connection = DBConnUtil.getConnection()) {
            String sql = "INSERT INTO JobApplications (JobID, ApplicantID, ApplicationDate, CoverLetter) VALUES (?, ?, ?, ?)";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, jobApplication.getJobID());
                preparedStatement.setInt(2, jobApplication.getApplicantID());
                preparedStatement.setDate(3, new java.sql.Date(System.currentTimeMillis())); 
                preparedStatement.setString(4, jobApplication.getCoverLetter());
                
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<JobApplication> getApplicationsForJob(int jobID) {
        List<JobApplication> jobApplications = new ArrayList<>();

        try (Connection connection = DBConnUtil.getConnection()) {
            String sql = "SELECT * FROM JobApplications WHERE JobID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, jobID);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        JobApplication jobApplication = new JobApplication();
                        jobApplication.setApplicationID(resultSet.getInt("ApplicationID"));
                        jobApplication.setJobID(resultSet.getInt("JobID"));
                        jobApplication.setApplicantID(resultSet.getInt("ApplicantID"));
                        jobApplication.setApplicationDate(resultSet.getDate("ApplicationDate"));
                        jobApplication.setCoverLetter(resultSet.getString("CoverLetter"));

                        jobApplications.add(jobApplication);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobApplications;
    }

    @Override
    public List<JobApplication> getApplicationsForApplicant(int applicantID) {
        List<JobApplication> jobApplications = new ArrayList<>();

        try (Connection connection = DBConnUtil.getConnection()) {
            String sql = "SELECT * FROM JobApplications WHERE ApplicantID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, applicantID);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        JobApplication jobApplication = new JobApplication();
                        jobApplication.setApplicationID(resultSet.getInt("ApplicationID"));
                        jobApplication.setJobID(resultSet.getInt("JobID"));
                        jobApplication.setApplicantID(resultSet.getInt("ApplicantID"));
                        jobApplication.setApplicationDate(resultSet.getDate("ApplicationDate"));
                        jobApplication.setCoverLetter(resultSet.getString("CoverLetter"));

                        jobApplications.add(jobApplication);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobApplications;
    }

    // Additional methods for job application operations if needed
}

