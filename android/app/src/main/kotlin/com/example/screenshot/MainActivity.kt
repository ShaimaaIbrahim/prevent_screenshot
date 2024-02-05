package com.example.screenshot

import android.annotation.SuppressLint
import android.app.Activity.ScreenCaptureCallback
import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugin.common.MethodChannel

@SuppressLint("NewApi")
class MainActivity : FlutterActivity() {

    private val screenCaptureCallback = ScreenCaptureCallback {
        _invoke()
    }

    private fun _invoke() {
        // Inside your activity or fragment
      MethodChannel(flutterEngine!!.dartExecutor.binaryMessenger, "your_channel_name").invokeMethod("")
    }

    override fun onStart() {
        super.onStart()
        registerScreenCaptureCallback(mainExecutor, screenCaptureCallback)
    }

    override fun onStop() {
        super.onStop()
        unregisterScreenCaptureCallback(screenCaptureCallback)
    }

}

//import android.annotation.SuppressLint
//import androidx.activity.ComponentActivity
//import io.flutter.embedding.android.FlutterActivity
//
//@SuppressLint("NewApi")
//class MainActivity: FlutterActivity() {
//
//    private val screenCaptureCallback = ScreenCaptureCallback {
//        // mainViewModel.screenshotValue()
//    }
//
//    override fun onStart() {
//        super.onStart()
//        registerScreenCaptureCallback(mainExecutor, screenCaptureCallback)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        unregisterScreenCaptureCallback(screenCaptureCallback)
//    }
//    //    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
////        super.configureFlutterEngine(flutterEngine)
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
////            val sound: Uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.packageName  + "/raw/alert")
////            val mChannel = NotificationChannel("app_alerts", "app_alerts_2", NotificationManager.IMPORTANCE_HIGH)
////            val audioAttributes: AudioAttributes = AudioAttributes.Builder()
////                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
////                .setUsage(AudioAttributes.USAGE_ALARM)
////                .build()
////            mChannel.setSound(sound , audioAttributes)
////            mChannel.description = "Important app Notifications"
////            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
////            notificationManager.createNotificationChannel(mChannel)
////        }
////    }
//
////    private val CHANNEL = "flutter.native/helper"
////
////    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
////        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
////
////            if(call.method == "preventScreenshots") {
////                activity.window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
////                result.success("Hello from Kotlin!")
////            } else {
////                result.notImplemented()
////            }
////        }
////    }
//}