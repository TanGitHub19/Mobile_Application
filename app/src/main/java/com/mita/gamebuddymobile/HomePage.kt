package com.mita.gamebuddymobile

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.mita.gamebuddymobile.api.UserDataClass
import com.mita.gamebuddymobile.databinding.ActivityHomePageBinding

class HomePage : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var recyclerView : RecyclerView
    private lateinit var userList : ArrayList<UserDataClass>
    private lateinit var UserAdapter : UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.bottom_home -> {
                    true
                }

//                R.id.bottom_users -> {
//                    val intent = Intent(this, Users::class.java)
//                    startActivity(intent)
//                    true
//                }
                // Handle other menu items if needed
                else -> false
            }
        }
        bottomNavigationView.selectedItemId = R.id.bottom_home

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        userList = ArrayList()

        userList.add(UserDataClass(R.drawable.baseline_people_24, "Lan2x", "Male", 18, "League of Legends, Valorant"))
        userList.add(UserDataClass(R.drawable.baseline_people_24, "Rhalf", "Male", 18, "League of Legends, Valorant"))
        userList.add(UserDataClass(R.drawable.baseline_people_24, "R2x", "Male", 18, "League of Legends, Valorant"))
        userList.add(UserDataClass(R.drawable.baseline_people_24, "Ken", "Male", 18, "League of Legends, Valorant"))
        userList.add(UserDataClass(R.drawable.baseline_people_24, "Tristan", "Male", 18, "League of Legends, Valorant"))
        userList.add(UserDataClass(R.drawable.baseline_people_24, "Cy", "Male", 18, "League of Legends, Valorant"))
        userList.add(UserDataClass(R.drawable.baseline_people_24, "Notnot", "Male", 18, "League of Legends, Valorant"))



        UserAdapter = UserAdapter(userList)
        recyclerView.adapter = UserAdapter

    }
}
