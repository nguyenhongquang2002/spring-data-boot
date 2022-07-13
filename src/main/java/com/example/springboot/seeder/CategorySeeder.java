package com.example.springboot.seeder;

import com.example.springboot.entity.Category;
import com.example.springboot.repository.CategoryRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CategorySeeder // implements CommandLineRunner
{
    @Autowired
    CategoryRepository categoryRepository;

    private static final int NUMBER_OF_CATEGORY = 100;
    public static ArrayList<Category> categories;

    public void generate() {
        Faker faker = new Faker();
        categories = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_CATEGORY; i++) {
            Category category = Category.builder()
                    .name(faker.name().name())
                    .build();
            categories.add(category);
        }
        categoryRepository.saveAll(categories);
    }
}