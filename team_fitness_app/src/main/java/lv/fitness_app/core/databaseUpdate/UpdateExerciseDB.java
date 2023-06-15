package lv.fitness_app.core.databaseUpdate;

import lv.fitness_app.core.database.jpa.JpaExerciseRepository;
import lv.fitness_app.core.domain.Exercise;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UpdateExerciseDB {

    @Autowired
    private JpaExerciseRepository jpaExerciseRepository;

    public void deleteAllFromExerciseDB() {
        jpaExerciseRepository.deleteAll();
    }

    public void updateExerciseDB(List<Exercise> exercises) {
        for (Exercise exercise : exercises) {
            jpaExerciseRepository.saveAll(exercises);
        }
    }

}
