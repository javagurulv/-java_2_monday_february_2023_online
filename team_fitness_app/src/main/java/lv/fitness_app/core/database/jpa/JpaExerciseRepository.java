package lv.fitness_app.core.database.jpa;

import java.util.List;
import lv.fitness_app.core.domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaExerciseRepository extends JpaRepository<Exercise, String> {

    List<Exercise> findByName(String name);

    List<Exercise> findByMuscleGroup(String muscleGroup);

    List<Exercise> findByNameAndMuscleGroup(String name, String muscleGroup);

}
