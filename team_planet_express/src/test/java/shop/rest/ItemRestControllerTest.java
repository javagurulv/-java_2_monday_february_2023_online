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
import shop.core.database.jpa.JpaItemRepository;
import shop.core.requests.manager.AddItemToShopRequest;
import shop.core.requests.manager.ChangeItemDataRequest;
import shop.core.responses.manager.AddItemToShopResponse;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ItemRestControllerTest {

    @Autowired
    private JpaItemRepository itemRepository;

    private static final ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void shouldReturnItem() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/item/1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        JsonNode itemName = mapper
                .readTree(response.getBody())
                .path("item")
                .path("name");
        assertEquals("Stop-and-Drop Suicide Booth", itemName.asText());
    }

    @SneakyThrows
    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void shouldReturnAllItems() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/item/all";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        JsonNode items = mapper
                .readTree(response.getBody())
                .path("shopItems");
        assertEquals(10, items.size());
    }

    @SneakyThrows
    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void shouldFindItem() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/item/search?itemName=morbo&price=50";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        JsonNode itemName = mapper
                .readTree(response.getBody())
                .path("items")
                .path(0)
                .path("name");
        assertEquals("Morbo on Management", itemName.asText());
    }

    @SneakyThrows
    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void shouldNotFindItem() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/item/search?itemName=brains";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        JsonNode items = mapper
                .readTree(response.getBody())
                .path("items");
        assertEquals(0, items.size());
    }

    @SneakyThrows
    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void shouldAddItem() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/item";
        AddItemToShopRequest request = new AddItemToShopRequest("uuwuu", "69.69", "420");
        HttpEntity<AddItemToShopRequest> httpRequest = new HttpEntity<>(request);
        AddItemToShopResponse response = restTemplate.postForObject(url, httpRequest, AddItemToShopResponse.class);
        assertNotNull(response);
        assertNull(response.getErrors());
        assertTrue(itemRepository.findByName("uuwuu").stream().findFirst().isPresent());
    }

    @SneakyThrows
    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void shouldUpdateItem() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/item/1";
        ChangeItemDataRequest request = new ChangeItemDataRequest("1", "", "500.00", "2");
        HttpEntity<ChangeItemDataRequest> httpRequest = new HttpEntity<>(request);
        restTemplate.put(url, httpRequest);
        assertTrue(itemRepository.findByName("Stop-and-Drop Suicide Booth").stream().findFirst().isPresent());
        assertEquals(new BigDecimal("500.00"), itemRepository.findByName("Stop-and-Drop Suicide Booth").stream().findFirst().get().getPrice());
        assertEquals(2, itemRepository.findByName("Stop-and-Drop Suicide Booth").stream().findFirst().get().getAvailableQuantity());
    }

}
