package com.raihan.Companyms.Company;

import java.util.List;

import com.raihan.Companyms.Company.dto.ReviewMessage;



public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Company company, Long id);
    void addCompany(Company company);
    Company getCompanyById(Long id);
    boolean deleteCompany(Company company);
    public void updateCompanyRating(ReviewMessage reviewMessage);
}
