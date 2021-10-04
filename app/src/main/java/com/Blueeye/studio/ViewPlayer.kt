package com.Blueeye.studio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class ViewPlayer : Fragment() {


//    lateinit var textView: TextView
    lateinit var backbutt: Button
    lateinit var audioPlay: ImageView
    lateinit var progressBar: ProgressBar
    lateinit var buttonRewind: ImageView
    lateinit var buttonForward: ImageView
    lateinit var imagePicturesTales: ImageView
    private var mInterstitialAd: InterstitialAd? = null
    var mAdView: AdView? = null
    var mAdViewBanner: AdView? = null
    var adRequest: AdRequest? = null
    var adRequestBanner: AdRequest? = null


    var play_pause = 0
    var listPictures = mutableListOf<Int>(
        R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4,
        R.drawable.i5, R.drawable.i6, R.drawable.i7, R.drawable.i8, R.drawable.i9, R.drawable.i10
    )

    var playMus = mutableListOf(
        R.raw.molodilnaya_dubinka,
        R.raw.lojka_alibabi,
        R.raw.bay_pevec,
        R.raw.desat_tubeteek,
        R.raw.bay_pastuh,
        R.raw.ryba_zmei,
        R.raw.sajenci,
        R.raw.shaitan,
        R.raw.volwebnaya_wuba,
        R.raw.ucheniy_zayac
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        MobileAds.initialize(context)

        mAdView = view.findViewById(R.id.adVideo)
        advertisingBanner()

        if (Singleton.currentTales > 4)
            advertising()


        Media.getI().setProgresSerTextView(progressBar)

        imagePicturesTales.background = resources.getDrawable(listPictures[Singleton.currentTales])

        if (Media.getI().getMp() == null) {
            Media.getI().setAudio(requireContext(), playMus[Singleton.currentTales])

        }
        progressBar.max = Media.getI().getDuration()

        onClickButton()

    }

    fun onClickButton() {

        audioPlay.setOnClickListener {
            play_pause++
            if (play_pause == 1) {
                audioPlay.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_pause))
//                audioPlay.background = resources.getDrawable(R.drawable.image_play)
                Media.getI().start()
//                Toast.makeText(context,Media.getI().isPlay().toString(), Toast.LENGTH_SHORT).show()

            }
            if (play_pause == 2) {
                audioPlay.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_play_arrow_24))
//                audioPlay.background = resources.getDrawable(R.drawable.image_play)
                Media.getI().pause()
//                timer().cancel()
                play_pause = 0
            }

        }
        backbutt.setOnClickListener {
            Singleton.currentTales = 0
            Singleton.switchFragment(ListAudio())
            Media.getI().reset()
        }

        buttonRewind.setOnClickListener {
            Media.getI().seekTo(Media.getI().getCurrentPos() - 2000)
            progressBar.progress = Media.getI().getCurrentPos()

//            timer().start()

//                notification()
        }

        buttonForward.setOnClickListener {
            Media.getI().seekTo(Media.getI().getCurrentPos() + 2000)
            progressBar.progress = Media.getI().getCurrentPos()
//            timer().start()

        }

    }

    fun advertisingBanner(){
        mAdViewBanner = view?.findViewById(R.id.adViewBanner)

//        mAdViewBanner?.adSize = AdSize.BANNER
//        mAdViewBanner?.adUnitId = "ca-app-pub-3375655106705533/5385553117"
//        ads:adUnitId="ca-app-pub-3375655106705533/5385553117">
//        ads:adSize="BANNER"

        adRequestBanner = AdRequest.Builder().build()
//        ca-app-pub-3375655106705533/5385553117
        mAdViewBanner?.loadAd(adRequestBanner!!)
    }


    fun advertising() {
        adRequest = AdRequest.Builder().build()
        mAdView?.loadAd(adRequest)

        audioPlay.isEnabled = false
        backbutt.isEnabled = false
        buttonForward.isEnabled = false
        buttonRewind.isEnabled = false
        Toast.makeText(context, "Загрузка рекламы...", Toast.LENGTH_LONG).show()

        InterstitialAd.load(
            context,
            "ca-app-pub-3375655106705533/6044028667",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    mInterstitialAd = null
                    audioPlay.isEnabled = true
                    backbutt.isEnabled = true
                    buttonForward.isEnabled = true
                    buttonRewind.isEnabled = true
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {

                    mInterstitialAd = interstitialAd
                    audioPlay.isEnabled = true
                    backbutt.isEnabled = true
                    buttonForward.isEnabled = true
                    buttonRewind.isEnabled = true
                    mInterstitialAd?.show(requireActivity())
                }

            })

    }

    fun init() {
//        textView = view?.findViewById(R.id.textView)!!
        backbutt = view?.findViewById(R.id.backbutt)!!
        audioPlay = view?.findViewById(R.id.audioPlay)!!
        progressBar = view?.findViewById(R.id.progressBar)!!
        buttonRewind = view?.findViewById(R.id.buttonRewind)!!
        buttonForward = view?.findViewById(R.id.buttonForward)!!
        imagePicturesTales = view?.findViewById(R.id.imagePicturesTales)!!
    }



}