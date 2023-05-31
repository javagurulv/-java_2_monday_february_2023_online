package java2.eln.core.services;

import java2.eln.core.database.DatabaseIM;
import java2.eln.core.database.InMemoryDatabaseImplIM;
import java2.eln.core.requests.DeleteReactionByCodeRequest;
import java2.eln.core.responses.errorPattern.CoreError;
import java2.eln.core.services.validators.DelReactionByCodeValidator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DelReactionByCodeValidatorTest {

    @Disabled
    @Test
    public void testNotEmptyCode() {
        DatabaseIM inMemoryDataBase = new InMemoryDatabaseImplIM();

        DeleteReactionByCodeRequest deleteReactionByCodeRequest = new DeleteReactionByCodeRequest("TP1");
        DelReactionByCodeValidator delReactionByCodeValidator = new DelReactionByCodeValidator();

        CoreError noReactionInDatabaseError =
                new CoreError("Reaction code not found", "enter the code of the reaction existing in the database");
        assertEquals(noReactionInDatabaseError.getMessage(), delReactionByCodeValidator.validate(deleteReactionByCodeRequest).get(0).getMessage());
        assertEquals(noReactionInDatabaseError.getField(), delReactionByCodeValidator.validate(deleteReactionByCodeRequest).get(0).getField());
    }

}