package shop.acceptance_tests.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import shop.core.database.UserRepository;
import shop.core.domain.User;
import shop.core.enums.UserRole;
import shop.core.requests.shared.SignInRequest;
import shop.core.requests.shared.SignOutRequest;
import shop.core.responses.shared.SignInResponse;
import shop.core.responses.shared.SignOutResponse;
import shop.core.services.actions.shared.SignInService;
import shop.core.services.actions.shared.SignOutService;
import shop.core.support.CurrentUserId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class SignInAndOutAcceptanceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CurrentUserId currentUserId;
    @Autowired
    private SignInService signInService;
    @Autowired
    private SignOutService signOutService;

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void shouldSignInAsCustomerAndBecomeGuestAfterSignOut() {
        User customer = new User("Morbo", "theAnnihilator", "pathetichumans", UserRole.CUSTOMER);
        userRepository.save(customer);
        SignInResponse signInResponse =
                signInService.execute(new SignInRequest(currentUserId, "theAnnihilator", "pathetichumans"));
        assertFalse(signInResponse.hasErrors());
        assertEquals(currentUserId.getValue(), userRepository.findByLoginName("theAnnihilator").orElseThrow().getId());
        assertEquals(UserRole.CUSTOMER.toString(), signInResponse.getUser().getUserRole());
        assertEquals("Morbo", signInResponse.getUser().getName());
        SignOutResponse signOutResponse =
                signOutService.execute(new SignOutRequest(currentUserId));
        assertFalse(signOutResponse.hasErrors());
        assertEquals(UserRole.GUEST, userRepository.findById(currentUserId.getValue()).orElseThrow().getUserRole());
    }

}
