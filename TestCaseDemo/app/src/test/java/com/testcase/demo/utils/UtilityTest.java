package com.testcase.demo.utils;

import android.text.TextUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.any;

/**
 * Utility Test.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(TextUtils.class)
public class UtilityTest {

    private static final String EMPTY_STRING = "";
    private static final String NULL_STRING = null;
    private static final String NA_STRING = "NA";
    private static final String DUMMY_DATA = "Meenal";
    public static String REQFORMAT = "yyyy-MM-dd";//2016-10-25";

    @Before
    public void setup() {
        // mocking TextUtils class to test for empty string
        PowerMockito.mockStatic(TextUtils.class);
        PowerMockito.when(TextUtils.isEmpty(any(CharSequence.class))).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                CharSequence a = (CharSequence) invocation.getArguments()[0];
                return !(a != null && a.length() > 0);
            }
        });
    }

    @Test
    public void formateDate() {
        Assert.assertEquals("02/01/2017", Utility.formateDate("2017-02-01T00:00:00.0000000"));

    }

    @Test
    public void getCurrentDate() {
        Assert.assertEquals("2017-05-12", Utility.getCurrentDateTimeFormatted());
        Assert.assertNotEquals("2017-03-24", Utility.getCurrentDateTimeFormatted());
    }

    @Test
    public void testEmptyGetStringText() {
        Assert.assertNotNull(Utility.getStringText(EMPTY_STRING));
    }

    @Test
    public void testNAGetStringText() {
        Assert.assertEquals(NA_STRING, Utility.getStringText(NULL_STRING));
    }

    @Test
    public void testGetStringText() {
        Assert.assertEquals(DUMMY_DATA, Utility.getStringText(DUMMY_DATA));
    }

    @Test
    public void getDate5daysAfterCurrentDate() {
        Assert.assertEquals("2017-06-17", Utility.getCalculatedDate(REQFORMAT, 5));
    }

    @Test
    public void getDate1daysAfterCurrentDate() {
        Assert.assertEquals("2017-06-13", Utility.getCalculatedDate(REQFORMAT, 1));
    }

    @Test
    public void compareDate() {
        Assert.assertEquals(5, Utility.compareDate("2017-06-17T00:00:00.0000000"));

        Assert.assertEquals(1, Utility.compareDate("2017-06-13T00:00:00.0000000"));
        Assert.assertNotEquals(2, Utility.compareDate("2017-03-18T00:00:00.0000000"));
    }

}
