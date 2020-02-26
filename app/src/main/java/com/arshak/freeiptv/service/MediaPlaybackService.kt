package com.arshak.freeiptv.service

import android.app.Service
import android.content.Intent
import android.os.*
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import androidx.media.MediaBrowserServiceCompat
import androidx.media.session.MediaButtonReceiver
import com.apple.android.music.playback.controller.MediaPlayerController
import com.apple.android.music.playback.controller.MediaPlayerControllerFactory
import com.apple.android.music.playback.model.*
import com.arshak.freeiptv.media.AppleMusicTokenProvider

/**
 * Copyright (C) 2017 Apple, Inc. All rights reserved.
 */
class MediaPlaybackService : MediaBrowserServiceCompat(),
    MediaPlayerController.Listener, Handler.Callback {
    private var serviceHandlerThread: HandlerThread? = null
    private var serviceHandler: Handler? = null
    private var playerController: MediaPlayerController? = null
    private var mediaSession: MediaSessionCompat? = null
    private var playbackNotificationManager: PlaybackNotificationManager? = null
    private var mediaProvider: LocalMediaProvider? = null

    companion object {
        private const val TAG = "MediaPlaybackService"
        private const val MESSAGE_START_COMMAND = 1
        private const val MESSAGE_TASK_REMOVED = 2

        init {
            try {
                System.loadLibrary("c++_shared")
                System.loadLibrary("appleMusicSDK")
            } catch (e: Exception) {
                Log.e(
                    TAG,
                    "Could not load library due to: " + Log.getStackTraceString(e)
                )
                throw e
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        serviceHandlerThread = HandlerThread(
            "MediaPlaybackService:Handler",
            Process.THREAD_PRIORITY_BACKGROUND
        )
        serviceHandlerThread!!.start()
        serviceHandler = Handler(serviceHandlerThread!!.looper, this)
        playbackNotificationManager = PlaybackNotificationManager(this, serviceHandler!!)
        playerController = MediaPlayerControllerFactory.createLocalController(
            this,
            serviceHandler!!,
            AppleMusicTokenProvider(this.application)
        )
        playerController!!.addListener(this)
        mediaSession = MediaSessionCompat(this, TAG)
        mediaSession!!.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS or MediaSessionCompat.FLAG_HANDLES_QUEUE_COMMANDS)
        mediaSession!!.setCallback(
            MediaSessionManager(
                this,
                serviceHandler!!,
                playerController!!,
                mediaSession!!
            ), serviceHandler
        )
        sessionToken = mediaSession!!.sessionToken
        mediaProvider = LocalMediaProvider(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        playbackNotificationManager!!.stop(true)
        mediaSession!!.release()
        playerController!!.release()
        serviceHandlerThread!!.quit()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        serviceHandler!!.obtainMessage(MESSAGE_START_COMMAND, intent)
            .sendToTarget()
        return Service.START_STICKY
    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot? { // TODO: This needs to make sure the client is allowed to browse
        return BrowserRoot(LocalMediaProvider.MEDIA_ROOT_ID, null)
    }

    override fun onLoadChildren(
        parentId: String,
        result: Result<List<MediaBrowserCompat.MediaItem>>
    ) {
        mediaProvider!!.loadMediaItems(parentId, result)
    }

    override fun handleMessage(msg: Message): Boolean {
        when (msg.what) {
            MESSAGE_START_COMMAND -> {
                val messageIntent = msg.obj as Intent
                val mediaButtonEvent =
                    MediaButtonReceiver.handleIntent(mediaSession, messageIntent)
                if (mediaButtonEvent == null) {
                    handleIntent(messageIntent)
                }
                return true
            }
            MESSAGE_TASK_REMOVED -> {
                stopSelf()
                return true
            }
        }
        return false
    }

    override fun onPlayerStateRestored(playerController: MediaPlayerController) {}
    override fun onPlaybackStateChanged(
        playerController: MediaPlayerController,
        previousState: Int,
        currentState: Int
    ) {
        when (currentState) {
            PlaybackState.PLAYING -> playbackNotificationManager!!.start()
            PlaybackState.PAUSED -> playbackNotificationManager!!.stop(
                false
            )
            PlaybackState.STOPPED -> playbackNotificationManager!!.stop(
                true
            )
        }
    }

    override fun onPlaybackStateUpdated(playerController: MediaPlayerController) {}
    override fun onBufferingStateChanged(
        playerController: MediaPlayerController,
        buffering: Boolean
    ) {
    }

    override fun onCurrentItemChanged(
        playerController: MediaPlayerController,
        previousItem: PlayerQueueItem?,
        currentItem: PlayerQueueItem?
    ) {
    }

    override fun onItemEnded(
        playerController: MediaPlayerController,
        queueItem: PlayerQueueItem,
        endPosition: Long
    ) {
    }

    override fun onMetadataUpdated(
        playerController: MediaPlayerController,
        currentItem: PlayerQueueItem
    ) {
    }

    override fun onPlaybackQueueChanged(
        playerController: MediaPlayerController,
        playbackQueueItems: List<PlayerQueueItem>
    ) {
    }

    override fun onPlaybackQueueItemsAdded(
        playerController: MediaPlayerController,
        queueInsertionType: Int,
        containerType: Int,
        itemType: Int
    ) {
    }

    override fun onPlaybackError(
        playerController: MediaPlayerController,
        error: MediaPlayerException
    ) {
    }

    override fun onPlaybackRepeatModeChanged(playerController: MediaPlayerController, @PlaybackRepeatMode currentRepeatMode: Int) {}
    override fun onPlaybackShuffleModeChanged(playerController: MediaPlayerController, @PlaybackShuffleMode currentShuffleMode: Int) {}
    override fun onTaskRemoved(rootIntent: Intent) {
        serviceHandler!!.sendEmptyMessage(MESSAGE_TASK_REMOVED)
    }

    private fun handleIntent(intent: Intent) {}
}