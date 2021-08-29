package com.inventory.pos;

import com.inventory.pos.service.FilesStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class PosApplication implements CommandLineRunner {
	@Resource
	FilesStorageService storageService;
	public static void main(String[] args) {
		SpringApplication.run(PosApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		storageService.init();
	}
}
