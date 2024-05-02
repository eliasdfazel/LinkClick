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
import android.content.Intent
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import co.geeksempire.link.click.Utils.Operations.extractUrl


class AccessibilityServices : AccessibilityService() {


    override fun onServiceConnected() {}

    override fun onAccessibilityEvent(accessibilityEvent: AccessibilityEvent) {

        when (accessibilityEvent.eventType) {
            AccessibilityEvent.TYPE_VIEW_CLICKED -> {
                Log.i(this@AccessibilityServices.javaClass.simpleName, "Clicked")

                accessibilityEvent.source?.let { accessibilityNodeInfo ->

                    try {

                        Log.i(this@AccessibilityServices.javaClass.simpleName, accessibilityNodeInfo.text.toString())
                        Log.i(this@AccessibilityServices.javaClass.simpleName, accessibilityNodeInfo.text.toString().extractUrl().toString())

                    } catch (e: Exception) {}

                }

            }
            AccessibilityEvent.TYPE_TOUCH_INTERACTION_END -> {
                Log.i(this@AccessibilityServices.javaClass.simpleName, "Touched")

                accessibilityEvent.source?.let { accessibilityNodeInfo ->

                    try {

                        Log.i(this@AccessibilityServices.javaClass.simpleName, accessibilityNodeInfo.text.toString())
                        Log.i(this@AccessibilityServices.javaClass.simpleName, accessibilityNodeInfo.text.toString().extractUrl().toString())

                    } catch (e: Exception) {}

                }

            }
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED -> {
                Log.i(this@AccessibilityServices.javaClass.simpleName, "Touched")

                accessibilityEvent.source?.let { accessibilityNodeInfo ->

                    try {

                        Log.i(this@AccessibilityServices.javaClass.simpleName, accessibilityNodeInfo.text.toString())
                        Log.i(this@AccessibilityServices.javaClass.simpleName, accessibilityNodeInfo.text.toString().extractUrl().toString())

                    } catch (e: Exception) {}

                }

            }
            else -> {

            }
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
