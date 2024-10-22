package com.raihan.Companyms.Company.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.raihan.Companyms.Company.Company;
import com.raihan.Companyms.Company.CompanyRepository;
import com.raihan.Companyms.Company.CompanyService;
import com.raihan.Companyms.Company.clients.ReviewClient;
import com.raihan.Companyms.Company.dto.ReviewMessage;

import jakarta.ws.rs.NotFoundException;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private ReviewClient reviewClient;

    public CompanyServiceImpl(CompanyRepository companyRepository, ReviewClient reviewClient) {
        this.companyRepository = companyRepository;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setName(company.getName());
            companyToUpdate.setDescription(company.getDescription());
            companyRepository.save(companyToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public void addCompany(Company company) {

        companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompany(Company company) {
        try{
            companyRepository.delete(company);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        Company company = companyRepository.findById(reviewMessage.getCompanyId()).orElseThrow(() -> new NotFoundException("Company not found"));
        Double avgRating = reviewClient.getAverageRating(reviewMessage.getCompanyId());
        company.setRating(avgRating);
        companyRepository.save(company);
    }
}
