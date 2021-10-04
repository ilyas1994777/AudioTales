package com.Blueeye.studio

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi

class MyBroadcastReceiver: BroadcastReceiver(){
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action


        if (action.equals("Stop")){

            Media.getI().stop()

        }
        if (action.equals("close")){
            Singleton.notifCounter = 1
            Media.getI().stop()
            Media.getI().closeProces()
//            exitProcess(0)
        }


    }
}