package jp.chikaharu11.instant_drumpad_tr808

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView

interface CustomAdapterListener {
    fun clicked(soundList: SoundList)
    fun clicked2(soundList: SoundList)
}

class CustomAdapter(context: Context, private var mSoundListList: List<SoundList>, private val listener: CustomAdapterListener) : ArrayAdapter<SoundList>(context, 0, mSoundListList) {

    private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val soundList = mSoundListList[position]

        var view = convertView
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.item_list, parent, false)
        }

        val name = view?.findViewById<TextView>(R.id.name)
        name?.text = soundList.name
        name?.setOnClickListener {
            listener.clicked2(soundList)
        }

        val imageButton = view?.findViewById<ImageButton>(R.id.imageButton)
        imageButton?.setOnClickListener {
            listener.clicked(soundList)
        }

        return view!!
    }
}