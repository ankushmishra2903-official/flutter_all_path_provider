package com.example.flutter_all_path_provider

import android.content.Context
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.FlutterPlugin.FlutterPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler


/** PathProviderExPlugin  */
class FlutterAllPathProviderPlugin : MethodCallHandler, FlutterPlugin {
    private var channel: MethodChannel? = null
    private var applicationContext: Context? = null
    override fun onAttachedToEngine(flutterPluginBinding: FlutterPluginBinding) {
        applicationContext = flutterPluginBinding.applicationContext
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, CHANNEL_NAME)
        channel!!.setMethodCallHandler(this)
    }

    override fun onDetachedFromEngine(flutterPluginBinding: FlutterPluginBinding) {
        channel!!.setMethodCallHandler(null)
        applicationContext = null
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        if (call.method == "getExtStorageData") {
            val reply: ArrayList<HashMap<*, *>> = StorageUtils.getExternalStorageAvailableData(applicationContext!!)
            result.success(reply)
        } else {
            result.notImplemented()
        }
    }

    companion object {
        private const val CHANNEL_NAME = "flutter_all_path_provider"
    }
}

// Map<String, Object> reply = new HashMap<>();
// ArrayList<String> paths =
// StorageUtils.getExternalFilePaths(getApplicationContext());
// reply.put("paths", paths);
// result.success(reply);