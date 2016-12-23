package eli.com.applicastertask.model.events;

import eli.com.applicastertask.model.classes.Query;

/**
 * Created by eli on 22/12/16.
 */

public class OnQuerySubmitEvent {
    protected Query query;

    public OnQuerySubmitEvent(Query query) {
        this.query = query;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
}
