package lv.fitness_app.database;

import lv.fitness_app.core.domain.Exercise;

import java.util.List;

public interface ExerciseRepository {

    List<Exercise> findByName(String name);

    List<Exercise> findByMuscleGroup(String muscleGroup);

    List<Exercise> findByNameAndMuscleGroup(String name, String muscleGroup);

}
