package services;

import database.Database;
import baseClasses.ReactionData;

public class AddReactionService {
    private Database database;


    public AddReactionService(Database database) {
        this.database = database;
    }

    public void execute(String code, String name, String filename) {
        ReactionData demoReactionLog = new ReactionData("TP1", "The Friedel-Crafts acylation");
        additionOfMaterials(filename, demoReactionLog); // The Materials added to the ReactionData from the file
        additionOfConditions(filename, demoReactionLog); // The Reaction Conditions added to the ReactionData from the file

        database.addReaction(demoReactionLog);
        consoleMessage(demoReactionLog);
    }

    private static void additionOfConditions(String filename, ReactionData demoReactionLog) {
        CreateConditionDataFromFile newConditions = new CreateConditionDataFromFile(filename);
        demoReactionLog.setConditions(newConditions.readFromFile());
    }

    private static void additionOfMaterials(String filename, ReactionData demoReactionLog) {
        CreateStructureFromFile newMaterial = new CreateStructureFromFile(filename);
        demoReactionLog.addStartingMaterial(newMaterial.readFromFile("SM1")); // starting material
        demoReactionLog.addStartingMaterial(newMaterial.readFromFile("SM2"));
        demoReactionLog.addStartingMaterial(newMaterial.readFromFile("SM3"));
        demoReactionLog.setMainProduct(newMaterial.readFromFile("MP")); // main product of the reaction
    }

    private static void consoleMessage(ReactionData addedReaction) {
        System.out.println("baseClasses.ReactionData object with code " + addedReaction.getCode() + " has been successfully added.");
    }
}
