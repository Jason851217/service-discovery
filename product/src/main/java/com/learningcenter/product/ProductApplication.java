package com.learningcenter.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;

@SpringBootApplication
@MapperScan(basePackages = "com.learningcenter.product.dao")
public class ProductApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ProductApplication.class, args);
        System.out.println(context.getBean(DataSource.class));
    }
}
