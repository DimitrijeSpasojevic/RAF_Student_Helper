package rs.raf.rafstudenthelper.presentation.view.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.rafstudenthelper.R
import rs.raf.rafstudenthelper.presentation.view.fragments.InputFragment
import rs.raf.rafstudenthelper.presentation.view.fragments.ListFragment
import rs.raf.rafstudenthelper.presentation.view.fragments.StatisticsFragment

class MainPagerAdapter(
    fragmentManager: FragmentManager,
    private val context: Context
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val ITEM_COUNT = 3
        const val FRAGMENT_1 = 0
        const val FRAGMENT_2 = 1
        const val FRAGMENT_3 = 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            FRAGMENT_1 -> ListFragment()
            FRAGMENT_2 -> InputFragment()
            else -> StatisticsFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            FRAGMENT_1 -> context.getString(R.string.courses)
            FRAGMENT_2 -> context.getString(R.string.notes)
            else -> context.getString(R.string.statistics)
        }
    }

}