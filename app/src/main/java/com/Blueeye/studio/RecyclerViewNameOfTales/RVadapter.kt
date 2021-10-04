package com.Blueeye.studio.audiotales.RecyclerViewNameOfTales

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.Blueeye.studio.ViewPlayer
import com.Blueeye.studio.Media
import com.Blueeye.studio.Singleton
import com.Blueeye.studio.R

class RVadapter(): RecyclerView.Adapter<RVadapter.AudioHolder>() {

    var listName = mutableListOf<dataList>()

    class AudioHolder(item: View): RecyclerView.ViewHolder(item) {
        val textview = item.findViewById<TextView>(R.id.textViewRVListName)
        val image = item.findViewById<ImageView>(R.id.imageViewRV)
//        val constRV = item.findViewById<ConstraintLayout>(R.id.constRV)
        val textViewAdvertising = item.findViewById<TextView>(R.id.textViewAdvertising)
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(dataList: dataList) {
            textview.text = dataList.nameTales
            image.setImageResource(dataList.pictures)

//            if (position > 4) {
//
//                image.setImageDrawable(image.resources.getDrawable(R.drawable.egg))
//            }

            if (position > 4)
                textViewAdvertising.text = "Внимание видео реклама"

            itemView.setOnClickListener {


//                if (position > 4) {
//                    Toast.makeText(Singleton.mainAc, "Нету денег бищара $position", Toast.LENGTH_SHORT).show()
//
//                } else {
                    Singleton.currentTales = position
                    Singleton.currentPositionScroll = position
                 println(position)
//                Singleton.counter++
                    Media.getI().stop()
                    Media.getI().setMpNull()

//                Toast.makeText(Singleton.mainAc, "$position", Toast.LENGTH_SHORT).show()
                    Singleton.switchFragment(ViewPlayer())
//                }
                    




            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_list_names_tales, parent, false)
        return AudioHolder(item)
    }

    override fun onBindViewHolder(holder: AudioHolder, position: Int) {
        holder.bind(listName[position])
    }

    override fun getItemCount(): Int {
        return listName.size
    }

    fun binding(dataList: dataList){
        listName.add(dataList)
        notifyDataSetChanged()
    }
}