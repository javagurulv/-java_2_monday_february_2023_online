package shop.core.services.fake;

import org.junit.jupiter.api.Test;
import shop.core.domain.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FakeUserGeneratorTest {

    private final FakeUserGenerator userGenerator = new FakeUserGenerator();

    @Test
    void shouldCreateListOf3Users() {
        List<User> users = userGenerator.createUsers();
        assertEquals(3, users.size());
        assertEquals("Customer", users.get(1).getName());
    }

}
