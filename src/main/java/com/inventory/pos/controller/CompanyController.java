package com.inventory.pos.controller;


import com.inventory.pos.dto.CompanyResponse;
import com.inventory.pos.dto.ResponseMessage;
import com.inventory.pos.entity.Company;
import com.inventory.pos.request.CreateCompanyRequest;
import com.inventory.pos.request.UpdateCompanyRequest;
import com.inventory.pos.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company/")
@CrossOrigin("http://localhost:4200")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @PostMapping("create")
    public CompanyResponse createCompany(@RequestBody CreateCompanyRequest createCompanyRequest){
        Company company=companyService.createCompany(createCompanyRequest);
        return new CompanyResponse(company);
    }

    @PutMapping("update")
    public CompanyResponse updateCompany(@RequestBody UpdateCompanyRequest updateCompanyRequest){
        Company company= companyService.updateCompany(updateCompanyRequest);
        return new CompanyResponse(company);
    }

    @DeleteMapping("delete")
    public ResponseEntity deleteCompany(@RequestParam Long id){
        String result=companyService.deleteCompany(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(result));
    }
}
