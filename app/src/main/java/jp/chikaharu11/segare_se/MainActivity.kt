package jp.chikaharu11.segare_se

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.drawable.ColorDrawable
import android.media.*
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.ParcelFileDescriptor
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.EasyPermissions
import java.io.FileDescriptor
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private fun rotateImageIfRequired(bitmap: Bitmap, context: Context, uri: Uri?): Bitmap? {
        val parcelFileDescriptor: ParcelFileDescriptor? = uri?.let { context.contentResolver.openFileDescriptor(it, "r") }
        val fileDescriptor: FileDescriptor = parcelFileDescriptor!!.fileDescriptor
        val ei = ExifInterface(fileDescriptor)
        val orientation: Int = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        parcelFileDescriptor.close()
        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(bitmap, 90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(bitmap, 180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(bitmap, 270)
            else -> bitmap
        }
    }

    private fun rotateImage(bitmap: Bitmap, degree: Int): Bitmap? {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotatedImg = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        bitmap.recycle()
        return rotatedImg
    }

    companion object {
        private const val READ_REQUEST_CODE: Int = 42
        private const val READ_REQUEST_CODE2: Int = 43
    }

    fun selectPhoto() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }
        startActivityForResult(intent, READ_REQUEST_CODE)
    }

    fun selectAudio() {
        val sa = Uri.parse("content://com.android.externalstorage.documents/document/primary%3AAndroid%2Fdata%2Fjp.chikaharu11.segare_se%2Ffiles%2FMusic")
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            putExtra(DocumentsContract.EXTRA_INITIAL_URI, sa)
            type = "audio/ogg"
        }
        startActivityForResult(intent, READ_REQUEST_CODE2)
    }

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
                        val intent = Intent(
                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse(
                                uriString
                        )
                        )
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
                "録音したサウンド",
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
                    3 -> selectAudio()
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

                .setMaxStreams(10)
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
            if (switch0.isChecked) {
                soundPool.autoPause()
            }
            soundPool.play(sound1, 1.0f, 1.0f, 1, 0, 1.0f)
        }

        imageView2.setOnClickListener {
            if (switch0.isChecked) {
                soundPool.autoPause()
            }
            soundPool.play(sound2, 1.0f, 1.0f, 1, 0, 1.0f)
        }

        imageView3.setOnClickListener {
            if (switch0.isChecked) {
                soundPool.autoPause()
            }
            soundPool.play(sound3, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView4.setOnClickListener {
            if (switch0.isChecked) {
                soundPool.autoPause()
            }
            soundPool.play(sound4, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView5.setOnClickListener {
            if (switch0.isChecked) {
                soundPool.autoPause()
            }
            soundPool.play(sound5, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView6.setOnClickListener {
            if (switch0.isChecked) {
                soundPool.autoPause()
            }
            soundPool.play(sound6, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView7.setOnClickListener {
            if (switch0.isChecked) {
                soundPool.autoPause()
            }
            soundPool.play(sound7, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView8.setOnClickListener {
            if (switch0.isChecked) {
                soundPool.autoPause()
            }
            soundPool.play(sound8, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView9.setOnClickListener {
            if (switch0.isChecked) {
                soundPool.autoPause()
            }
            soundPool.play(sound9, 1.0f, 1.0f, 0, 0, 1.0f)

        }

        imageView10.setOnClickListener {
            if (switch0.isChecked) {
                soundPool.autoPause()
            }
            soundPool.play(sound10, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView11.setOnClickListener {
            if (switch0.isChecked) {
                soundPool.autoPause()
            }
            soundPool.play(sound11, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView12.setOnClickListener {
            if (switch0.isChecked) {
                soundPool.autoPause()
            }
            soundPool.play(sound12, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView13.setOnClickListener {
            if (switch0.isChecked) {
                soundPool.autoPause()
            }
            soundPool.play(sound13, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView14.setOnClickListener {
            if (switch0.isChecked) {
                soundPool.autoPause()
            }
            soundPool.play(sound14, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        imageView15.setOnClickListener {
            if (switch0.isChecked) {
                soundPool.autoPause()
            }
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (resultCode != RESULT_OK) {
            return
        }
        when (requestCode) {
            READ_REQUEST_CODE -> {

                resultData?.data?.also { uri ->
                    val inputStream = contentResolver?.openInputStream(uri)
                    val imageOptions = BitmapFactory.Options()
                    val contentResolver = this.contentResolver
                    val projection = arrayOf(MediaStore.MediaColumns.SIZE)
                    val fileSize = contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
                        if (cursor.moveToFirst()) {
                            cursor.getLong(0)
                        } else {
                            null
                        }
                    }
                    if (fileSize != null) {
                        when  {
                            fileSize < 1048576 -> imageOptions.inSampleSize = 1
                            fileSize in 1048576..2097151 -> imageOptions.inSampleSize = 2
                            fileSize in 2097152..4194303 -> imageOptions.inSampleSize = 4
                            fileSize in 4194304..8388607 -> imageOptions.inSampleSize = 8
                            fileSize in 8388608..16777215 -> imageOptions.inSampleSize = 16
                            fileSize in 16777216..33554431 -> imageOptions.inSampleSize = 32
                            fileSize in 33554432..67108863 -> imageOptions.inSampleSize = 64
                            fileSize in 67108864..134217728 -> imageOptions.inSampleSize = 128
                            else -> imageOptions.inSampleSize = 256
                        }
                    }
                    imageOptions.inPreferredConfig = Bitmap.Config.RGB_565
                    val image = BitmapFactory.decodeStream(inputStream, null, imageOptions)

                    when {
                        radioButton.isChecked -> imageView.setImageBitmap(image?.let { rotateImageIfRequired(it, this, uri) })
                        radioButton2.isChecked -> imageView2.setImageBitmap(image?.let { rotateImageIfRequired(it, this, uri) })
                        radioButton3.isChecked -> imageView3.setImageBitmap(image?.let { rotateImageIfRequired(it, this, uri) })
                        radioButton4.isChecked -> imageView4.setImageBitmap(image?.let { rotateImageIfRequired(it, this, uri) })
                        radioButton5.isChecked -> imageView5.setImageBitmap(image?.let { rotateImageIfRequired(it, this, uri) })
                        radioButton6.isChecked -> imageView6.setImageBitmap(image?.let { rotateImageIfRequired(it, this, uri) })
                        radioButton7.isChecked -> imageView7.setImageBitmap(image?.let { rotateImageIfRequired(it, this, uri) })
                        radioButton8.isChecked -> imageView8.setImageBitmap(image?.let { rotateImageIfRequired(it, this, uri) })
                        radioButton9.isChecked -> imageView9.setImageBitmap(image?.let { rotateImageIfRequired(it, this, uri) })
                        radioButton10.isChecked -> imageView10.setImageBitmap(image?.let { rotateImageIfRequired(it, this, uri) })
                        radioButton11.isChecked -> imageView11.setImageBitmap(image?.let { rotateImageIfRequired(it, this, uri) })
                        radioButton12.isChecked -> imageView12.setImageBitmap(image?.let { rotateImageIfRequired(it, this, uri) })
                        radioButton13.isChecked -> imageView13.setImageBitmap(image?.let { rotateImageIfRequired(it, this, uri) })
                        radioButton14.isChecked -> imageView14.setImageBitmap(image?.let { rotateImageIfRequired(it, this, uri) })
                        radioButton15.isChecked -> imageView15.setImageBitmap(image?.let { rotateImageIfRequired(it, this, uri) })
                    }
                }
            }
            READ_REQUEST_CODE2 -> {

                resultData?.data?.also { uri ->
                    val docId = DocumentsContract.getDocumentId(uri)
                    val split = docId.split(":".toRegex()).toTypedArray()
                    val type = split[0]
                    if ("primary".equals(type, ignoreCase = true)) {
                        val item = "/storage/emulated/0/" + split[1]
                        when {
                            radioButton.isChecked -> {
                                sound1 = soundPool.load(item, 1)
                                textView.text = item.replaceBeforeLast("/", "")
                            }
                            radioButton2.isChecked -> {
                                sound2 = soundPool.load(item, 1)
                                textView2.text = item.replaceBeforeLast("/", "")
                            }
                            radioButton3.isChecked -> {
                                sound3 = soundPool.load(item, 1)
                                textView3.text = item.replaceBeforeLast("/", "")
                            }
                            radioButton4.isChecked -> {
                                sound4 = soundPool.load(item, 1)
                                textView4.text = item.replaceBeforeLast("/", "")
                            }
                            radioButton5.isChecked -> {
                                sound5 = soundPool.load(item, 1)
                                textView5.text = item.replaceBeforeLast("/", "")
                            }
                            radioButton6.isChecked -> {
                                sound6 = soundPool.load(item, 1)
                                textView6.text = item.replaceBeforeLast("/", "")
                            }
                            radioButton7.isChecked -> {
                                sound7 = soundPool.load(item, 1)
                                textView7.text = item.replaceBeforeLast("/", "")
                            }
                            radioButton8.isChecked -> {
                                sound8 = soundPool.load(item, 1)
                                textView8.text = item.replaceBeforeLast("/", "")
                            }
                            radioButton9.isChecked -> {
                                sound9 = soundPool.load(item, 1)
                                textView9.text = item.replaceBeforeLast("/", "")
                            }
                            radioButton10.isChecked -> {
                                sound10 = soundPool.load(item, 1)
                                textView10.text = item.replaceBeforeLast("/", "")
                            }
                            radioButton11.isChecked -> {
                                sound11 = soundPool.load(item, 1)
                                textView11.text = item.replaceBeforeLast("/", "")
                            }
                            radioButton12.isChecked -> {
                                sound12 = soundPool.load(item, 1)
                                textView12.text = item.replaceBeforeLast("/", "")
                            }
                            radioButton13.isChecked -> {
                                sound13 = soundPool.load(item, 1)
                                textView13.text = item.replaceBeforeLast("/", "")
                            }
                            radioButton14.isChecked -> {
                                sound14 = soundPool.load(item, 1)
                                textView14.text = item.replaceBeforeLast("/", "")
                            }
                            radioButton15.isChecked -> {
                                sound15 = soundPool.load(item, 1)
                                textView15.text = item.replaceBeforeLast("/", "")
                            }
                            radioButton16.isChecked -> {
                                mp.release()
                                mp2.release()
                                mp = MediaPlayer()
                                mp2 = MediaPlayer()
                                volumeControlStream = AudioManager.STREAM_MUSIC
                                val uri1 = Uri.parse(item)
                                mp.setDataSource(applicationContext, uri1)
                                mp2.setDataSource(applicationContext, uri1)
                                supportActionBar?.title = item.replaceBeforeLast("/", "")
                                mp.prepare()
                                mp2.prepare()
                            }
                        }
                    } else {
                        try {
                            val item2 = "/stroage/" + type + "/" + split[1]
                            when {
                                radioButton.isChecked -> {
                                    sound1 = soundPool.load(item2, 1)
                                    textView.text = item2.replaceBeforeLast("/", "")
                                }
                                radioButton2.isChecked -> {
                                    sound2 = soundPool.load(item2, 1)
                                    textView2.text = item2.replaceBeforeLast("/", "")
                                }
                                radioButton3.isChecked -> {
                                    sound3 = soundPool.load(item2, 1)
                                    textView3.text = item2.replaceBeforeLast("/", "")
                                }
                                radioButton4.isChecked -> {
                                    sound4 = soundPool.load(item2, 1)
                                    textView4.text = item2.replaceBeforeLast("/", "")
                                }
                                radioButton5.isChecked -> {
                                    sound5 = soundPool.load(item2, 1)
                                    textView5.text = item2.replaceBeforeLast("/", "")
                                }
                                radioButton6.isChecked -> {
                                    sound6 = soundPool.load(item2, 1)
                                    textView6.text = item2.replaceBeforeLast("/", "")
                                }
                                radioButton7.isChecked -> {
                                    sound7 = soundPool.load(item2, 1)
                                    textView7.text = item2.replaceBeforeLast("/", "")
                                }
                                radioButton8.isChecked -> {
                                    sound8 = soundPool.load(item2, 1)
                                    textView8.text = item2.replaceBeforeLast("/", "")
                                }
                                radioButton9.isChecked -> {
                                    sound9 = soundPool.load(item2, 1)
                                    textView9.text = item2.replaceBeforeLast("/", "")
                                }
                                radioButton10.isChecked -> {
                                    sound10 = soundPool.load(item2, 1)
                                    textView10.text = item2.replaceBeforeLast("/", "")
                                }
                                radioButton11.isChecked -> {
                                    sound11 = soundPool.load(item2, 1)
                                    textView11.text = item2.replaceBeforeLast("/", "")
                                }
                                radioButton12.isChecked -> {
                                    sound12 = soundPool.load(item2, 1)
                                    textView12.text = item2.replaceBeforeLast("/", "")
                                }
                                radioButton13.isChecked -> {
                                    sound13 = soundPool.load(item2, 1)
                                    textView13.text = item2.replaceBeforeLast("/", "")
                                }
                                radioButton14.isChecked -> {
                                    sound14 = soundPool.load(item2, 1)
                                    textView14.text = item2.replaceBeforeLast("/", "")
                                }
                                radioButton15.isChecked -> {
                                    sound15 = soundPool.load(item2, 1)
                                    textView15.text = item2.replaceBeforeLast("/", "")
                                }
                                radioButton16.isChecked -> {
                                    mp.release()
                                    mp2.release()
                                    mp = MediaPlayer()
                                    mp2 = MediaPlayer()
                                    volumeControlStream = AudioManager.STREAM_MUSIC
                                    val uri2 = Uri.parse(item2)
                                    mp.setDataSource(applicationContext, uri2)
                                    mp2.setDataSource(applicationContext, uri2)
                                    supportActionBar?.title = item2.replaceBeforeLast("/", "")
                                    mp.prepare()
                                    mp2.prepare()
                                }
                            }
                        } catch (e: Exception) {
                            Toast.makeText(applicationContext, "MUSICフォルダ以外の音声ファイルは\n指定できません。", Toast.LENGTH_LONG).show()
                        }
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
                val uri = Uri.parse(item.replaceBefore("content", ""))
                mp.setDataSource(applicationContext, uri)
                mp2.setDataSource(applicationContext, uri)
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
                val uri3 = Uri.parse(item)
                mp.setDataSource(applicationContext, uri3)
                mp2.setDataSource(applicationContext, uri3)
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
                val uri4 = Uri.parse(item)
                mp.setDataSource(applicationContext, uri4)
                mp2.setDataSource(applicationContext, uri4)
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

        val menuLamp2 = menu.findItem(R.id.menu2)
        if (menuSwitch2) {
            menuLamp2.setIcon(R.drawable.ic_baseline_radio_button_checked_24)
        } else {
            menuLamp2.setIcon(R.drawable.ic_baseline_radio_button_checked_24_2)
        }

        val menuLamp3 = menu.findItem(R.id.menu8)
        if (menuSwitch0) {
            menuLamp3.setIcon(R.drawable.ic_baseline_filter_2_24)
        } else {
            menuLamp3.setIcon(R.drawable.ic_baseline_filter_1_24)
        }

        return true
    }

    private var menuSwitch = true
    private var menuSwitch2 = true
    private var menuSwitch0 = true
    private var mediaRecorder = MediaRecorder()

    @SuppressLint("SimpleDateFormat")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val timeStamp: String = SimpleDateFormat("MM月dd日HH時mm分ss秒").format(Date())
        val soundFilePath = this.getExternalFilesDir(Environment.DIRECTORY_MUSIC).toString() + "/$timeStamp" + "の録音.ogg"

        fun startRecording() {
            try {
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT)
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT)
                mediaRecorder.setOutputFile(soundFilePath)
                mediaRecorder.setMaxDuration(180000)
                mediaRecorder.setOnInfoListener { _, what, _ ->
                    if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED) {
                        mediaRecorder.stop()
                        menuSwitch2 = true
                        invalidateOptionsMenu()
                        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#00BCD4")))
                        switch2.isChecked = false
                        Toast.makeText(applicationContext, "録音時間の上限に達しました。", Toast.LENGTH_SHORT).show()
                    }
                }
                mediaRecorder.prepare()
                mediaRecorder.start()
                Toast.makeText(applicationContext, "音声録音を開始します。", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "録音に失敗したか、マイクの権限がありません。", Toast.LENGTH_LONG).show()

            }
        }

        fun stopRecording() {
            try {
                mediaRecorder.stop()
                Toast.makeText(applicationContext, "録音が終わりました。", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "録音停止に失敗しました。", Toast.LENGTH_LONG).show()
            }
        }
        when (item.itemId) {

            R.id.menu1 -> {
                if (switch1.isChecked) {
                    stop()
                    soundPool.autoPause()
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

            R.id.menu2 -> {
                if (switch2.isChecked) {
                    menuSwitch2 = true
                    invalidateOptionsMenu()
                    stopRecording()
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#00BCD4")))
                    switch2.isChecked = false
                } else {
                    menuSwitch2 = false
                    invalidateOptionsMenu()
                    startRecording()
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#ff7f50")))
                    switch2.isChecked = true
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

            R.id.menu6 -> {
                AlertDialog.Builder(this)
                        .setTitle("終了しますか？")
                        .setPositiveButton("YES") { _, _ ->
                            finish()
                        }
                        .setNegativeButton("NO") { _, _ ->

                        }
                        .show()

                return true
            }

            R.id.menu7 -> {
                radioButton16.performClick()
                selectAudio()
                return true
            }

            R.id.menu8 -> {
                if (switch0.isChecked) {
                    menuSwitch0 = true
                    invalidateOptionsMenu()
                    switch0.isChecked = false
                } else {
                    menuSwitch0 = false
                    invalidateOptionsMenu()
                    switch0.isChecked = true
                }
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    private val permissions = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onDestroy() {
        mp = MediaPlayer()
        mp2 = MediaPlayer()
        mp.reset()
        mp2.reset()
        mp.release()
        mp2.release()
        if (EasyPermissions.hasPermissions(this, *permissions)) {
            soundPool.autoPause()
            soundPool.release()
        }
        mediaRecorder.reset()
        mediaRecorder.release()
        super.onDestroy()
    }

    override fun onPause() {
        menuSwitch = true
        invalidateOptionsMenu()
        switch1.isChecked = false
        if (!menuSwitch2) {
            menuSwitch2 = true
            invalidateOptionsMenu()
            mediaRecorder.stop()
            supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#00BCD4")))
            Toast.makeText(applicationContext, "録音を中断しました。", Toast.LENGTH_SHORT).show()
            switch2.isChecked = false
        }

        if (EasyPermissions.hasPermissions(this, *permissions)) {
            stop()
            soundPool.autoPause()
        }
        super.onPause()
    }
}
