package eli.com.applicastertask.model.classes;

import com.twitter.sdk.android.core.models.Tweet;

import java.io.Serializable;

/**
 * Created by eli on 22/12/16.
 */

public class QueryResult implements Serializable {
    protected String text;
    protected Long id;

    public QueryResult(Long id, String text) {
        this.text = text;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
