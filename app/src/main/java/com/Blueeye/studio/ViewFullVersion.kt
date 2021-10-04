package com.Blueeye.studio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.Blueeye.studio.audiotales.RecyclerViewNameOfTales.MainMenu.ViewMainMenu
import com.Blueeye.studio.R


class ViewFullVersion : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_full_version, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainMenuButton = view.findViewById<Button>(R.id.mainMenuButton)
        mainMenuButton.setOnClickListener {
            Singleton.switchFragment(ViewMainMenu())
        }


    }
}