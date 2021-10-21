package com.inventory.pos.service;

import com.inventory.pos.dao.SaleRepository;
import com.inventory.pos.entity.Sale;
import com.inventory.pos.request.SaleRequest;
import com.inventory.pos.request.UpdateSaleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SaleService {
    @Autowired
    SaleRepository saleRepository;
    public void createSale(SaleRequest saleRequest){
        Sale sale=new Sale(saleRequest);
        saleRepository.save(sale);
    }

    public Sale getSaleByOrderDate(Date date){
        Sale sale= this.saleRepository.findAllByOrderDate(date);
        return sale;
    }

    public void updateSale(UpdateSaleRequest updateSaleRequest){
        Sale sale= new Sale();
        sale.setId(updateSaleRequest.getId());
        sale.setTotalSale(updateSaleRequest.getTotalSale());
        sale.setOrderDate(updateSaleRequest.getOrderDate());
        saleRepository.save(sale);
    }
    public List<Sale>findTop10ByOrderByOrderDate(){
        return this.saleRepository.findTop10ByOrderByOrderDate();
    }
}
