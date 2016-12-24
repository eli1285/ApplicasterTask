package eli.com.applicastertask.activities;

import android.os.Bundle;

import eli.com.applicastertask.fragments.TweetsFragment;


public class LauncherActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            // Prevent of replace fragment on orientation change
            replaceFragment(TweetsFragment.newInstance(), false);
        }
    }


}
