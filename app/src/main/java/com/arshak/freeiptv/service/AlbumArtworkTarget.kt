package com.arshak.freeiptv.service

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Pair
import com.squareup.picasso.Picasso.LoadedFrom
import com.squareup.picasso.Target

/**
 * Created by Arshak Avagyan on 2020-02-20.
 * Project Name: FreeIPTV
 */
internal class AlbumArtworkTarget(
    private val mediaSessionManager: MediaSessionManager,
    var url: String
) : Target {
    override fun onBitmapLoaded(
        bitmap: Bitmap,
        loadedFrom: LoadedFrom
    ) {
        mediaSessionManager.backgroundHandler.obtainMessage(
            MediaSessionManager.MESSAGE_UPDATE_ARTWORK,
            Pair(url, bitmap)
        ).sendToTarget()
    }

    override fun onBitmapFailed(
        e: Exception,
        errorDrawable: Drawable
    ) = Unit

    override fun onPrepareLoad(drawable: Drawable) = Unit

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other !is AlbumArtworkTarget) {
            return false
        }
        return url == other.url
    }

    override fun hashCode(): Int {
        return url.hashCode()
    }
}