package rs.raf.rafstudenthelper.presentation.view.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import okhttp3.internal.cache2.Relay.Companion.edit
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.compat.SharedViewModelCompat.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.rafstudenthelper.presentation.contract.NoteContract
import rs.raf.rafstudenthelper.presentation.view.compose.showLogin
import rs.raf.rafstudenthelper.presentation.viewmodel.NoteViewModel

class LoginActivity : AppCompatActivity() {

    val prefKeyName = "prefKeyName"
    val prefKeyPass = "prefKeyPass"
    val validPassword: String = "123123"
    private val sharedPref by inject<SharedPreferences>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                showLogin()
            }
        }
    }

    private fun initView() {
        val splashScreen: SplashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            Thread.sleep(4000)
//            checkIfUserLog()
            false
        }
        val intent = Intent (this, MainActivity::class.java)
        startActivity(intent)
    }

//    private fun checkIfUserLog() {
//        var name = sharedPref.getString(prefKeyName, null)
//        if (name == null){
//            val intent = Intent(this, MainActivity::class.java).apply {
//                putExtra(EXTRA_MESSAGE, "message")
//            }
//            startActivity(intent)
//        }
//    }


    private fun startMain(){

    }

    private fun login(name: String, password: String){
//        sharedPref
//            .edit()
//            .putString(prefKeyName, name)
//            .putString(prefKeyPass, password)
//            .apply()
        val intent = Intent (this, MainActivity::class.java)
        startActivity(intent)
    }


}
