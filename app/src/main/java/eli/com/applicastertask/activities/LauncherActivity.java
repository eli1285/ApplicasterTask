package eli.com.applicastertask.activities;

import android.os.Bundle;

import eli.com.applicastertask.fragments.TweetsFragment;


public class LauncherActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceFragment(TweetsFragment.newInstance(), false);
    }


}
