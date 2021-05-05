package jp.chikaharu11.chikaharu_sampler

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.*
import android.media.projection.MediaProjectionManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.arthenica.mobileffmpeg.FFmpeg
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_dialog.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), CustomAdapterListener {

    private lateinit var mediaProjectionManager: MediaProjectionManager

    private val handler = Handler()
    private var hoge = "test"
    var fuga = 0
    var fuga2 = 0

    companion object {
        private const val READ_REQUEST_CODE2: Int = 43
        private const val READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 41
        private const val RECORD_AUDIO_PERMISSION_REQUEST_CODE = 42
        private const val MEDIA_PROJECTION_REQUEST_CODE = 13
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

    fun selectEX() {
        if (!isReadExternalStoragePermissionGranted()) {
            requestReadExternalStoragePermission()
        } else {
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
                        radioButton17.isChecked -> {
                            hoge = item.replaceBefore("/", "")
                            button4.performClick()
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
            exSpinner.isFocusable = false
        }
    }

    private lateinit var soundPool: SoundPool

    private lateinit var mp: MediaPlayer

    private lateinit var lmp: LoopMediaPlayer

    private lateinit var aCustomAdapter: CustomAdapter
    private lateinit var bCustomAdapter: CustomAdapter
    private lateinit var cCustomAdapter: CustomAdapter
    private lateinit var dCustomAdapter: CustomAdapter
    private lateinit var eCustomAdapter: CustomAdapter
    private lateinit var fCustomAdapter: CustomAdapter
    private lateinit var gCustomAdapter: CustomAdapter
    private lateinit var hCustomAdapter: CustomAdapter
    private lateinit var iCustomAdapter: CustomAdapter
    private lateinit var jCustomAdapter: CustomAdapter
    private lateinit var kCustomAdapter: CustomAdapter
    private lateinit var lCustomAdapter: CustomAdapter
    private lateinit var mCustomAdapter: CustomAdapter

    private lateinit var nCustomAdapter: CustomAdapter
    private lateinit var oCustomAdapter: CustomAdapter
    private lateinit var pCustomAdapter: CustomAdapter
    private lateinit var qCustomAdapter: CustomAdapter
    private lateinit var rCustomAdapter: CustomAdapter
    private lateinit var sCustomAdapter: CustomAdapter

    private lateinit var aSoundList: MutableList<SoundList>
    private lateinit var bSoundList: MutableList<SoundList>
    private lateinit var cSoundList: MutableList<SoundList>
    private lateinit var dSoundList: MutableList<SoundList>
    private lateinit var eSoundList: MutableList<SoundList>
    private lateinit var fSoundList: MutableList<SoundList>
    private lateinit var gSoundList: MutableList<SoundList>
    private lateinit var hSoundList: MutableList<SoundList>
    private lateinit var iSoundList: MutableList<SoundList>
    private lateinit var jSoundList: MutableList<SoundList>
    private lateinit var kSoundList: MutableList<SoundList>
    private lateinit var lSoundList: MutableList<SoundList>
    private lateinit var mSoundList: MutableList<SoundList>

    private lateinit var nSoundList: MutableList<SoundList>
    private lateinit var oSoundList: MutableList<SoundList>
    private lateinit var pSoundList: MutableList<SoundList>
    private lateinit var qSoundList: MutableList<SoundList>
    private lateinit var rSoundList: MutableList<SoundList>

    private lateinit var sSoundList: MutableList<SoundList>

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
    private var sound16 = 0


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        aSoundList = arrayListOf(
                SoundList("e808_bd_long_01.ogg"),
                SoundList("e808_bd_long_02.ogg"),
                SoundList("e808_bd_long_03.ogg"),
                SoundList("e808_bd_long_04.ogg"),
                SoundList("e808_bd_long_05.ogg"),
                SoundList("e808_bd_long_06.ogg"),
                SoundList("e808_bd_long_07.ogg"),
                SoundList("e808_bd_long_08.ogg"),
                SoundList("e808_bd_long_09.ogg"),
                SoundList("e808_bd_long_10.ogg"),
                SoundList("e808_bd_long_11.ogg"),
                SoundList("e808_bd_long_12.ogg"),
                SoundList("e808_bd_long_13.ogg"),
                SoundList("e808_bd_long_14.ogg"),
                SoundList("e808_bd_long_15.ogg"),
                SoundList("e808_bd_long_16.ogg"),
                SoundList("e808_bd_long_17.ogg"),
                SoundList("e808_bd_short_01.ogg"),
                SoundList("e808_bd_short_02.ogg"),
                SoundList("e808_bd_short_03.ogg"),
                SoundList("e808_bd_short_04.ogg"),
                SoundList("e808_bd_short_05.ogg"),
                SoundList("e808_bd_short_06.ogg"),
                SoundList("e808_bd_short_07.ogg"),
                SoundList("e808_bd_short_08.ogg"),
                SoundList("e808_bd_short_09.ogg"),
                SoundList("e808_bd_short_10.ogg"),
                SoundList("e808_bd_short_11.ogg"),
                SoundList("e808_bd_short_12.ogg")
                )

        bSoundList = arrayListOf(
                SoundList("e808_cp01.ogg"),
                SoundList("e808_cp02.ogg"),
                SoundList("e808_cp03.ogg"),
                SoundList("e808_cp04.ogg"),
                SoundList("e808_cp05.ogg"),
                SoundList("e808_cp06.ogg"),
                SoundList("e808_cp07.ogg"),
                SoundList("e808_cp08.ogg"),
                SoundList("e808_cp09.ogg"),
                SoundList("e808_cp10.ogg"),
                SoundList("e808_cp11.ogg"),
                SoundList("e808_cp12.ogg"),
                SoundList("e808_cp13.ogg"),
                SoundList("e808_cp14.ogg"),
                SoundList("e808_cp15.ogg")
        )
        cSoundList = arrayListOf(
                SoundList("e808_cl01.ogg"),
                SoundList("e808_cl02.ogg"),
                SoundList("e808_cl03.ogg"),
                SoundList("e808_cl04.ogg"),
                SoundList("e808_cl05.ogg"),
                SoundList("e808_cl06.ogg"),
                SoundList("e808_cl07.ogg"),
                SoundList("e808_cl08.ogg"),
                SoundList("e808_cl09.ogg"),
                SoundList("e808_cl10.ogg"),
                SoundList("e808_cl11.ogg")
        )
        dSoundList = arrayListOf(
                SoundList("e808_ch01.ogg"),
                SoundList("e808_ch02.ogg"),
                SoundList("e808_ch03.ogg"),
                SoundList("e808_ch04.ogg"),
                SoundList("e808_ch05.ogg"),
                SoundList("e808_ch06.ogg"),
                SoundList("e808_ch07.ogg"),
                SoundList("e808_ch08.ogg"),
                SoundList("e808_ch09.ogg"),
                SoundList("e808_ch10.ogg"),
                SoundList("e808_ch11.ogg"),
                SoundList("e808_ch12.ogg")
        )
        eSoundList = arrayListOf(
                SoundList("e808_hc01.ogg"),
                SoundList("e808_hc02.ogg"),
                SoundList("e808_hc03.ogg"),
                SoundList("e808_hc04.ogg"),
                SoundList("e808_hc05.ogg"),
                SoundList("e808_hc06.ogg"),
                SoundList("e808_hc07.ogg"),
                SoundList("e808_hc08.ogg"),
                SoundList("e808_hc09.ogg"),
                SoundList("e808_hc10.ogg"),
                SoundList("e808_mc01.ogg"),
                SoundList("e808_mc02.ogg"),
                SoundList("e808_mc03.ogg"),
                SoundList("e808_mc04.ogg"),
                SoundList("e808_mc05.ogg"),
                SoundList("e808_mc06.ogg"),
                SoundList("e808_mc07.ogg"),
                SoundList("e808_mc08.ogg"),
                SoundList("e808_mc09.ogg"),
                SoundList("e808_mc10.ogg"),
                SoundList("e808_lc01.ogg"),
                SoundList("e808_lc02.ogg"),
                SoundList("e808_lc03.ogg"),
                SoundList("e808_lc04.ogg"),
                SoundList("e808_lc05.ogg"),
                SoundList("e808_lc06.ogg"),
                SoundList("e808_lc07.ogg"),
                SoundList("e808_lc08.ogg"),
                SoundList("e808_lc09.ogg"),
                SoundList("e808_lc10.ogg")
        )
        fSoundList = arrayListOf(
                SoundList("e808_cb01.ogg"),
                SoundList("e808_cb02.ogg"),
                SoundList("e808_cb03.ogg"),
                SoundList("e808_cb04.ogg"),
                SoundList("e808_cb05.ogg"),
                SoundList("e808_cb06.ogg"),
                SoundList("e808_cb07.ogg"),
                SoundList("e808_cb08.ogg"),
                SoundList("e808_cb09.ogg"),
                SoundList("e808_cb10.ogg"),
                SoundList("e808_cb11.ogg"),
                SoundList("e808_cb12.ogg"),
                SoundList("e808_cb13.ogg"),
                SoundList("e808_cb14.ogg"),
                SoundList("e808_cb15.ogg")
        )
        gSoundList = arrayListOf(
                SoundList("e808_cy01.ogg"),
                SoundList("e808_cy02.ogg"),
                SoundList("e808_cy03.ogg"),
                SoundList("e808_cy04.ogg"),
                SoundList("e808_cy05.ogg"),
                SoundList("e808_cy06.ogg"),
                SoundList("e808_cy07.ogg"),
                SoundList("e808_cy08.ogg"),
                SoundList("e808_cy09.ogg"),
                SoundList("e808_cy10.ogg"),
                SoundList("e808_cy11.ogg"),
                SoundList("e808_cy12.ogg"),
                SoundList("e808_cy13.ogg"),
                SoundList("e808_cy14.ogg"),
                SoundList("e808_cy15.ogg"),
                SoundList("e808_cy16.ogg")
        )
        hSoundList = arrayListOf(
                SoundList("e808_ma01.ogg"),
                SoundList("e808_ma02.ogg"),
                SoundList("e808_ma03.ogg"),
                SoundList("e808_ma04.ogg"),
                SoundList("e808_ma05.ogg"),
                SoundList("e808_ma06.ogg"),
                SoundList("e808_ma07.ogg"),
                SoundList("e808_ma08.ogg"),
                SoundList("e808_ma09.ogg"),
                SoundList("e808_ma10.ogg"),
                SoundList("e808_ma11.ogg")
        )
        iSoundList = arrayListOf(
                SoundList("e808_oh01.ogg"),
                SoundList("e808_oh02.ogg"),
                SoundList("e808_oh03.ogg"),
                SoundList("e808_oh04.ogg"),
                SoundList("e808_oh05.ogg"),
                SoundList("e808_oh06.ogg"),
                SoundList("e808_oh07.ogg"),
                SoundList("e808_oh08.ogg"),
                SoundList("e808_oh09.ogg"),
                SoundList("e808_oh10.ogg"),
                SoundList("e808_oh11.ogg"),
                SoundList("e808_oh12.ogg"),
                SoundList("e808_oh13.ogg")
        )
        jSoundList = arrayListOf(
                SoundList("e808_rs01.ogg"),
                SoundList("e808_rs02.ogg"),
                SoundList("e808_rs03.ogg"),
                SoundList("e808_rs04.ogg"),
                SoundList("e808_rs05.ogg"),
                SoundList("e808_rs06.ogg"),
                SoundList("e808_rs07.ogg"),
                SoundList("e808_rs08.ogg"),
                SoundList("e808_rs09.ogg"),
                SoundList("e808_rs10.ogg"),
                SoundList("e808_rs11.ogg")
        )
        kSoundList = arrayListOf(
                SoundList("e808_sd01.ogg"),
                SoundList("e808_sd02.ogg"),
                SoundList("e808_sd03.ogg"),
                SoundList("e808_sd04.ogg"),
                SoundList("e808_sd05.ogg"),
                SoundList("e808_sd06.ogg"),
                SoundList("e808_sd07.ogg"),
                SoundList("e808_sd08.ogg"),
                SoundList("e808_sd09.ogg"),
                SoundList("e808_sd10.ogg"),
                SoundList("e808_sd11.ogg"),
                SoundList("e808_sd12.ogg"),
                SoundList("e808_sd13.ogg"),
                SoundList("e808_sd14.ogg"),
                SoundList("e808_sd15.ogg"),
                SoundList("e808_sd16.ogg"),
                SoundList("e808_sd17.ogg"),
                SoundList("e808_sd18.ogg"),
                SoundList("e808_sd19.ogg"),
                SoundList("e808_sd20.ogg")
        )
        lSoundList = arrayListOf(
                SoundList("e808_lt01.ogg"),
                SoundList("e808_lt02.ogg"),
                SoundList("e808_lt03.ogg"),
                SoundList("e808_lt04.ogg"),
                SoundList("e808_lt05.ogg"),
                SoundList("e808_lt06.ogg"),
                SoundList("e808_lt07.ogg"),
                SoundList("e808_lt08.ogg"),
                SoundList("e808_lt09.ogg"),
                SoundList("e808_lt10.ogg"),
                SoundList("e808_lt11.ogg"),
                SoundList("e808_lt12.ogg"),
                SoundList("e808_mt01.ogg"),
                SoundList("e808_mt02.ogg"),
                SoundList("e808_mt03.ogg"),
                SoundList("e808_mt04.ogg"),
                SoundList("e808_mt05.ogg"),
                SoundList("e808_mt06.ogg"),
                SoundList("e808_mt07.ogg"),
                SoundList("e808_mt08.ogg"),
                SoundList("e808_mt09.ogg"),
                SoundList("e808_mt10.ogg"),
                SoundList("e808_mt11.ogg"),
                SoundList("e808_mt12.ogg"),
                SoundList("e808_ht01.ogg"),
                SoundList("e808_ht02.ogg"),
                SoundList("e808_ht03.ogg"),
                SoundList("e808_ht04.ogg"),
                SoundList("e808_ht05.ogg"),
                SoundList("e808_ht06.ogg"),
                SoundList("e808_ht07.ogg"),
                SoundList("e808_ht08.ogg"),
                SoundList("e808_ht09.ogg"),
                SoundList("e808_ht10.ogg"),
                SoundList("e808_ht11.ogg"),
                SoundList("e808_ht12.ogg")
        )
        mSoundList = arrayListOf(
                SoundList("e808_loop_bd_8501.ogg"),
                SoundList("e808_loop_bd_8502.ogg"),
                SoundList("e808_loop_bd_8503.ogg"),
                SoundList("e808_loop_bd_8504.ogg"),
                SoundList("e808_loop_bd_8505.ogg"),
                SoundList("e808_loop_bd_8506.ogg"),
                SoundList("e808_loop_bd_8507.ogg"),
                SoundList("e808_loop_bd_8508.ogg"),
                SoundList("e808_loop_bd_9501.ogg"),
                SoundList("e808_loop_bd_9502.ogg"),
                SoundList("e808_loop_bd_9503.ogg"),
                SoundList("e808_loop_bd_9504.ogg"),
                SoundList("e808_loop_bd_9505.ogg"),
                SoundList("e808_loop_bd_9506.ogg"),
                SoundList("e808_loop_bd_9507.ogg"),
                SoundList("e808_loop_bd_9508.ogg"),
                SoundList("e808_loop_bd_10501.ogg"),
                SoundList("e808_loop_bd_10502.ogg"),
                SoundList("e808_loop_bd_10503.ogg"),
                SoundList("e808_loop_bd_10504.ogg"),
                SoundList("e808_loop_bd_10505.ogg"),
                SoundList("e808_loop_bd_10506.ogg"),
                SoundList("e808_loop_bd_10507.ogg"),
                SoundList("e808_loop_bd_10508.ogg"),
                SoundList("e808_loop_bd_12401.ogg"),
                SoundList("e808_loop_bd_12402.ogg"),
                SoundList("e808_loop_bd_12403.ogg"),
                SoundList("e808_loop_bd_12404.ogg"),
                SoundList("e808_loop_bd_12405.ogg"),
                SoundList("e808_loop_bd_12406.ogg"),
                SoundList("e808_loop_bd_12407.ogg"),
                SoundList("e808_loop_bd_12408.ogg"),
                SoundList("e808_loop_bd_13201.ogg"),
                SoundList("e808_loop_bd_13202.ogg"),
                SoundList("e808_loop_bd_13203.ogg"),
                SoundList("e808_loop_bd_13204.ogg"),
                SoundList("e808_loop_bd_13205.ogg"),
                SoundList("e808_loop_bd_13206.ogg"),
                SoundList("e808_loop_bd_13207.ogg"),
                SoundList("e808_loop_bd_13208.ogg"),
                SoundList("e808_loop_hats_8501.ogg"),
                SoundList("e808_loop_hats_8502.ogg"),
                SoundList("e808_loop_hats_8503.ogg"),
                SoundList("e808_loop_hats_8504.ogg"),
                SoundList("e808_loop_hats_8505.ogg"),
                SoundList("e808_loop_hats_8506.ogg"),
                SoundList("e808_loop_hats_8507.ogg"),
                SoundList("e808_loop_hats_8508.ogg"),
                SoundList("e808_loop_hats_9501.ogg"),
                SoundList("e808_loop_hats_9502.ogg"),
                SoundList("e808_loop_hats_9503.ogg"),
                SoundList("e808_loop_hats_9504.ogg"),
                SoundList("e808_loop_hats_9505.ogg"),
                SoundList("e808_loop_hats_9506.ogg"),
                SoundList("e808_loop_hats_9507.ogg"),
                SoundList("e808_loop_hats_9508.ogg"),
                SoundList("e808_loop_hats_10501.ogg"),
                SoundList("e808_loop_hats_10502.ogg"),
                SoundList("e808_loop_hats_10503.ogg"),
                SoundList("e808_loop_hats_10504.ogg"),
                SoundList("e808_loop_hats_10505.ogg"),
                SoundList("e808_loop_hats_10506.ogg"),
                SoundList("e808_loop_hats_10507.ogg"),
                SoundList("e808_loop_hats_10508.ogg"),
                SoundList("e808_loop_hats_12401.ogg"),
                SoundList("e808_loop_hats_12402.ogg"),
                SoundList("e808_loop_hats_12403.ogg"),
                SoundList("e808_loop_hats_12404.ogg"),
                SoundList("e808_loop_hats_12405.ogg"),
                SoundList("e808_loop_hats_12406.ogg"),
                SoundList("e808_loop_hats_12407.ogg"),
                SoundList("e808_loop_hats_12408.ogg"),
                SoundList("e808_loop_hats_13201.ogg"),
                SoundList("e808_loop_hats_13202.ogg"),
                SoundList("e808_loop_hats_13203.ogg"),
                SoundList("e808_loop_hats_13204.ogg"),
                SoundList("e808_loop_hats_13205.ogg"),
                SoundList("e808_loop_hats_13206.ogg"),
                SoundList("e808_loop_hats_13207.ogg"),
                SoundList("e808_loop_hats_13208.ogg"),
                SoundList("e808_loop_perc_8501.ogg"),
                SoundList("e808_loop_perc_8502.ogg"),
                SoundList("e808_loop_perc_8503.ogg"),
                SoundList("e808_loop_perc_8504.ogg"),
                SoundList("e808_loop_perc_9501.ogg"),
                SoundList("e808_loop_perc_9502.ogg"),
                SoundList("e808_loop_perc_9503.ogg"),
                SoundList("e808_loop_perc_9504.ogg"),
                SoundList("e808_loop_perc_10501.ogg"),
                SoundList("e808_loop_perc_10502.ogg"),
                SoundList("e808_loop_perc_10503.ogg"),
                SoundList("e808_loop_perc_10504.ogg"),
                SoundList("e808_loop_perc_12401.ogg"),
                SoundList("e808_loop_perc_12402.ogg"),
                SoundList("e808_loop_perc_12403.ogg"),
                SoundList("e808_loop_perc_12404.ogg"),
                SoundList("e808_loop_perc_13201.ogg"),
                SoundList("e808_loop_perc_13202.ogg"),
                SoundList("e808_loop_perc_13203.ogg"),
                SoundList("e808_loop_perc_13204.ogg"),
                SoundList("e808_loop_sd_8501.ogg"),
                SoundList("e808_loop_sd_8502.ogg"),
                SoundList("e808_loop_sd_8503.ogg"),
                SoundList("e808_loop_sd_8504.ogg"),
                SoundList("e808_loop_sd_8505.ogg"),
                SoundList("e808_loop_sd_8506.ogg"),
                SoundList("e808_loop_sd_8507.ogg"),
                SoundList("e808_loop_sd_8508.ogg"),
                SoundList("e808_loop_sd_9501.ogg"),
                SoundList("e808_loop_sd_9502.ogg"),
                SoundList("e808_loop_sd_9503.ogg"),
                SoundList("e808_loop_sd_9504.ogg"),
                SoundList("e808_loop_sd_9505.ogg"),
                SoundList("e808_loop_sd_9506.ogg"),
                SoundList("e808_loop_sd_9507.ogg"),
                SoundList("e808_loop_sd_9508.ogg"),
                SoundList("e808_loop_sd_10501.ogg"),
                SoundList("e808_loop_sd_10502.ogg"),
                SoundList("e808_loop_sd_10503.ogg"),
                SoundList("e808_loop_sd_10504.ogg"),
                SoundList("e808_loop_sd_10505.ogg"),
                SoundList("e808_loop_sd_10506.ogg"),
                SoundList("e808_loop_sd_10507.ogg"),
                SoundList("e808_loop_sd_10508.ogg"),
                SoundList("e808_loop_sd_12401.ogg"),
                SoundList("e808_loop_sd_12402.ogg"),
                SoundList("e808_loop_sd_12403.ogg"),
                SoundList("e808_loop_sd_12404.ogg"),
                SoundList("e808_loop_sd_12405.ogg"),
                SoundList("e808_loop_sd_12406.ogg"),
                SoundList("e808_loop_sd_12407.ogg"),
                SoundList("e808_loop_sd_13201.ogg"),
                SoundList("e808_loop_sd_13202.ogg"),
                SoundList("e808_loop_sd_13203.ogg"),
                SoundList("e808_loop_sd_13204.ogg"),
                SoundList("e808_loop_sd_13205.ogg"),
                SoundList("e808_loop_sd_13206.ogg"),
                SoundList("e808_loop_sd_13207.ogg"),
                SoundList("e808_loop_sd_13208.ogg"),
                SoundList("e808_loop_toms_8501.ogg"),
                SoundList("e808_loop_toms_8502.ogg"),
                SoundList("e808_loop_toms_8503.ogg"),
                SoundList("e808_loop_toms_8504.ogg"),
                SoundList("e808_loop_toms_9501.ogg"),
                SoundList("e808_loop_toms_9502.ogg"),
                SoundList("e808_loop_toms_9503.ogg"),
                SoundList("e808_loop_toms_9504.ogg"),
                SoundList("e808_loop_toms_10501.ogg"),
                SoundList("e808_loop_toms_10502.ogg"),
                SoundList("e808_loop_toms_10503.ogg"),
                SoundList("e808_loop_toms_10504.ogg"),
                SoundList("e808_loop_toms_12401.ogg"),
                SoundList("e808_loop_toms_12402.ogg"),
                SoundList("e808_loop_toms_12403.ogg"),
                SoundList("e808_loop_toms_12404.ogg"),
                SoundList("e808_loop_toms_13201.ogg"),
                SoundList("e808_loop_toms_13202.ogg"),
                SoundList("e808_loop_toms_13203.ogg"),
                SoundList("e808_loop_toms_13204.ogg")
        )
        nSoundList = arrayListOf(
                SoundList("e808_loop_bd_8501.ogg"),
                SoundList("e808_loop_bd_8502.ogg"),
                SoundList("e808_loop_bd_8503.ogg"),
                SoundList("e808_loop_bd_8504.ogg"),
                SoundList("e808_loop_bd_8505.ogg"),
                SoundList("e808_loop_bd_8506.ogg"),
                SoundList("e808_loop_bd_8507.ogg"),
                SoundList("e808_loop_bd_8508.ogg"),
                SoundList("e808_loop_hats_8501.ogg"),
                SoundList("e808_loop_hats_8502.ogg"),
                SoundList("e808_loop_hats_8503.ogg"),
                SoundList("e808_loop_hats_8504.ogg"),
                SoundList("e808_loop_hats_8505.ogg"),
                SoundList("e808_loop_hats_8506.ogg"),
                SoundList("e808_loop_hats_8507.ogg"),
                SoundList("e808_loop_hats_8508.ogg"),
                SoundList("e808_loop_perc_8501.ogg"),
                SoundList("e808_loop_perc_8502.ogg"),
                SoundList("e808_loop_perc_8503.ogg"),
                SoundList("e808_loop_perc_8504.ogg"),
                SoundList("e808_loop_sd_8501.ogg"),
                SoundList("e808_loop_sd_8502.ogg"),
                SoundList("e808_loop_sd_8503.ogg"),
                SoundList("e808_loop_sd_8504.ogg"),
                SoundList("e808_loop_sd_8505.ogg"),
                SoundList("e808_loop_sd_8506.ogg"),
                SoundList("e808_loop_sd_8507.ogg"),
                SoundList("e808_loop_sd_8508.ogg"),
                SoundList("e808_loop_toms_8501.ogg"),
                SoundList("e808_loop_toms_8502.ogg"),
                SoundList("e808_loop_toms_8503.ogg"),
                SoundList("e808_loop_toms_8504.ogg")
        )
        oSoundList = arrayListOf(
                SoundList("e808_loop_bd_9501.ogg"),
                SoundList("e808_loop_bd_9502.ogg"),
                SoundList("e808_loop_bd_9503.ogg"),
                SoundList("e808_loop_bd_9504.ogg"),
                SoundList("e808_loop_bd_9505.ogg"),
                SoundList("e808_loop_bd_9506.ogg"),
                SoundList("e808_loop_bd_9507.ogg"),
                SoundList("e808_loop_bd_9508.ogg"),
                SoundList("e808_loop_hats_9501.ogg"),
                SoundList("e808_loop_hats_9502.ogg"),
                SoundList("e808_loop_hats_9503.ogg"),
                SoundList("e808_loop_hats_9504.ogg"),
                SoundList("e808_loop_hats_9505.ogg"),
                SoundList("e808_loop_hats_9506.ogg"),
                SoundList("e808_loop_hats_9507.ogg"),
                SoundList("e808_loop_hats_9508.ogg"),
                SoundList("e808_loop_perc_9501.ogg"),
                SoundList("e808_loop_perc_9502.ogg"),
                SoundList("e808_loop_perc_9503.ogg"),
                SoundList("e808_loop_perc_9504.ogg"),
                SoundList("e808_loop_sd_9501.ogg"),
                SoundList("e808_loop_sd_9502.ogg"),
                SoundList("e808_loop_sd_9503.ogg"),
                SoundList("e808_loop_sd_9504.ogg"),
                SoundList("e808_loop_sd_9505.ogg"),
                SoundList("e808_loop_sd_9506.ogg"),
                SoundList("e808_loop_sd_9507.ogg"),
                SoundList("e808_loop_sd_9508.ogg"),
                SoundList("e808_loop_toms_9501.ogg"),
                SoundList("e808_loop_toms_9502.ogg"),
                SoundList("e808_loop_toms_9503.ogg"),
                SoundList("e808_loop_toms_9504.ogg")
        )
        pSoundList = arrayListOf(
                SoundList("e808_loop_bd_10501.ogg"),
                SoundList("e808_loop_bd_10502.ogg"),
                SoundList("e808_loop_bd_10503.ogg"),
                SoundList("e808_loop_bd_10504.ogg"),
                SoundList("e808_loop_bd_10505.ogg"),
                SoundList("e808_loop_bd_10506.ogg"),
                SoundList("e808_loop_bd_10507.ogg"),
                SoundList("e808_loop_bd_10508.ogg"),
                SoundList("e808_loop_hats_10501.ogg"),
                SoundList("e808_loop_hats_10502.ogg"),
                SoundList("e808_loop_hats_10503.ogg"),
                SoundList("e808_loop_hats_10504.ogg"),
                SoundList("e808_loop_hats_10505.ogg"),
                SoundList("e808_loop_hats_10506.ogg"),
                SoundList("e808_loop_hats_10507.ogg"),
                SoundList("e808_loop_hats_10508.ogg"),
                SoundList("e808_loop_perc_10501.ogg"),
                SoundList("e808_loop_perc_10502.ogg"),
                SoundList("e808_loop_perc_10503.ogg"),
                SoundList("e808_loop_perc_10504.ogg"),
                SoundList("e808_loop_sd_10501.ogg"),
                SoundList("e808_loop_sd_10502.ogg"),
                SoundList("e808_loop_sd_10503.ogg"),
                SoundList("e808_loop_sd_10504.ogg"),
                SoundList("e808_loop_sd_10505.ogg"),
                SoundList("e808_loop_sd_10506.ogg"),
                SoundList("e808_loop_sd_10507.ogg"),
                SoundList("e808_loop_sd_10508.ogg"),
                SoundList("e808_loop_toms_10501.ogg"),
                SoundList("e808_loop_toms_10502.ogg"),
                SoundList("e808_loop_toms_10503.ogg"),
                SoundList("e808_loop_toms_10504.ogg")
        )
        qSoundList = arrayListOf(
                SoundList("e808_loop_bd_12401.ogg"),
                SoundList("e808_loop_bd_12402.ogg"),
                SoundList("e808_loop_bd_12403.ogg"),
                SoundList("e808_loop_bd_12404.ogg"),
                SoundList("e808_loop_bd_12405.ogg"),
                SoundList("e808_loop_bd_12406.ogg"),
                SoundList("e808_loop_bd_12407.ogg"),
                SoundList("e808_loop_bd_12408.ogg"),
                SoundList("e808_loop_hats_12401.ogg"),
                SoundList("e808_loop_hats_12402.ogg"),
                SoundList("e808_loop_hats_12403.ogg"),
                SoundList("e808_loop_hats_12404.ogg"),
                SoundList("e808_loop_hats_12405.ogg"),
                SoundList("e808_loop_hats_12406.ogg"),
                SoundList("e808_loop_hats_12407.ogg"),
                SoundList("e808_loop_hats_12408.ogg"),
                SoundList("e808_loop_perc_12401.ogg"),
                SoundList("e808_loop_perc_12402.ogg"),
                SoundList("e808_loop_perc_12403.ogg"),
                SoundList("e808_loop_perc_12404.ogg"),
                SoundList("e808_loop_sd_12401.ogg"),
                SoundList("e808_loop_sd_12402.ogg"),
                SoundList("e808_loop_sd_12403.ogg"),
                SoundList("e808_loop_sd_12404.ogg"),
                SoundList("e808_loop_sd_12405.ogg"),
                SoundList("e808_loop_sd_12406.ogg"),
                SoundList("e808_loop_sd_12407.ogg"),
                SoundList("e808_loop_toms_12401.ogg"),
                SoundList("e808_loop_toms_12402.ogg"),
                SoundList("e808_loop_toms_12403.ogg"),
                SoundList("e808_loop_toms_12404.ogg")
        )
        rSoundList = arrayListOf(
                SoundList("e808_loop_bd_13201.ogg"),
                SoundList("e808_loop_bd_13202.ogg"),
                SoundList("e808_loop_bd_13203.ogg"),
                SoundList("e808_loop_bd_13204.ogg"),
                SoundList("e808_loop_bd_13205.ogg"),
                SoundList("e808_loop_bd_13206.ogg"),
                SoundList("e808_loop_bd_13207.ogg"),
                SoundList("e808_loop_bd_13208.ogg"),
                SoundList("e808_loop_hats_13201.ogg"),
                SoundList("e808_loop_hats_13202.ogg"),
                SoundList("e808_loop_hats_13203.ogg"),
                SoundList("e808_loop_hats_13204.ogg"),
                SoundList("e808_loop_hats_13205.ogg"),
                SoundList("e808_loop_hats_13206.ogg"),
                SoundList("e808_loop_hats_13207.ogg"),
                SoundList("e808_loop_hats_13208.ogg"),
                SoundList("e808_loop_perc_13201.ogg"),
                SoundList("e808_loop_perc_13202.ogg"),
                SoundList("e808_loop_perc_13203.ogg"),
                SoundList("e808_loop_perc_13204.ogg"),
                SoundList("e808_loop_sd_13201.ogg"),
                SoundList("e808_loop_sd_13202.ogg"),
                SoundList("e808_loop_sd_13203.ogg"),
                SoundList("e808_loop_sd_13204.ogg"),
                SoundList("e808_loop_sd_13205.ogg"),
                SoundList("e808_loop_sd_13206.ogg"),
                SoundList("e808_loop_sd_13207.ogg"),
                SoundList("e808_loop_sd_13208.ogg"),
                SoundList("e808_loop_toms_13201.ogg"),
                SoundList("e808_loop_toms_13202.ogg"),
                SoundList("e808_loop_toms_13203.ogg"),
                SoundList("e808_loop_toms_13204.ogg")
        )
        sSoundList = arrayListOf()

        val listView = findViewById<ListView>(R.id.list_view)
        // MainActivity自身をListenerとして渡す
        aCustomAdapter = CustomAdapter(this, aSoundList, this)
        bCustomAdapter = CustomAdapter(this, bSoundList, this)
        cCustomAdapter = CustomAdapter(this, cSoundList, this)
        dCustomAdapter = CustomAdapter(this, dSoundList, this)
        eCustomAdapter = CustomAdapter(this, eSoundList, this)
        fCustomAdapter = CustomAdapter(this, fSoundList, this)
        gCustomAdapter = CustomAdapter(this, gSoundList, this)
        hCustomAdapter = CustomAdapter(this, hSoundList, this)
        iCustomAdapter = CustomAdapter(this, iSoundList, this)
        jCustomAdapter = CustomAdapter(this, jSoundList, this)
        kCustomAdapter = CustomAdapter(this, kSoundList, this)
        lCustomAdapter = CustomAdapter(this, lSoundList, this)
        mCustomAdapter = CustomAdapter(this, mSoundList, this)
        nCustomAdapter = CustomAdapter(this, nSoundList, this)
        oCustomAdapter = CustomAdapter(this, oSoundList, this)
        pCustomAdapter = CustomAdapter(this, pSoundList, this)
        qCustomAdapter = CustomAdapter(this, qSoundList, this)
        rCustomAdapter = CustomAdapter(this, rSoundList, this)
        sCustomAdapter = CustomAdapter(this, sSoundList, this)

        listView.adapter = aCustomAdapter

        mp = MediaPlayer()

        supportActionBar?.title ="e808_loop_bd_8501"


            val audioUri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI
            val cursor = contentResolver.query(audioUri, null, null, null, null)
            cursor!!.moveToFirst()
            val path: Array<String?> = arrayOfNulls(cursor.count)
            for (i in path.indices) {
                path[i] = cursor.getString(cursor.getColumnIndex("_data"))
                (sSoundList as ArrayList<SoundList>).add(
                        SoundList(path[i].toString())
                )
                cursor.moveToNext()
            }

            cursor.close()


        val meSpinner = findViewById<Spinner>(R.id.menu_spinner)

        val adapter3 = ArrayAdapter.createFromResource(this, R.array.spinnerItems, android.R.layout.simple_spinner_item)

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

                val soundListView = findViewById<ListView>(R.id.list_view)

                when(position){
                    0 -> {
                        radioButton19.performClick()
                        soundListView.adapter = aCustomAdapter
                        aCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    1 -> {
                        radioButton19.performClick()
                        soundListView.adapter = bCustomAdapter
                        bCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    2 -> {
                        radioButton19.performClick()
                        soundListView.adapter = cCustomAdapter
                        cCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    3 -> {
                        radioButton19.performClick()
                        soundListView.adapter = dCustomAdapter
                        dCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    4 -> {
                        radioButton19.performClick()
                        soundListView.adapter = eCustomAdapter
                        eCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    5 -> {
                        radioButton19.performClick()
                        soundListView.adapter = fCustomAdapter
                        fCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    6 -> {
                        radioButton19.performClick()
                        soundListView.adapter = gCustomAdapter
                        gCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    7 -> {
                        radioButton19.performClick()
                        soundListView.adapter = hCustomAdapter
                        hCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    8 -> {
                        radioButton19.performClick()
                        soundListView.adapter = iCustomAdapter
                        iCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    9 -> {
                        radioButton19.performClick()
                        soundListView.adapter = jCustomAdapter
                        jCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    10 -> {
                        radioButton19.performClick()
                        soundListView.adapter = kCustomAdapter
                        kCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    11 -> {
                        radioButton19.performClick()
                        soundListView.adapter = lCustomAdapter
                        lCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    12 -> {
                        radioButton19.performClick()
                        soundListView.adapter = mCustomAdapter
                        mCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    13 -> {
                        radioButton18.performClick()
                        soundListView.adapter = sCustomAdapter
                        sCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    14 -> selectEX()
                    15 -> selectAudio()
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
                        soundPool.play(sound1, 1.0f, 1.0f, 1, 0, 1.0f)
                }
                false
        }

        imageView2.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound2, 1.0f, 1.0f, 1, 0, 1.0f)
                }
                false
        }

        imageView3.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound3, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView4.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound4, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView5.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound5, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView6.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound6, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView7.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound7, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView8.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound8, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView9.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound9, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false

        }

        imageView10.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound10, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView11.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound11, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView12.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound12, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView13.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound13, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView14.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                        soundPool.play(sound14, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                false
        }

        imageView15.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
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

    override fun clicked(soundList: SoundList) {
        sound16 = if (radioButton18.isChecked) {
            soundPool.load(soundList.name, 1)
        } else {
            soundPool.load(assets.openFd(soundList.name), 1)
        }
            soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f)
            }
    }

    override fun clicked2(soundList: SoundList) {
        when {
            radioButton.isChecked && radioButton18.isChecked -> {
                sound1 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
            }
            radioButton2.isChecked && radioButton18.isChecked -> {
                sound2 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView2.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
            }
            radioButton3.isChecked && radioButton18.isChecked -> {
                sound3 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView3.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
            }
            radioButton4.isChecked && radioButton18.isChecked -> {
                sound4 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView4.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
            }
            radioButton5.isChecked && radioButton18.isChecked -> {
                sound5 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView5.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
            }
            radioButton6.isChecked && radioButton18.isChecked -> {
                sound6 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView6.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
            }
            radioButton7.isChecked && radioButton18.isChecked -> {
                sound7 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView7.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
            }
            radioButton8.isChecked && radioButton18.isChecked -> {
                sound8 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView8.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
            }
            radioButton9.isChecked && radioButton18.isChecked -> {
                sound9 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView9.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
            }
            radioButton10.isChecked && radioButton18.isChecked -> {
                sound10 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView10.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
            }
            radioButton11.isChecked && radioButton18.isChecked -> {
                sound11 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView11.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
            }
            radioButton12.isChecked && radioButton18.isChecked -> {
                sound12 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView12.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
            }
            radioButton13.isChecked && radioButton18.isChecked -> {
                sound13 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView13.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
            }
            radioButton14.isChecked && radioButton18.isChecked -> {
                sound14 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView14.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
            }
            radioButton15.isChecked && radioButton18.isChecked -> {
                sound15 = soundPool.load(soundList.name, 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView15.text = soundList.name.replaceBeforeLast("/", "").replace("/", "")
            }
            radioButton.isChecked && radioButton19.isChecked -> {
                sound1 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView.text = soundList.name
            }
            radioButton2.isChecked && radioButton19.isChecked -> {
                sound2 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView2.text = soundList.name
            }
            radioButton3.isChecked && radioButton19.isChecked -> {
                sound3 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView3.text = soundList.name
            }
            radioButton4.isChecked && radioButton19.isChecked -> {
                sound4 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView4.text = soundList.name
            }
            radioButton5.isChecked && radioButton19.isChecked -> {
                sound5 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView5.text = soundList.name
            }
            radioButton6.isChecked && radioButton19.isChecked -> {
                sound6 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView6.text = soundList.name
            }
            radioButton7.isChecked && radioButton19.isChecked -> {
                sound7 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView7.text = soundList.name
            }
            radioButton8.isChecked && radioButton19.isChecked -> {
                sound8 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView8.text = soundList.name
            }
            radioButton9.isChecked && radioButton19.isChecked -> {
                sound9 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView9.text = soundList.name
            }
            radioButton10.isChecked && radioButton19.isChecked -> {
                sound10 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView10.text = soundList.name
            }
            radioButton11.isChecked && radioButton19.isChecked -> {
                sound11 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView11.text = soundList.name
            }
            radioButton12.isChecked && radioButton19.isChecked -> {
                sound12 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView12.text = soundList.name
            }
            radioButton13.isChecked && radioButton19.isChecked -> {
                sound13 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView13.text = soundList.name
            }
            radioButton14.isChecked && radioButton19.isChecked -> {
                sound14 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView14.text = soundList.name
            }
            radioButton15.isChecked && radioButton19.isChecked -> {
                sound15 = soundPool.load(assets.openFd(soundList.name), 1)
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
                textView15.text = soundList.name
            }
            radioButton16.isChecked -> {
                lmp.release()
                lmp = LoopMediaPlayer(this@MainActivity, Uri.parse("android.resource://" + packageName + "/raw/" + soundList.name.replace(".ogg", "")))
                supportActionBar?.title = soundList.name
                soundPool.setOnLoadCompleteListener{ soundPool, _, _ ->
                    soundPool.stop(soundPool.play(sound16, 1.0f, 1.0f, 0, 0, 1.0f))
                }
            }
        }
        findViewById<ListView>(R.id.list_view).visibility = View.INVISIBLE
    }

    private fun startCapturing() {
        if (!isRecordAudioPermissionGranted()) {
            requestRecordAudioPermission()
        } else {
            startMediaProjectionRequest()
        }
    }

    private fun stopCapturing() {

        startService(Intent(this, AudioCaptureService::class.java).apply {
            action = AudioCaptureService.ACTION_STOP
        })
        menuSwitch0 = true
        switch0.isChecked = false
        invalidateOptionsMenu()
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#5A5A66")))
    }

    private fun isRecordAudioPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestRecordAudioPermission() {
        ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                RECORD_AUDIO_PERMISSION_REQUEST_CODE
        )
    }

    private fun isReadExternalStoragePermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestReadExternalStoragePermission() {
        ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                RECORD_AUDIO_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        if (requestCode == RECORD_AUDIO_PERMISSION_REQUEST_CODE) {
            if (grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                        this,
                        "Permissions to capture audio granted. Click the button once again.",
                        Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                        this, "Permissions to capture audio denied.",
                        Toast.LENGTH_SHORT
                ).show()
            }
        }

        if (requestCode == READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                        this,
                        "Permissions to capture audio granted. Click the button once again.",
                        Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                        this, "Permissions to capture audio denied.",
                        Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    /**
     * Before a capture session can be started, the capturing app must
     * call MediaProjectionManager.createScreenCaptureIntent().
     * This will display a dialog to the user, who must tap "Start now" in order for a
     * capturing session to be started. This will allow both video and audio to be captured.
     */
    private fun startMediaProjectionRequest() {
        // use applicationContext to avoid memory leak on Android 10.
        // see: https://partnerissuetracker.corp.google.com/issues/139732252
        mediaProjectionManager =
                applicationContext.getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        startActivityForResult(
                mediaProjectionManager.createScreenCaptureIntent(),
                MEDIA_PROJECTION_REQUEST_CODE
        )
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (requestCode == MEDIA_PROJECTION_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                menuSwitch0 = false
                switch0.isChecked = true
                invalidateOptionsMenu()
                supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#EC7357")))
                Toast.makeText(
                        this,
                        "MediaProjection permission obtained. Foreground service will be started to capture audio.",
                        Toast.LENGTH_SHORT
                ).show()

                val audioCaptureIntent = Intent(this, AudioCaptureService::class.java).apply {
                    action = AudioCaptureService.ACTION_START
                    putExtra(AudioCaptureService.EXTRA_RESULT_DATA, resultData!!)
                }
                startForegroundService(audioCaptureIntent)
            } else {
                Toast.makeText(
                        this, "Request to obtain MediaProjection denied.",
                        Toast.LENGTH_SHORT
                ).show()
            }
        }
        if (resultCode != RESULT_OK) {
            return
        }
        when (requestCode) {

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
                            radioButton17.isChecked -> {
                                hoge = item
                                button4.performClick()
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
                                radioButton17.isChecked -> {
                                    hoge = item2
                                    button4.performClick()
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
        if (!isReadExternalStoragePermissionGranted()) {
            requestReadExternalStoragePermission()
        } else {

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

    private fun selectCh() {

        val chSpinner = findViewById<Spinner>(R.id.choose_loop_spinner)

        val adapterC = ArrayAdapter.createFromResource(this, R.array.spinnerItems2, android.R.layout.simple_spinner_item)

        adapterC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        chSpinner.adapter = adapterC

        chSpinner.performClick()


        chSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?, position: Int, id: Long
            ) {
                if (!chSpinner.isFocusable) {
                    chSpinner.isFocusable = true
                    return
                }

                val soundListView = findViewById<ListView>(R.id.list_view)

                when(position){
                    0 -> {
                        lmp.stop()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1.isChecked = false
                        radioButton16.performClick()
                        soundListView.adapter = nCustomAdapter
                        nCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    1 -> {
                        lmp.stop()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1.isChecked = false
                        radioButton16.performClick()
                        soundListView.adapter = oCustomAdapter
                        oCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    2 -> {
                        lmp.stop()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1.isChecked = false
                        radioButton16.performClick()
                        soundListView.adapter = pCustomAdapter
                        pCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    3 -> {
                        lmp.stop()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1.isChecked = false
                        radioButton16.performClick()
                        soundListView.adapter = qCustomAdapter
                        qCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    4 -> {
                        lmp.stop()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1.isChecked = false
                        radioButton16.performClick()
                        soundListView.adapter = rCustomAdapter
                        rCustomAdapter.notifyDataSetChanged()
                        soundListView.visibility = View.VISIBLE
                    }
                    5 -> {
                        lmp.stop()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1.isChecked = false
                        select3()
                    }
                    6 -> {
                        lmp.stop()
                        menuSwitch = true
                        invalidateOptionsMenu()
                        switch1.isChecked = false
                        select2()
                    }
                    7 -> {
                        radioButton16.performClick()
                        selectAudio()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        chSpinner.isFocusable = false
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

        val menuLamp3 = menu.findItem(R.id.menu8)
        if (menuSwitch0) {
            menuLamp3.setIcon(R.drawable.ic_baseline_radio_button_checked_24_2)
        } else {
            menuLamp3.setIcon(R.drawable.ic_baseline_radio_button_checked_24)
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
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "録音停止に失敗しました。", Toast.LENGTH_LONG).show()
            }
        }

            button4.setOnClickListener {
                val myDir = this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).toString() + "/showwavespics.png"
                FFmpeg.execute("-i $hoge -filter_complex showwavespic=s=2560x1280:colors=blue:scale=0 -y $myDir")

                val builder = AlertDialog.Builder(this)
                val inflater = layoutInflater
                val dialogView = inflater.inflate(R.layout.custom_dialog, null)

                mp.release()
                mp = MediaPlayer()
                mp.setDataSource(this, Uri.parse(hoge))
                mp.prepare()

                val seekBar = dialogView.findViewById<SeekBar>(R.id.seekBar)
                val seekBar2 = dialogView.findViewById<SeekBar>(R.id.seekBar2)

                seekBar.progress = 0
                seekBar2.progress = 0

                seekBar.max = mp.duration

                seekBar2.max = mp.duration
                seekBar2.progress = mp.duration

                fuga = 0
                fuga2 = mp.duration

                val text1 = dialogView.findViewById<TextView>(R.id.textView16)
                val text2 = dialogView.findViewById<TextView>(R.id.textView17)
                val text3 = dialogView.findViewById<TextView>(R.id.textView18)

                text1.text = SimpleDateFormat("mm:ss.SSS").format(Date(0.toLong())).toString()
                text2.text = SimpleDateFormat("mm:ss.SSS").format(Date(mp.duration.toLong())).toString()
                text3.text = hoge.replaceBeforeLast("/", "").replace("/", "")

                    seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {


                        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                            text1.text = SimpleDateFormat("mm:ss.SSS").format(Date(progress.toLong()))
                                fuga = progress

                        }


                        override fun onStartTrackingTouch(seekBar: SeekBar?) {

                        }


                        override fun onStopTrackingTouch(seekBar: SeekBar?) {

                        }
                    })

                    seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {


                        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                            text2.text = SimpleDateFormat("mm:ss.SSS").format(Date(progress.toLong()))
                                fuga2 = progress
                        }


                        override fun onStartTrackingTouch(seekBar: SeekBar?) {

                        }


                        override fun onStopTrackingTouch(seekBar: SeekBar?) {

                        }
                    })


                    val image = dialogView.findViewById<View>(R.id.imageView16) as ImageView

                    image.setImageURI(Uri.parse(myDir))

                    val button = dialogView.findViewById(R.id.button) as Button
                    val button2 = dialogView.findViewById(R.id.button2) as Button
                    val button3 = dialogView.findViewById(R.id.button3) as Button

                    button.setOnClickListener {
                            when {
                                    fuga < fuga2 -> {
                                            val builder2 = AlertDialog.Builder(this)
                                            val inflater2 = layoutInflater
                                            val dialogView2 = inflater2.inflate(R.layout.file_name, null)
                                            builder2.setView(dialogView2)
                                                    .setTitle("ファイル名を入力して下さい")
                                                    .setPositiveButton("保存") { _, _ ->
                                                            val nt = dialogView2.findViewById<EditText>(R.id.filename)
                                                            val fnt = this.getExternalFilesDir(Environment.DIRECTORY_MUSIC).toString() + "/" + nt.text.replace("/".toRegex(), "") + hoge.replaceBeforeLast(".", "")
                                                            try {
                                                                FFmpeg.execute("-ss ${text1.text} -to ${text2.text} -i $hoge -y $fnt")
                                                                button3.performClick()
                                                                Toast.makeText(applicationContext, "保存しました", Toast.LENGTH_SHORT).show()
                                                            } catch (e: Exception) {
                                                                Toast.makeText(applicationContext, "保存できませんでした", Toast.LENGTH_LONG).show()
                                                            }
                                                    }
                                                    .setNegativeButton("キャンセル") { _, _ ->

                                                    }
                                                    .show()

                                    }
                                    fuga > fuga2 -> Toast.makeText(applicationContext, "時間を設定し直してください", Toast.LENGTH_SHORT).show()
                                    fuga == fuga2 -> Toast.makeText(applicationContext, "時間を設定し直してください", Toast.LENGTH_SHORT).show()
                            }
                    }

                    button2.setOnClickListener {
                        if (switch3.isChecked) {
                            mp.stop()
                            mp.prepare()
                            handler.removeCallbacksAndMessages(null)
                            switch3.isChecked = false
                        } else {
                            mp.seekTo(fuga)
                            mp.start()
                            switch3.isChecked = true
                            if (mp.isPlaying)
                                handler.postDelayed({
                                    mp.stop()
                                    mp.prepare()
                                    switch3.isChecked = false
                                }, (fuga2 - fuga).toLong())
                        }
                    }



                    builder.setView(dialogView)
                            .setOnCancelListener {
                                mp.stop()
                                mp.prepare()
                                switch3.isChecked = false
                            }
                            val dialog = builder.create()
                            dialog.show()

                button3.setOnClickListener {
                    dialog.cancel()
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

            R.id.menu1a -> {
                radioButton17.performClick()
                selectAudio()
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

            R.id.menu8 -> {
                when {

                    Build.VERSION.SDK_INT < Build.VERSION_CODES.Q -> Toast.makeText(applicationContext, "この機能はAndroid10以上の機種で使えます", Toast.LENGTH_LONG).show()

                    switch0.isChecked -> stopCapturing()

                    else -> startCapturing()

                }

                return true
            }

            R.id.menu9 -> {
                radioButton17.performClick()
                selectEX()
                return true
            }

            R.id.menu9b -> {
                if (!isRecordAudioPermissionGranted()) {
                    requestRecordAudioPermission()
                } else {
                    if (switch0.isChecked) {
                        stopCapturing()
                    }
                    val builder3 = AlertDialog.Builder(this)
                    val inflater3 = layoutInflater
                    val dialogView3 = inflater3.inflate(R.layout.record_dialog, null)
                    val rec = dialogView3.findViewById<ImageView>(R.id.imageView17)
                    rec.setImageResource(R.drawable.ic_baseline_mic_24)
                    rec.setOnClickListener {
                        if (switch2.isChecked) {
                            menuSwitch2 = true
                            stopRecording()
                            Toast.makeText(applicationContext, "録音が終わりました。", Toast.LENGTH_SHORT).show()
                            rec.setImageResource(R.drawable.ic_baseline_mic_24)
                            switch2.isChecked = false
                        } else {
                            menuSwitch2 = false
                            startRecording()
                            rec.setImageResource(R.drawable.ic_baseline_mic_24_2)
                            switch2.isChecked = true
                        }
                    }
                    builder3.setView(dialogView3)
                            .setTitle("録音を(開始/終了)するには、\nマイクをタッチしてください。")
                            .setNegativeButton("戻る") { _, _ ->
                                if (switch2.isChecked) {
                                    menuSwitch2 = true
                                    stopRecording()
                                    Toast.makeText(applicationContext, "録音を中断しました。", Toast.LENGTH_SHORT).show()
                                    rec.setImageResource(R.drawable.ic_baseline_mic_24)
                                    switch2.isChecked = false
                                }
                            }
                            .setOnCancelListener {
                                if (switch2.isChecked) {
                                    menuSwitch2 = true
                                    stopRecording()
                                    Toast.makeText(applicationContext, "録音を中断しました。", Toast.LENGTH_SHORT).show()
                                    rec.setImageResource(R.drawable.ic_baseline_mic_24)
                                    switch2.isChecked = false
                                }
                            }
                    val dialog = builder3.create()
                    dialog.show()

                }
                return true
            }

            R.id.menu10 -> {
                selectCh()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
            lmp = LoopMediaPlayer.create(this, Uri.parse("android.resource://" + packageName + "/raw/" + R.raw.ta))
        lmp.reset()
        lmp.release()
        mp.reset()
        mp.release()
        soundPool.autoPause()
        soundPool.release()

        mediaRecorder.reset()
        mediaRecorder.release()
        super.onDestroy()
    }

    override fun onPause() {
        menuSwitch = true
        invalidateOptionsMenu()
        switch1.isChecked = false
        if (mp.isPlaying) {
            mp.stop()
            mp.prepare()
            switch3.isChecked = false
        }
        if (!menuSwitch2) {
            menuSwitch2 = true
            invalidateOptionsMenu()
            mediaRecorder.stop()
            Toast.makeText(applicationContext, "録音を中断しました。", Toast.LENGTH_SHORT).show()
            switch2.isChecked = false
        }

            lmp.stop()
            soundPool.autoPause()

        super.onPause()
    }
}
