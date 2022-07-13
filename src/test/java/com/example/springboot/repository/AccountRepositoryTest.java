package com.example.springboot.repository;

import com.example.springboot.SpringbootApplication;
import com.example.springboot.entity.Order;
import com.example.springboot.entity.OrderDetail;
import com.example.springboot.entity.OrderDetailId;
import com.example.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository oderRepository;

    List<Product> products = productRepository.findAll();

    @Test
    public void testLinhTinh() {
        Order order = Order.builder()
                .id(System.currentTimeMillis())
                .createdAt(LocalDateTime.now())
                .status(1)
                .build();
        HashSet<OrderDetail> orderDetails = new HashSet<>();
        for (Product product :
                products) {
            OrderDetail orderDetail = OrderDetail.builder()
                    .orderDetailId(OrderDetailId.builder()
                            .orderId(order.getId())
                            .productId(product.getId())
                            .build())
                    .order(order)
                    .product(product)
                    .quantity(10)
                    .unitPrice(0)
                    .build();
            orderDetails.add(orderDetail);
        }
        order.setOrderDetails(orderDetails);
        oderRepository.save(order);
        System.out.println("Hello");
    }
}