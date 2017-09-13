package com.testcase.demo;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Class RobolectricTestRunnerWithResources created on 6/12/17.
 */

public class RobolectricTestRunnerWithResources extends RobolectricTestRunner {

    @Override
    protected Config buildGlobalConfig() {
        return Config.Builder.defaults()
                .setPackageName("com.testcase.demo")
                .setManifest("AndroidManifest.xml")
                .setResourceDir("../../../res/merged/debug") // relative to manifest
                .setAssetDir("../../../assets/debug") // relative to manifest
                .build();
    }

    public RobolectricTestRunnerWithResources(Class<?> testClass) throws InitializationError {
        super(testClass);
    }
}
