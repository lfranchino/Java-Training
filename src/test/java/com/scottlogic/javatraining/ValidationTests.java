package com.scottlogic.javatraining;

import com.scottlogic.javatraining.classes.*;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ValidationTests {

    private ValidatorFactory validatorFactory;
    private Validator validator;

    @BeforeEach
    public void setUp() {
        System.out.println("Testing Validation");
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterEach
    public void tearDown() {
        validatorFactory.close();
    }

    @Test
    @DisplayName("Order validation stops empty orders from being made")
    void test() {
        SellOrder sellOrder = new SellOrder(null, 1, 1);
        SellOrder sellOrder1 = new SellOrder("Test", 0, 1);
        SellOrder sellOrder2 = new SellOrder("Test", 1, 0);
        Set<ConstraintViolation<SellOrder>> violations1 = validator.validate(sellOrder);
        Set<ConstraintViolation<SellOrder>> violations2 = validator.validate(sellOrder1);
        Set<ConstraintViolation<SellOrder>> violations3 = validator.validate(sellOrder2);

        assertTrue(!violations1.isEmpty());
        assertTrue(!violations2.isEmpty());
        assertTrue(!violations3.isEmpty());
    }
}
