package com.akshat.FirstSpring.company.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.akshat.FirstSpring.company.Company;
import com.akshat.FirstSpring.company.CompanyRepository;
import com.akshat.FirstSpring.company.CompanyService;

@Service
public class CompanyServiceImplementation implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImplementation(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company updatedCompany, Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public boolean createCompany(Company company) {
        try {
            companyRepository.save(company);
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        try{
            companyRepository.deleteById(id);
        }catch(Exception e){
            System.out.println(e.toString());
            return false;
        }

        return true;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

}
