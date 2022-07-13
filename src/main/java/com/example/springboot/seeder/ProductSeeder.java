package com.example.springboot.seeder;

import com.example.springboot.entity.Category;
import com.example.springboot.entity.Product;
import com.example.springboot.repository.ProductRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class ProductSeeder // implements CommandLineRunner
{
    @Autowired
    ProductRepository productRepository;

    private static final int NUMBER_OF_PRODUCT = 100;

    public static ArrayList<Product> products;

    public void generate() {
        Random random = new Random();
        Faker faker = new Faker();
        int maxIndexCategory = CategorySeeder.categories.size() - 1;
        int minIndexCategory = 0;
        products = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_PRODUCT; i++) {
            Product product = Product.builder()
                    .name(faker.name().name())
                    .category(CategorySeeder.categories.get(random.nextInt(maxIndexCategory - minIndexCategory) + minIndexCategory))
                    .build();
            products.add(product);
        }
        productRepository.saveAll(products);
    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        Faker faker = new Faker();
//        int maxPrice = 20000;
//        int minPrice = 1000;
//        Random random = new Random();
//        int numberOfProduct = 0;
//        List<Product> products = new ArrayList<>();
//        for (int i = 0; i < numberOfProduct; i++) {
//            products.add(Product.builder()
//                    .name(faker.name().title())
//                    .price(random.nextInt(maxPrice - minPrice) + minPrice)
//                    .status(1)
//                    .build());
//        }
//        productRepository.saveAll(products);
//    }
}