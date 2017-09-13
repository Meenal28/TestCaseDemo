package com.testcase.demo.view;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.testcase.demo.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Class LoginActivityTest created on 6/12/17.
 */


@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    private static final String STRING_TO_BE_TYPED_INVALID_USERNAME = "meenal.sharma@gmail.com";
    private static final String STRING_TO_BE_TYPED_INVALID_PASSWORD = "te12345";

    private static final String STRING_TO_BE_TYPED_VALID_USERNAME = "foo@example.com";
    private static final String STRING_TO_BE_TYPED_VALID_PASSWORD = "foo";
    private static final String STRING_LOGIN = "Sign in or register";
    private Context context;


    @Before
    public void setUp() {
        context = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void loginInValidCredentialsTest() {

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.email), isDisplayed()));
        appCompatEditText.perform(typeText(STRING_TO_BE_TYPED_INVALID_USERNAME), closeSoftKeyboard());


        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.password), isDisplayed()));
        appCompatEditText2.perform(typeText(STRING_TO_BE_TYPED_INVALID_PASSWORD), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.email_sign_in_button), withText(STRING_LOGIN), isDisplayed()));
        appCompatButton.perform(click());

    }

    @Test
    public void loginValidCredentialsTest() {

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.email), isDisplayed()));
        appCompatEditText.perform(typeText(STRING_TO_BE_TYPED_VALID_USERNAME), closeSoftKeyboard());


        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.password), isDisplayed()));
        appCompatEditText2.perform(typeText(STRING_TO_BE_TYPED_VALID_PASSWORD), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.email_sign_in_button), withText(STRING_LOGIN), isDisplayed()));
        appCompatButton.perform(click());

    }


}
