package com.splitjsbundle;

import androidx.annotation.NonNull;

import com.facebook.hermes.reactexecutor.HermesExecutorFactory;
import com.facebook.react.PackageList;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactInstanceEventListener;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint;
import com.facebook.react.defaults.DefaultReactActivityDelegate;
import com.facebook.react.defaults.DefaultReactNativeHost;

import java.util.List;

public class SingleBundleActivity extends ReactActivity {

    /**
     * Returns the name of the main component registered from JavaScript. This is used to schedule
     * rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "Module1";
    }

    /**
     * Returns the instance of the {@link ReactActivityDelegate}. Here we use a util class {@link
     * DefaultReactActivityDelegate} which allows you to easily enable Fabric and Concurrent React
     * (aka React 18) with two boolean flags.
     */
    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new MyReactActivityDelegate(
                this,
                getMainComponentName(),
                // If you opted-in for the New Architecture, we enable the Fabric Renderer.
                DefaultNewArchitectureEntryPoint.getFabricEnabled());
    }

    class MyReactActivityDelegate extends DefaultReactActivityDelegate {

        public MyReactActivityDelegate(@NonNull ReactActivity activity, @NonNull String mainComponentName, boolean fabricEnabled) {
            super(activity, mainComponentName, fabricEnabled);
        }

        @Override
        protected ReactNativeHost getReactNativeHost() {
            return new DefaultReactNativeHost(SingleBundleActivity.this.getApplication()) {
                @Override
                public boolean getUseDeveloperSupport() {
                    return true;
                }

                @Override
                protected List<ReactPackage> getPackages() {
                    @SuppressWarnings("UnnecessaryLocalVariable")
                    List<ReactPackage> packages = new PackageList(this).getPackages();
                    // Packages that cannot be autolinked yet can be added manually here, for example:
                    // packages.add(new MyReactNativePackage());
                    return packages;
                }

                @Override
                protected String getJSMainModuleName() {
                    return "index";
                }

                @Override
                protected boolean isNewArchEnabled() {
                    return BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;
                }

                @Override
                protected Boolean isHermesEnabled() {
                    return BuildConfig.IS_HERMES_ENABLED;
                }

                @Override
                protected ReactInstanceManager createReactInstanceManager() {
                    ReactInstanceManager reactInstanceManager = ReactInstanceManager.builder()
                            .setApplication(SingleBundleActivity.this.getApplication())
                            .setJSMainModulePath("index")
                            .setBundleAssetName("index.android.jsbundle")
                            .addPackages(getPackages())
                            .setUseDeveloperSupport(true)
                            .setInitialLifecycleState(LifecycleState.BEFORE_CREATE)
                            .setJavaScriptExecutorFactory(new HermesExecutorFactory())
                            .build();

                    return reactInstanceManager;
                }
            };
        }
    }
}
