package lv.fitness_app.core.domain;

import java.util.Objects;

public class Exercise {

    private String name;
    private String muscleGroup;
    private String DetailedMuscleGroup;
    private String OtherMuscleGroup;
    private Type type;
    private String mechanics;
    private String equipment;
    private Difficulty difficulty;
    private String description;

    public Exercise() { }

    public Exercise(String name, String muscleGroup, String detailedMuscleGroup, String otherMuscleGroup, Type type, String mechanics, String equipment, Difficulty difficulty, String description) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        DetailedMuscleGroup = detailedMuscleGroup;
        OtherMuscleGroup = otherMuscleGroup;
        this.type = type;
        this.mechanics = mechanics;
        this.equipment = equipment;
        this.difficulty = difficulty;
        this.description = description;
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
        return DetailedMuscleGroup;
    }

    public void setDetailedMuscleGroup(String detailedMuscleGroup) {
        DetailedMuscleGroup = detailedMuscleGroup;
    }

    public String getOtherMuscleGroup() {
        return OtherMuscleGroup;
    }

    public void setOtherMuscleGroup(String otherMuscleGroup) {
        OtherMuscleGroup = otherMuscleGroup;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return Objects.equals(name, exercise.name) && Objects.equals(muscleGroup, exercise.muscleGroup) && Objects.equals(DetailedMuscleGroup, exercise.DetailedMuscleGroup) && Objects.equals(OtherMuscleGroup, exercise.OtherMuscleGroup) && type == exercise.type && Objects.equals(mechanics, exercise.mechanics) && Objects.equals(equipment, exercise.equipment) && difficulty == exercise.difficulty && Objects.equals(description, exercise.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, muscleGroup, DetailedMuscleGroup, OtherMuscleGroup, type, mechanics, equipment, difficulty, description);
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", muscleGroup='" + muscleGroup + '\'' +
                ", DetailedMuscleGroup='" + DetailedMuscleGroup + '\'' +
                ", OtherMuscleGroup='" + OtherMuscleGroup + '\'' +
                ", type=" + type +
                ", mechanics='" + mechanics + '\'' +
                ", equipment='" + equipment + '\'' +
                ", difficulty=" + difficulty +
                ", description='" + description + '\'' +
                '}';
    }
}
