package xyz.geminiwen.espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by geminiwen on 28/12/2016.
 */
@RunWith(AndroidJUnit4.class)
public class ToastActivityTest {
    @Rule
    public ActivityTestRule<ToastActivity> mTestRule = new ActivityTestRule<>(ToastActivity.class);

    @Test
    public void testClick() throws Exception {
        ToastActivity activity = mTestRule.getActivity();
        onView(withId(R.id.btn))
                .perform(click());

        Thread.sleep(200);
        onView(withText("Binggo!"))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }
}
