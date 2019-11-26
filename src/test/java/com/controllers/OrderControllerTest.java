package com.controllers;

import com.TestUtils;
import com.model.persistence.User;
import com.model.persistence.UserOrder;
import com.model.persistence.repositories.OrderRepository;
import com.model.persistence.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class OrderControllerTest {
    private OrderController orderController;

    private OrderRepository orderRepo = mock(OrderRepository.class);
    private UserRepository userRepo = mock(UserRepository.class);


    @Before
    public void setUp() {
        orderController = new OrderController();
        TestUtils.injectObjects(orderController, "orderRepository", orderRepo);
        TestUtils.injectObjects(orderController, "userRepository", userRepo);
    }

    @Test
    public void submitOrderTest() throws Exception {
        UserOrder order = new UserOrder();
        User user = new User();
        user.setUsername("test");
        order.setUser(user);

        final ResponseEntity<UserOrder> response = orderController.submit("test");
        assertNotNull(response);
    }

    @Test
    public void getOrderTest() throws Exception {

        final ResponseEntity<List<UserOrder>> response = orderController.getOrdersForUser("test");
        assertNotNull(response);

    }



}
