package jp.chikaharu11.chikaharu_sampler

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView

interface CustomAdapterListener {
    fun clicked(animal: Animal)
    fun clicked2(animal: Animal)
}



class CustomAdapter(context: Context, private var mAnimalList: List<Animal>, private val listener: CustomAdapterListener) : ArrayAdapter<Animal>(context, 0, mAnimalList) {

    private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Animalの取得
        val animal = mAnimalList[position]

        // レイアウトの設定
        var view = convertView
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.item_list, parent, false)
        }

        val name = view?.findViewById<TextView>(R.id.name)
        name?.text = animal.name
        name?.setOnClickListener {
            listener.clicked2(animal)
        }

        val button = view?.findViewById<Button>(R.id.button)
        button?.setOnClickListener {
            listener.clicked(animal)
        }

        return view!!
    }
}