/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel
 * Last modified 5/4/20 12:51 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */
package co.geeksempire.link.click.Service

import android.accessibilityservice.AccessibilityService
import android.app.Service
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import co.geeksempire.link.click.Utils.Operations.extractUrl


class AccessibilityServices : AccessibilityService() {

    override fun onServiceConnected() {}

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int { return Service.START_STICKY }

    override fun onAccessibilityEvent(accessibilityEvent: AccessibilityEvent) {

        var parseUrl: String = ""

        when (accessibilityEvent.eventType) {
            AccessibilityEvent.TYPE_VIEW_CLICKED -> {

                if (accessibilityEvent.packageName == "com.instagram.android") {
                    Log.i(this@AccessibilityServices.javaClass.simpleName, "Clicked")

                    accessibilityEvent.source?.let { accessibilityNodeInfo ->

                        try {

                            Log.i(this@AccessibilityServices.javaClass.simpleName, accessibilityNodeInfo.text.toString())
                            Log.i(this@AccessibilityServices.javaClass.simpleName, accessibilityNodeInfo.text.toString().extractUrl())

                            parseUrl = accessibilityNodeInfo.text.toString().extractUrl()

                        } catch (_: Exception) {}

                    }

                }

            }
            else -> {

            }
        }

        if (parseUrl.isNotEmpty()) {

            applicationContext.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(parseUrl)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            })

        }

    }

    override fun onInterrupt() {
        startService(Intent(applicationContext, AccessibilityServices::class.java))
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
