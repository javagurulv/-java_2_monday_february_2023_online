package lv.fitness_app.exercises.search_engine;

import lv.fitness_app.exercises.core.exercise_domain.Exercise;

public interface SearchCriteria {

    boolean match(Exercise exercise);
}
