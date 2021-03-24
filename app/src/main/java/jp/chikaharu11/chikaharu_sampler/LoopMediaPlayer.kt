package jp.chikaharu11.chikaharu_sampler

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.net.Uri
import android.util.Log


class LoopMediaPlayer private constructor(context: Context, resId: Uri?) {
    private var mContext: Context? = null
    private var mResId: Uri? = null
    private var mCounter = 1
    private var mCurrentPlayer: MediaPlayer? = null
    private var mNextPlayer: MediaPlayer? = null
    private fun createNextMediaPlayer() {
        mNextPlayer = MediaPlayer.create(mContext, mResId)
        mCurrentPlayer!!.setNextMediaPlayer(mNextPlayer)
        mCurrentPlayer!!.setOnCompletionListener(onCompletionListener)
    }

    private val onCompletionListener =
        OnCompletionListener { mediaPlayer ->
            mediaPlayer.release()
            mCurrentPlayer = mNextPlayer
            createNextMediaPlayer()
            Log.d(TAG, String.format("Loop #%d", ++mCounter))
        }

    companion object {
        val TAG = LoopMediaPlayer::class.java.simpleName
        fun create(context: Context, resId: Uri?): LoopMediaPlayer {
            return LoopMediaPlayer(context, resId)
        }
    }

    init {
        mContext = context
        mResId = resId
        mCurrentPlayer = MediaPlayer.create(mContext, mResId)
        mCurrentPlayer?.setOnPreparedListener { mCurrentPlayer?.start() }
        createNextMediaPlayer()
    }
}