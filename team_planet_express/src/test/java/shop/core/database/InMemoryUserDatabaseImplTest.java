package shop.core.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.in_memory.InMemoryUserDatabaseImpl;
import shop.core.domain.user.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InMemoryUserDatabaseImplTest {

    @Mock
    private User mockUser;

    @InjectMocks
    private InMemoryUserDatabaseImpl database;

    @Test
    void shouldIncreaseInSizeAfterSave() {
        database.save(mockUser);
        assertEquals(1, database.getUsers().size());
    }

    @Test
    void shouldReturnCreatedUser() {
        User createdUser = database.save(mockUser);
        assertNotNull(createdUser);
    }

    @Test
    void shouldSetNextIdForUser() {
        Long idBefore = database.getNextId();
        database.save(mockUser);
        verify(mockUser).setId(idBefore);
    }

    @Test
    void shouldIncreaseNextIdAfterSave() {
        Long idBefore = database.getNextId();
        database.save(mockUser);
        Long idAfter = database.getNextId();
        assertEquals(1, idAfter - idBefore);
    }

    @Test
    void shouldReturnFoundUserById() {
        when(mockUser.getId()).thenReturn(1L);
        database.getUsers().add(mockUser);
        assertTrue(database.findById(1L).isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalForNonexistentUserById() {
        when(mockUser.getId()).thenReturn(2L);
        database.getUsers().add(mockUser);
        assertTrue(database.findById(1L).isEmpty());
    }

    @Test
    void shouldReturnFoundUserByLogin() {
        when(mockUser.getLogin()).thenReturn("user");
        database.getUsers().add(mockUser);
        assertTrue(database.findByLoginName("user").isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalForNonexistentUserByLogin() {
        when(mockUser.getLogin()).thenReturn("different user");
        database.getUsers().add(mockUser);
        assertTrue(database.findByLoginName("user").isEmpty());
    }

    @Test
    void shouldReturn3Users() {
        database.getUsers().add(mockUser);
        database.getUsers().add(mockUser);
        database.getUsers().add(mockUser);
        assertEquals(3, database.getAllUsers().size());
    }

}
