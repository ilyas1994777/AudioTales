package com.Blueeye.studio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Blueeye.studio.R
import com.Blueeye.studio.audiotales.RecyclerViewNameOfTales.MainMenu.ViewMainMenu
import com.Blueeye.studio.audiotales.RecyclerViewNameOfTales.RVadapter
import com.Blueeye.studio.audiotales.RecyclerViewNameOfTales.dataList


class ListAudio : Fragment() {

    lateinit var recyclerV : RecyclerView
    private var adapterr = RVadapter()
//      lateinit var listState : Parcelable
      lateinit var imageButtonBack : ImageView

    private var listName = mutableListOf<String>("Молодильная \n дубинка", "Ложка алибабы", "Бай певец", "Десять тюбитеек",
        "Бай пастух", "Рыба змей", "Саженцы", "Шайтан", "Волшебная шуба", "Ученый заяц")
    private var listPicture = mutableListOf<Int>(R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4,
        R.drawable.i5, R.drawable.i6, R.drawable.i7, R.drawable.i8, R.drawable.i9, R.drawable.i10)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (savedInstanceState != null) {
//            listState=savedInstanceState.getParcelable("ListState")!!
//        };

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_audio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageButtonBack = view.findViewById(R.id.imageButtonBack)
        recyclerV = view.findViewById(R.id.recyclerV)
        recyclerV.adapter = adapterr
        recyclerV.layoutManager = LinearLayoutManager(context)

        imageButtonBack.setOnClickListener {
            Singleton.switchFragment(ViewMainMenu())
        }

      if(adapterr.listName.count() == 0) {
          for (i in 0..listName.count() - 1) {
              var list = dataList(listName[i], listPicture[i])

              adapterr.binding(list)

          }
      }

//        recyclerV.getLayoutManager()?.onRestoreInstanceState(listState)
        recyclerV.scrollToPosition(Singleton.currentPositionScroll)


    }




//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//
//        outState.putParcelable("ListState", recyclerV.getLayoutManager()?.onSaveInstanceState())
//    }

}