package com.Blueeye.studio

import android.app.NotificationManager
import androidx.fragment.app.Fragment
import com.Blueeye.studio.R

object Singleton{
    var currentTales = 0
    lateinit var mainAc: MainActivity
    lateinit var notificationManager: NotificationManager
   var notifCounter = 0
    var currentPositionScroll = 0





    fun switchFragment(fragment: Fragment){
        mainAc.supportFragmentManager.beginTransaction().apply {
            replace(R.id.emptyFragment, fragment)
            addToBackStack(null)
            commit()
        }
    }




}