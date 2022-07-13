package com.example.springboot.search;

import com.example.springboot.SpringbootApplication;
import com.example.springboot.entity.Order;
import com.example.springboot.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
class OrderSpecificationTest {
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void demoTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        OrderSpecification filter01 = new OrderSpecification(new SearchCriteria("name", "=", ""));
        OrderSpecification filter02 = new OrderSpecification(new SearchCriteria("price", ">=", 11000));
        OrderSpecification filter03 = new OrderSpecification(new SearchCriteria("price", "<=", 21000));
        List<Order> orders = orderRepository.findAll(filter01.and(filter02).and(filter03));
        System.out.println(orders.size());
    }
}