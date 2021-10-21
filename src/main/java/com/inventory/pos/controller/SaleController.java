package com.inventory.pos.controller;

import com.inventory.pos.entity.Sale;
import com.inventory.pos.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sale/")
@CrossOrigin("http://localhost:4200")
public class SaleController {
    @Autowired
    SaleService saleService;


    @GetMapping("findTop10ByOrderByOrderDate")
    public List<Sale> findTop10ByOrderByOrderDate(){
        return saleService.findTop10ByOrderByOrderDate();
    }
}
