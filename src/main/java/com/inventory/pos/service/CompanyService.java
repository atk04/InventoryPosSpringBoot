package com.inventory.pos.service;

import com.inventory.pos.dao.CompanyRepository;
import com.inventory.pos.entity.Company;
import com.inventory.pos.request.CreateCompanyRequest;
import com.inventory.pos.request.UpdateCompanyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public Company createCompany(CreateCompanyRequest createCompanyRequest){
        Company company=new Company(createCompanyRequest);
        company=companyRepository.save(company);
        return company;
    }

    public Company updateCompany(UpdateCompanyRequest updateCompanyRequest){
        Company company=companyRepository.findById(updateCompanyRequest.getId()).get();
        company.setName(updateCompanyRequest.getName());
        company.setAddress(updateCompanyRequest.getAddress());
        company.setPhoneNumber(updateCompanyRequest.getPhoneNumber());
        company.setEmailAddress(updateCompanyRequest.getEmailAddress());
        company.setWebsiteAddress(updateCompanyRequest.getWebsiteAddress());
        companyRepository.save(company);
        return company;
    }

    public String deleteCompany(Long id){
        Company company=companyRepository.findById(id).get();
        String companyName=company.getName();
        companyRepository.deleteById(id);
        return companyName;
    }
}
