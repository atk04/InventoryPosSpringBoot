package com.inventory.pos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCompanyRequest {

    private String name;
    private String address;
    private String phoneNumber;
    private String emailAddress;
    private String websiteAddress;
}
