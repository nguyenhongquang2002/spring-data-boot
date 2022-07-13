package com.example.springboot.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationSeeder implements CommandLineRunner {

    private final boolean needSeed = true;

    @Autowired
    CategorySeeder categorySeeder;

    @Autowired
    ProductSeeder productSeeder;

    @Override
    public void run(String... args) throws Exception {
        if (needSeed) {
            categorySeeder.generate();
            productSeeder.generate();
        }
    }
}
