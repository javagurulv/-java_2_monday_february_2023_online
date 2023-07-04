package lv.fitness_app.core.databaseUpdate;

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
    private SessionFactory sessionFactory;

    public void deleteAllFromExerciseDB() {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete Exercise");
        query.executeUpdate();
    }

    public void updateExerciseDB(List<Exercise> exercises) {
        for (Exercise exercise : exercises) {
            sessionFactory.getCurrentSession().persist(exercise);
        }
    }

}
