package com.arshak.freeiptv.service

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.*
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.RatingCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.util.Pair
import com.apple.android.music.playback.controller.MediaPlayerController
import com.apple.android.music.playback.model.*
import com.apple.android.music.playback.queue.CatalogPlaybackQueueItemProvider
import com.apple.android.music.playback.queue.PlaybackQueueInsertionType
import com.apple.android.music.playback.queue.PlaybackQueueItemProvider
import com.arshak.freeiptv.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

internal class MediaSessionManager(
    private val context: Context,
    backgroundHandler: Handler,
    private val playerController: MediaPlayerController,
    mediaSession: MediaSessionCompat
) : MediaSessionCompat.Callback(), MediaPlayerController.Listener, Handler.Callback {
    private val mediaSession: MediaSessionCompat
    private val metadataBuilder: MediaMetadataCompat.Builder
    private var metadata: MediaMetadataCompat? = null
    private val playbackStateBuilder: PlaybackStateCompat.Builder
    var backgroundHandler: Handler
    private val mainHandler: Handler
    private val artworkWidth: Int
    private val artworkHeight: Int
    private var artworkTarget: AlbumArtworkTarget? = null
    private var queueItems: ArrayList<MediaSessionCompat.QueueItem>? = null

    @Override
    override fun onCommand(
        command: String,
        extras: Bundle,
        cb: ResultReceiver
    ) {
        extras.classLoader = javaClass.classLoader
        when {
            MediaControllerCommand.COMMAND_REMOVE_QUEUE_ITEM == command -> {
                playerController.removeQueueItemWithId(extras.getLong(MediaControllerCommand.COMMAND_ARGUMENT_PLAYBACK_QUEUE_ID))
            }
            MediaControllerCommand.COMMAND_MOVE_QUEUE_ITEM == command -> {
                val sourceItemId =
                    extras.getLong(MediaControllerCommand.COMMAND_ARGUMENT_PLAYBACK_QUEUE_ID)
                val targetItemId =
                    extras.getLong(MediaControllerCommand.COMMAND_ARGUMENT_PLAYBACK_QUEUE_ID_TARGET)
                val moveTargetType =
                    extras.getInt(MediaControllerCommand.COMMAND_ARGUMENT_PLAYBACK_QUEUE_MOVE_TARGET_TYPE)
                playerController.moveQueueItemWithId(sourceItemId, targetItemId, moveTargetType)
            }
            MediaControllerCommand.COMMAND_ADD_QUEUE_ITEMS == command -> {
                val provider: PlaybackQueueItemProvider =
                    extras.getParcelable(MediaControllerCommand.COMMAND_ARGUMENT_PLAYBACK_QUEUE_ITEM_PROVIDER)!!
                @PlaybackQueueInsertionType val insertionType =
                    extras.getInt(MediaControllerCommand.COMMAND_ARGUMENT_PLAYBACK_QUEUE_INSERTION_TYPE)
                playerController.addQueueItems(provider, insertionType)
            }
        }
    }

    @Override
    override fun onMediaButtonEvent(mediaButtonEvent: Intent): Boolean {
        return false
    }

    override fun onPrepare() = Unit
    override fun onPrepareFromMediaId(mediaId: String, extras: Bundle) = Unit
    override fun onPrepareFromSearch(query: String, extras: Bundle) = Unit
    override fun onPrepareFromUri(uri: Uri, extras: Bundle) = Unit

    override fun onPlay() {
        playerController.play()
    }

    override fun onPlayFromMediaId(mediaId: String, extras: Bundle?) {
        val builder = CatalogPlaybackQueueItemProvider.Builder()
        var containerType = MediaContainerType.NONE
        var itemType = MediaItemType.UNKNOWN

        when {
            extras != null -> {
                containerType = extras.getInt("containerType", MediaContainerType.NONE)
                itemType = extras.getInt("itemType", MediaItemType.UNKNOWN)
            }
        }

        when {
            containerType != MediaContainerType.NONE -> {
                builder.containers(containerType, mediaId)
            }
            else -> {
                builder.items(itemType, mediaId)
            }
        }
        playerController.prepare(builder.build(), true)
    }

    override fun onPlayFromSearch(query: String, extras: Bundle) = Unit
    override fun onPlayFromUri(uri: Uri, extras: Bundle) = Unit


    override fun onSkipToQueueItem(id: Long) {
        playerController.skipToQueueItemWithId(id)
    }

    override fun onPause() {
        playerController.pause()
    }

    override fun onSkipToNext() {
        playerController.skipToNextItem()
    }

    override fun onSkipToPrevious() {
        playerController.skipToPreviousItem()
    }

    override fun onFastForward() = Unit
    override fun onRewind() = Unit

    override fun onStop() = playerController.stop()

    override fun onSeekTo(pos: Long) = playerController.seekToPosition(pos)

    override fun onSetRating(rating: RatingCompat) {}
    override fun onSetRepeatMode(repeatMode: Int) {}
    override fun onSetShuffleMode(shuffleMode: Int) {}
    override fun onCustomAction(action: String, extras: Bundle) {}
    override fun onAddQueueItem(description: MediaDescriptionCompat) {}
    override fun onAddQueueItem(
        description: MediaDescriptionCompat,
        index: Int
    ) {
    }

    override fun onRemoveQueueItem(description: MediaDescriptionCompat) {}
    override fun onPlayerStateRestored(playerController: MediaPlayerController) {}
    override fun onPlaybackStateChanged(
        playerController: MediaPlayerController,
        previousState: Int,
        currentState: Int
    ) {
        updatePlaybackState(currentState, playerController.isBuffering)
    }

    override fun onPlaybackStateUpdated(playerController: MediaPlayerController) {}
    override fun onBufferingStateChanged(
        playerController: MediaPlayerController,
        buffering: Boolean
    ) {
        updatePlaybackState(playerController.playbackState, buffering)
    }

    override fun onCurrentItemChanged(
        playerController: MediaPlayerController,
        previousItem: PlayerQueueItem?,
        currentItem: PlayerQueueItem?
    ) {
        updateMetaData(previousItem, currentItem)
        updatePlaybackState(playerController.playbackState, playerController.isBuffering)
    }

    override fun onItemEnded(
        playerController: MediaPlayerController,
        queueItem: PlayerQueueItem,
        endPosition: Long
    ) = Unit

    override fun onMetadataUpdated(
        playerController: MediaPlayerController,
        currentItem: PlayerQueueItem
    ) = updateMetaData(null, currentItem)

    override fun onPlaybackQueueChanged(
        playerController: MediaPlayerController,
        playbackQueueItems: List<PlayerQueueItem>
    ) =
        updateQueueItems(playbackQueueItems)

    override fun onPlaybackQueueItemsAdded(
        playerController: MediaPlayerController,
        queueInsertionType: Int,
        containerType: Int,
        itemType: Int
    ) = Unit

    override fun onPlaybackError(
        playerController: MediaPlayerController,
        error: MediaPlayerException
    ) = Unit

    override fun onPlaybackRepeatModeChanged(playerController: MediaPlayerController, @PlaybackRepeatMode currentRepeatMode: Int) {
        mediaSession.setRepeatMode(
            convertRepeatMode(
                currentRepeatMode
            )
        )
    }

    override fun onPlaybackShuffleModeChanged(playerController: MediaPlayerController, @PlaybackShuffleMode currentShuffleMode: Int) {
        mediaSession.setShuffleMode(
            convertShuffleMode(
                currentShuffleMode
            )
        )
    }

    override fun handleMessage(msg: Message): Boolean {
        when (msg.what) {
            MESSAGE_INIT -> {
                init()
                return true
            }
            MESSAGE_LOAD_ARTWORK -> {
                val artworkUrl = msg.obj as String
                if (artworkTarget != null) { //
                    // Picasso.with(context).cancelRequest(artworkTarget);
// TODO Artwork Target Glide implementation
                }
                artworkTarget = AlbumArtworkTarget(this, artworkUrl)
                //                Picasso.with(context).load(artworkUrl).into(artworkTarget);
// TODO Artwork Target Glide implementation
                return true
            }
            MESSAGE_UPDATE_ARTWORK -> {
                val artworkData =
                    msg.obj as Pair<String, Bitmap>
                if (artworkData.first == metadata!!.getString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI)) {
                    metadataBuilder.putBitmap(
                        MediaMetadataCompat.METADATA_KEY_ALBUM_ART,
                        artworkData.second
                    )
                    metadata = metadataBuilder.build()
                    mediaSession.setMetadata(metadata)
                }
                return true
            }
        }
        return false
    }

    private fun init() {
        queueItems = ArrayList()
        updatePlaybackState(playerController.playbackState, playerController.isBuffering)
        updateMetaData(null, playerController.currentItem)
        updateQueueItems(playerController.queueItems)
    }

    private fun updatePlaybackState(
        @PlaybackState currentState: Int, buffering: Boolean
    ) {
        playbackStateBuilder.setState(
            convertPlaybackState(
                currentState,
                buffering
            ), playerController.currentPosition, playerController.playbackRate
        )
        playbackStateBuilder.setBufferedPosition(playerController.bufferedPosition)
        playbackStateBuilder.setActions(
            allowedActions(
                playerController
            )
        )
        mediaSession.setPlaybackState(playbackStateBuilder.build())
        mediaSession.isActive = currentState != PlaybackState.STOPPED
    }

    private fun updateMetaData(
        previousItem: PlayerQueueItem?,
        currentItem: PlayerQueueItem?
    ) {
        var title: String? = null
        var albumTitle: String? = null
        var artistName: String? = null
        var albumArtistName: String? = null
        var duration: Long = -1
        var releaseDate: String? = null
        var genreName: String? = null
        var composerName: String? = null
        var url: String? = null
        var artworkUrl: String? = null
        if (currentItem != null) {
            val item = currentItem.item
            title = item.title
            albumTitle = item.albumTitle
            artistName = item.artistName
            albumArtistName = item.albumArtistName
            duration = item.duration
            releaseDate =
                formatReleaseDate(item.releaseDate)
            genreName = item.genreName
            composerName = item.composerName
            url = item.url
            artworkUrl = item.getArtworkUrl(artworkWidth, artworkHeight)
        }
        metadataBuilder.putString(MediaMetadataCompat.METADATA_KEY_TITLE, title)
        metadataBuilder.putString(MediaMetadataCompat.METADATA_KEY_ALBUM, albumTitle)
        metadataBuilder.putString(MediaMetadataCompat.METADATA_KEY_ARTIST, artistName)
        metadataBuilder.putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST, albumArtistName)
        metadataBuilder.putLong(MediaMetadataCompat.METADATA_KEY_DURATION, duration)
        metadataBuilder.putString(MediaMetadataCompat.METADATA_KEY_DATE, releaseDate)
        metadataBuilder.putString(MediaMetadataCompat.METADATA_KEY_GENRE, genreName)
        metadataBuilder.putString(MediaMetadataCompat.METADATA_KEY_COMPOSER, composerName)
        metadataBuilder.putString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI, url)
        metadataBuilder.putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, artworkUrl)
        // TODO: Add additional fields
        val artworkChanged = loadAlbumArtwork(currentItem, previousItem)
        if (artworkChanged) {
            metadataBuilder.putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, null)
        }
        metadata = metadataBuilder.build()
        mediaSession.setMetadata(metadata)
    }

    private fun updateQueueItems(playbackQueueItems: List<PlayerQueueItem>) {
        val itemCount = playbackQueueItems.size
        queueItems!!.clear()
        queueItems!!.ensureCapacity(itemCount)
        val builder = MediaDescriptionCompat.Builder()
        for (i in 0 until itemCount) {
            val queueItem = playbackQueueItems[i]
            val item = queueItem.item
            builder.setTitle(item.title)
            builder.setSubtitle(item.artistName)
            builder.setIconUri(Uri.parse(item.artworkUrl))
            builder.setMediaId(item.subscriptionStoreId)
            queueItems!!.add(
                MediaSessionCompat.QueueItem(
                    builder.build(),
                    queueItem.playbackQueueId
                )
            )
        }
        mediaSession.setQueue(queueItems)
    }

    private fun loadAlbumArtwork(
        currentItem: PlayerQueueItem?,
        previousItem: PlayerQueueItem?
    ): Boolean {
        val previousArtworkUrl =
            previousItem?.item?.getArtworkUrl(
                artworkWidth,
                artworkHeight
            )
        val currentArtworkUrl =
            currentItem?.item?.getArtworkUrl(
                artworkWidth,
                artworkHeight
            )
        if (currentArtworkUrl != null && currentArtworkUrl != previousArtworkUrl) {
            mainHandler.obtainMessage(
                MESSAGE_LOAD_ARTWORK,
                currentArtworkUrl
            ).sendToTarget()
            return true
        }
        return false
    }

    companion object {
        private const val TAG = "MediaSessionManager"
        private val RELEASE_DATE_FORMAT: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        const val MESSAGE_INIT = 1
        const val MESSAGE_LOAD_ARTWORK = 2
        const val MESSAGE_UPDATE_ARTWORK = 3
        private fun convertPlaybackState(
            @PlaybackState playbackState: Int, buffering: Boolean
        ): Int {
            return when (playbackState) {
                PlaybackState.STOPPED -> PlaybackStateCompat.STATE_STOPPED
                PlaybackState.PAUSED -> PlaybackStateCompat.STATE_PAUSED
                PlaybackState.PLAYING -> if (buffering) PlaybackStateCompat.STATE_BUFFERING else PlaybackStateCompat.STATE_PLAYING
                else -> PlaybackStateCompat.STATE_NONE
            }
        }

        private fun convertRepeatMode(@PlaybackRepeatMode repeatMode: Int): Int {
            return when (repeatMode) {
                PlaybackRepeatMode.REPEAT_MODE_ALL -> PlaybackStateCompat.REPEAT_MODE_ALL
                PlaybackRepeatMode.REPEAT_MODE_ONE -> PlaybackStateCompat.REPEAT_MODE_ONE
                PlaybackRepeatMode.REPEAT_MODE_OFF -> PlaybackStateCompat.REPEAT_MODE_NONE
                else -> PlaybackStateCompat.REPEAT_MODE_NONE
            }
        }

        private fun convertShuffleMode(@PlaybackShuffleMode shuffleMode: Int): Int {
            when (shuffleMode) {
                PlaybackShuffleMode.SHUFFLE_MODE_OFF -> return PlaybackStateCompat.SHUFFLE_MODE_NONE
                PlaybackShuffleMode.SHUFFLE_MODE_SONGS -> return PlaybackStateCompat.SHUFFLE_MODE_ALL
            }
            return PlaybackStateCompat.SHUFFLE_MODE_NONE
        }

        private fun allowedActions(playerController: MediaPlayerController): Long { // TODO: This will need to take into account queue state, etc as to whether skip is allowed
            var result =
                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS or PlaybackStateCompat.ACTION_SKIP_TO_NEXT
            when (playerController.playbackState) {
                PlaybackState.PLAYING -> result =
                    result or PlaybackStateCompat.ACTION_PAUSE
                PlaybackState.PAUSED -> result =
                    result or PlaybackStateCompat.ACTION_PLAY or PlaybackStateCompat.ACTION_STOP
                PlaybackState.STOPPED -> result =
                    result or PlaybackStateCompat.ACTION_PLAY
            }
            return result
        }

        private fun formatReleaseDate(releaseDate: Date?): String? {
            return if (releaseDate == null) {
                null
            } else RELEASE_DATE_FORMAT.format(
                releaseDate
            )
        }
    }

    init {
        playerController.addListener(this)
        this.mediaSession = mediaSession
        metadataBuilder = MediaMetadataCompat.Builder()
        playbackStateBuilder = PlaybackStateCompat.Builder()
        this.backgroundHandler = Handler(backgroundHandler.looper, this)
        mainHandler = Handler(Looper.getMainLooper(), this)
        artworkWidth = context.resources.getDimensionPixelSize(R.dimen.notification_artwork_width)
        artworkHeight = context.resources.getDimensionPixelSize(R.dimen.notification_artwork_width)
        this.backgroundHandler.sendEmptyMessage(MESSAGE_INIT)
    }
}