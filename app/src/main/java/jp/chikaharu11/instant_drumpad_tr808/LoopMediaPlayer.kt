package jp.chikaharu11.instant_drumpad_tr808

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.media.PlaybackParams
import android.net.Uri
import android.util.Log
import android.widget.Toast


class LoopMediaPlayer(context: Context, resId: Uri) {
    private var mContext: Context? = null
    private var mResId: Uri? = null
    private var mCounter = 1
    private var mCurrentPlayer: MediaPlayer? = null
    private var mNextPlayer: MediaPlayer? = null
    private var count = 0.6f
    private var bpm = 1.0f
    private fun createNextMediaPlayer() {
        mNextPlayer = MediaPlayer.create(mContext, mResId)
        mCurrentPlayer!!.setNextMediaPlayer(mNextPlayer)
        mCurrentPlayer!!.setOnCompletionListener(onCompletionListener)
        setVolume(count,count)
        speed(bpm)
    }

    private val onCompletionListener =
        OnCompletionListener { mediaPlayer ->
            mediaPlayer.release()
            mCurrentPlayer = mNextPlayer
            createNextMediaPlayer()
            Log.d(TAG, String.format("Loop #%d", ++mCounter))
        }

    @get:Throws(IllegalStateException::class)
    val isPlaying: Boolean
        get() = mCurrentPlayer!!.isPlaying

    private fun setVolume(leftVolume: Float, rightVolume: Float) {
        mCurrentPlayer!!.setVolume(leftVolume, rightVolume)
    }

    fun volumePlus() {
        if (count <= 1.0f) {
            count += 0.25f
        }
        setVolume(count, count)
        when {
            count >= 1.0f -> Toast.makeText(mContext, R.string.highest, Toast.LENGTH_SHORT).show()
            count >= 0.75f -> Toast.makeText(mContext, R.string.high, Toast.LENGTH_SHORT).show()
            count >= 0.5f -> Toast.makeText(mContext, R.string.middle, Toast.LENGTH_SHORT).show()
            count >= 0.25f -> Toast.makeText(mContext, R.string.low, Toast.LENGTH_SHORT).show()
            count >= 0.0f -> Toast.makeText(mContext, R.string.lowest, Toast.LENGTH_SHORT).show()
        }
    }

    fun volumeMinus() {
        if (count >= 0.11f) {
            count -= 0.25f
        }
        setVolume(count,count)
        when {
            count >= 1.0f -> Toast.makeText(mContext, R.string.highest, Toast.LENGTH_SHORT).show()
            count >= 0.75f -> Toast.makeText(mContext, R.string.high, Toast.LENGTH_SHORT).show()
            count >= 0.5f -> Toast.makeText(mContext, R.string.middle, Toast.LENGTH_SHORT).show()
            count >= 0.25f -> Toast.makeText(mContext, R.string.low, Toast.LENGTH_SHORT).show()
            count >= 0.0f -> Toast.makeText(mContext, R.string.lowest, Toast.LENGTH_SHORT).show()
        }
    }

    private fun speed(tempo : Float) {
        val params = PlaybackParams()
        params.speed = tempo
        mCurrentPlayer!!.playbackParams = params
    }

    fun speedChange() {
        bpm += 0.1f
        speed(bpm)
    }

    @Throws(IllegalStateException::class)
    fun start() {
        mCurrentPlayer!!.start()
    }

    @Throws(IllegalStateException::class)
    fun stop() {
        mCurrentPlayer!!.stop()
        mCurrentPlayer!!.prepare()
    }

    @Throws(IllegalStateException::class)
    fun pause() {
        mCurrentPlayer!!.pause()
    }

    fun release() {
        mCurrentPlayer!!.release()
        mNextPlayer!!.release()
    }

    fun reset() {
        mCurrentPlayer!!.reset()
        mNextPlayer!!.reset()
    }

    companion object {
        val TAG = LoopMediaPlayer::class.java.simpleName
        fun create(context: Context, resId: Uri): LoopMediaPlayer {
            return LoopMediaPlayer(context, resId)
        }
    }

    init {
        mContext = context
        mResId = resId
        mCurrentPlayer = MediaPlayer.create(mContext, mResId)
        createNextMediaPlayer()
    }
}