package jp.chikaharu11.chikaharu_sampler

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.drawable.ColorDrawable
import android.media.*
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
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
        val sa = Uri.parse("content://com.android.externalstorage.documents/document/primary%3AAndroid%2Fdata%2Fjp.chikaharu11.chikaharu_sampler%2Ffiles%2FMusic")
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            putExtra(DocumentsContract.EXTRA_INITIAL_URI, sa)
            type = "audio/*"
        }
        startActivityForResult(intent, READ_REQUEST_CODE2)
    }

    fun selectSA() {

        val audioSA = mutableListOf(
                "e808_bd_long_01.ogg",
                "e808_bd_long_02.ogg",
                "e808_bd_long_03.ogg",
                "e808_bd_long_04.ogg",
                "e808_bd_long_05.ogg",
                "e808_bd_long_06.ogg",
                "e808_bd_long_07.ogg",
                "e808_bd_long_08.ogg",
                "e808_bd_long_09.ogg",
                "e808_bd_long_10.ogg",
                "e808_bd_long_11.ogg",
                "e808_bd_long_12.ogg",
                "e808_bd_long_13.ogg",
                "e808_bd_long_14.ogg",
                "e808_bd_long_15.ogg",
                "e808_bd_long_16.ogg",
                "e808_bd_long_17.ogg",
                "e808_bd_short_01.ogg",
                "e808_bd_short_02.ogg",
                "e808_bd_short_03.ogg",
                "e808_bd_short_04.ogg",
                "e808_bd_short_05.ogg",
                "e808_bd_short_06.ogg",
                "e808_bd_short_07.ogg",
                "e808_bd_short_08.ogg",
                "e808_bd_short_09.ogg",
                "e808_bd_short_10.ogg",
                "e808_bd_short_11.ogg",
                "e808_bd_short_12.ogg",
                "e808_cb01.ogg",
                "e808_cb02.ogg",
                "e808_cb03.ogg",
                "e808_cb04.ogg",
                "e808_cb05.ogg",
                "e808_cb06.ogg",
                "e808_cb07.ogg",
                "e808_cb08.ogg",
                "e808_cb09.ogg",
                "e808_cb10.ogg",
                "e808_cb11.ogg",
                "e808_cb12.ogg",
                "e808_cb13.ogg",
                "e808_cb14.ogg",
                "e808_cb15.ogg",
                "e808_ch01.ogg",
                "e808_ch02.ogg",
                "e808_ch03.ogg",
                "e808_ch04.ogg",
                "e808_ch05.ogg",
                "e808_ch06.ogg",
                "e808_ch07.ogg",
                "e808_ch08.ogg",
                "e808_ch09.ogg",
                "e808_ch10.ogg",
                "e808_ch11.ogg",
                "e808_ch12.ogg",
                "e808_cl01.ogg",
                "e808_cl02.ogg",
                "e808_cl03.ogg",
                "e808_cl04.ogg",
                "e808_cl05.ogg",
                "e808_cl06.ogg",
                "e808_cl07.ogg",
                "e808_cl08.ogg",
                "e808_cl09.ogg",
                "e808_cl10.ogg",
                "e808_cl11.ogg",
                "e808_cp01.ogg",
                "e808_cp02.ogg",
                "e808_cp03.ogg",
                "e808_cp04.ogg",
                "e808_cp05.ogg",
                "e808_cp06.ogg",
                "e808_cp07.ogg",
                "e808_cp08.ogg",
                "e808_cp09.ogg",
                "e808_cp10.ogg",
                "e808_cp11.ogg",
                "e808_cp12.ogg",
                "e808_cp13.ogg",
                "e808_cp14.ogg",
                "e808_cp15.ogg",
                "e808_cy01.ogg",
                "e808_cy02.ogg",
                "e808_cy03.ogg",
                "e808_cy04.ogg",
                "e808_cy05.ogg",
                "e808_cy06.ogg",
                "e808_cy07.ogg",
                "e808_cy08.ogg",
                "e808_cy09.ogg",
                "e808_cy10.ogg",
                "e808_cy11.ogg",
                "e808_cy12.ogg",
                "e808_cy13.ogg",
                "e808_cy14.ogg",
                "e808_cy15.ogg",
                "e808_cy16.ogg",
                "e808_hc01.ogg",
                "e808_hc02.ogg",
                "e808_hc03.ogg",
                "e808_hc04.ogg",
                "e808_hc05.ogg",
                "e808_hc06.ogg",
                "e808_hc07.ogg",
                "e808_hc08.ogg",
                "e808_hc09.ogg",
                "e808_hc10.ogg",
                "e808_ht01.ogg",
                "e808_ht02.ogg",
                "e808_ht03.ogg",
                "e808_ht04.ogg",
                "e808_ht05.ogg",
                "e808_ht06.ogg",
                "e808_ht07.ogg",
                "e808_ht08.ogg",
                "e808_ht09.ogg",
                "e808_ht10.ogg",
                "e808_ht11.ogg",
                "e808_ht12.ogg",
                "e808_lc01.ogg",
                "e808_lc02.ogg",
                "e808_lc03.ogg",
                "e808_lc04.ogg",
                "e808_lc05.ogg",
                "e808_lc06.ogg",
                "e808_lc07.ogg",
                "e808_lc08.ogg",
                "e808_lc09.ogg",
                "e808_lc10.ogg",
                "e808_loop_bd_8501.ogg",
                "e808_loop_bd_8502.ogg",
                "e808_loop_bd_8503.ogg",
                "e808_loop_bd_8504.ogg",
                "e808_loop_bd_8505.ogg",
                "e808_loop_bd_8506.ogg",
                "e808_loop_bd_8507.ogg",
                "e808_loop_bd_8508.ogg",
                "e808_loop_bd_9501.ogg",
                "e808_loop_bd_9502.ogg",
                "e808_loop_bd_9503.ogg",
                "e808_loop_bd_9504.ogg",
                "e808_loop_bd_9505.ogg",
                "e808_loop_bd_9506.ogg",
                "e808_loop_bd_9507.ogg",
                "e808_loop_bd_9508.ogg",
                "e808_loop_bd_10501.ogg",
                "e808_loop_bd_10502.ogg",
                "e808_loop_bd_10503.ogg",
                "e808_loop_bd_10504.ogg",
                "e808_loop_bd_10505.ogg",
                "e808_loop_bd_10506.ogg",
                "e808_loop_bd_10507.ogg",
                "e808_loop_bd_10508.ogg",
                "e808_loop_bd_12401.ogg",
                "e808_loop_bd_12402.ogg",
                "e808_loop_bd_12403.ogg",
                "e808_loop_bd_12404.ogg",
                "e808_loop_bd_12405.ogg",
                "e808_loop_bd_12406.ogg",
                "e808_loop_bd_12407.ogg",
                "e808_loop_bd_12408.ogg",
                "e808_loop_bd_13201.ogg",
                "e808_loop_bd_13202.ogg",
                "e808_loop_bd_13203.ogg",
                "e808_loop_bd_13204.ogg",
                "e808_loop_bd_13205.ogg",
                "e808_loop_bd_13206.ogg",
                "e808_loop_bd_13207.ogg",
                "e808_loop_bd_13208.ogg",
                "e808_loop_hats_8501.ogg",
                "e808_loop_hats_8502.ogg",
                "e808_loop_hats_8503.ogg",
                "e808_loop_hats_8504.ogg",
                "e808_loop_hats_8505.ogg",
                "e808_loop_hats_8506.ogg",
                "e808_loop_hats_8507.ogg",
                "e808_loop_hats_8508.ogg",
                "e808_loop_hats_9501.ogg",
                "e808_loop_hats_9502.ogg",
                "e808_loop_hats_9503.ogg",
                "e808_loop_hats_9504.ogg",
                "e808_loop_hats_9505.ogg",
                "e808_loop_hats_9506.ogg",
                "e808_loop_hats_9507.ogg",
                "e808_loop_hats_9508.ogg",
                "e808_loop_hats_10501.ogg",
                "e808_loop_hats_10502.ogg",
                "e808_loop_hats_10503.ogg",
                "e808_loop_hats_10504.ogg",
                "e808_loop_hats_10505.ogg",
                "e808_loop_hats_10506.ogg",
                "e808_loop_hats_10507.ogg",
                "e808_loop_hats_10508.ogg",
                "e808_loop_hats_12401.ogg",
                "e808_loop_hats_12402.ogg",
                "e808_loop_hats_12403.ogg",
                "e808_loop_hats_12404.ogg",
                "e808_loop_hats_12405.ogg",
                "e808_loop_hats_12406.ogg",
                "e808_loop_hats_12407.ogg",
                "e808_loop_hats_12408.ogg",
                "e808_loop_hats_13201.ogg",
                "e808_loop_hats_13202.ogg",
                "e808_loop_hats_13203.ogg",
                "e808_loop_hats_13204.ogg",
                "e808_loop_hats_13205.ogg",
                "e808_loop_hats_13206.ogg",
                "e808_loop_hats_13207.ogg",
                "e808_loop_hats_13208.ogg",
                "e808_loop_perc_8501.ogg",
                "e808_loop_perc_8502.ogg",
                "e808_loop_perc_8503.ogg",
                "e808_loop_perc_8504.ogg",
                "e808_loop_perc_9501.ogg",
                "e808_loop_perc_9502.ogg",
                "e808_loop_perc_9503.ogg",
                "e808_loop_perc_9504.ogg",
                "e808_loop_perc_10501.ogg",
                "e808_loop_perc_10502.ogg",
                "e808_loop_perc_10503.ogg",
                "e808_loop_perc_10504.ogg",
                "e808_loop_perc_12401.ogg",
                "e808_loop_perc_12402.ogg",
                "e808_loop_perc_12403.ogg",
                "e808_loop_perc_12404.ogg",
                "e808_loop_perc_13201.ogg",
                "e808_loop_perc_13202.ogg",
                "e808_loop_perc_13203.ogg",
                "e808_loop_perc_13204.ogg",
                "e808_loop_sd_8501.ogg",
                "e808_loop_sd_8502.ogg",
                "e808_loop_sd_8503.ogg",
                "e808_loop_sd_8504.ogg",
                "e808_loop_sd_8505.ogg",
                "e808_loop_sd_8506.ogg",
                "e808_loop_sd_8507.ogg",
                "e808_loop_sd_8508.ogg",
                "e808_loop_sd_9501.ogg",
                "e808_loop_sd_9502.ogg",
                "e808_loop_sd_9503.ogg",
                "e808_loop_sd_9504.ogg",
                "e808_loop_sd_9505.ogg",
                "e808_loop_sd_9506.ogg",
                "e808_loop_sd_9507.ogg",
                "e808_loop_sd_9508.ogg",
                "e808_loop_sd_10501.ogg",
                "e808_loop_sd_10502.ogg",
                "e808_loop_sd_10503.ogg",
                "e808_loop_sd_10504.ogg",
                "e808_loop_sd_10505.ogg",
                "e808_loop_sd_10506.ogg",
                "e808_loop_sd_10507.ogg",
                "e808_loop_sd_10508.ogg",
                "e808_loop_sd_12401.ogg",
                "e808_loop_sd_12402.ogg",
                "e808_loop_sd_12403.ogg",
                "e808_loop_sd_12404.ogg",
                "e808_loop_sd_12405.ogg",
                "e808_loop_sd_12406.ogg",
                "e808_loop_sd_12407.ogg",
                "e808_loop_sd_13201.ogg",
                "e808_loop_sd_13202.ogg",
                "e808_loop_sd_13203.ogg",
                "e808_loop_sd_13204.ogg",
                "e808_loop_sd_13205.ogg",
                "e808_loop_sd_13206.ogg",
                "e808_loop_sd_13207.ogg",
                "e808_loop_sd_13208.ogg",
                "e808_loop_toms_8501.ogg",
                "e808_loop_toms_8502.ogg",
                "e808_loop_toms_8503.ogg",
                "e808_loop_toms_8504.ogg",
                "e808_loop_toms_9501.ogg",
                "e808_loop_toms_9502.ogg",
                "e808_loop_toms_9503.ogg",
                "e808_loop_toms_9504.ogg",
                "e808_loop_toms_10501.ogg",
                "e808_loop_toms_10502.ogg",
                "e808_loop_toms_10503.ogg",
                "e808_loop_toms_10504.ogg",
                "e808_loop_toms_12401.ogg",
                "e808_loop_toms_12402.ogg",
                "e808_loop_toms_12403.ogg",
                "e808_loop_toms_12404.ogg",
                "e808_loop_toms_13201.ogg",
                "e808_loop_toms_13202.ogg",
                "e808_loop_toms_13203.ogg",
                "e808_loop_toms_13204.ogg",
                "e808_lt01.ogg",
                "e808_lt02.ogg",
                "e808_lt03.ogg",
                "e808_lt04.ogg",
                "e808_lt05.ogg",
                "e808_lt06.ogg",
                "e808_lt07.ogg",
                "e808_lt08.ogg",
                "e808_lt09.ogg",
                "e808_lt10.ogg",
                "e808_lt11.ogg",
                "e808_lt12.ogg",
                "e808_ma01.ogg",
                "e808_ma02.ogg",
                "e808_ma03.ogg",
                "e808_ma04.ogg",
                "e808_ma05.ogg",
                "e808_ma06.ogg",
                "e808_ma07.ogg",
                "e808_ma08.ogg",
                "e808_ma09.ogg",
                "e808_ma10.ogg",
                "e808_ma11.ogg",
                "e808_mc01.ogg",
                "e808_mc02.ogg",
                "e808_mc03.ogg",
                "e808_mc04.ogg",
                "e808_mc05.ogg",
                "e808_mc06.ogg",
                "e808_mc07.ogg",
                "e808_mc08.ogg",
                "e808_mc09.ogg",
                "e808_mc10.ogg",
                "e808_mt01.ogg",
                "e808_mt02.ogg",
                "e808_mt03.ogg",
                "e808_mt04.ogg",
                "e808_mt05.ogg",
                "e808_mt06.ogg",
                "e808_mt07.ogg",
                "e808_mt08.ogg",
                "e808_mt09.ogg",
                "e808_mt10.ogg",
                "e808_mt11.ogg",
                "e808_mt12.ogg",
                "e808_oh01.ogg",
                "e808_oh02.ogg",
                "e808_oh03.ogg",
                "e808_oh04.ogg",
                "e808_oh05.ogg",
                "e808_oh06.ogg",
                "e808_oh07.ogg",
                "e808_oh08.ogg",
                "e808_oh09.ogg",
                "e808_oh10.ogg",
                "e808_oh11.ogg",
                "e808_oh12.ogg",
                "e808_oh13.ogg",
                "e808_rs01.ogg",
                "e808_rs02.ogg",
                "e808_rs03.ogg",
                "e808_rs04.ogg",
                "e808_rs05.ogg",
                "e808_rs06.ogg",
                "e808_rs07.ogg",
                "e808_rs08.ogg",
                "e808_rs09.ogg",
                "e808_rs10.ogg",
                "e808_rs11.ogg",
                "e808_sd01.ogg",
                "e808_sd02.ogg",
                "e808_sd03.ogg",
                "e808_sd04.ogg",
                "e808_sd05.ogg",
                "e808_sd06.ogg",
                "e808_sd07.ogg",
                "e808_sd08.ogg",
                "e808_sd09.ogg",
                "e808_sd10.ogg",
                "e808_sd11.ogg",
                "e808_sd12.ogg",
                "e808_sd13.ogg",
                "e808_sd14.ogg",
                "e808_sd15.ogg",
                "e808_sd16.ogg",
                "e808_sd17.ogg",
                "e808_sd18.ogg",
                "e808_sd19.ogg",
                "e808_sd20.ogg"
        )

            when {
                    radioButton1a.isChecked -> {
                        audioSA.removeIf { !it.contains("bd") }
                        audioSA.removeIf { it.contains("loop") }
                    }
                    radioButton2a.isChecked -> audioSA.removeIf { !it.contains("cp") }
                    radioButton3a.isChecked -> audioSA.removeIf { !it.contains("cl") }
                    radioButton4a.isChecked -> audioSA.removeIf { !it.contains("ch") }
                    radioButton5a.isChecked -> {
                        audioSA.removeIf { !it.contains("c") }
                        audioSA.removeIf { it.contains("cp") }
                        audioSA.removeIf { it.contains("cl") }
                        audioSA.removeIf { it.contains("ch") }
                        audioSA.removeIf { it.contains("cb") }
                        audioSA.removeIf { it.contains("cy") }
                        audioSA.removeIf { it.contains("loop") }
                    }
                    radioButton6a.isChecked -> audioSA.removeIf { !it.contains("cb") }
                    radioButton7a.isChecked -> audioSA.removeIf { !it.contains("cy") }
                    radioButton8a.isChecked -> audioSA.removeIf { !it.contains("ma") }
                    radioButton9a.isChecked -> audioSA.removeIf { !it.contains("oh") }
                    radioButton10a.isChecked -> audioSA.removeIf { !it.contains("rs") }
                    radioButton11a.isChecked -> audioSA.removeIf { !it.contains("sd") }
                    radioButton12a.isChecked -> {
                        audioSA.removeIf { !it.contains("t") }
                        audioSA.removeIf { it.contains("bd") }
                        audioSA.removeIf { it.contains("loop") }
                    }
                    radioButton13a.isChecked -> audioSA.removeIf { !it.contains("loop") }
            }

        val saSpinner = findViewById<Spinner>(R.id.sampler_spinner)

        val adapterSA = ArrayAdapter(
                applicationContext,
                android.R.layout.simple_spinner_item, audioSA
        )

        adapterSA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)



        saSpinner.adapter = adapterSA

        saSpinner.performClick()


        saSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?, position: Int, id: Long
            ) {
                if (!saSpinner.isFocusable) {
                    saSpinner.isFocusable = true
                    return
                }
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String
                when {
                    radioButton.isChecked -> {
                        sound1 = soundPool.load(assets.openFd(item), 1)
                        textView.text = item
                    }
                    radioButton2.isChecked -> {
                        sound2 = soundPool.load(assets.openFd(item), 1)
                        textView2.text = item
                    }
                    radioButton3.isChecked -> {
                        sound3 = soundPool.load(assets.openFd(item), 1)
                        textView3.text = item
                    }
                    radioButton4.isChecked -> {
                        sound4 = soundPool.load(assets.openFd(item), 1)
                        textView4.text = item
                    }
                    radioButton5.isChecked -> {
                        sound5 = soundPool.load(assets.openFd(item), 1)
                        textView5.text = item
                    }
                    radioButton6.isChecked -> {
                        sound6 = soundPool.load(assets.openFd(item), 1)
                        textView6.text = item
                    }
                    radioButton7.isChecked -> {
                        sound7 = soundPool.load(assets.openFd(item), 1)
                        textView7.text = item
                    }
                    radioButton8.isChecked -> {
                        sound8 = soundPool.load(assets.openFd(item), 1)
                        textView8.text = item
                    }
                    radioButton9.isChecked -> {
                        sound9 = soundPool.load(assets.openFd(item), 1)
                        textView9.text = item
                    }
                    radioButton10.isChecked -> {
                        sound10 = soundPool.load(assets.openFd(item), 1)
                        textView10.text = item
                    }
                    radioButton11.isChecked -> {
                        sound11 = soundPool.load(assets.openFd(item), 1)
                        textView11.text = item
                    }
                    radioButton12.isChecked -> {
                        sound12 = soundPool.load(assets.openFd(item), 1)
                        textView12.text = item
                    }
                    radioButton13.isChecked -> {
                        sound13 = soundPool.load(assets.openFd(item), 1)
                        textView13.text = item
                    }
                    radioButton14.isChecked -> {
                        sound14 = soundPool.load(assets.openFd(item), 1)
                        textView14.text = item
                    }
                    radioButton15.isChecked -> {
                        sound15 = soundPool.load(assets.openFd(item), 1)
                        textView15.text = item
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        saSpinner.isFocusable = false
    }

    private fun selectLSA() {

        val audioLSA = mutableListOf(
                "e808_loop_bd_8501.ogg",
                "e808_loop_bd_8502.ogg",
                "e808_loop_bd_8503.ogg",
                "e808_loop_bd_8504.ogg",
                "e808_loop_bd_8505.ogg",
                "e808_loop_bd_8506.ogg",
                "e808_loop_bd_8507.ogg",
                "e808_loop_bd_8508.ogg",
                "e808_loop_bd_9501.ogg",
                "e808_loop_bd_9502.ogg",
                "e808_loop_bd_9503.ogg",
                "e808_loop_bd_9504.ogg",
                "e808_loop_bd_9505.ogg",
                "e808_loop_bd_9506.ogg",
                "e808_loop_bd_9507.ogg",
                "e808_loop_bd_9508.ogg",
                "e808_loop_bd_10501.ogg",
                "e808_loop_bd_10502.ogg",
                "e808_loop_bd_10503.ogg",
                "e808_loop_bd_10504.ogg",
                "e808_loop_bd_10505.ogg",
                "e808_loop_bd_10506.ogg",
                "e808_loop_bd_10507.ogg",
                "e808_loop_bd_10508.ogg",
                "e808_loop_bd_12401.ogg",
                "e808_loop_bd_12402.ogg",
                "e808_loop_bd_12403.ogg",
                "e808_loop_bd_12404.ogg",
                "e808_loop_bd_12405.ogg",
                "e808_loop_bd_12406.ogg",
                "e808_loop_bd_12407.ogg",
                "e808_loop_bd_12408.ogg",
                "e808_loop_bd_13201.ogg",
                "e808_loop_bd_13202.ogg",
                "e808_loop_bd_13203.ogg",
                "e808_loop_bd_13204.ogg",
                "e808_loop_bd_13205.ogg",
                "e808_loop_bd_13206.ogg",
                "e808_loop_bd_13207.ogg",
                "e808_loop_bd_13208.ogg",
                "e808_loop_hats_8501.ogg",
                "e808_loop_hats_8502.ogg",
                "e808_loop_hats_8503.ogg",
                "e808_loop_hats_8504.ogg",
                "e808_loop_hats_8505.ogg",
                "e808_loop_hats_8506.ogg",
                "e808_loop_hats_8507.ogg",
                "e808_loop_hats_8508.ogg",
                "e808_loop_hats_9501.ogg",
                "e808_loop_hats_9502.ogg",
                "e808_loop_hats_9503.ogg",
                "e808_loop_hats_9504.ogg",
                "e808_loop_hats_9505.ogg",
                "e808_loop_hats_9506.ogg",
                "e808_loop_hats_9507.ogg",
                "e808_loop_hats_9508.ogg",
                "e808_loop_hats_10501.ogg",
                "e808_loop_hats_10502.ogg",
                "e808_loop_hats_10503.ogg",
                "e808_loop_hats_10504.ogg",
                "e808_loop_hats_10505.ogg",
                "e808_loop_hats_10506.ogg",
                "e808_loop_hats_10507.ogg",
                "e808_loop_hats_10508.ogg",
                "e808_loop_hats_12401.ogg",
                "e808_loop_hats_12402.ogg",
                "e808_loop_hats_12403.ogg",
                "e808_loop_hats_12404.ogg",
                "e808_loop_hats_12405.ogg",
                "e808_loop_hats_12406.ogg",
                "e808_loop_hats_12407.ogg",
                "e808_loop_hats_12408.ogg",
                "e808_loop_hats_13201.ogg",
                "e808_loop_hats_13202.ogg",
                "e808_loop_hats_13203.ogg",
                "e808_loop_hats_13204.ogg",
                "e808_loop_hats_13205.ogg",
                "e808_loop_hats_13206.ogg",
                "e808_loop_hats_13207.ogg",
                "e808_loop_hats_13208.ogg",
                "e808_loop_perc_8501.ogg",
                "e808_loop_perc_8502.ogg",
                "e808_loop_perc_8503.ogg",
                "e808_loop_perc_8504.ogg",
                "e808_loop_perc_9501.ogg",
                "e808_loop_perc_9502.ogg",
                "e808_loop_perc_9503.ogg",
                "e808_loop_perc_9504.ogg",
                "e808_loop_perc_10501.ogg",
                "e808_loop_perc_10502.ogg",
                "e808_loop_perc_10503.ogg",
                "e808_loop_perc_10504.ogg",
                "e808_loop_perc_12401.ogg",
                "e808_loop_perc_12402.ogg",
                "e808_loop_perc_12403.ogg",
                "e808_loop_perc_12404.ogg",
                "e808_loop_perc_13201.ogg",
                "e808_loop_perc_13202.ogg",
                "e808_loop_perc_13203.ogg",
                "e808_loop_perc_13204.ogg",
                "e808_loop_sd_8501.ogg",
                "e808_loop_sd_8502.ogg",
                "e808_loop_sd_8503.ogg",
                "e808_loop_sd_8504.ogg",
                "e808_loop_sd_8505.ogg",
                "e808_loop_sd_8506.ogg",
                "e808_loop_sd_8507.ogg",
                "e808_loop_sd_8508.ogg",
                "e808_loop_sd_9501.ogg",
                "e808_loop_sd_9502.ogg",
                "e808_loop_sd_9503.ogg",
                "e808_loop_sd_9504.ogg",
                "e808_loop_sd_9505.ogg",
                "e808_loop_sd_9506.ogg",
                "e808_loop_sd_9507.ogg",
                "e808_loop_sd_9508.ogg",
                "e808_loop_sd_10501.ogg",
                "e808_loop_sd_10502.ogg",
                "e808_loop_sd_10503.ogg",
                "e808_loop_sd_10504.ogg",
                "e808_loop_sd_10505.ogg",
                "e808_loop_sd_10506.ogg",
                "e808_loop_sd_10507.ogg",
                "e808_loop_sd_10508.ogg",
                "e808_loop_sd_12401.ogg",
                "e808_loop_sd_12402.ogg",
                "e808_loop_sd_12403.ogg",
                "e808_loop_sd_12404.ogg",
                "e808_loop_sd_12405.ogg",
                "e808_loop_sd_12406.ogg",
                "e808_loop_sd_12407.ogg",
                "e808_loop_sd_13201.ogg",
                "e808_loop_sd_13202.ogg",
                "e808_loop_sd_13203.ogg",
                "e808_loop_sd_13204.ogg",
                "e808_loop_sd_13205.ogg",
                "e808_loop_sd_13206.ogg",
                "e808_loop_sd_13207.ogg",
                "e808_loop_sd_13208.ogg",
                "e808_loop_toms_8501.ogg",
                "e808_loop_toms_8502.ogg",
                "e808_loop_toms_8503.ogg",
                "e808_loop_toms_8504.ogg",
                "e808_loop_toms_9501.ogg",
                "e808_loop_toms_9502.ogg",
                "e808_loop_toms_9503.ogg",
                "e808_loop_toms_9504.ogg",
                "e808_loop_toms_10501.ogg",
                "e808_loop_toms_10502.ogg",
                "e808_loop_toms_10503.ogg",
                "e808_loop_toms_10504.ogg",
                "e808_loop_toms_12401.ogg",
                "e808_loop_toms_12402.ogg",
                "e808_loop_toms_12403.ogg",
                "e808_loop_toms_12404.ogg",
                "e808_loop_toms_13201.ogg",
                "e808_loop_toms_13202.ogg",
                "e808_loop_toms_13203.ogg",
                "e808_loop_toms_13204.ogg"
        )

        when {
            radioButton1b.isChecked -> audioLSA.removeIf { !it.contains("85") }
            radioButton2b.isChecked -> audioLSA.removeIf { !it.contains("95") }
            radioButton3b.isChecked -> audioLSA.removeIf { !it.contains("105") }
            radioButton4b.isChecked -> audioLSA.removeIf { !it.contains("124") }
            radioButton5b.isChecked -> audioLSA.removeIf { !it.contains("132") }
        }

        val lsaSpinner = findViewById<Spinner>(R.id.loop_sampler_spinner)

        val adapterLSA = ArrayAdapter(
                applicationContext,
                android.R.layout.simple_spinner_item, audioLSA
        )

        adapterLSA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)



        lsaSpinner.adapter = adapterLSA

        lsaSpinner.performClick()


        lsaSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?, position: Int, id: Long
            ) {
                if (!lsaSpinner.isFocusable) {
                    lsaSpinner.isFocusable = true
                    return
                }
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String
                    if (radioButton16.isChecked) {
                        lmp.release()
                        lmp = LoopMediaPlayer(this@MainActivity, Uri.parse("android.resource://" + packageName + "/raw/" + item.replace(".ogg", "")))
                        supportActionBar?.title = item
                    }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        lsaSpinner.isFocusable = false
    }

    private lateinit var soundPool: SoundPool

    private lateinit var lmp: LoopMediaPlayer

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


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title ="e808_loop_bd_8501"

        val permissions = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE
        )

        if (!EasyPermissions.hasPermissions(this, *permissions)) {
            AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setMessage("このアプリはスマートフォンや、\nSDカード内の音声ファイル、\n画像を使用します。\nアプリの設定画面に移動して、\nストレージ権限の許可をお願いします。")
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


            val audio1 = mutableSetOf(
                ""
            )

            audio1.clear()

            val audioUri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI
            val cursor = contentResolver.query(audioUri, null, null, null, null)
            cursor!!.moveToFirst()
            val path: Array<String?> = arrayOfNulls(cursor.count)
            for (i in path.indices) {
                path[i] = cursor.getString(cursor.getColumnIndex("_data"))
                audio1.add(
                    path[i]?.replaceBeforeLast("/", "(")
                        ?.replace("/", "") + ")  " + path[i].toString()
                )
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
                    when {
                        radioButton.isChecked -> {
                            sound1 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton2.isChecked -> {
                            sound2 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView2.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton3.isChecked -> {
                            sound3 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView3.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton4.isChecked -> {
                            sound4 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView4.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton5.isChecked -> {
                            sound5 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView5.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton6.isChecked -> {
                            sound6 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView6.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton7.isChecked -> {
                            sound7 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView7.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton8.isChecked -> {
                            sound8 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView8.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton9.isChecked -> {
                            sound9 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView9.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton10.isChecked -> {
                            sound10 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView10.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton11.isChecked -> {
                            sound11 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView11.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton12.isChecked -> {
                            sound12 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView12.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton13.isChecked -> {
                            sound13 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView13.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton14.isChecked -> {
                            sound14 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView14.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton15.isChecked -> {
                            sound15 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView15.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }



        fun selectEX() {

            val audio2 = mutableSetOf(
                    ""
            )

            audio2.clear()

            val audioUri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            val cursor2 = contentResolver.query(audioUri2, null, null, null, null)
            cursor2!!.moveToFirst()
            val path2: Array<String?> = arrayOfNulls(cursor2.count)
            for (i in path2.indices) {
                path2[i] = cursor2.getString(cursor2.getColumnIndex("_data"))
                audio2.add(path2[i]?.replaceBeforeLast("/", "(")?.replace("/", "") + ")  " + path2[i].toString())
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

            exSpinner.performClick()


            exSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?, position: Int, id: Long
                ) {
                    if (!exSpinner.isFocusable) {
                        exSpinner.isFocusable = true
                        return
                    }
                    val spinnerParent = parent as Spinner
                    val item = spinnerParent.selectedItem as String
                    when {
                        radioButton.isChecked -> {
                            sound1 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton2.isChecked -> {
                            sound2 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView2.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton3.isChecked -> {
                            sound3 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView3.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton4.isChecked -> {
                            sound4 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView4.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton5.isChecked -> {
                            sound5 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView5.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton6.isChecked -> {
                            sound6 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView6.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton7.isChecked -> {
                            sound7 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView7.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton8.isChecked -> {
                            sound8 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView8.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton9.isChecked -> {
                            sound9 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView9.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton10.isChecked -> {
                            sound10 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView10.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton11.isChecked -> {
                            sound11 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView11.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton12.isChecked -> {
                            sound12 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView12.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton13.isChecked -> {
                            sound13 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView13.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton14.isChecked -> {
                            sound14 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView14.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                        radioButton15.isChecked -> {
                            sound15 = soundPool.load(item.replaceBefore("/", ""), 1)
                            textView15.text = item.replaceBeforeLast("/", "").replace("/", "")
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
            exSpinner.isFocusable = false
        }

        val menu1 = listOf(
                "Bass Drum [BD]",
                "Clap [CP]",
                "Claves [CL]",
                "Closed Hi Hat [CH]",
                "Congas [HC-MC-LC]",
                "Cowbell [CB]",
                "Cymbal [CY]",
                "Maracas [MA]",
                "Open Hi Hat [OH]",
                "Rimshot [RS]",
                "Snare Drum [SD]",
                "Tom Toms [LT-MT-HT]",
                "Loops",
                "内部サウンド",
                "外部(ダウンロード)サウンド",
                "録音したサウンド",
                "魔王魂さんからDLする",
                "画像を選ぶ"
        )

        val meSpinner = findViewById<Spinner>(R.id.menu_spinner)

        val adapter3 = ArrayAdapter(
                applicationContext,
                android.R.layout.simple_spinner_item, menu1
        )

        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)



        meSpinner.adapter = adapter3


        meSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?, position: Int, id: Long
            ) {
                if (!meSpinner.isFocusable) {
                    meSpinner.isFocusable = true
                    return
                }
                when(position){
                    0 -> { radioButton1a.performClick()
                            selectSA() }
                    1 -> { radioButton2a.performClick()
                            selectSA() }
                    2 -> { radioButton3a.performClick()
                            selectSA() }
                    3 -> { radioButton4a.performClick()
                            selectSA() }
                    4 -> { radioButton5a.performClick()
                            selectSA() }
                    5 -> { radioButton6a.performClick()
                            selectSA() }
                    6 -> { radioButton7a.performClick()
                            selectSA() }
                    7 -> { radioButton8a.performClick()
                            selectSA() }
                    8 -> { radioButton9a.performClick()
                            selectSA() }
                    9 -> { radioButton10a.performClick()
                            selectSA() }
                    10 -> { radioButton11a.performClick()
                            selectSA() }
                    11 -> { radioButton12a.performClick()
                            selectSA() }
                    12 -> { radioButton13a.performClick()
                            selectSA() }
                    13 -> inSpinner.performClick()
                    14 -> selectEX()
                    15 -> selectAudio()
                    16 -> startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://maoudamashii.jokersounds.com/")))
                    17 -> selectPhoto()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        meSpinner.isFocusable = false




        val audioAttributes = AudioAttributes.Builder()

                .setUsage(AudioAttributes.USAGE_GAME)

                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build()

        soundPool = SoundPool.Builder()

                .setAudioAttributes(audioAttributes)

                .setMaxStreams(10)
                .build()

        sound1 = soundPool.load(assets.openFd("e808_bd_long_01.ogg"), 1)

        sound2 = soundPool.load(assets.openFd("e808_bd_long_02.ogg"), 1)

        sound3 = soundPool.load(assets.openFd("e808_bd_long_03.ogg"), 1)

        sound4 = soundPool.load(assets.openFd("e808_rs09.ogg"), 1)

        sound5 = soundPool.load(assets.openFd("e808_bd_short_05.ogg"), 1)

        sound6 = soundPool.load(assets.openFd("e808_hc03.ogg"), 1)

        sound7 = soundPool.load(assets.openFd("e808_lc08.ogg"), 1)

        sound8 = soundPool.load(assets.openFd("e808_lt02.ogg"), 1)

        sound9 = soundPool.load(assets.openFd("e808_cy04.ogg"), 1)

        sound10 = soundPool.load(assets.openFd("e808_mc05.ogg"), 1)

        sound11 = soundPool.load(assets.openFd("e808_cb07.ogg"), 1)

        sound12 = soundPool.load(assets.openFd("e808_sd09.ogg"), 1)

        sound13 = soundPool.load(assets.openFd("e808_bd_long_07.ogg"), 1)

        sound14 = soundPool.load(assets.openFd("e808_lc10.ogg"), 1)

        sound15 = soundPool.load(assets.openFd("e808_oh09.ogg"), 1)

        lmp = LoopMediaPlayer.create(this, Uri.parse("android.resource://" + packageName + "/raw/" + R.raw.e808_loop_bd_8501))


        imageView.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        if (switch0.isChecked) {
                                soundPool.autoPause()
                        }
                        soundPool.play(sound1, 1.0f, 1.0f, 1, 0, 1.0f)
                }
                false
        }

        imageView2.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        if (switch0.isChecked) {
                                soundPool.autoPause()
                        }
                        soundPool.play(sound2, 1.0f, 1.0f, 1, 0, 1.0f)
                }
                false
        }

        imageView3.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        if (switch0.isChecked) {
                                soundPool.autoPause()
                        }
                        soundPool.play(sound3, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView4.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        if (switch0.isChecked) {
                                soundPool.autoPause()
                        }
                        soundPool.play(sound4, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView5.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        if (switch0.isChecked) {
                                soundPool.autoPause()
                        }
                        soundPool.play(sound5, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView6.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        if (switch0.isChecked) {
                                soundPool.autoPause()
                        }
                        soundPool.play(sound6, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView7.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        if (switch0.isChecked) {
                                soundPool.autoPause()
                        }
                        soundPool.play(sound7, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView8.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        if (switch0.isChecked) {
                                soundPool.autoPause()
                        }
                        soundPool.play(sound8, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView9.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        if (switch0.isChecked) {
                                soundPool.autoPause()
                        }
                        soundPool.play(sound9, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false

        }

        imageView10.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        if (switch0.isChecked) {
                                soundPool.autoPause()
                        }
                        soundPool.play(sound10, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView11.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        if (switch0.isChecked) {
                                soundPool.autoPause()
                        }
                        soundPool.play(sound11, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView12.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        if (switch0.isChecked) {
                                soundPool.autoPause()
                        }
                        soundPool.play(sound12, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView13.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        if (switch0.isChecked) {
                                soundPool.autoPause()
                        }
                        soundPool.play(sound13, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView14.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        if (switch0.isChecked) {
                                soundPool.autoPause()
                        }
                        soundPool.play(sound14, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView15.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        if (switch0.isChecked) {
                                soundPool.autoPause()
                        }
                        soundPool.play(sound15, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
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
                                textView.text = item.replaceBeforeLast("/", "").replace("/", "")
                            }
                            radioButton2.isChecked -> {
                                sound2 = soundPool.load(item, 1)
                                textView2.text = item.replaceBeforeLast("/", "").replace("/", "")
                            }
                            radioButton3.isChecked -> {
                                sound3 = soundPool.load(item, 1)
                                textView3.text = item.replaceBeforeLast("/", "").replace("/", "")
                            }
                            radioButton4.isChecked -> {
                                sound4 = soundPool.load(item, 1)
                                textView4.text = item.replaceBeforeLast("/", "").replace("/", "")
                            }
                            radioButton5.isChecked -> {
                                sound5 = soundPool.load(item, 1)
                                textView5.text = item.replaceBeforeLast("/", "").replace("/", "")
                            }
                            radioButton6.isChecked -> {
                                sound6 = soundPool.load(item, 1)
                                textView6.text = item.replaceBeforeLast("/", "").replace("/", "")
                            }
                            radioButton7.isChecked -> {
                                sound7 = soundPool.load(item, 1)
                                textView7.text = item.replaceBeforeLast("/", "").replace("/", "")
                            }
                            radioButton8.isChecked -> {
                                sound8 = soundPool.load(item, 1)
                                textView8.text = item.replaceBeforeLast("/", "").replace("/", "")
                            }
                            radioButton9.isChecked -> {
                                sound9 = soundPool.load(item, 1)
                                textView9.text = item.replaceBeforeLast("/", "").replace("/", "")
                            }
                            radioButton10.isChecked -> {
                                sound10 = soundPool.load(item, 1)
                                textView10.text = item.replaceBeforeLast("/", "").replace("/", "")
                            }
                            radioButton11.isChecked -> {
                                sound11 = soundPool.load(item, 1)
                                textView11.text = item.replaceBeforeLast("/", "").replace("/", "")
                            }
                            radioButton12.isChecked -> {
                                sound12 = soundPool.load(item, 1)
                                textView12.text = item.replaceBeforeLast("/", "").replace("/", "")
                            }
                            radioButton13.isChecked -> {
                                sound13 = soundPool.load(item, 1)
                                textView13.text = item.replaceBeforeLast("/", "").replace("/", "")
                            }
                            radioButton14.isChecked -> {
                                sound14 = soundPool.load(item, 1)
                                textView14.text = item.replaceBeforeLast("/", "").replace("/", "")
                            }
                            radioButton15.isChecked -> {
                                sound15 = soundPool.load(item, 1)
                                textView15.text = item.replaceBeforeLast("/", "").replace("/", "")
                            }
                            radioButton16.isChecked -> {
                                lmp.release()
                                lmp = LoopMediaPlayer(this@MainActivity, Uri.parse(item))
                                supportActionBar?.title = item.replaceBeforeLast("/", "").replace("/", "")
                            }
                        }
                    } else {
                        try {
                            val item2 = "/stroage/" + type + "/" + split[1]
                            when {
                                radioButton.isChecked -> {
                                    sound1 = soundPool.load(item2, 1)
                                    textView.text = item2.replaceBeforeLast("/", "").replace("/", "")
                                }
                                radioButton2.isChecked -> {
                                    sound2 = soundPool.load(item2, 1)
                                    textView2.text = item2.replaceBeforeLast("/", "").replace("/", "")
                                }
                                radioButton3.isChecked -> {
                                    sound3 = soundPool.load(item2, 1)
                                    textView3.text = item2.replaceBeforeLast("/", "").replace("/", "")
                                }
                                radioButton4.isChecked -> {
                                    sound4 = soundPool.load(item2, 1)
                                    textView4.text = item2.replaceBeforeLast("/", "").replace("/", "")
                                }
                                radioButton5.isChecked -> {
                                    sound5 = soundPool.load(item2, 1)
                                    textView5.text = item2.replaceBeforeLast("/", "").replace("/", "")
                                }
                                radioButton6.isChecked -> {
                                    sound6 = soundPool.load(item2, 1)
                                    textView6.text = item2.replaceBeforeLast("/", "").replace("/", "")
                                }
                                radioButton7.isChecked -> {
                                    sound7 = soundPool.load(item2, 1)
                                    textView7.text = item2.replaceBeforeLast("/", "").replace("/", "")
                                }
                                radioButton8.isChecked -> {
                                    sound8 = soundPool.load(item2, 1)
                                    textView8.text = item2.replaceBeforeLast("/", "").replace("/", "")
                                }
                                radioButton9.isChecked -> {
                                    sound9 = soundPool.load(item2, 1)
                                    textView9.text = item2.replaceBeforeLast("/", "").replace("/", "")
                                }
                                radioButton10.isChecked -> {
                                    sound10 = soundPool.load(item2, 1)
                                    textView10.text = item2.replaceBeforeLast("/", "").replace("/", "")
                                }
                                radioButton11.isChecked -> {
                                    sound11 = soundPool.load(item2, 1)
                                    textView11.text = item2.replaceBeforeLast("/", "").replace("/", "")
                                }
                                radioButton12.isChecked -> {
                                    sound12 = soundPool.load(item2, 1)
                                    textView12.text = item2.replaceBeforeLast("/", "").replace("/", "")
                                }
                                radioButton13.isChecked -> {
                                    sound13 = soundPool.load(item2, 1)
                                    textView13.text = item2.replaceBeforeLast("/", "").replace("/", "")
                                }
                                radioButton14.isChecked -> {
                                    sound14 = soundPool.load(item2, 1)
                                    textView14.text = item2.replaceBeforeLast("/", "").replace("/", "")
                                }
                                radioButton15.isChecked -> {
                                    sound15 = soundPool.load(item2, 1)
                                    textView15.text = item2.replaceBeforeLast("/", "").replace("/", "")
                                }
                                radioButton16.isChecked -> {
                                    lmp.release()
                                    lmp = LoopMediaPlayer(this@MainActivity, Uri.parse(item2))
                                    supportActionBar?.title = item2.replaceBeforeLast("/", "").replace("/", "")
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
            path[i] = cursor.getString(cursor.getColumnIndex("_data"))
            audio1.add(path[i]?.replaceBeforeLast("/", "(")?.replace("/", "") + ")  " + path[i].toString())
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
                try {
                    val spinnerParent = parent as Spinner
                    val item = spinnerParent.selectedItem as String
                    volumeControlStream = AudioManager.STREAM_MUSIC
                    val uri3 = Uri.parse(item.replaceBefore("/", ""))
                    lmp.release()
                    lmp = LoopMediaPlayer(this@MainActivity, uri3)
                    supportActionBar?.title = item.replaceBeforeLast("/", "").replace("/", "")
                } catch (e: Exception) {
                    Toast.makeText(applicationContext, "この音声ファイルは\n設定できません。", Toast.LENGTH_LONG).show()
                }
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
            path[i] = cursor.getString(cursor.getColumnIndex("_data"))
            audio1.add(path[i]?.replaceBeforeLast("/", "(")?.replace("/", "") + ")  " + path[i].toString())
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
                volumeControlStream = AudioManager.STREAM_MUSIC
                val uri4 = Uri.parse(item.replaceBefore("/", ""))
                lmp.release()
                lmp = LoopMediaPlayer(this@MainActivity, uri4)
                supportActionBar?.title =item.replaceBeforeLast("/", "").replace("/", "")
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
            menuLamp2.setIcon(R.drawable.ic_baseline_mic_24)
        } else {
            menuLamp2.setIcon(R.drawable.ic_baseline_mic_24_2)
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
                    lmp.stop()
                    soundPool.autoPause()
                    menuSwitch = true
                    invalidateOptionsMenu()
                    switch1.isChecked = false
                } else {
                    lmp.start()
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

            R.id.menu3a -> {
                lmp.stop()
                menuSwitch = true
                invalidateOptionsMenu()
                switch1.isChecked = false
                radioButton16.performClick()
                radioButton1b.performClick()
                selectLSA()
                return true
            }

            R.id.menu3b -> {
                lmp.stop()
                menuSwitch = true
                invalidateOptionsMenu()
                switch1.isChecked = false
                radioButton16.performClick()
                radioButton2b.performClick()
                selectLSA()
                return true
            }

            R.id.menu3c -> {
                lmp.stop()
                menuSwitch = true
                invalidateOptionsMenu()
                switch1.isChecked = false
                radioButton16.performClick()
                radioButton3b.performClick()
                selectLSA()
                return true
            }

            R.id.menu3d -> {
                lmp.stop()
                menuSwitch = true
                invalidateOptionsMenu()
                switch1.isChecked = false
                radioButton16.performClick()
                radioButton4b.performClick()
                selectLSA()
                return true
            }

            R.id.menu3e -> {
                lmp.stop()
                menuSwitch = true
                invalidateOptionsMenu()
                switch1.isChecked = false
                radioButton16.performClick()
                radioButton5b.performClick()
                selectLSA()
                return true
            }

            R.id.menu4 -> {
                lmp.stop()
                menuSwitch = true
                invalidateOptionsMenu()
                switch1.isChecked = false
                select3()
                return true
            }

            R.id.menu5 -> {
                lmp.stop()
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

            R.id.menu9 -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://maoudamashii.jokersounds.com/")))
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    private val permissions = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onDestroy() {
            lmp = LoopMediaPlayer.create(this, Uri.parse("android.resource://" + packageName + "/raw/" + R.raw.ta))
        lmp.reset()
        lmp.release()
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
            lmp.stop()
            soundPool.autoPause()
        }
        super.onPause()
    }
}
