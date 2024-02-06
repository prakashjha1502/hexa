package com.hexaware.controller.dao;

import java.util.List;

import com.hexaware.model.entity.Company;



public interface ICompany {
    void addCompany(Company company);

    List<Company> getCompanies();

    Company getCompanyById(int companyID);

    
}