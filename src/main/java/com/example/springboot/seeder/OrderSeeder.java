package com.example.springboot.seeder;

import com.example.springboot.entity.*;
import com.example.springboot.entity.enums.OrderSimpleStatus;
import com.example.springboot.repository.OrderRepository;

import com.example.springboot.util.DateTimeHelper;
import com.example.springboot.util.NumberUtil;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

@Component
@Slf4j
public class OrderSeeder {

    public static List<Order> orders;
    public static final int NUMBER_OF_ORDER = 1000;
    public static final int NUMBER_OF_DONE = 600;
    public static final int NUMBER_OF_CANCEL = 200;
    public static final int NUMBER_OF_PENDING = 200;
    public static final int MIN_ORDER_DETAIL = 2;
    public static final int MAX_ORDER_DETAIL = 5;
    public static final int MIN_PRODUCT_QUANTITY = 1;
    public static final int MAX_PRODUCT_QUANTITY = 5;

    @Autowired
    OrderRepository oderRepository;
    List<OrderSeedByTime> seeder;

    public void configData() {
        seeder = new ArrayList<>();
        seeder.add(OrderSeedByTime.builder()
                .orderStatus(OrderSimpleStatus.DONE)
                .seedTypeByTime(OrderSeedByTimeType.DAY)
                .day(18)
                .month(Month.JUNE)
                .year(2022)
                .build());
        seeder.add(OrderSeedByTime.builder()
                .orderStatus(OrderSimpleStatus.DONE)
                .seedTypeByTime(OrderSeedByTimeType.DAY)
                .day(17)
                .month(Month.JUNE)
                .year(2022)
                .build());
        seeder.add(OrderSeedByTime.builder()
                .orderStatus(OrderSimpleStatus.DONE)
                .seedTypeByTime(OrderSeedByTimeType.MONTH)
                .month(Month.JUNE)
                .year(2022)
                .build());
        seeder.add(OrderSeedByTime.builder()
                .orderStatus(OrderSimpleStatus.PROCESSING)
                .seedTypeByTime(OrderSeedByTimeType.MONTH)
                .month(Month.JUNE)
                .year(2022)
                .build());
        seeder.add(OrderSeedByTime.builder()
                .orderStatus(OrderSimpleStatus.DONE)
                .seedTypeByTime(OrderSeedByTimeType.MONTH)
                .month(Month.MAY)
                .year(2022)
                .build());
        seeder.add(OrderSeedByTime.builder()
                .orderStatus(OrderSimpleStatus.DONE)
                .seedTypeByTime(OrderSeedByTimeType.MONTH)
                .month(Month.APRIL)
                .year(2022)
                .build());
        seeder.add(OrderSeedByTime.builder()
                .orderStatus(OrderSimpleStatus.PROCESSING)
                .seedTypeByTime(OrderSeedByTimeType.MONTH)
                .month(Month.APRIL)
                .year(2022)
                .build());
        seeder.add(OrderSeedByTime.builder()
                .orderStatus(OrderSimpleStatus.DONE)
                .seedTypeByTime(OrderSeedByTimeType.MONTH)
                .month(Month.JUNE)
                .year(2022)
                .build());
    }

    public void generate() {
        configData();
        log.debug("-------------Seeding order -----------");
        Faker faker = new Faker();
        orders = new ArrayList<>();
        for (OrderSeedByTime orderSeedByTime :
                seeder) {
            int numberOfOrder = orderSeedByTime.getOrderCount();
            for (int i = 0; i < numberOfOrder; i++) {
                int randomUserIndex = NumberUtil.getRandomNumber(0, UserSeeder.users.size() - 1);
                User user = UserSeeder.users.get(randomUserIndex);
                Order order = new Order();
                order.setId(Long.parseLong((UUID.randomUUID().toString())));
                order.setStatus(orderSeedByTime.getOrderStatus());
                LocalDateTime orderCreatedTime = calculateOrderCreatedTime(orderSeedByTime);
                order.setCreatedAt(orderCreatedTime);
                order.setUpdatedAt(orderCreatedTime);
                order.setUserId(user.getId());
                Set<OrderDetail> orderDetails = new HashSet<>();
                HashMap<String, Product> mapProduct = new HashMap<>();
                int orderDetailNumber = NumberUtil.getRandomNumber(MIN_ORDER_DETAIL, MAX_ORDER_DETAIL);
                for (int j = 0; j < orderDetailNumber; j++) {
                    int ramdomProductIndex = NumberUtil.getRandomNumber(0, ProductSeeder.products.size() - 1);
                    Product product = ProductSeeder.products.get(ramdomProductIndex);
                    if (mapProduct.containsKey(product.getId())) {
                        continue;
                    }
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrderDetailId(new OrderDetailId(order.getId(), product.getId()));
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(product);
                    orderDetail.setUnitPrice(product.getPrice());
                    orderDetail.setQuantity(NumberUtil.getRandomNumber(MIN_PRODUCT_QUANTITY, MAX_PRODUCT_QUANTITY));
                    orderDetails.add(orderDetail);
                    mapProduct.put(product.getId(), product);
                }
                order.setOrderDetails(orderDetails);
                order.calculateTotalPrice();
                orders.add(order);
            }
        }
        oderRepository.saveAll(orders);
        log.debug("-----------------End of seeding order-----------------");
//        int maxIndexCategory = CategorySeeder.categories.size() - 1;
//        int minIndexCategory = 0;
//        ArrayList<Product> products = new ArrayList<>();
//        for (int i = 0; i < NUMBER_OF_ORDER; i++) {
//            Order order = Order.builder()
//                    .id(System.currentTimeMillis())
//                    .createdAt(LocalDateTime.now())
//                    .status(1)
//                    .build();
//            HashSet<OrderDetail> orderDetails = new HashSet<>();
//            HashSet<Long> productIds = new HashSet<>();
//            int orderDetailNumber = NumberUtil.;
//            for (int j = 0; j < orderDetailNumber; j++) {
//                int randomProductIndex = NumberUtil.genz
//                Product product = ProductSeeder.products.get();
//            }
//        }
    }

    private LocalDateTime calculateOrderCreatedTime(OrderSeedByTime orderSeedByTime) {
        LocalDateTime result = null;
        LocalDateTime tempLocalDateTime = null;
        int tempMonth = 1;
        int tempYear = 2022;
        switch (orderSeedByTime.getSeedTypeByTime()) {
            case YEAR:
                tempMonth = DateTimeHelper.getRandomMonth().getValue();
                tempYear = orderSeedByTime.getYear();
                tempLocalDateTime = LocalDateTime.of(tempYear, tempMonth, 1, 0, 0, 0);
                result = tempLocalDateTime.plusMonths(1).minusDays(1);
                break;
            case MONTH:
                tempMonth = orderSeedByTime.getMonth().getValue();
                tempYear = orderSeedByTime.getYear();
                tempLocalDateTime = LocalDateTime.of(tempYear, tempMonth, 1, 0, 0, 0);
                LocalDateTime lastDayOfMonth = tempLocalDateTime.plusMonths(1).minusDays(1);
                int ramdomDay = NumberUtil.getRandomNumber(1, lastDayOfMonth.getDayOfMonth());
                result = LocalDateTime.of(tempYear, tempMonth, ramdomDay, 0, 0, 0);
                if (result.isAfter(LocalDateTime.now())) {
                    ramdomDay = NumberUtil.getRandomNumber(1, LocalDateTime.now().getDayOfMonth());
                    result = LocalDateTime.of(tempYear, tempMonth, ramdomDay, 0, 0, 0);
                }
                break;
            case DAY:
                result = LocalDateTime.of(orderSeedByTime.getYear(), orderSeedByTime.getMonth(), orderSeedByTime.getDay(), 0, 0, 0);
                break;
        }
        return result;
    }
}
