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
import rs.raf.rafstudenthelper.presentation.view.compose.showLogin
import timber.log.Timber

class LoginActivity : AppCompatActivity() {

    val prefKeyName = "prefKeyName"
    val prefKeyPass = "prefKeyPass"
    val validPassword: String = "123123"

    var name: String? = null

    private val sharedPref by inject<SharedPreferences>()

    override fun onCreate(savedInstanceState: Bundle?) {
        initSplash()
        super.onCreate(savedInstanceState)

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                showLogin{ password, username ->
                    run {
                        login(username, password)
                    }
                }

            }
        }
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
        if(name != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login(name: String, password: String) {
        sharedPref
            .edit()
            .putString(prefKeyName, name)
            .putString(prefKeyPass, password)
            .apply()
        val intent = Intent (this, MainActivity::class.java)
        startActivity(intent)
    }


}
