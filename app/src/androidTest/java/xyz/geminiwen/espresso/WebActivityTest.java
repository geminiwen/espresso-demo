package xyz.geminiwen.espresso;

import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.jakewharton.espresso.OkHttp3IdlingResource;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.nio.charset.Charset;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okio.Buffer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.registerIdlingResources;
import static android.support.test.espresso.Espresso.unregisterIdlingResources;
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
public class WebActivityTest {
    @Rule
    public ActivityTestRule<WebActivity> mTestRule = new ActivityTestRule<>(WebActivity.class);

    @ClassRule
    public static MockWebServer sMockWebServer = new MockWebServer();

    @BeforeClass
    public static void setUp() {
        sMockWebServer.setDispatcher(sServerDispatcher);
    }

    @Test
    public void testClick() throws Exception {
        WebActivity activity = mTestRule.getActivity();
        IdlingResource okhttp = OkHttp3IdlingResource.create("test", activity.getOkHttpClient());
        registerIdlingResources(okhttp);

        activity.setBaseUrl(sMockWebServer.url("/").toString());
        onView(withId(R.id.btn))
                .perform(click());

        onView(withText("hello world!"))
                // toast is not in the same window with activity
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
        unregisterIdlingResources(okhttp);
    }

    private static Dispatcher sServerDispatcher = new Dispatcher() {
        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
            MockResponse response = new MockResponse();
            response.setBody("hello world!");
            response.setResponseCode(200);
            return response;
        }
    };
}
