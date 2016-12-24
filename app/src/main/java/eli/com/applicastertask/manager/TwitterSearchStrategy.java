package eli.com.applicastertask.manager;

import android.util.Log;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.params.Geocode;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import eli.com.applicastertask.interfaces.SearchStrategy;
import eli.com.applicastertask.model.classes.Query;
import eli.com.applicastertask.model.classes.TwitterQuery;
import eli.com.applicastertask.model.events.OnTweetsRetriedEvent;
import eli.com.applicastertask.utils.Constant;

/**
 * Created by eli on 22/12/16.
 */

public class TwitterSearchStrategy implements SearchStrategy {


    @Override
    public void search(Query query) {
        if (query instanceof TwitterQuery){
            TwitterQuery twitterQuery = (TwitterQuery) query;
            search(twitterQuery.getQueryText(),
                    null,
                    null,
                    null,
                    twitterQuery.getResultType().getName(),
                    twitterQuery.getLimit(), null, null, null, true);
        }else {
            search(query.getQueryText(),
                    null,
                    null,
                    null,
                    Constant.DEFAULT_TYPE_RESULT.getName(),
                    query.getLimit(), null, null, null, true);
        }
    }

    private void search(String query,
                        Geocode geocode,
                        String lang,
                        String locale,
                        String resultType,
                        Integer count,
                        String until,
                        Long sinceId,
                        Long maxId,
                        Boolean includeEntities) {
        Log.e("eli", "search for " + query);

        TwitterCore.getInstance().getApiClient().getSearchService().tweets(query,
                geocode,
                lang,
                locale,
                resultType,
                count,
                until,
                sinceId,
                maxId,
                includeEntities).enqueue(new Callback<Search>() {
            @Override
            public void success(Result<Search> result) {
                if (result != null && result.data != null) {
                    List<Tweet> tweets = result.data.tweets;
                    if (tweets != null && !tweets.isEmpty()) {
                        EventBus.getDefault().post(new OnTweetsRetriedEvent(true, tweets));
                    } else {
                        EventBus.getDefault().post(new OnTweetsRetriedEvent(false, null));
                    }
                } else {
                    EventBus.getDefault().post(new OnTweetsRetriedEvent(false, null));
                }
            }

            @Override
            public void failure(TwitterException exception) {
                Log.e("eli", "failure", exception);
                EventBus.getDefault().post(new OnTweetsRetriedEvent(false, null));
            }
        });

    }



}
