package co.geeksempire.link.click

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.geeksempire.link.click.Utils.Settings.accessibilityServiceEnabled
import co.geeksempire.link.click.Utils.Settings.openAccessibilityServices
import co.geeksempire.link.click.Utils.Views.Dialogue.ConfirmDialogue
import co.geeksempire.link.click.Utils.Views.Dialogue.ConfirmDialogueInterface
import co.geeksempire.link.click.databinding.DashboardBinding

class Dashboard : AppCompatActivity() {

    private lateinit var dashboardBinding: DashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardBinding = DashboardBinding.inflate(layoutInflater)
        setContentView(dashboardBinding.root)

        dashboardBinding.enableService.setImageDrawable(if (accessibilityServiceEnabled(this@Dashboard)) {

            getDrawable(R.drawable.on)

        } else {

            getDrawable(R.drawable.off)

        })

        dashboardBinding.enableService.setOnClickListener {

            Handler(Looper.getMainLooper()).postDelayed({

                ConfirmDialogue(this@Dashboard, dashboardBinding.root)
                    .initialize(getString(R.string.serviceTitle), getString(R.string.serviceDescription))
                    .show(object : ConfirmDialogueInterface {

                        override fun confirmed() {

                            openAccessibilityServices(this@Dashboard)

                            Toast.makeText(applicationContext, "Enable Link Click", Toast.LENGTH_LONG).show()

                        }

                        override fun dismissed() {



                        }

                    })

            }, 555)

        }

        dashboardBinding.share.setOnClickListener {

            this@Dashboard.startActivity(Intent(Intent.ACTION_SEND).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, getString(R.string.sharingScript))
            })

        }

        dashboardBinding.facebook.setOnClickListener {

            this@Dashboard.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.facebookLink))))

        }

        dashboardBinding.youtube.setOnClickListener {

            this@Dashboard.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.youtubeLink))))

        }

        dashboardBinding.instagram.setOnClickListener {

            this@Dashboard.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.instagramLink))))

        }

        dashboardBinding.testLinkClicks.apply {

            text = Html.fromHtml(getString(R.string.testNotice), Html.FROM_HTML_MODE_COMPACT)

            setOnClickListener {

                this@Dashboard.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.testLinkClick))))

            }

        }

    }

}