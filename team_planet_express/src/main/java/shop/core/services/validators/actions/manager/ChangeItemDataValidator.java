package shop.core.services.validators.actions.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.Database;
import shop.core.domain.item.Item;
import shop.core.requests.manager.ChangeItemDataRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;
import shop.core.support.ErrorCodeUtil;
import shop.core.support.Placeholder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ChangeItemDataValidator {

    @Autowired
    private ErrorCodeUtil errorCodeUtil;
    @Autowired
    private Database database;
    @Autowired
    private InputStringValidator inputStringValidator;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;

    public List<CoreError> validate(ChangeItemDataRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request.getItemId(), errors);
        validatePrice(request.getNewPrice(), errors);
        validateQuantity(request.getNewAvailableQuantity(), errors);
        if (errors.isEmpty()) {
            validateDuplicate(request).ifPresent(errors::add);
        }
        return errors;
    }

    private void validateId(String id, List<CoreError> errors) {
        List<Placeholder> placeholders = new ArrayList<>();
        placeholders.add(new Placeholder("VALUE", "Item id"));

        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(id, placeholders);
        inputStringValidatorData.setPresentChecker(true);
        inputStringValidatorData.setNumberChecker(true);
        inputStringValidatorData.setNotNegativeChecker(true);
        inputStringValidatorData.setNotDecimalChecker(true);

        errors.addAll(inputStringValidator.validate(inputStringValidatorData));
        if (errors.isEmpty()) {
            validateIdExistsInShop(id).ifPresent(errors::add);
        }
    }

    private void validatePrice(String price, List<CoreError> errors) {
        List<Placeholder> placeholders = new ArrayList<>();
        placeholders.add(new Placeholder("VALUE", "Price"));

        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(price, placeholders);
        inputStringValidatorData.setNumberChecker(true);
        inputStringValidatorData.setNotNegativeChecker(true);

        errors.addAll(inputStringValidator.validate(inputStringValidatorData));
    }

    private void validateQuantity(String availableQuantity, List<CoreError> errors) {
        List<Placeholder> placeholders = new ArrayList<>();
        placeholders.add(new Placeholder("VALUE", "Quantity"));

        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(availableQuantity, placeholders);
        inputStringValidatorData.setNumberChecker(true);
        inputStringValidatorData.setNotNegativeChecker(true);
        inputStringValidatorData.setNotDecimalChecker(true);

        errors.addAll(inputStringValidator.validate(inputStringValidatorData));
    }

    private Optional<CoreError> validateDuplicate(ChangeItemDataRequest request) {
        Item originalItem = databaseAccessValidator.getItemById(Long.parseLong(request.getItemId()));
        String newItemName = setNewItemName(request, originalItem);
        BigDecimal newPrice = setNewPrice(request, originalItem);
        return (database.accessItemDatabase().getAllItems().stream()
                .filter(item -> !originalItem.getId().equals(item.getId()))
                .anyMatch(item -> newItemName.equals(item.getName()) && newPrice.compareTo(item.getPrice()) == 0))
                ? Optional.of(errorCodeUtil.errorBuild("ERROR_CODE_13"))
                : Optional.empty();
    }

    private Optional<CoreError> validateIdExistsInShop(String id) {
        return (id != null && !id.isBlank() &&
                database.accessItemDatabase().findById(Long.parseLong(id)).isEmpty())
                ? Optional.of(errorCodeUtil.errorBuild("ERROR_CODE_12"))
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
