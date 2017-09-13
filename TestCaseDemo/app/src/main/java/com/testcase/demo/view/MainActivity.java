package com.testcase.demo.view;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.GridLayout;
import android.widget.Toolbar;

import com.testcase.demo.R;

/**
 * Class MainActivity created on 6/12/17.
 */

public class MainActivity extends Activity {


        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setActionBar(toolbar);

            GridLayout layout = (GridLayout) findViewById(R.id.grid_layout);
            layout.requestLayout();
        }
}
