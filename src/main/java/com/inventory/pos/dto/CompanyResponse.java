package com.inventory.pos.dto;

import com.inventory.pos.entity.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResponse {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String emailAddress;
    private String websiteAddress;

    public CompanyResponse(Company company){
        this.id=company.getId();
        this.name=company.getName();
        this.address=company.getAddress();
        this.phoneNumber=company.getPhoneNumber();
        this.emailAddress=company.getEmailAddress();
        this.websiteAddress=company.getWebsiteAddress();
    }
}
