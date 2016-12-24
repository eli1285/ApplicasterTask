package eli.com.applicastertask.activities;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import eli.com.applicastertask.utils.RandomString;

import static eli.com.applicastertask.utils.TestConstant.SEARCH_FOR;
import static eli.com.applicastertask.utils.TestConstant.SEARCH_FOR_EMPTY;
import static eli.com.applicastertask.utils.TestConstant.SEARCH_FOR_HASH_TAG;


/**
 * Created by eli on 24/12/16.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestApp extends BaseSearchTest{
    @Rule
    public ActivityTestRule<LauncherActivity> mActivityTestRule = new ActivityTestRule<>(LauncherActivity.class);

    @Test
    public void TestApp() {

        clickOnSearch();
        typeTextOnSearch(SEARCH_FOR_EMPTY);
        clickOnImeSearch(SEARCH_FOR_EMPTY);
        assertRowResultNotVisible();

        waitThread(5000);

        typeTextOnSearch(SEARCH_FOR_HASH_TAG);
        clickOnImeSearch(SEARCH_FOR_HASH_TAG);
        assertRowResultNotVisible();

        waitThread(5000);

        typeTextOnSearch(SEARCH_FOR);
        clickOnImeSearch(SEARCH_FOR);
        assertRowResultVisible();

        waitThread(5000);

        typeTextOnSearch(SEARCH_FOR_EMPTY);
        clickOnImeSearch(SEARCH_FOR_EMPTY);
        assertRowResultVisible();

        waitThread(5000);

        String randStr = new RandomString(140).nextString();
        typeTextOnSearch(randStr);
        clickOnImeSearch(randStr);
        assertRowResultNotVisible();

        waitThread(5000);

        String randStrBig = new RandomString(200).nextString();
        typeTextOnSearch(randStrBig);
        clickOnImeSearch(randStrBig);
        assertRowResultNotVisible();

        waitThread(5000);
    }


}
