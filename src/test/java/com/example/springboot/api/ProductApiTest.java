package com.example.springboot.api;

import com.example.springboot.SpringbootApplication;
import com.example.springboot.entity.Product;
import com.example.springboot.repository.ProductRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringbootApplication.class})
class ProductApiTest {
    MockMvc mockMvc;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductApi productApi;
    @Before
    public void preTest() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(productApi)
                .build();
    }

//    @Test
//    public void requestBodyTest() {
//        try {
//            when(productApi.findAll()).thenReturn((ResponseEntity<List<Product>>) productRepository.findAll());
//            mockMvc
//                    .perform(get("/api/v1/products")
//                            .content(String.valueOf(new Product()))
//                            .contentType("application/json"))
//                    .andDo(MockMvcResultHandlers.print())
//                    .andExpect(status().isOk());
//        } catch (Exception e) {
//            System.out.println("Exception: " + e);
//        }
//    }
}