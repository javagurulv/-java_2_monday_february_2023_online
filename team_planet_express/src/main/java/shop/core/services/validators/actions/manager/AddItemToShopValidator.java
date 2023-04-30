package shop.core.services.validators.actions.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.Database;
import shop.core.requests.manager.AddItemToShopRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;
import shop.core.support.ErrorCodeUtil;
import shop.core.support.Placeholder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddItemToShopValidator {

    @Autowired
    private ErrorCodeUtil errorCodeUtil;
    @Autowired
    private Database database;
    @Autowired
    private InputStringValidator inputStringValidator;

    public List<CoreError> validate(AddItemToShopRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateItemName(request.getItemName(), errors);
        validatePrice(request.getPrice(), errors);
        validateQuantity(request.getAvailableQuantity(), errors);
        return errors;
    }

    private void validateItemName(String itemName, List<CoreError> errors) {
        List<Placeholder> placeholders = new ArrayList<>();
        placeholders.add(new Placeholder("VALUE", "Item name"));

        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(itemName, placeholders);
        inputStringValidatorData.setPresentChecker(true);

        errors.addAll(inputStringValidator.validate(inputStringValidatorData));
        validateItemNameDoesNotAlreadyExist(itemName).ifPresent(errors::add);
    }

    private void validatePrice(String price, List<CoreError> errors) {
        List<Placeholder> placeholders = new ArrayList<>();
        placeholders.add(new Placeholder("VALUE", "Price"));

        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(price, placeholders);
        inputStringValidatorData.setPresentChecker(true);
        inputStringValidatorData.setNumberChecker(true);
        inputStringValidatorData.setNotNegativeChecker(true);

        errors.addAll(inputStringValidator.validate(inputStringValidatorData));
    }

    private void validateQuantity(String availableQuantity, List<CoreError> errors) {
        List<Placeholder> placeholders = new ArrayList<>();
        placeholders.add(new Placeholder("VALUE", "Quantity"));

        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(availableQuantity, placeholders);
        inputStringValidatorData.setPresentChecker(true);
        inputStringValidatorData.setNumberChecker(true);
        inputStringValidatorData.setNotNegativeChecker(true);
        inputStringValidatorData.setNotDecimalChecker(true);

        errors.addAll(inputStringValidator.validate(inputStringValidatorData));
    }

    private Optional<CoreError> validateItemNameDoesNotAlreadyExist(String itemName) {
        return (itemName != null && !itemName.isBlank() &&
                database.accessItemDatabase().findByName(itemName).isPresent())
                ? Optional.of(errorCodeUtil.errorBuild("ERROR_CODE_11"))
                : Optional.empty();
    }

}
