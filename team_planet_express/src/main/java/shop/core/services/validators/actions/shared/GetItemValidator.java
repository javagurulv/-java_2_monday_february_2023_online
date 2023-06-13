package shop.core.services.validators.actions.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.ItemRepository;
import shop.core.error_code_processing.ErrorProcessor;
import shop.core.requests.shared.GetItemRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetItemValidator {

    private static final String FIELD_ID = "id";
    private static final String VALUE_NAME_ID = "Item id";
    private static final String ERROR_ID_NOT_EXISTS = "VDT-CID-INE";

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private InputStringValidator inputStringValidator;
    @Autowired
    private ErrorProcessor errorProcessor;

    public List<CoreError> validate(GetItemRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request.getItemId(), errors);
        return errors;
    }

    private void validateId(String id, List<CoreError> errors) {
        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(id, FIELD_ID, VALUE_NAME_ID);
        inputStringValidator.validateIsPresent(inputStringValidatorData).ifPresent(errors::add);
        if (errors.isEmpty()) {
            errors.addAll(inputStringValidator.validateIsNumberNotNegativeNotDecimal(inputStringValidatorData));
        }
        if (errors.isEmpty()) {
            validateIdExistsInShop(id).ifPresent(errors::add);
        }
    }

    private Optional<CoreError> validateIdExistsInShop(String id) {
        return (id != null && !id.isBlank() &&
                itemRepository.findById(Long.parseLong(id)).isEmpty())
                ? Optional.of(errorProcessor.getCoreError(FIELD_ID, ERROR_ID_NOT_EXISTS))
                : Optional.empty();
    }

}
