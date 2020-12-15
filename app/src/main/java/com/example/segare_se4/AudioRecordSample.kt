package com.example.segare_se4

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.util.Log
import kotlin.math.max

class AudioRecordSample {

    // サンプリングレート (Hz)
    // 全デバイスサポート保障は44100のみ
    private val samplingRate = 44100

    // フレームレート (fps)
    // 1秒間に何回音声データを処理したいか
    // 各自好きに決める
    private val frameRate = 10

    // 1フレームの音声データ(=Short値)の数
    private val oneFrameDataCount = samplingRate / frameRate

    // 1フレームの音声データのバイト数 (byte)
    // Byte = 8 bit, Short = 16 bit なので, Shortの倍になる
    private val oneFrameSizeInByte = oneFrameDataCount * 2

    // 音声データのバッファサイズ (byte)
    // 要件1:oneFrameSizeInByte より大きくする必要がある
    // 要件2:デバイスの要求する最小値より大きくする必要がある
    private val audioBufferSizeInByte =
        max(oneFrameSizeInByte * 10, // 適当に10フレーム分のバッファを持たせた
            AudioRecord.getMinBufferSize(samplingRate,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT))

    fun startRecording() {

        // インスタンスの作成
        val audioRecord = AudioRecord(
            MediaRecorder.AudioSource.MIC, // 音声のソース
            samplingRate, // サンプリングレート
            AudioFormat.CHANNEL_IN_MONO, // チャネル設定. MONO and STEREO が全デバイスサポート保障
            AudioFormat.ENCODING_PCM_16BIT, // PCM16が全デバイスサポート保障
            audioBufferSizeInByte) // バッファ

        // 音声データを幾つずつ処理するか( = 1フレームのデータの数)
        audioRecord.positionNotificationPeriod = oneFrameDataCount

        // ここで指定した数になったタイミングで, 後続の onMarkerReached が呼び出される
        // 通常のストリーミング処理では必要なさそう？
        audioRecord.notificationMarkerPosition = 40000 // 使わないなら設定しない.

        // 音声データを格納する配列
        val audioDataArray = ShortArray(oneFrameDataCount)

        // コールバックを指定
        audioRecord.setRecordPositionUpdateListener(object : AudioRecord.OnRecordPositionUpdateListener {

            // フレームごとの処理
            override fun onPeriodicNotification(recorder: AudioRecord) {
                recorder.read(audioDataArray, 0, oneFrameDataCount) // 音声データ読込
                Log.v("AudioRecord", "onPeriodicNotification size=${audioDataArray.size}")
                // 好きに処理する
            }

            // マーカータイミングの処理.
            // notificationMarkerPosition に到達した際に呼ばれる
            override fun onMarkerReached(recorder: AudioRecord) {
                recorder.read(audioDataArray, 0, oneFrameDataCount) // 音声データ読込
                Log.v("AudioRecord", "onMarkerReached size=${audioDataArray.size}")
                // 好きに処理する
            }
        })

        audioRecord.startRecording()
    }
}