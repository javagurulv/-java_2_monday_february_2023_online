package lv.fitness_app.exercises.core.exercise_database;

import lv.fitness_app.exercises.core.exercise_domain.Exercise;

import java.util.List;

public interface ExerciseDatabase {

    List<Exercise> getAllExercises();

    List<Exercise> findByName(String name);

    List<Exercise> findByMuscleGroup(String muscleGroup);

    List<Exercise> findByType(String type);

    List<Exercise> findByEquipment(String equipment);

    List<Exercise> findByDifficulty(String difficulty);
}