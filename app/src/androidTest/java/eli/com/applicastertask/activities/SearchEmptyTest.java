package eli.com.applicastertask.activities;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static eli.com.applicastertask.constants.Constant.SEARCH_FOR_EMPTY;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SearchEmptyTest extends BaseSearchTest {

    @Rule
    public ActivityTestRule<LauncherActivity> mActivityTestRule = new ActivityTestRule<>(LauncherActivity.class);

    @Test
    public void searchEmptyTest() {

        clickOnSearch();
        typeTextOnSearch(SEARCH_FOR_EMPTY);
        clickOnImeSearch();
        assertRowResultVisible(false);


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
