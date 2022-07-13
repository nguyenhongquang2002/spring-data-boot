package com.example.springboot.repository;

import com.example.springboot.SpringbootApplication;
import com.example.springboot.entity.Product;
import com.example.springboot.util.StringUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringbootApplication.class})
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    public void testPaging() {
        int page = 3;
        int limit = 10;
        Page<Product> pageProduct = productRepository.findAll(PageRequest.of(page -1, limit, Sort.by(Sort.Direction.DESC, "id")));
        System.out.println("Total elements: " + pageProduct.getTotalElements());
        System.out.println("Total pages: " + pageProduct.getTotalPages());
        System.out.println("Current page: " + pageProduct.getNumber());
        System.out.println("Current limit: " + pageProduct.getSize());
        List<Product> list = pageProduct.getContent();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }

//    @Test
//    public void findAllByName() {
//        List<Product> list = productRepository.search("%A%");
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).toString());
//        }
//    }

//    @Test
//    public void testSaveProduct() {
//        Product product = Product.builder()
//                .name("Cuong")
//                .description("Free")
//                .slug(StringUtil.toSlug("Cuong"))
//                .thumbnail("cuong.png")
//                .price(1000)
//                .status(1)
//                .build();
//        productRepository.save(product);
//    }

    @Test
    public void testGetList() {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

//    @Test
//    public void testGetById() {
//        Optional<Product> optionalProduct = productRepository.findById(1);
//        if (optionalProduct.isPresent()) {
//            Product product = optionalProduct.get();
//            System.out.println(product.getName().equals("Cuong"));
//        }
//    }

    @Test
    public void testDelete() {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }
}