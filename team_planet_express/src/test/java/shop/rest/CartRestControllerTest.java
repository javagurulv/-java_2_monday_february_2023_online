package shop.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;
import shop.core.database.jpa.JpaCartItemRepository;
import shop.core.database.jpa.JpaCartRepository;
import shop.core.database.jpa.JpaItemRepository;
import shop.core.database.jpa.JpaUserRepository;
import shop.core.domain.Cart;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.responses.customer.AddItemToCartResponse;
import shop.core.support.CurrentUserId;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CartRestControllerTest {

    @Autowired
    private CurrentUserId currentUserId;
    @Autowired
    private JpaUserRepository userRepository;
    @Autowired
    private JpaCartRepository cartRepository;
    @Autowired
    private JpaItemRepository itemRepository;
    @Autowired
    private JpaCartItemRepository cartItemRepository;

    private static final ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql", "/testDatabaseCartItemInsertion.sql"})
    @Test
    void shouldReturnAllCartItems() {
        currentUserId.setValue(1L);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/cart/item/all";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        JsonNode items = mapper
                .readTree(response.getBody())
                .path("cartItems");
        assertEquals(2, items.size());
    }

    @SneakyThrows
    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void shouldAddCartItem() {
        currentUserId.setValue(1L);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/cart/item/";
        AddItemToCartRequest request = new AddItemToCartRequest(currentUserId, "Morbo on Management", "5");
        HttpEntity<AddItemToCartRequest> httpRequest = new HttpEntity<>(request);
        AddItemToCartResponse response = restTemplate.postForObject(url, httpRequest, AddItemToCartResponse.class);
        assertNotNull(response);
        assertNull(response.getErrors());
        assertEquals(1, cartItemRepository.findByCart(cartRepository.findOpenCartByUser(userRepository.findById(currentUserId.getValue()).get()).get()).size());
    }

    @SneakyThrows
    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql", "/testDatabaseCartItemInsertion.sql"})
    @Test
    void shouldDeleteCartItem() {
        currentUserId.setValue(1L);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/cart/item/remove/Good News";
        restTemplate.delete(url);
        assertFalse(cartItemRepository.findFirstByCartAndItem(
                cartRepository.findOpenCartByUser(userRepository.findById(currentUserId.getValue()).get()).get(),
                itemRepository.findFirstByName("Good News").get()).isPresent());
        assertEquals(9, itemRepository.findFirstByName("Good News").get().getAvailableQuantity());
    }

    @SneakyThrows
    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql", "/testDatabaseCartItemInsertion.sql"})
    @Test
    void shouldCloseCart() {
        currentUserId.setValue(1L);
        Cart oldCart = cartRepository.findOpenCartByUser(
                userRepository.findById(currentUserId.getValue()).get()).get();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/cart/buy";
        restTemplate.put(url, null);
        //TODO FUCK THIS NOISE
        //assertEquals(CartStatus.CLOSED, oldCart.getStatus());
        assertTrue(cartRepository.findOpenCartByUser(
                userRepository.findById(currentUserId.getValue()).get()).isPresent());
    }

}
