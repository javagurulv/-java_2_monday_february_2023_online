package lv.fitness_app.core.requests;
public class SearchExerciseByMuscleGroupRequest {

    private String muscleGroup;

    private Ordering ordering;
    private Paging paging;

    public SearchExerciseByMuscleGroupRequest(String muscleGroup, Ordering ordering, Paging paging) {
        this.muscleGroup = (muscleGroup == null || muscleGroup.length() == 0) ? "%" : muscleGroup;
        this.ordering = ordering;
        this.paging = paging;

    }

    public String getMuscleGroup() {
        return muscleGroup;
    }
    public Ordering getOrdering() {
        return this.ordering;
    }
    public Paging getPaging() {
        return this.paging;
    }

}