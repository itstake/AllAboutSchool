package me.itstake.allaboutschool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.main_activity.*
import me.itstake.allaboutschool.ui.fragments.feed.FeedFragment
import me.itstake.allaboutschool.ui.fragments.meals.MealsFragment
import me.itstake.allaboutschool.ui.fragments.settings.SettingsFragment
import me.itstake.allaboutschool.ui.fragments.timetable.TimeTableFragment
import me.itstake.allaboutschool.ui.fragments.todo.ToDoFragment

class MainActivity : AppCompatActivity() {

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { t ->
        when(t.itemId) {
            R.id.action_feed -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, FeedFragment.newInstance())
                        .commitNow()
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_time_table -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, TimeTableFragment.newInstance())
                        .commitNow()
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_todo -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, ToDoFragment.newInstance())
                        .commitNow()
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_meals -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, MealsFragment.newInstance())
                        .commitNow()
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_settings -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, SettingsFragment.newInstance())
                        .commitNow()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, TimeTableFragment.newInstance())
                    .commitNow()
            nav.selectedItemId = R.id.action_time_table
        }
        nav.setOnNavigationItemSelectedListener(navListener)
    }

}