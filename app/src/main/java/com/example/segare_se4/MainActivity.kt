package com.example.segare_se4

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.media.*
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.EasyPermissions


class MainActivity : AppCompatActivity() {

    private val handler = Handler()

    private lateinit var mp: MediaPlayer

    private lateinit var mp2: MediaPlayer

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

        val permissions = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE
        )

        if (!EasyPermissions.hasPermissions(this, *permissions)) {
            AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setMessage("このアプリはスマートフォン内の\n着信音、音声ファイル、画像を使用します。\nアプリの設定画面に移動して、\nストレージ権限の許可をお願いします。")
                    .setPositiveButton("設定する") { _, _ ->
                        val uriString = "package:$packageName"
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse(uriString))
                        startActivity(intent)
                        finish()
                    }
                    .setNegativeButton("後で") { _, _ ->
                        finish()
                    }
                    .show()
            return
        }

        mp = MediaPlayer()
        mp2 = MediaPlayer()

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

        audio1.clear()

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
                    radioButton.isChecked -> { sound1 = soundPool.load(item, 1)
                        textView.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton2.isChecked -> { sound2 = soundPool.load(item, 1)
                        textView2.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton3.isChecked -> { sound3 = soundPool.load(item, 1)
                        textView3.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton4.isChecked -> { sound4 = soundPool.load(item, 1)
                        textView4.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton5.isChecked -> { sound5 = soundPool.load(item, 1)
                        textView5.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton6.isChecked -> { sound6 = soundPool.load(item, 1)
                        textView6.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton7.isChecked -> { sound7 = soundPool.load(item, 1)
                        textView7.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton8.isChecked -> { sound8 = soundPool.load(item, 1)
                        textView8.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton9.isChecked -> { sound9 = soundPool.load(item, 1)
                        textView9.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton10.isChecked -> { sound10 = soundPool.load(item, 1)
                        textView10.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton11.isChecked -> { sound11 = soundPool.load(item, 1)
                        textView11.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton12.isChecked -> { sound12 = soundPool.load(item, 1)
                        textView12.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton13.isChecked -> { sound13 = soundPool.load(item, 1)
                        textView13.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton14.isChecked -> { sound14 = soundPool.load(item, 1)
                        textView14.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton15.isChecked -> { sound15 = soundPool.load(item, 1)
                        textView15.text = item.replaceBeforeLast("/", "")
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val audio2 = mutableSetOf(
                ""
        )

        audio2.clear()

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
                    radioButton.isChecked -> { sound1 = soundPool.load(item, 1)
                        textView.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton2.isChecked -> { sound2 = soundPool.load(item, 1)
                        textView2.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton3.isChecked -> { sound3 = soundPool.load(item, 1)
                        textView3.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton4.isChecked -> { sound4 = soundPool.load(item, 1)
                        textView4.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton5.isChecked -> { sound5 = soundPool.load(item, 1)
                        textView5.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton6.isChecked -> { sound6 = soundPool.load(item, 1)
                        textView6.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton7.isChecked -> { sound7 = soundPool.load(item, 1)
                        textView7.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton8.isChecked -> { sound8 = soundPool.load(item, 1)
                        textView8.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton9.isChecked -> { sound9 = soundPool.load(item, 1)
                        textView9.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton10.isChecked -> { sound10 = soundPool.load(item, 1)
                        textView10.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton11.isChecked -> { sound11 = soundPool.load(item, 1)
                        textView11.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton12.isChecked -> { sound12 = soundPool.load(item, 1)
                        textView12.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton13.isChecked -> { sound13 = soundPool.load(item, 1)
                        textView13.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton14.isChecked -> { sound14 = soundPool.load(item, 1)
                        textView14.text = item.replaceBeforeLast("/", "")
                    }
                    radioButton15.isChecked -> { sound15 = soundPool.load(item, 1)
                        textView15.text = item.replaceBeforeLast("/", "")
                    }
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
                    when {
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
        mp.start()
        handler.postDelayed({ mp.pause() }, mp.duration.toLong())
        handler.postDelayed({ mp2.start() }, mp.duration.toLong())
        handler.postDelayed({ mp2.pause() }, mp2.duration.toLong() * 2)
        handler.postDelayed({ mp.start() }, mp2.duration.toLong() * 2)
        handler.postDelayed({ mp.pause() }, mp.duration.toLong() * 3)
        handler.postDelayed({ mp2.start() }, mp.duration.toLong() * 3)
        handler.postDelayed({ mp2.pause() }, mp2.duration.toLong() * 4)
        handler.postDelayed({ mp.start() }, mp2.duration.toLong() * 4)
        handler.postDelayed({ mp.pause() }, mp.duration.toLong() * 5)
        handler.postDelayed({ mp2.start() }, mp.duration.toLong() * 5)
        handler.postDelayed({ mp2.pause() }, mp2.duration.toLong() * 6)
        handler.postDelayed({ mp.start() }, mp2.duration.toLong() * 6)
        handler.postDelayed({ mp.pause() }, mp.duration.toLong() * 7)
        handler.postDelayed({ mp2.start() }, mp.duration.toLong() * 7)
        handler.postDelayed({ mp2.pause() }, mp2.duration.toLong() * 8)
        handler.postDelayed({ mp.start() }, mp2.duration.toLong() * 8)
        handler.postDelayed({ mp.pause() }, mp.duration.toLong() * 9)
        handler.postDelayed({ mp2.start() }, mp.duration.toLong() * 9)
        handler.postDelayed({ mp2.pause() }, mp2.duration.toLong() * 10)
        handler.postDelayed({
            menuSwitch = true
            invalidateOptionsMenu()
            switch1.isChecked = false
        }, mp2.duration.toLong() * 10)
    }

    private fun stop() {
        handler.removeCallbacksAndMessages(null)

        if (mp.isPlaying) {
            mp.stop()
            mp.prepare()
        }
        if (mp2.isPlaying) {
            mp2.stop()
            mp2.prepare()
        }else{
            return
        }
    }

    private fun select() {
        val audio1 = mutableSetOf(
                ""
        )

        audio1.clear()

        val manager = RingtoneManager(this)

        manager.setType(RingtoneManager.TYPE_RINGTONE)
        val cursor: Cursor = manager.cursor
        while (cursor.moveToNext()) {
            val title: String = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX)
            val uriPrefix: String = cursor.getString(RingtoneManager.URI_COLUMN_INDEX)
            val index: String = cursor.getString(RingtoneManager.ID_COLUMN_INDEX)
            val uri = "$uriPrefix/$index"
                audio1.add("($title)  $uri")
        }


        val spinnerItems = audio1.sorted()

        val inSpinner = findViewById<Spinner>(R.id.mp_spinner)

        val adapter = ArrayAdapter(
                applicationContext,
                android.R.layout.simple_spinner_item, spinnerItems
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)



        inSpinner.adapter = adapter

        inSpinner.performClick()

        inSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?, position: Int, id: Long
            ) {
                if (!inSpinner.isFocusable) {
                    inSpinner.isFocusable = true
                    return
                }
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String
                mp.release()
                mp2.release()
                mp = MediaPlayer()
                mp2 = MediaPlayer()
                volumeControlStream = AudioManager.STREAM_MUSIC
                mp.setDataSource(item.replaceBefore("content", ""))
                mp2.setDataSource(item.replaceBefore("content", ""))
                supportActionBar?.title =item.replaceAfter(")", "")
                mp.prepare()
                mp2.prepare()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        inSpinner.isFocusable = false
    }

    private fun select2() {
        val audio1 = mutableSetOf(
                ""
        )

        audio1.clear()

        val audioUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
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

        val inSpinner = findViewById<Spinner>(R.id.mp_spinner)

        val adapter = ArrayAdapter(
                applicationContext,
                android.R.layout.simple_spinner_item, spinnerItems
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)



        inSpinner.adapter = adapter

        inSpinner.performClick()

        inSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?, position: Int, id: Long
            ) {
                if (!inSpinner.isFocusable) {
                    inSpinner.isFocusable = true
                    return
                }
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String
                mp.release()
                mp2.release()
                mp = MediaPlayer()
                mp2 = MediaPlayer()
                volumeControlStream = AudioManager.STREAM_MUSIC
                mp.setDataSource(item)
                mp2.setDataSource(item)
                supportActionBar?.title =item.replaceBeforeLast("/", "")
                mp.prepare()
                mp2.prepare()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        inSpinner.isFocusable = false
    }

    private fun select3() {
        val audio1 = mutableSetOf(
                ""
        )

        audio1.clear()

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

        val inSpinner = findViewById<Spinner>(R.id.mp_spinner)

        val adapter = ArrayAdapter(
                applicationContext,
                android.R.layout.simple_spinner_item, spinnerItems
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)



        inSpinner.adapter = adapter

        inSpinner.performClick()

        inSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?, position: Int, id: Long
            ) {
                if (!inSpinner.isFocusable) {
                    inSpinner.isFocusable = true
                    return
                }
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String
                mp.release()
                mp2.release()
                mp = MediaPlayer()
                mp2 = MediaPlayer()
                volumeControlStream = AudioManager.STREAM_MUSIC
                mp.setDataSource(item)
                mp2.setDataSource(item)
                supportActionBar?.title =item.replaceBeforeLast("/", "")
                mp.prepare()
                mp2.prepare()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        inSpinner.isFocusable = false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val menuLamp = menu!!.findItem(R.id.menu1)
        if (menuSwitch) {
            menuLamp.setIcon(R.drawable.ic_baseline_play_arrow_24)
        } else {
            menuLamp.setIcon(R.drawable.ic_baseline_stop_24)
        }
        return true
    }

    private var menuSwitch = true

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.menu1 -> {
                if (switch1.isChecked) {
                    stop()
                    menuSwitch = true
                    invalidateOptionsMenu()
                    switch1.isChecked = false
                } else {
                    play()
                    menuSwitch = false
                    invalidateOptionsMenu()
                    switch1.isChecked = true
                }
                return true
            }

            R.id.menu3 -> {
                stop()
                menuSwitch = true
                invalidateOptionsMenu()
                switch1.isChecked = false
                select()
                return true
            }

            R.id.menu4 -> {
                stop()
                menuSwitch = true
                invalidateOptionsMenu()
                switch1.isChecked = false
                select3()
                return true
            }

            R.id.menu5 -> {
                stop()
                menuSwitch = true
                invalidateOptionsMenu()
                switch1.isChecked = false
                select2()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        mp = MediaPlayer()
        mp2 = MediaPlayer()
        mp.reset()
        mp2.reset()
        mp.release()
        mp2.release()
        super.onDestroy()
    }

    override fun onPause() {
        menuSwitch = true
        invalidateOptionsMenu()
        switch1.isChecked = false
        val permissions = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        if (EasyPermissions.hasPermissions(this, *permissions))
            stop()
        super.onPause()
    }
}
