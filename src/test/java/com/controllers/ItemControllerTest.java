package com.controllers;

import com.TestUtils;
import com.model.persistence.Cart;
import com.model.persistence.Item;
import com.model.persistence.User;
import com.model.persistence.repositories.ItemRepository;
import com.model.persistence.repositories.UserRepository;
import com.model.requests.ModifyCartRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class ItemControllerTest {
    private ItemController itemController;

    private ItemRepository itemRepo = mock(ItemRepository.class);

    @Before
    public void setUp() {
        itemController = new ItemController();
        TestUtils.injectObjects(itemController, "itemRepository", itemRepo);
    }

    @Test
    public void getItemTest() throws Exception {

        final ResponseEntity<Item> response = itemController.getItemById(0L);
        assertNotNull(response);

        final ResponseEntity<List<Item>> response2 = itemController.getItemsByName("testItem");
        assertNotNull(response2);

        final ResponseEntity<List<Item>> response3 = itemController.getItems();
        assertNotNull(response3);


    }

}
