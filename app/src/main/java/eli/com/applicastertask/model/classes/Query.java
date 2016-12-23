package eli.com.applicastertask.model.classes;

import java.io.Serializable;

/**
 * Created by eli on 22/12/16.
 */

public class Query implements Serializable{
    protected String queryText;
    protected Integer limit;

    public Query(String queryText, Integer limit) {
        this.queryText = queryText;
        this.limit = limit;
    }

    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
