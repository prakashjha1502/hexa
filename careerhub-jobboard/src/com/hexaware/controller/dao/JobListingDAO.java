package com.hexaware.controller.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.exception.InvalidSalaryException;
import com.hexaware.model.entity.JobListing;
import com.hexaware.util.DBConnUtil;


public class JobListingDAO implements IJobListing {

    @Override
    public void insertJobListing(JobListing jobListing) {
        try (Connection connection = DBConnUtil.getConnection()) {
            String sql = "INSERT INTO JobListings (CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType, PostedDate) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, jobListing.getCompanyID());
                preparedStatement.setString(2, jobListing.getJobTitle());
                preparedStatement.setString(3, jobListing.getJobDescription());
                preparedStatement.setString(4, jobListing.getJobLocation());
                preparedStatement.setDouble(5, jobListing.getSalary());
                preparedStatement.setString(6, jobListing.getJobType());
                preparedStatement.setDate(7, new java.sql.Date(System.currentTimeMillis())); 
                
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<JobListing> getJobListings() {
        List<JobListing> jobListings = new ArrayList<>();

        try (Connection connection = DBConnUtil.getConnection()) {
            String sql = "SELECT * FROM JobListings";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        JobListing jobListing = new JobListing();
                        jobListing.setJobID(resultSet.getInt("JobID"));
                        jobListing.setCompanyID(resultSet.getInt("CompanyID"));
                        jobListing.setJobTitle(resultSet.getString("JobTitle"));
                        jobListing.setJobDescription(resultSet.getString("JobDescription"));
                        jobListing.setJobLocation(resultSet.getString("JobLocation"));
                        jobListing.setSalary(resultSet.getDouble("Salary"));
                        jobListing.setJobType(resultSet.getString("JobType"));
                        jobListing.setPostedDate(resultSet.getDate("PostedDate"));

                        jobListings.add(jobListing);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobListings;
    }

    @Override
    public List<JobListing> getJobListingsForCompany(int companyID) {
        List<JobListing> jobListings = new ArrayList<>();

        try (Connection connection = DBConnUtil.getConnection()) {
            String sql = "SELECT * FROM JobListings WHERE CompanyID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, companyID);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        JobListing jobListing = new JobListing();
                        jobListing.setJobID(resultSet.getInt("JobID"));
                        jobListing.setCompanyID(resultSet.getInt("CompanyID"));
                        jobListing.setJobTitle(resultSet.getString("JobTitle"));
                        jobListing.setJobDescription(resultSet.getString("JobDescription"));
                        jobListing.setJobLocation(resultSet.getString("JobLocation"));
                        jobListing.setSalary(resultSet.getDouble("Salary"));
                        jobListing.setJobType(resultSet.getString("JobType"));
                        jobListing.setPostedDate(resultSet.getDate("PostedDate"));

                        jobListings.add(jobListing);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobListings;
    }

    @Override
    public JobListing getJobListingById(int jobID) {
        JobListing jobListing = null;

        try (Connection connection = DBConnUtil.getConnection()) {
            String sql = "SELECT * FROM JobListings WHERE JobID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, jobID);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        jobListing = new JobListing();
                        jobListing.setJobID(resultSet.getInt("JobID"));
                        jobListing.setCompanyID(resultSet.getInt("CompanyID"));
                        jobListing.setJobTitle(resultSet.getString("JobTitle"));
                        jobListing.setJobDescription(resultSet.getString("JobDescription"));
                        jobListing.setJobLocation(resultSet.getString("JobLocation"));
                        jobListing.setSalary(resultSet.getDouble("Salary"));
                        jobListing.setJobType(resultSet.getString("JobType"));
                        jobListing.setPostedDate(resultSet.getDate("PostedDate"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobListing;
    }
    
    
    @Override
    public List<JobListing> getJobListingsInRange(double from, double to) {
        List<JobListing> jobListings = new ArrayList<>();

        try (Connection connection = DBConnUtil.getConnection()) {
            String sql = "SELECT * FROM JobListings WHERE Salary BETWEEN ? AND ?";
            

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            	
            	preparedStatement.setDouble(1,from);
            	preparedStatement.setDouble(2,to);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        JobListing jobListing = new JobListing();
                        jobListing.setJobID(resultSet.getInt("JobID"));
                        jobListing.setCompanyID(resultSet.getInt("CompanyID"));
                        jobListing.setJobTitle(resultSet.getString("JobTitle"));
                        jobListing.setJobDescription(resultSet.getString("JobDescription"));
                        jobListing.setJobLocation(resultSet.getString("JobLocation"));
                        jobListing.setSalary(resultSet.getDouble("Salary"));
                        jobListing.setJobType(resultSet.getString("JobType"));
                        jobListing.setPostedDate(resultSet.getDate("PostedDate"));

                        jobListings.add(jobListing);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobListings;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static double calculateAverageSalary() throws InvalidSalaryException {
        String sql = "SELECT Salary FROM JobListing";
        double totalSalary = 0;
        int numberOfJobs = 0;

        try (Connection connection = DBConnUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
        	
            while (resultSet.next()) {
                double salary = resultSet.getDouble("Salary");
                totalSalary = totalSalary + salary;
                numberOfJobs++;
            }
            
            if(totalSalary > 0) {
            	return (numberOfJobs > 0) ? (totalSalary / numberOfJobs) : 0;
            }else throw new InvalidSalaryException("Error calcualting average salary");
        } 
        catch (SQLException e) {
            e.printStackTrace();
           
        }catch(InvalidSalaryException e){
        	System.out.println(e);
        }

        
        return (numberOfJobs > 0) ? (totalSalary / numberOfJobs) : 0;
    }
   
}

