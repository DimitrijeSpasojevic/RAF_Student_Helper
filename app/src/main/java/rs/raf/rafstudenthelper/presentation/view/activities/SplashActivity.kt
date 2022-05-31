package rs.raf.rafstudenthelper.presentation.view.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.koin.android.ext.android.inject
import rs.raf.rafstudenthelper.R
import rs.raf.rafstudenthelper.databinding.ActivityMainBinding
import rs.raf.rafstudenthelper.databinding.ActivitySplashBinding
import rs.raf.rafstudenthelper.presentation.view.compose.showLogin
import timber.log.Timber

class SplashActivity : AppCompatActivity() {

    private val prefKeyName = "prefKeyName"

    var name: String? = null

    private val sharedPref by inject<SharedPreferences>()


//    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        initSplash()
        super.onCreate(savedInstanceState)
//        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_splash)
    }



    private fun initSplash() {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            checkIfUserLog()
            false
        }

    }

    private fun checkIfUserLog(){
        name = sharedPref.getString(prefKeyName, null)
        if(name == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        finish()
    }
}
