package com.splitjsbundle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.hermes.reactexecutor.HermesExecutorFactory
import com.facebook.react.PackageList
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactRootView
import com.facebook.react.common.LifecycleState

class MultiBundleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val reactRootView = ReactRootView(this)
        val reactInstanceManager = ReactInstanceManager.builder()
            .setApplication(this.application)
            .setJSMainModulePath("index")
            .setBundleAssetName("_dll.android.bundle")
            .addPackages(PackageList(this.application).packages)
            .setUseDeveloperSupport(false)
            .setInitialLifecycleState(LifecycleState.BEFORE_CREATE)
            .setJavaScriptExecutorFactory(HermesExecutorFactory())
            .build()

        reactInstanceManager.createReactContextInBackground()
        reactInstanceManager.addReactInstanceEventListener { reactContext -> //这里加载业务 Bundle
            reactContext.catalystInstance.loadScriptFromAssets(
                reactContext.assets,
                "assets://module1/buz.android.bundle",
                false
            )
            reactRootView.startReactApplication(reactInstanceManager, "Module1")
            setContentView(reactRootView)
        }
    }
}
