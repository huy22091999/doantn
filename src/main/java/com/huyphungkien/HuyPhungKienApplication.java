package com.huyphungkien;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.huyphungkien.config.StorageProperties;
import com.huyphungkien.domain.Product;
import com.huyphungkien.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class HuyPhungKienApplication {
	public static void main(String[] args)
	{
		SpringApplication.run(HuyPhungKienApplication.class, args);
		Product n=new Product();
	}
	@Bean
	CommandLineRunner init(StorageService ss) {
		return(args->{
			ss.init();
		});
	}

}
