package xyz.geminiwen.espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by geminiwen on 28/12/2016.
 */
@RunWith(AndroidJUnit4.class)
public class ViewActivityTest {
    @Rule
    public ActivityTestRule<ViewActivity> mTestRule = new ActivityTestRule<>(ViewActivity.class);

    @Test
    public void testClick() throws Exception {
        ViewActivity activity = mTestRule.getActivity();
        onView(withId(R.id.btn))
                .perform(click());

        Thread.sleep(200);
        onView(withText("Binggo!"))
                .check(matches(isDisplayed()));
    }
}
