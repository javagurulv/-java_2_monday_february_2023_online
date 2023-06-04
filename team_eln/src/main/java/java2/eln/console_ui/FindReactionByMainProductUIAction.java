package java2.eln.console_ui;

import java2.eln.core.requests.FindReactionsByMainProductRequest;
import java2.eln.core.responses.FindReactionsByMainProductResponse;
import java2.eln.core.services.FindReactionsByMainProductService;
import java2.eln.core.services.GetStructureFromSMILE;
import java2.eln.core.domain.StructureData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindReactionByMainProductUIAction implements UIAction{
    @Autowired
    FindReactionsByMainProductService findReactionByMainProductService;
    @Autowired
    GetStructureFromSMILE getStructureFromSMILE;
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Main Product SMILE to search: ");
        String  smile = scanner.nextLine();

        StructureData searchedStructure = getStructureFromSMILE.execute(smile);

        FindReactionsByMainProductRequest findReactionsByMainProductRequest =
                new FindReactionsByMainProductRequest(searchedStructure);
        FindReactionsByMainProductResponse findReactionsByMainProductResponse =
                findReactionByMainProductService.execute(findReactionsByMainProductRequest);

        findReactionsByMainProductResponse.getSearchingResults().forEach(System.out::println);
    }
}
