package com.testcase.demo.view;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

import com.testcase.demo.BuildConfig;
import com.testcase.demo.R;
import com.testcase.demo.RobolectricTestRunnerWithResources;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * Class LoginActivityUnitTest created on 6/9/17.
 */
@RunWith(RobolectricTestRunnerWithResources.class)
@Config(constants = BuildConfig.class)
public class LoginActivityUnitTest {

    private EditText emailView;
    private EditText passwordView;
    private Button button;

    @Before
    public void setUp() {
        Activity activity = Robolectric.setupActivity(LoginActivity.class);
        button = (Button) activity.findViewById(R.id.email_sign_in_button);
        emailView = (EditText) activity.findViewById(R.id.email);
        passwordView = (EditText) activity.findViewById(R.id.password);
    }
    @Test
    public void loginSuccess() {
        emailView.setText("foo@example.com");
        passwordView.setText("foo");
        button.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertThat("Next activity has started", application.getNextStartedActivity(), is(notNullValue()));
    }

    @Test
    public void loginWithEmptyUsernameAndPassword() {
        button.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertThat("Next activity should not started", application.getNextStartedActivity(), is(nullValue()));
        assertThat("Show error for Email field ", emailView.getError(), is(CoreMatchers.notNullValue()));
        assertThat("Show error for Password field ", passwordView.getError(), is(CoreMatchers.notNullValue()));
    }

    @Test
    public void loginFailure() {
        emailView.setText("invalid@email");
        passwordView.setText("invalidpassword");
        button.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertThat("Next activity should not started", application.getNextStartedActivity(), is(nullValue()));
        assertThat("Show error for Email field ", emailView.getError(), is(CoreMatchers.notNullValue()));
        assertThat("Show error for Password field ", passwordView.getError(), is(CoreMatchers.notNullValue()));
    }
}
