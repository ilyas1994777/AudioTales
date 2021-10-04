package com.Blueeye.studio.audiotales.RecyclerViewNameOfTales.MainMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.Blueeye.studio.ListAudio
import com.Blueeye.studio.Singleton
import com.Blueeye.studio.ViewFullVersion
import com.Blueeye.studio.R


class ViewMainMenu : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_main_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonAudio : Button = view.findViewById(R.id.buttonAudio)
        val buttonProVersion : Button = view.findViewById(R.id.buttonProVersion)

        buttonAudio.setOnClickListener {
            Singleton.switchFragment(ListAudio())
        }

        buttonProVersion.setOnClickListener {
            Singleton.switchFragment(ViewFullVersion())
        }

    }
}