package lv.fitness_app.databaseUpdate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GetExercisesFromWebsite {

    public static void main(String[] args) throws IOException {

        String link = "https://www.jefit.com/exercises/bodypart.php?id=11&exercises=All" +
                "&All=0&Bands=0&Bench=0&Dumbbell=0&EZBar=0&Kettlebell=0&MachineStrength=0&MachineCardio=0&Barbell=0&BodyOnly=0&ExerciseBall=0&FoamRoll=0&PullBar=0&WeightPlate=0&Other=0&Strength=0&" +
                "Stretching=0&Powerlifting=0&OlympicWeightLifting=0&Beginner=0&Intermediate=0&Expert=0&page=";

        int pages = getNumberOfPages(link);
        List<String> listWithExercises = getExerciseDescription(link, pages);
        writeToFile(listWithExercises);
    }

    private static List<String> getExerciseDescription(String link, int pages) throws IOException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < pages;i++) {
            Document document = getDocByLink(link +i);
            Elements links = document.select("h4 > a");

            for (var elem:links) {
                Document documentWithExercise = getDocByLink("https://www.jefit.com/exercises/" + elem.attr("href"));
                list.add(extractInfo(documentWithExercise));
            }
        }
        return list;
    }

    private static String extractInfo(Document doc){

        String name = doc.selectFirst("h1").text();

        Element mainMuscleGroupElement = doc.selectFirst("p:contains(Main Muscle Group :)");
        String mainMuscleGroup = mainMuscleGroupElement.ownText().replace("Main Muscle Group :", "").trim();

        Element detailedMuscleGroupElement = doc.selectFirst("p:contains(Detailed Muscle Group :)");
        String detailedMuscleGroup = null;
        if (detailedMuscleGroupElement!=null) {
            detailedMuscleGroup = detailedMuscleGroupElement.ownText().replace("Detailed Muscle Group :", "").trim();
        }

        Element otherMuscleGroupsElement = doc.selectFirst("p:contains(Other Muscle Groups :)");
        String otherMuscleGroups = null;
        if (otherMuscleGroupsElement!=null) {
            otherMuscleGroups = otherMuscleGroupsElement.ownText().replace("Detailed Muscle Group :", "").trim();
        }

        Element typeElement = doc.selectFirst("p:contains(Type :)");
        String type = typeElement.ownText().replace("Type :", "").trim();

        Element mechanicsElement = doc.selectFirst("p:contains(Mechanics :)");
        String mechanics = mechanicsElement.ownText().replace("Mechanics :", "").trim();

        Element equipmentElement = doc.selectFirst("p:contains(Equipment :)");
        String equipment = equipmentElement.ownText().replace("Equipment :", "").trim();
        if (equipment.isBlank()){
            equipment = "Not needed";
        }

        Element difficultyElement = doc.selectFirst("p:contains(Difficulty :)");
        String difficulty = difficultyElement.ownText().replace("Difficulty :", "").trim();

        Elements elements = doc.select("p.p-2");

        var description = "";
        for (Element element : elements) {
            var text = element.ownText();
            text = text.replace(";", ":");
            description+=text;
        }

        Element gifElement = doc.selectFirst("video");
        String gif = null;
        if (gifElement!=null){
            gif = gifElement.attr("src");
        }

        String row = name + ";"+mainMuscleGroup +";"+detailedMuscleGroup+";"+otherMuscleGroups+ ";"+type +";" + mechanics+";"+ equipment +";"+difficulty+";"+description + ";"+gif+"\n";
        return row;

    }
    public static void writeToFile(List<String> content) {
        String filePath = "exercises.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.newLine();
            for (String row:content) {
                writer.append(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getNumberOfPages(String link) throws IOException {
        Document document = getDocByLink(link+"1");
        Element elem = document.selectFirst(".pageCell");
        String text = elem.text().replace(" ", "");
        String[] textParts = text.split("of");
        int numberOfPages = Integer.parseInt(textParts[1]);
        return numberOfPages;
    }

    private static Document getDocByLink(String link) throws IOException {
        Document document = Jsoup.connect(link).get();
        return document;
    }
}

