package shop.core.services.validators.actions.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.ItemRepository;
import shop.core.domain.item.Item;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;
import shop.core.support.error_code_processing.ErrorProcessor;
import shop.core_api.requests.manager.ChangeItemDataRequest;
import shop.core_api.responses.CoreError;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ChangeItemDataValidator {

    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_PRICE = "price";
    private static final String FIELD_QUANTITY = "quantity";
    private static final String FIELD_BUTTON = "button";
    private static final String VALUE_NAME_ITEM = "Item name";
    private static final String VALUE_NAME_ID = "Item id";
    private static final String VALUE_NAME_PRICE = "Price";
    private static final String VALUE_NAME_QUANTITY = "Quantity";
    private static final String ERROR_ID_NOT_EXISTS = "VDT-CID-INE";
    private static final String ERROR_ITEM_EXISTS = "VDT-CID-EIE";

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private InputStringValidator inputStringValidator;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;
    @Autowired
    private ErrorProcessor errorProcessor;

    public List<CoreError> validate(ChangeItemDataRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request.getItemId(), errors);
        validateItemName(request.getNewItemName(), errors);
        validatePrice(request.getNewPrice(), errors);
        validateQuantity(request.getNewAvailableQuantity(), errors);
        if (errors.isEmpty()) {
            validateDuplicate(request).ifPresent(errors::add);
        }
        return errors;
    }

    private void validateId(String id, List<CoreError> errors) {
        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(id, FIELD_ID, VALUE_NAME_ID);
        inputStringValidator.validateIsPresent(inputStringValidatorData).ifPresent(errors::add);
        errors.addAll(inputStringValidator.validateIsNumberNotNegativeNotDecimal(inputStringValidatorData));
        if (errors.isEmpty()) {
            validateIdExistsInShop(id).ifPresent(errors::add);
        }
    }

    private void validateItemName(String itemName, List<CoreError> errors) {
        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(itemName, FIELD_NAME, VALUE_NAME_ITEM);
        inputStringValidator.validateLength(inputStringValidatorData, 32).ifPresent(errors::add);
    }

    private void validatePrice(String price, List<CoreError> errors) {
        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(price, FIELD_PRICE, VALUE_NAME_PRICE);
        inputStringValidator.validateDecimalNumberLength(inputStringValidatorData, 6).ifPresent(errors::add);
        errors.addAll(inputStringValidator.validateIsNumberNotNegative(inputStringValidatorData));
    }

    private void validateQuantity(String availableQuantity, List<CoreError> errors) {
        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(availableQuantity, FIELD_QUANTITY, VALUE_NAME_QUANTITY);
        errors.addAll(inputStringValidator.validateIsNumberNotNegativeNotDecimal(inputStringValidatorData));
    }

    private Optional<CoreError> validateDuplicate(ChangeItemDataRequest request) {
        Item originalItem = databaseAccessValidator.getItemById(Long.parseLong(request.getItemId()));
        String newItemName = setNewItemName(request, originalItem);
        BigDecimal newPrice = setNewPrice(request, originalItem);
        return (itemRepository.getAllItems().stream()
                .filter(item -> !originalItem.getId().equals(item.getId()))
                .anyMatch(item -> newItemName.equals(item.getName()) && newPrice.compareTo(item.getPrice()) == 0))
                ? Optional.of(errorProcessor.getCoreError(FIELD_BUTTON, ERROR_ITEM_EXISTS))
                : Optional.empty();
    }

    private Optional<CoreError> validateIdExistsInShop(String id) {
        return (id != null && !id.isBlank() &&
                itemRepository.findById(Long.parseLong(id)).isEmpty())
                ? Optional.of(errorProcessor.getCoreError(FIELD_ID, ERROR_ID_NOT_EXISTS))
                : Optional.empty();
    }

    private String setNewItemName(ChangeItemDataRequest request, Item originalItem) {
        return (newValueExists(request.getNewItemName()))
                ? request.getNewItemName()
                : originalItem.getName();
    }

    private BigDecimal setNewPrice(ChangeItemDataRequest request, Item originalItem) {
        return (newValueExists(request.getNewPrice()))
                ? new BigDecimal(request.getNewPrice()).setScale(2, RoundingMode.HALF_UP)
                : originalItem.getPrice();
    }

    private boolean newValueExists(String value) {
        return value != null && !value.isBlank();
    }

}
