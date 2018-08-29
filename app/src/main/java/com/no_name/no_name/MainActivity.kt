package com.no_name.no_name

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.no_name.no_name.util.ThemeUtil.getThemeName
import daio.io.dresscode.dressCodeName
import daio.io.dresscode.matchDressCode
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity

/**
 * Main activity aka home screen of app
 */
class MainActivity : AppCompatActivity() {
    /**
     * Set initial configuration
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        matchDressCode()
        super.onCreate(savedInstanceState)
        dressCodeName = getThemeName(this)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (intent.getBooleanExtra("serverDown", false)) {
            alert("We are sorry, but our servers do not seem to be working at the moment. Please wait a few minutes before you try again.", "Sorry") {
                positiveButton("Okay") {
                    finishAffinity() // TODO: Loading activity will somehow still be opened after close
                    System.exit(0)
                }
            }.show()
        }

        if (intent.getBooleanExtra("notConnected", false))
            longToast("No internet connection!")

        fab.setOnClickListener { view ->
            startActivity<SettingsActivity>()
        }
    }


    /**
     * Inflate the [menu]; this adds items to the action bar if it is present
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    /**
     * Handling action bar [item] clicks
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
