package rs.raf.rafstudenthelper.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import rs.raf.rafstudenthelper.modules.movieModule
import rs.raf.rafstudenthelper.modules.coreModule
import rs.raf.rafstudenthelper.modules.noteModule
import timber.log.Timber

class RAFStudentHelperApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initTimber()
        initKoin()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        val modules = listOf(
            coreModule,
            movieModule,
            noteModule
        )
        startKoin {
            androidLogger(Level.ERROR)
            // Use application context
            androidContext(this@RAFStudentHelperApplication)
            // Use properties from assets/koin.properties
            androidFileProperties()
            // Use koin fragment factory for fragment instantiation
            fragmentFactory()
            // modules
            modules(modules)
        }
    }

}