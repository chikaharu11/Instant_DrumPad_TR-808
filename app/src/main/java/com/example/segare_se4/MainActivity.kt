package com.example.segare_se4

import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var mp: MediaPlayer

    private lateinit var soundPool: SoundPool

    private var sound1 = 0
    private var sound2 = 0
    private var sound3 = 0
    private var sound4 = 0
    private var sound5 = 0
    private var sound6 = 0
    private var sound7 = 0
    private var sound8 = 0
    private var sound9 = 0
    private var sound10 = 0
    private var sound11 = 0
    private var sound12 = 0
    private var sound13 = 0
    private var sound14 = 0
    private var sound15 = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun selectPhoto() {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"
            }
            startActivityForResult(intent, READ_REQUEST_CODE)
        }

        val audio1 = mutableSetOf(
            ""
        )

        val audioUri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI
        val cursor = contentResolver.query(audioUri, null, null, null, null)
        cursor!!.moveToFirst()
        val path: Array<String?> = arrayOfNulls(cursor.count)
        for (i in path.indices) {
            path[i] = cursor.getString(1)
            audio1.add(path[i].toString())
            cursor.moveToNext()
        }

        cursor.close()

        val spinnerItems = audio1.sorted()

        val inSpinner = findViewById<Spinner>(R.id.internal_spinner)

        val adapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_item, spinnerItems
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)



        inSpinner.adapter = adapter


        inSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?, position: Int, id: Long
            ) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String
                when{
                    radioButton.isChecked -> sound1 = soundPool.load(item, 1)
                    radioButton2.isChecked -> sound2 = soundPool.load(item, 1)
                    radioButton3.isChecked -> sound3 = soundPool.load(item, 1)
                    radioButton4.isChecked -> sound4 = soundPool.load(item, 1)
                    radioButton5.isChecked -> sound5 = soundPool.load(item, 1)
                    radioButton6.isChecked -> sound6 = soundPool.load(item, 1)
                    radioButton7.isChecked -> sound7 = soundPool.load(item, 1)
                    radioButton8.isChecked -> sound8 = soundPool.load(item, 1)
                    radioButton9.isChecked -> sound9 = soundPool.load(item, 1)
                    radioButton10.isChecked -> sound10 = soundPool.load(item, 1)
                    radioButton11.isChecked -> sound11 = soundPool.load(item, 1)
                    radioButton12.isChecked -> sound12 = soundPool.load(item, 1)
                    radioButton13.isChecked -> sound13 = soundPool.load(item, 1)
                    radioButton14.isChecked -> sound14 = soundPool.load(item, 1)
                    radioButton15.isChecked -> sound15 = soundPool.load(item, 1)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val audio2 = mutableSetOf(
            ""
        )

        val audioUri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val cursor2 = contentResolver.query(audioUri2, null, null, null, null)
        cursor2!!.moveToFirst()
        val path2: Array<String?> = arrayOfNulls(cursor2.count)
        for (i in path2.indices) {
            path2[i] = cursor2.getString(1)
            audio2.add(path2[i].toString())
            cursor2.moveToNext()
        }

        cursor2.close()

        val spinnerItems2 = audio2.sorted()

        val exSpinner = findViewById<Spinner>(R.id.external_spinner)

        val adapter2 = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_item, spinnerItems2
        )

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)



        exSpinner.adapter = adapter2


        exSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?, position: Int, id: Long
            ) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String
                when{
                    radioButton.isChecked -> sound1 = soundPool.load(item, 1)
                    radioButton2.isChecked -> sound2 = soundPool.load(item, 1)
                    radioButton3.isChecked -> sound3 = soundPool.load(item, 1)
                    radioButton4.isChecked -> sound4 = soundPool.load(item, 1)
                    radioButton5.isChecked -> sound5 = soundPool.load(item, 1)
                    radioButton6.isChecked -> sound6 = soundPool.load(item, 1)
                    radioButton7.isChecked -> sound7 = soundPool.load(item, 1)
                    radioButton8.isChecked -> sound8 = soundPool.load(item, 1)
                    radioButton9.isChecked -> sound9 = soundPool.load(item, 1)
                    radioButton10.isChecked -> sound10 = soundPool.load(item, 1)
                    radioButton11.isChecked -> sound11 = soundPool.load(item, 1)
                    radioButton12.isChecked -> sound12 = soundPool.load(item, 1)
                    radioButton13.isChecked -> sound13 = soundPool.load(item, 1)
                    radioButton14.isChecked -> sound14 = soundPool.load(item, 1)
                    radioButton15.isChecked -> sound15 = soundPool.load(item, 1)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val menu1 = mutableSetOf(
            "内部サウンド",
            "外部サウンド",
            "画像を選ぶ"
        )

        val spinnerItems3 = menu1.sorted()

        val meSpinner = findViewById<Spinner>(R.id.menu_spinner)

        val adapter3 = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_item, spinnerItems3
        )

        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)



        meSpinner.adapter = adapter3


        meSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?, position: Int, id: Long
            ) {
                if (!inSpinner.isFocusable) {
                    inSpinner.isFocusable = true
                    return
                }
                when(position){
                    0 -> inSpinner.performClick()
                    1 -> exSpinner.performClick()
                    2 -> selectPhoto()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        inSpinner.isFocusable = false


        val audioAttributes = AudioAttributes.Builder()

                .setUsage(AudioAttributes.USAGE_GAME)

                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build()

        soundPool = SoundPool.Builder()

                .setAudioAttributes(audioAttributes)

                .setMaxStreams(15)
                .build()

        sound1 = soundPool.load(this, R.raw.ta, 1)

        sound2 = soundPool.load(this, R.raw.ta, 1)

        sound3 = soundPool.load(this, R.raw.ta, 1)

        sound4 = soundPool.load(this, R.raw.ta, 1)

        sound5 = soundPool.load(this, R.raw.ta, 1)

        sound6 = soundPool.load(this, R.raw.ta, 1)

        sound7 = soundPool.load(this, R.raw.ta, 1)

        sound8 = soundPool.load(this, R.raw.ta, 1)

        sound9 = soundPool.load(this, R.raw.ta, 1)

        sound10 = soundPool.load(this, R.raw.ta, 1)

        sound11 = soundPool.load(this, R.raw.ta, 1)

        sound12 = soundPool.load(this, R.raw.ta, 1)

        sound13 = soundPool.load(this, R.raw.ta, 1)

        sound14 = soundPool.load(this, R.raw.ta, 1)

        sound15 = soundPool.load(this, R.raw.ta, 1)


        imageView.setOnClickListener {
            soundPool.play(sound1, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView2.setOnClickListener {
            soundPool.play(sound2, 1.0f, 1.0f, 1, 0, 1.0f)
        }

        imageView3.setOnClickListener {
            soundPool.play(sound3, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView4.setOnClickListener {
            soundPool.play(sound4, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView5.setOnClickListener {
            soundPool.play(sound5, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView6.setOnClickListener {
            soundPool.play(sound6, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView7.setOnClickListener {
            soundPool.play(sound7, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView8.setOnClickListener {
            soundPool.play(sound8, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView9.setOnClickListener {
            soundPool.play(sound9, 1.0f, 1.0f, 0, 0, 1.0f)

        }

        imageView10.setOnClickListener {
            soundPool.play(sound10, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView11.setOnClickListener {
            soundPool.play(sound11, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView12.setOnClickListener {
            soundPool.play(sound12, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView13.setOnClickListener {
            soundPool.play(sound13, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView14.setOnClickListener {
            soundPool.play(sound14, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView15.setOnClickListener {
            soundPool.play(sound15, 1.0f, 1.0f, 0, 0, 1.0f)
        }


        imageView.setOnLongClickListener {
            radioButton.performClick()
            meSpinner.performClick()
            true
        }
        imageView2.setOnLongClickListener {
            radioButton2.performClick()
            meSpinner.performClick()
            true
        }
        imageView3.setOnLongClickListener {
            radioButton3.performClick()
            meSpinner.performClick()
            true
        }
        imageView4.setOnLongClickListener {
            radioButton4.performClick()
            meSpinner.performClick()
            true
        }
        imageView5.setOnLongClickListener {
            radioButton5.performClick()
            meSpinner.performClick()
            true
        }
        imageView6.setOnLongClickListener {
            radioButton6.performClick()
            meSpinner.performClick()
            true
        }
        imageView7.setOnLongClickListener {
            radioButton7.performClick()
            meSpinner.performClick()
            true
        }
        imageView8.setOnLongClickListener {
            radioButton8.performClick()
            meSpinner.performClick()
            true
        }
        imageView9.setOnLongClickListener {
            radioButton9.performClick()
            meSpinner.performClick()
            true
        }
        imageView10.setOnLongClickListener {
            radioButton10.performClick()
            meSpinner.performClick()
            true
        }
        imageView11.setOnLongClickListener {
            radioButton11.performClick()
            meSpinner.performClick()
            true
        }
        imageView12.setOnLongClickListener {
            radioButton12.performClick()
            meSpinner.performClick()
            true
        }
        imageView13.setOnLongClickListener {
            radioButton13.performClick()
            meSpinner.performClick()
            true
        }
        imageView14.setOnLongClickListener {
            radioButton14.performClick()
            meSpinner.performClick()
            true
        }
        imageView15.setOnLongClickListener {
            radioButton15.performClick()
            meSpinner.performClick()
            true
        }
    }

    companion object {
        private const val READ_REQUEST_CODE: Int = 42
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (resultCode != RESULT_OK) {
            return
        }
        when (requestCode) {
            READ_REQUEST_CODE -> {

                resultData?.data?.also { uri ->
                    val inputStream = contentResolver?.openInputStream(uri)
                    val image = BitmapFactory.decodeStream(inputStream)
                    when{
                        radioButton.isChecked -> imageView.setImageBitmap(image)
                        radioButton2.isChecked -> imageView2.setImageBitmap(image)
                        radioButton3.isChecked -> imageView3.setImageBitmap(image)
                        radioButton4.isChecked -> imageView4.setImageBitmap(image)
                        radioButton5.isChecked -> imageView5.setImageBitmap(image)
                        radioButton6.isChecked -> imageView6.setImageBitmap(image)
                        radioButton7.isChecked -> imageView7.setImageBitmap(image)
                        radioButton8.isChecked -> imageView8.setImageBitmap(image)
                        radioButton9.isChecked -> imageView9.setImageBitmap(image)
                        radioButton10.isChecked -> imageView10.setImageBitmap(image)
                        radioButton11.isChecked -> imageView11.setImageBitmap(image)
                        radioButton12.isChecked -> imageView12.setImageBitmap(image)
                        radioButton13.isChecked -> imageView13.setImageBitmap(image)
                        radioButton14.isChecked -> imageView14.setImageBitmap(image)
                        radioButton15.isChecked -> imageView15.setImageBitmap(image)
                    }
                }
            }
        }
    }

    private fun play() {
        mp = MediaPlayer()
        volumeControlStream = AudioManager.STREAM_MUSIC
        mp.setDataSource("/system/media/audio/ringtones/Gimme_Mo_Town.ogg")
        mp.isLooping=true
        mp.prepare()
        mp.start()
    }

    private fun stop() {
        mp.stop()
        mp.reset()
        mp.release()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.menu1 -> {
                play()
                return true
            }

            R.id.menu2 -> {
                stop()
                return true
            }

            R.id.menu3 -> {
                return true
            }

            R.id.menu4 -> {
                return true
            }

            R.id.menu5 -> {
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
    override fun onDestroy() {
        mp.release()
        super.onDestroy()
    }
}


