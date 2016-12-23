package eli.com.applicastertask.model.classes;

import com.twitter.sdk.android.core.models.Tweet;

/**
 * Created by eli on 22/12/16.
 */

public class TwitterQueryResult extends QueryResult {

    protected String userName;
    protected String userImagePath;

    public TwitterQueryResult(Long id, String text, String userImagePath, String userName) {
        super(id, text);
        this.userImagePath = userImagePath;
        this.userName = userName;
    }

    public TwitterQueryResult(Tweet t) {
        super(t.id, t.text);
        if (t.user != null) {
            userName = t.user.screenName; //+ " (" + t.user.name + ")";
            userImagePath = t.user.profileImageUrl;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImagePath() {
        return userImagePath;
    }

    public void setUserImagePath(String userImagePath) {
        this.userImagePath = userImagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Tweet)) return false;
        final Tweet other = (Tweet) o;
        return this.id == other.id;
    }
}
