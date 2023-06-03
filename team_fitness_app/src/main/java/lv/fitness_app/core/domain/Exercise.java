package lv.fitness_app.core.domain;

import java.util.Objects;

public class Exercise {

    private String name;
    private String muscleGroup;
    private String detailedMuscleGroup;
    private String otherMuscleGroup;
    private Type type;
    private String mechanics;
    private String equipment;
    private Difficulty difficulty;
    private String description;
    private String gif;

    public Exercise() {
    }

    public Exercise(String name, String muscleGroup, String detailedMuscleGroup, String otherMuscleGroup, Type type, String mechanics, String equipment, Difficulty difficulty, String description, String gif) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        detailedMuscleGroup = detailedMuscleGroup;
        otherMuscleGroup = otherMuscleGroup;
        this.type = type;
        this.mechanics = mechanics;
        this.equipment = equipment;
        this.difficulty = difficulty;
        this.description = description;
        this.gif = gif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public String getDetailedMuscleGroup() {
        return detailedMuscleGroup;
    }

    public void setDetailedMuscleGroup(String detailedMuscleGroup) {
        detailedMuscleGroup = detailedMuscleGroup;
    }

    public String getOtherMuscleGroup() {
        return otherMuscleGroup;
    }

    public void setOtherMuscleGroup(String otherMuscleGroup) {
        otherMuscleGroup = otherMuscleGroup;
    }

    public String getType() {
        return type.name().toUpperCase();
    }

    public void setType(String type) {
        this.type = Type.valueOf(type.toUpperCase());
    }

    public String getMechanics() {
        return mechanics;
    }

    public void setMechanics(String mechanics) {
        this.mechanics = mechanics;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getDifficulty() {
        return difficulty.name().toUpperCase();
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = Difficulty.valueOf(difficulty.toUpperCase());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGif() {
        return gif;
    }

    public void setGif(String gif) {
        this.gif = gif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return Objects.equals(name, exercise.name) && Objects.equals(muscleGroup, exercise.muscleGroup) && Objects.equals(detailedMuscleGroup, exercise.detailedMuscleGroup) && Objects.equals(otherMuscleGroup, exercise.otherMuscleGroup) && type == exercise.type && Objects.equals(mechanics, exercise.mechanics) && Objects.equals(equipment, exercise.equipment) && difficulty == exercise.difficulty && Objects.equals(description, exercise.description) && Objects.equals(gif, exercise.gif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, muscleGroup, detailedMuscleGroup, otherMuscleGroup, type, mechanics, equipment, difficulty, description, gif);
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", muscleGroup='" + muscleGroup + '\'' +
                ", DetailedMuscleGroup='" + detailedMuscleGroup + '\'' +
                ", OtherMuscleGroup='" + otherMuscleGroup + '\'' +
                ", type=" + type +
                ", mechanics='" + mechanics + '\'' +
                ", equipment='" + equipment + '\'' +
                ", difficulty=" + difficulty +
                ", description='" + description + '\'' +
                ", gif='" + gif + '\'' +
                '}';
    }
}
