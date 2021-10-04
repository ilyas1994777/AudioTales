package com.Blueeye.studio

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.Blueeye.studio.R
import com.Blueeye.studio.audiotales.RecyclerViewNameOfTales.MainMenu.ViewMainMenu
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}

        val intent = Intent(this, Media::class.java)
        startForegroundService(intent)




        Media.mainActtt = this
        Singleton.mainAc = this

        Singleton.notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        
        if (Singleton.notifCounter == 1){
//             println("qwewqrewrwerqweqwe")
//            stopService(intent)

        } else {

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.emptyFragment, ViewMainMenu())
                commit()
            }

            if (Singleton.notifCounter == 3){
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.emptyFragment, ListAudio())
                    commit()

                }
            }
        }



    }



//    override fun onDestroy() {
//        super.onDestroy()
//        Singleton.mp.release()
//    }

}