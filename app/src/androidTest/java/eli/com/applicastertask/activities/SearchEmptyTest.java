package eli.com.applicastertask.activities;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static eli.com.applicastertask.utils.TestConstant.SEARCH_FOR_EMPTY;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SearchEmptyTest extends BaseSearchTest {

    @Rule
    public ActivityTestRule<LauncherActivity> mActivityTestRule = new ActivityTestRule<>(LauncherActivity.class);

    @Test
    public void SearchEmptyTest() {

        clickOnSearch();
        typeTextOnSearch(SEARCH_FOR_EMPTY);
        clickOnImeSearch(SEARCH_FOR_EMPTY);
        assertRowResultNotVisible();



    }


}
