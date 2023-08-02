package lv.fitness_app.core.database;

import lv.fitness_app.core.domain.Exercise;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Component
//@Transactional
public class OrmExerciseRepositoryImpl implements ExerciseRepository{
  //  @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Exercise> findByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT e FROM Exercise e WHERE name =:name", Exercise.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Exercise> findByMuscleGroup(String muscleGroup) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT e FROM Exercise e WHERE muscleGroup =:muscleGroup", Exercise.class);
        query.setParameter("muscleGroup", muscleGroup);
        return query.getResultList();
    }

    @Override
    public List<Exercise> findByNameAndMuscleGroup(String name, String muscleGroup) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT e FROM Exercise e WHERE name =:name AND  muscleGroup = :muscleGroup", Exercise.class);
        query.setParameter("name", name);
        query.setParameter("muscleGroup", muscleGroup);
        return query.getResultList();
    }
}
