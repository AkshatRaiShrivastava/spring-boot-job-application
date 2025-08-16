package com.akshat.FirstSpring.company.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.akshat.FirstSpring.company.Company;
import com.akshat.FirstSpring.company.CompanyRepository;
import com.akshat.FirstSpring.company.CompanyService;


@Service
public class CompanyServiceImplementation implements CompanyService{
    private CompanyRepository companyRepository;
    
    public CompanyServiceImplementation(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
       return companyRepository.findAll();
    }
    
}
