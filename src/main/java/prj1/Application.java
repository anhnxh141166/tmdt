package prj1;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import prj1.domains.Category;
import prj1.services.CategoryService;

@SpringBootApplication
        (exclude = {
//                DataSourceAutoConfiguration.class ,
//                MongoAutoConfiguration.class
//                ,
//                MongoDataAutoConfiguration.class
        })
//@EnableMongoRepositories
public class Application {
//    @Bean
//    CommandLineRunner commandLineRunner(CategoryService categoryService) {
//        return args -> {
//            Category category = new Category("1", null, "Computer", null);
//            categoryService.createOrUpdateCategory(category);
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
