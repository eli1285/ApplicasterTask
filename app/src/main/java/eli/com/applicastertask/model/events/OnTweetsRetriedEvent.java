package eli.com.applicastertask.model.events;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

/**
 * Created by eli on 22/12/16.
 */

public class OnTweetsRetriedEvent {
    private boolean isSuccess;
    private List<Tweet> tweets;

    public OnTweetsRetriedEvent(boolean isSuccess, List<Tweet> tweets) {
        this.tweets = tweets;
        this.isSuccess = isSuccess;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
