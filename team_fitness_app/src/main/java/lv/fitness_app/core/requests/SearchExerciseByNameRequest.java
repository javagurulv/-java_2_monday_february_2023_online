package lv.fitness_app.core.requests;

public class SearchExerciseByNameRequest {

    private String name;
    private Ordering ordering;
    private Paging paging;

    public SearchExerciseByNameRequest(String name, Ordering ordering, Paging paging) {
        this.name = (name == null || name.length() == 0) ? "%" : name;
        this.ordering = ordering;
        this.paging = paging;
    }
    public SearchExerciseByNameRequest(String name, Ordering ordering) {
        this.name = (name == null || name.length() == 0) ? "%" : name;
        this.ordering = ordering;
    }
    public SearchExerciseByNameRequest(String name, Paging paging) {
        this.name = (name == null || name.length() == 0) ? "%" : name;
        this.paging = paging;
    }

    public String getName() {
        return name;
    }
    public boolean isNameProvided() {
        return this.name != null && !this.name.isEmpty();
    }
    public Ordering getOrdering(){
        return this.ordering;
    }
    public Paging getPaging() {
        return this.paging;
    }

}
