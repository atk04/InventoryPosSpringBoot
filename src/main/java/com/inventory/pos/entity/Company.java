package com.inventory.pos.entity;

import com.inventory.pos.request.CreateCompanyRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="company")
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="email_address")
    private String emailAddress;

    @Column(name="website_address")
    private String websiteAddress;

    @OneToMany(cascade=CascadeType.ALL,mappedBy = "company")
    private Set<Invoice>invoices=new HashSet<>();

    public Company(CreateCompanyRequest createCompanyRequest){
        this.name=createCompanyRequest.getName();
        this.address=createCompanyRequest.getAddress();
        this.phoneNumber=createCompanyRequest.getPhoneNumber();
        this.emailAddress=createCompanyRequest.getEmailAddress();
        this.websiteAddress=createCompanyRequest.getWebsiteAddress();
    }

}
