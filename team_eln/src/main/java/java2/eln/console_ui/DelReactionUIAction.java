package java2.eln.console_ui;

import java2.eln.core.services.DelReactionService;

import java.util.Scanner;

public class DelReactionUIAction implements UIAction{
    private DelReactionService delReactionService;

    public DelReactionUIAction(DelReactionService delReactionService) {
        this.delReactionService = delReactionService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Reaction Code to delete: ");
        String  reactionCode = scanner.nextLine();
        delReactionService.execute(reactionCode);

    }
}
