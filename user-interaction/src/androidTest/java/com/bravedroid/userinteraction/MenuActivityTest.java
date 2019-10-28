package com.bravedroid.userinteraction;

import androidx.test.core.app.ActivityScenario;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.bravedroid.userinteraction.util.TestMatchers.withItemMenuIconDrawable;

public class MenuActivityTest {

    @Test
    public void testOptionsMenu_WhenUserClickOnRandomizer_ItemMenuIconShouldBeSetToTheSecondIcon() throws InterruptedException {
        //arrange
        ActivityScenario<ActivityUnderTest> scenario = ActivityScenario.launch(ActivityUnderTest.class);
        //act
        onView(withId(R.id.textView_main_random))
                .perform(click());
        Thread.sleep(10_000);
        //assert
        onView(withId(R.id.action_order))
                .check(matches(withItemMenuIconDrawable(R.drawable.ic_order_name)));
    }

    public static class ActivityUnderTest extends MenuActivity {
        @Override
        protected int[] provideIconsArray() {
            return new int[]{
                    R.drawable.ic_shop,
                    R.drawable.ic_order_name,
            };
        }
    }
}
