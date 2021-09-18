package com.inventory.pos.config;

import com.inventory.pos.entity.Invoice;
import com.inventory.pos.entity.InvoiceDetail;
import com.inventory.pos.entity.Product;
import com.inventory.pos.entity.ProductCategory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {
    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer()
    {
        return RepositoryRestConfigurer.withConfig(config -> {
            config.exposeIdsFor(ProductCategory.class, Product.class, Invoice.class, InvoiceDetail.class);
        });
    }
}