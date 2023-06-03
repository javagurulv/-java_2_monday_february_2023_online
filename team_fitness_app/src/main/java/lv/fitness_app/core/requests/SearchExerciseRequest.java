package lv.fitness_app.core.requests;

import lv.fitness_app.core.domain.Difficulty;
import lv.fitness_app.core.domain.Type;

public class SearchExerciseRequest {

    private String name;
    private String muscleGroup;
    private String detailedMuscleGroup;
    private String otherMuscleGroup;
    private Type type;
    private String mechanics;
    private String equipment;
    private Difficulty difficulty;
    private Ordering ordering;
    private Paging paging;

    public SearchExerciseRequest(String name, String muscleGroup, String detailedMuscleGroup, String otherMuscleGroup, Type type, String mechanics, String equipment, Difficulty difficulty) {
        this.name = (name == null || name.length() == 0) ? "%" : name;
        this.muscleGroup = (muscleGroup == null || muscleGroup.length() == 0) ? "%" : muscleGroup;
        this.detailedMuscleGroup = (detailedMuscleGroup == null || detailedMuscleGroup.length() == 0) ? "%" : detailedMuscleGroup;
        this.otherMuscleGroup = (otherMuscleGroup == null || otherMuscleGroup.length() == 0) ? "%" : otherMuscleGroup;
        this.type = type;
        this.mechanics = (mechanics == null || mechanics.length() == 0) ? "%" : mechanics;
        this.equipment = (equipment == null || equipment.length() == 0) ? "%" : equipment;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public String getDetailedMuscleGroup() {
        return detailedMuscleGroup;
    }

    public String getOtherMuscleGroup() {
        return otherMuscleGroup;
    }

    public Type getType() {
        return type;
    }

    public String getMechanics() {
        return mechanics;
    }

    public String getEquipment() {
        return equipment;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
