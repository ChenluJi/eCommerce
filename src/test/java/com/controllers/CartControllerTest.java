package com.controllers;

import com.TestUtils;
import com.model.persistence.Cart;
import com.model.persistence.Item;
import com.model.persistence.User;
import com.model.persistence.repositories.CartRepository;
import com.model.persistence.repositories.ItemRepository;
import com.model.persistence.repositories.UserRepository;
import com.model.requests.ModifyCartRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

public class CartControllerTest {

    private CartController cartController;

    private UserRepository userRepo = mock(UserRepository.class);

    private CartRepository cartRepo = mock(CartRepository.class);
    private ItemRepository itemRepo = mock(ItemRepository.class);

    private BCryptPasswordEncoder encoder = mock(BCryptPasswordEncoder.class);

    @Before
    public void setUp() {

        cartController = new CartController();
        TestUtils.injectObjects(cartController, "cartRepository", cartRepo);
        TestUtils.injectObjects(cartController, "userRepository", userRepo);
        TestUtils.injectObjects(cartController, "itemRepository", itemRepo);
        User user = new User();
        Cart cart = new Cart();
        List<Item> items = new ArrayList<>();
        Item item = new Item();
        item.setPrice(new BigDecimal(0));
        items.add(item);
        user.setUsername("test");
        user.setPassword("testPassword");
        cart.setUser(user);
        cart.setItems(items);
        user.setCart(cart);
        Optional<Item> optional = Optional.of(item);
        // mock repository methods
        when(userRepo.findByUsername(any(String.class))).thenReturn(user);
        when(itemRepo.findById(any(Long.class))).thenReturn(optional);
        userRepo.save(user);
        cartRepo.save(cart);

    }

    @Test
    public void addToCartTest() throws Exception {

        ModifyCartRequest r = new ModifyCartRequest();
        r.setUsername("test");
        r.setQuantity(1);

        final ResponseEntity<Cart> response = cartController.addTocart(r);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void removeFromCartTest() throws Exception {
        ModifyCartRequest r = new ModifyCartRequest();
        r.setUsername("test");
        r.setQuantity(1);
        final ResponseEntity<Cart> response = cartController.removeFromcart(r);
        assertNotNull(response);


    }

}
