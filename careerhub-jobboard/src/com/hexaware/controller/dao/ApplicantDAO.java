package com.hexaware.controller.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.model.entity.Applicant;
import com.hexaware.util.DBConnUtil;



public class ApplicantDAO implements IApplicant {

    @Override
    public void insertApplicant(Applicant applicant) {
        try (Connection connection = DBConnUtil.getConnection()) {
            String sql = "INSERT INTO Applicants (FirstName, LastName, Email, Phone, Resume) VALUES (?, ?, ?, ?, ?)";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, applicant.getFirstName());
                preparedStatement.setString(2, applicant.getLastName());
                preparedStatement.setString(3, applicant.getEmail());
                preparedStatement.setString(4, applicant.getPhone());
                preparedStatement.setString(5, applicant.getResume());
                
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Applicant> getApplicants() {
        List<Applicant> applicants = new ArrayList<>();

        try (Connection connection = DBConnUtil.getConnection()) {
            String sql = "SELECT * FROM Applicants";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Applicant applicant = new Applicant();
                        applicant.setApplicantID(resultSet.getInt("ApplicantID"));
                        applicant.setFirstName(resultSet.getString("FirstName"));
                        applicant.setLastName(resultSet.getString("LastName"));
                        applicant.setEmail(resultSet.getString("Email"));
                        applicant.setPhone(resultSet.getString("Phone"));
                        applicant.setResume(resultSet.getString("Resume"));

                        applicants.add(applicant);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return applicants;
    }

    @Override
    public Applicant getApplicantById(int applicantID) {
        Applicant applicant = null;

        try (Connection connection = DBConnUtil.getConnection()) {
            String sql = "SELECT * FROM Applicants WHERE ApplicantID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, applicantID);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        applicant = new Applicant();
                        applicant.setApplicantID(resultSet.getInt("ApplicantID"));
                        applicant.setFirstName(resultSet.getString("FirstName"));
                        applicant.setLastName(resultSet.getString("LastName"));
                        applicant.setEmail(resultSet.getString("Email"));
                        applicant.setPhone(resultSet.getString("Phone"));
                        applicant.setResume(resultSet.getString("Resume"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return applicant;
    }

    
}
