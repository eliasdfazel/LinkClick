package co.geeksempire.link.click

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.geeksempire.link.click.Utils.Views.Dialogue.ConfirmDialogue
import co.geeksempire.link.click.Utils.Views.Dialogue.ConfirmDialogueInterface
import co.geeksempire.link.click.databinding.DashboardBinding

class Dashboard : AppCompatActivity() {

    private lateinit var dashboardBinding: DashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardBinding = DashboardBinding.inflate(layoutInflater)
        setContentView(dashboardBinding.root)

        dashboardBinding.enableService.setOnClickListener {

            Handler(Looper.getMainLooper()).postDelayed({

                ConfirmDialogue(this@Dashboard, dashboardBinding.root)
                    .initialize(getString(R.string.serviceTitle), getString(R.string.serviceDescription))
                    .show(object : ConfirmDialogueInterface {

                        override fun confirmed() {

                            startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
                                ActivityOptions.makeCustomAnimation(applicationContext, R.anim.fade_in, 0).toBundle())

                            Toast.makeText(applicationContext, "Enable Link Click", Toast.LENGTH_LONG).show();

                        }

                        override fun dismissed() {



                        }

                    })

            }, 555)

        }

    }

}