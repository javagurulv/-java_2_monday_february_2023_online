package lv.fitness_app.database;

import lv.fitness_app.core.domain.Exercise;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//@Component
@Transactional
public class ExerciseRepository {

    @Autowired
    private SessionFactory sessionFactory;


    public void save(Exercise exercise) {
        sessionFactory.getCurrentSession().save(exercise);
    }

    public Exercise findByName(String name) {
        return sessionFactory.getCurrentSession().get(Exercise.class, name);
    }
}
