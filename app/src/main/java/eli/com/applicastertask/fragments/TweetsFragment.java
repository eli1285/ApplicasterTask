package eli.com.applicastertask.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twitter.sdk.android.core.models.Tweet;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import eli.com.applicastertask.R;
import eli.com.applicastertask.adapters.TwitterSearchResultAdapter;
import eli.com.applicastertask.manager.ExecutorServiceTickerStrategy;
import eli.com.applicastertask.manager.TwitterSearchStrategy;
import eli.com.applicastertask.model.classes.Query;
import eli.com.applicastertask.model.classes.TwitterQueryImageResult;
import eli.com.applicastertask.model.classes.TwitterQueryResult;
import eli.com.applicastertask.model.events.OnQuerySubmitEvent;
import eli.com.applicastertask.model.events.OnTweetsRetriedEvent;
import eli.com.applicastertask.model.events.TimeToRefreshEvent;
import eli.com.applicastertask.utils.Constant;


public class TweetsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rvTweets;
    private TwitterSearchStrategy twitterSearchStrategy;
    private Query currentQuery;
    private ExecutorServiceTickerStrategy executorServiceTickerStrategy;
    private TwitterSearchResultAdapter twitterSearchResultAdapter;
    private boolean isOnSearchProgress;

    public TweetsFragment() {
        // Required empty public constructor
    }

    public static TweetsFragment newInstance() {
        return new TweetsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init the concrete strategy classes
        twitterSearchStrategy = new TwitterSearchStrategy();
        executorServiceTickerStrategy = new ExecutorServiceTickerStrategy();
    }

    @Override
    public void onStart() {
        super.onStart();
        executorServiceTickerStrategy.start(Constant.REFRESH_SCREEN_DELAY, Constant.REFRESH_SCREEN_INTERVAL, TimeUnit.SECONDS);
    }

    @Override
    public void onStop() {
        executorServiceTickerStrategy.stop();
        super.onStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_applicaster_task, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFinds(view);
        setListeners();
    }

    private void initFinds(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        rvTweets = (RecyclerView) view.findViewById(R.id.rvTweets);
    }

    private void setListeners() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    /**
     * Called when time interval finish and the list need to be refreshed
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TimeToRefreshEvent event) {
        /**
         * Prevent from refresh if is in progress.
         * If the user has not typed query So there is nothing to refresh
         */
        if (!isOnSearchProgress && currentQuery != null) {
            swipeRefreshLayout.setRefreshing(true);
            searchCurrentQuery();
        }
    }

    /**
     * Called when action-search was pressed
     *
     * @param event
     */
    @Subscribe()
    public void onEvent(OnQuerySubmitEvent event) {
        currentQuery = event.getQuery();// save the query to the refresh functionality
        swipeRefreshLayout.setRefreshing(true);
        searchCurrentQuery();// search on twitter, with the fabric api
    }

    /**
     * Called when twitter search was finish, and now need to load the result
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(OnTweetsRetriedEvent event) {
        if (event.isSuccess()) {
            /**
             * Convert fabric tweet class into my private model.
             * This because I want to decouple the using of the fabric api, so we be able to change library easily
             */
            List<TwitterQueryResult> tweetsLocalResult = new LinkedList<>();
            List<Tweet> tweets = event.getTweets();
            for (Tweet t : tweets) {
                if (isImageTweet(t)) {
                    tweetsLocalResult.add(new TwitterQueryImageResult(t));
                } else {
                    tweetsLocalResult.add(new TwitterQueryResult(t));
                }
            }
            setAdapter(tweetsLocalResult);
        } else {
            setAdapter(null);
        }
        isOnSearchProgress = false;
        swipeRefreshLayout.setRefreshing(false);
    }


    private void setAdapter(List<TwitterQueryResult> tweets) {
        if (tweets != null && !tweets.isEmpty()) {
            if (twitterSearchResultAdapter == null) {
                initAndSetAdapter(tweets);
            } else {
                refreshAdapter(tweets);
            }
        }
    }

    private void refreshAdapter(List<TwitterQueryResult> tweets) {
        twitterSearchResultAdapter.refreshItems(tweets);
    }

    private void initAndSetAdapter(List<TwitterQueryResult> tweets) {
        twitterSearchResultAdapter = new TwitterSearchResultAdapter(getActivity(), tweets);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), layoutManager.getOrientation());
        rvTweets.addItemDecoration(dividerItemDecoration);
        rvTweets.setLayoutManager(layoutManager);
        rvTweets.setAdapter(twitterSearchResultAdapter);
    }

    private boolean isImageTweet(Tweet t) {
        boolean isImageTweet = false;
        if (t.entities != null && t.entities.media != null && !t.entities.media.isEmpty()) {
            isImageTweet = true;
        }
        return isImageTweet;
    }


    @Override
    public void onRefresh() {
        if (currentQuery != null) {
            searchCurrentQuery();
        } else {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private void searchCurrentQuery() {
        isOnSearchProgress = true;
        twitterSearchStrategy.search(currentQuery);
    }
}
