package com.crudApp.mountain;

import com.crudApp.mountain.mapper.MountainMapper;
import com.crudApp.mountain.repository.MountainRepository;
import com.crudApp.mountain.service.MountainService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//		(exclude = {DataSourceAutoConfiguration.class })
public class MountainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MountainApplication.class, args);
	}

}
