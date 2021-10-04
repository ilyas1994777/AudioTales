package com.Blueeye.studio

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.Blueeye.studio.R


class Media : Service() {
    private var mp:MediaPlayer? = null
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: NotificationCompat.Builder
    private val channelId = "ch"
    private val description = "Test notification"
private var progressBar:ProgressBar? = null
    private var notification:Notification? = null
//    private var textView:TextView? = null
    private var timer_:CountDownTimer? = null
//    companion object{
//         var med = Media()
//
//   }


    companion object{
    var mainActtt : MainActivity? = null
       private var instance: Media? = null

             fun getI(): Media {
                    if (instance == null)
                        instance = Media()
                 return instance as Media
                }
    }

    fun getMp():MediaPlayer?{
        return mp
    }
    fun setProgresSerTextView(progressBar:ProgressBar){
        this.progressBar = progressBar
//        this.textView = textView
    }
    fun timer(): CountDownTimer {
        val countDownTimer = object : CountDownTimer( getI().getDuration().toLong(), 100) {
            override fun onTick(millisUntilFinished: Long) {
                if(getI().getMp() != null)
                    progressBar?.progress =   getI().getCurrentPos()
                else Log.d("error", "error")

//                textView?.text = ( Media.getI().getCurrentPos()).toString()

            }

            override fun onFinish() {
//                textView?.text = "finish"
                progressBar?.progress =  getI().getCurrentPos()
            }
        }
        return countDownTimer
    }

    fun setMpNull(){

        this.mp =null
    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= 26) {
            val updateIntent = Intent(this, MyBroadcastReceiver::class.java)
            updateIntent .apply {
                action = "Stop"
//                putExtra("UserID","100")
            }
            val updatePendingIntent = PendingIntent.getBroadcast(this,0,updateIntent ,0)

                  val closeAppAction = Intent(this, MyBroadcastReceiver::class.java)
                   closeAppAction .apply {
                        action = "close"
        //                putExtra("UserID","100")
                    }
                    val closeAppIntent = PendingIntent.getBroadcast(this,0,closeAppAction ,0)

            Singleton.notifCounter = 3
            val intent_ = Intent(this, MainActivity::class.java)


            val pendingIntent = PendingIntent.getActivity(this, 0, intent_, 0)
            val CHANNEL_ID = "my_channel_01"
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
                channel
            )
             notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Проигрыватель Сказок")
                .setContentText("Нажмите что бы открыть приложение")
                .setSmallIcon(R.drawable.ic_baseline_play_arrow_24)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.i1))
                .setContentIntent(pendingIntent)
                .setTicker("Aldar Cose")
//                 .setAutoCancel(true)
                .addAction(R.drawable.ic_baseline_arrow_back_24,"Остановить сказку",updatePendingIntent)
                .addAction(R.drawable.ic_baseline_arrow_back_24,"Закрыть приложене",closeAppIntent)
                .build()
            with(NotificationManagerCompat.from(this)) {

                notify(1, notification!!)
            }


            startForeground(1, notification)
        }



//         Singleton.Media = Media()
//        Toast.makeText(this, Singleton.Media!!.isPlay().toString(), Toast.LENGTH_SHORT).show()

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//
//       if(mp != null) Toast.makeText(this, mp!!.isPlaying.toString(), Toast.LENGTH_SHORT).show()
        return START_NOT_STICKY
    }



    fun setAudio(context: Context, index: Int) {
        getI().mp = MediaPlayer.create(context, index)
        timer_ = timer()
    }

    fun isPlay(): Boolean {
        return getI().mp!!.isPlaying
    }

    fun getCurrentPos(): Int {

        return getI().mp!!.currentPosition
    }

    fun seekTo(ms: Int) {
        if(getI().mp != null)  {
            getI().mp!!.seekTo(ms)
        timer_!!.start()
        }
    }

    fun reset() {
        if(getI().mp != null)   getI().mp!!.reset()
    }

    fun pause() {
        if(getI().mp != null)  {
            getI().mp!!.pause()
        timer_!!.cancel()
        }
    }

    fun getDuration(): Int {
        return getI().mp!!.duration
    }

    fun start() {
        if(getI().mp != null)  {
            getI().mp!!.start()

            timer_!!.start()
        }
    }

    fun stop() {
       if(getI().mp != null) {
           getI().mp!!.stop()
       timer_!!.cancel()
       }
    }

     @SuppressLint("WrongConstant")
     @RequiresApi(Build.VERSION_CODES.N)
     fun closeProces(){
         println("CLOSEPRCEs")
//         stopForeground(1)
//
         stopSelf()
         stopForeground(true)

     }

//    override fun onTaskRemoved(rootIntent: Intent?) {
//        super.onTaskRemoved(rootIntent)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
//        {
//            stopForeground(0);
//        }
//        else
//        {
//            stopForeground(true);
//        }
//println("onTask")
////        stopSelf();
//
//
////        exitProcess(0);
//    }


//    override fun onDestroy() {
//        println("ONDESTROY")
//        super.onDestroy()
//        println("ONDESTROY")
//    }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}