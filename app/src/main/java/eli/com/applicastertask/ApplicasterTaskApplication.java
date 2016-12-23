package eli.com.applicastertask;

import android.app.Application;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

/**
 * Created by eli on 22/12/16.
 */
public class ApplicasterTaskApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        String consumerKey = getString(R.string.consumerKey);
        String SecretKet = getString(R.string.SecretKet);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(consumerKey, SecretKet);
        Fabric.with(this, new Twitter(authConfig));
    }
}
