package com.zpc.operations.pop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.zpc.operations.pop")
@EnableJpaRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class)

public class PopApplication extends SpringBootServletInitializer {//

    public static void main(String[] args) {
        SpringApplication.run(PopApplication.class, args);
    }

}
// th:rel="stylesheet" th:href="@{webjars/bootstrap/4.0.0-2/css/bootstrap.min.css}"