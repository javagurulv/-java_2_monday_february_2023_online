package lv.fitness_app.core.requests;

public class SearchExercisesRequest {

    private Ordering ordering;
    private Paging paging;

    public SearchExercisesRequest(Ordering ordering, Paging paging) {
        this.ordering = ordering;
        this.paging = paging;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }
}