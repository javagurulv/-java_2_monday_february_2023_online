package shop.acceptance_tests.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.config.ShopConfiguration;
import shop.core.database.Database;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.requests.shared.SignInRequest;
import shop.core.requests.shared.SignOutRequest;
import shop.core.responses.shared.SignInResponse;
import shop.core.responses.shared.SignOutResponse;
import shop.core.services.actions.shared.SignInService;
import shop.core.services.actions.shared.SignOutService;
import shop.core.support.CurrentUserId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ShopConfiguration.class})
public class SignInAndOutAcceptanceTest {

    @Autowired
    private Database database;
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
        database.accessUserDatabase().save(customer);
        SignInResponse signInResponse =
                signInService.execute(new SignInRequest(currentUserId, "theAnnihilator", "pathetichumans"));
        assertFalse(signInResponse.hasErrors());
        assertEquals(currentUserId.getValue(), database.accessUserDatabase().findByLoginName("theAnnihilator").orElseThrow().getId());
        assertEquals(UserRole.CUSTOMER, signInResponse.getUser().getUserRole());
        assertEquals("Morbo", signInResponse.getUser().getName());
        SignOutResponse signOutResponse =
                signOutService.execute(new SignOutRequest(currentUserId));
        assertFalse(signOutResponse.hasErrors());
        assertEquals(UserRole.GUEST, database.accessUserDatabase().findById(currentUserId.getValue()).orElseThrow().getUserRole());
    }

}
