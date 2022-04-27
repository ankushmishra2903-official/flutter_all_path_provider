package com.example.flutter_all_path_provider_example

import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugins.GeneratedPluginRegistrant

open class MainActivity : FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.flutterEngine?.let { GeneratedPluginRegistrant.registerWith(it) }
    }
}