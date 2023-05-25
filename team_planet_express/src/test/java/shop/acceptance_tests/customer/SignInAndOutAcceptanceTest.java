package shop.acceptance_tests.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.config.ShopConfiguration;
import shop.core.database.Repository;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.requests.shared.SignInRequest;
import shop.core.requests.shared.SignOutRequest;
import shop.core.responses.shared.SignInResponse;
import shop.core.responses.shared.SignOutResponse;
import shop.core.services.actions.shared.SignInService;
import shop.core.services.actions.shared.SignOutService;
import shop.core.support.CurrentUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ShopConfiguration.class})
public class SignInAndOutAcceptanceTest {

    @Autowired
    private Repository repository;
    @Autowired
    private CurrentUser currentUser;
    @Autowired
    private SignInService signInService;
    @Autowired
    private SignOutService signOutService;

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void shouldSignInAsCustomerAndBecomeGuestAfterSignOut() {
        User customer = new User("Morbo", "theAnnihilator", "pathetichumans", UserRole.CUSTOMER);
        repository.accessUserRepository().save(customer);
        SignInResponse signInResponse =
                signInService.execute(new SignInRequest(currentUser, "theAnnihilator", "pathetichumans"));
        assertFalse(signInResponse.hasErrors());
        assertEquals(currentUser.getUser().getId(), repository.accessUserRepository().findByLoginName("theAnnihilator").orElseThrow().getId());
        assertEquals(UserRole.CUSTOMER, signInResponse.getUser().getUserRole());
        assertEquals("Morbo", signInResponse.getUser().getName());
        SignOutResponse signOutResponse =
                signOutService.execute(new SignOutRequest(currentUser));
        assertFalse(signOutResponse.hasErrors());
        assertEquals(UserRole.GUEST, repository.accessUserRepository().findById(currentUser.getUser().getId()).orElseThrow().getUserRole());
    }

}
