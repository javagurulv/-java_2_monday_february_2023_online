package lv.fitness_app.core.database.jpa;

import lv.fitness_app.core.domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface JpaExerciseRepository extends JpaRepository<Exercise, String> {

	List<Exercise> findByName(String name);

	List<Exercise> findByMuscleGroup(String muscleGroup);

	List<Exercise> findByNameAndMuscleGroup(String name, String muscleGroup);

}
