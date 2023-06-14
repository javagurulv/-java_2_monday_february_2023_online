package lv.fitness_app.core.requests;

public class SearchExerciseRequest {

    private String name;
    private String muscleGroup;
    private Ordering ordering;
    private Paging paging;

    public SearchExerciseRequest(String name, String muscleGroup) {
        this.name = (name == null || name.length() == 0) ? "%" : name;
        this.muscleGroup = (muscleGroup == null || muscleGroup.length() == 0) ? "%" : muscleGroup;
    }
    public SearchExerciseRequest(String name,
                              String muscleGroup,
                              Ordering ordering) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.ordering = ordering;
    }

    public SearchExerciseRequest(String name,
                              String muscleGroup,
                              Paging paging) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.paging = paging;
    }

    public SearchExerciseRequest(String name,
                              String muscleGroup,
                              Ordering ordering,
                              Paging paging) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getName() {
        return name;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public boolean isNameProvided() {
        return this.name != null && !this.name.isEmpty();
    }

    public boolean isMuscleGroupProvided() {
        return this.muscleGroup != null && !this.muscleGroup.isEmpty();
    }

    public Ordering getOrdering() {
        return this.ordering;
    }

    public Paging getPaging() {
        return this.paging;
    }

}
