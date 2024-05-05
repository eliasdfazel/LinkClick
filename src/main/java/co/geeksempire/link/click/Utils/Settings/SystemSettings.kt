package co.geeksempire.link.click.Utils.Settings

import android.app.ActivityOptions
import android.content.ComponentName
import android.content.Intent
import android.provider.Settings
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import co.geeksempire.link.click.R
import co.geeksempire.link.click.Service.AccessibilityServices

fun openAccessibilityServices(context: AppCompatActivity) {

    context.startActivity(
        Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
        ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, 0).toBundle())

}

fun accessibilityServiceEnabled(context: AppCompatActivity) : Boolean {

    val expectedComponentName = ComponentName(context, AccessibilityServices::class.java)

    val enabledServicesSetting = Settings.Secure.getString(context.contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES) ?: return false

    val colonSplitter = TextUtils.SimpleStringSplitter(':')
    colonSplitter.setString(enabledServicesSetting)

    while (colonSplitter.hasNext()) {

        val componentNameString = colonSplitter.next()

        val enabledService = ComponentName.unflattenFromString(componentNameString)

        if (enabledService != null && enabledService == expectedComponentName) {

            return true
        }

    }

    return false
}