package eli.com.applicastertask.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import eli.com.applicastertask.R;
import eli.com.applicastertask.enums.TwitterResultType;
import eli.com.applicastertask.fragments.BaseFragment;
import eli.com.applicastertask.model.classes.TwitterQuery;
import eli.com.applicastertask.model.events.OnQuerySubmitEvent;
import eli.com.applicastertask.utils.Constant;
import eli.com.applicastertask.utils.Settings;


/**
 * Created by eli on 22/12/16.
 */

public class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SearchView searchView;
    private TextView tvAppTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFinds();
        setSupportActionBar(toolbar);
        initSearchOpenListener();
        initSearchCloseListener();
        initSearchOnSubmitListener();
        searchView.setImeOptions(searchView.getImeOptions() | EditorInfo.IME_FLAG_NO_EXTRACT_UI);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // on orientation change close the search
        searchView.onActionViewCollapsed();
        tvAppTitle.setVisibility(View.VISIBLE);
    }

    /**
     * Set the search view listener,
     * onQueryTextSubmit triggered when the keyboard action-search button pressed,
     * if the text not started with '#' it will append in the beginning of the text,
     * this can be change from the {@link Settings#toAddHashTagIfNotExist},
     * the query max length is 140 chars, as Twitter
     */
    private void initSearchOnSubmitListener() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!TextUtils.isEmpty(query)) {
                    if (Settings.toAddHashTagIfNotExist && !query.startsWith(Constant.HASH_TAG_IDENTIFIER)) {
                        query = Constant.HASH_TAG_IDENTIFIER + query;
                    }
                    if (query.length() > Constant.MAX_LENGTH_QUERY) {
                        query = query.substring(0, Constant.MAX_LENGTH_QUERY);
                    }
                    TwitterQuery twitterQuery = new TwitterQuery(query, Constant.MAX_SIZE_RESULT, TwitterResultType.RECENT);
                    EventBus.getDefault().post(new OnQuerySubmitEvent(twitterQuery));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void initSearchCloseListener() {
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                tvAppTitle.setVisibility(View.VISIBLE);
                return false;
            }
        });
    }

    private void initSearchOpenListener() {
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvAppTitle.setVisibility(View.GONE);
            }
        });
    }


    private void initFinds() {
        toolbar = (Toolbar) findViewById(R.id.toolBarInclude);
        searchView = (SearchView) toolbar.findViewById(R.id.searchView);
        tvAppTitle = (TextView) toolbar.findViewById(R.id.tvAppTitle);
    }

    /**
     * Replace fragment in the container layout
     *
     * @param fragment
     * @param withBackStack - if to save the transaction in back stack
     */
    public void replaceFragment(BaseFragment fragment, boolean withBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flContainer, fragment);
        if (withBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

}
