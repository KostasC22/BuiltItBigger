package com.havistudio.builditbigger;

import android.app.Application;
import android.content.Context;
import android.support.v4.util.Pair;
import android.test.ApplicationTestCase;
import android.widget.TextView;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testVerifyNotEmptyString() {
        boolean isNotEmptyString = false;
        String temp = null;

        try {
            temp = new EndpointsAsyncTask().execute(new Pair<Context, String>(this.getContext(), "Manfred")).get();
        } catch (Exception e){

        }
        if(!temp.isEmpty() && temp != null){
            isNotEmptyString = true;
        }

        assertEquals(true, isNotEmptyString);
    }
}