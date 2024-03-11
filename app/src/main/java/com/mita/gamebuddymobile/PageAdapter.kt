package com.mita.gamebuddymobile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageAdapter(fragmentManager:FragmentManager,lifecycle: Lifecycle):FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 2 // Adjusted to 2 since you mentioned you want to display two different Fragments
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                AllUsersFragment()
            }
            1 -> {
                MessagesSavedFragment()
            }
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}