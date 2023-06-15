package lv.fitness_app.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "musclegroup")
    private String muscleGroup;
    @Column(name = "detailedmusclegroup")
    private String detailedMuscleGroup;
    @Column(name = "othermusclegroup")
    private String otherMuscleGroup;
    @Column(name = "type")
    private String type;
    @Column(name = "mechanics")
    private String mechanics;
    @Column(name = "equipment")
    private String equipment;
    @Column(name = "difficulty")
    private String difficulty;
    @Column(name = "description")
    private String description;
    @Column(name = "gif")
    private String gif;

    public Exercise() {
    }

    public Exercise(String name, String muscleGroup, String detailedMuscleGroup, String otherMuscleGroup, String type, String mechanics, String equipment, String difficulty, String description, String gif) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.detailedMuscleGroup = detailedMuscleGroup;
        this.otherMuscleGroup = otherMuscleGroup;
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
        this.detailedMuscleGroup = detailedMuscleGroup;
    }

    public String getOtherMuscleGroup() {
        return otherMuscleGroup;
    }

    public void setOtherMuscleGroup(String otherMuscleGroup) {
        this.otherMuscleGroup = otherMuscleGroup;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
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
