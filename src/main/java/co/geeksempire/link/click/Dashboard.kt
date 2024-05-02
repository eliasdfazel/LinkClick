package co.geeksempire.link.click

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.geeksempire.link.click.databinding.DashboardBinding

class Dashboard : AppCompatActivity() {

    private lateinit var dashboardBinding: DashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardBinding = DashboardBinding.inflate(layoutInflater)
        setContentView(dashboardBinding.root)



    }

}