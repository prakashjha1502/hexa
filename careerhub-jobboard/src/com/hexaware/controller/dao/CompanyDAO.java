package com.hexaware.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.model.entity.Company;
import com.hexaware.util.DBConnUtil;



public class CompanyDAO implements ICompany {

    public void addCompany(Company company) {
        try (Connection connection = DBConnUtil.getConnection()) {
            String sql = "INSERT INTO Companies (CompanyName, Location) VALUES (?, ?)";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, company.getCompanyName());
                preparedStatement.setString(2, company.getLocation());
                
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Company> getCompanies() {
        List<Company> companies = new ArrayList<>();

        try (Connection connection = DBConnUtil.getConnection()) {
            String sql = "SELECT * FROM Companies";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Company company = new Company();
                        company.setCompanyID(resultSet.getInt("CompanyID"));
                        company.setCompanyName(resultSet.getString("CompanyName"));
                        company.setLocation(resultSet.getString("Location"));

                        companies.add(company);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return companies;
    }

    @Override
    public Company getCompanyById(int companyID) {
        Company company = null;

        try (Connection connection = DBConnUtil.getConnection()) {
            String sql = "SELECT * FROM Companies WHERE CompanyID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, companyID);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        company = new Company();
                        company.setCompanyID(resultSet.getInt("CompanyID"));
                        company.setCompanyName(resultSet.getString("CompanyName"));
                        company.setLocation(resultSet.getString("Location"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return company;
    }
    
    
}