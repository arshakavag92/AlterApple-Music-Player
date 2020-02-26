package com.arshak.freeiptv.service

import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaDescriptionCompat
import android.util.JsonReader
import androidx.media.MediaBrowserServiceCompat
import com.apple.android.music.playback.model.MediaContainerType
import com.apple.android.music.playback.model.MediaItemType
import java.io.IOException
import java.io.InputStreamReader
import java.util.ArrayList

public class DataLoader internal constructor(
    private val applicationContext: Context,
    parentId: String,
    result: MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>>
) : AsyncTask<Void?, Void?, Void?>() {
    private val fileName: String
    private val result: MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>>


    override fun doInBackground(vararg p0: Void?): Void? {
        try {
            result.sendResult(
                readItemsFromFile(
                    applicationContext,
                    fileName
                )
            )
        } catch (e: IOException) {
            result.sendResult(emptyList<MediaBrowserCompat.MediaItem>())
        }
        return null
    }

    companion object {
        private fun getFileName(parentId: String): String {
            val result = StringBuilder("media_data/")
            if (LocalMediaProvider.MEDIA_ROOT_ID == parentId) {
                result.append("root")
            } else {
                result.append(parentId.replace(':', '_'))
            }
            result.append(".json")
            return result.toString()
        }

        @Throws(IOException::class)
        private fun readItemsFromFile(
            context: Context,
            fileName: String
        ): List<MediaBrowserCompat.MediaItem> {
            val reader = JsonReader(
                InputStreamReader(
                    context.assets.open(fileName), "UTF-8"
                )
            )
            return try {
                readItemsArray(reader)
            } finally {
                reader.close()
            }
        }

        @Throws(IOException::class)
        private fun readItemsArray(reader: JsonReader): List<MediaBrowserCompat.MediaItem> {
            val result: MutableList<MediaBrowserCompat.MediaItem> =
                ArrayList()
            reader.beginArray()
            while (reader.hasNext()) {
                result.add(readItem(reader))
            }
            reader.endArray()
            return result
        }

        @Throws(IOException::class)
        private fun readItem(reader: JsonReader): MediaBrowserCompat.MediaItem {
            var flags = 0
            val mediaDescriptionBuilder =
                MediaDescriptionCompat.Builder()
            reader.beginObject()
            while (reader.hasNext()) {
                val fieldName = reader.nextName()
                if ("id" == fieldName) {
                    mediaDescriptionBuilder.setMediaId(reader.nextString())
                } else if ("title" == fieldName) {
                    mediaDescriptionBuilder.setTitle(reader.nextString())
                } else if ("subtitle" == fieldName) {
                    mediaDescriptionBuilder.setSubtitle(reader.nextString())
                } else if ("description" == fieldName) {
                    mediaDescriptionBuilder.setDescription(reader.nextString())
                } else if ("browseable" == fieldName) {
                    if (reader.nextBoolean()) {
                        flags = flags or MediaBrowserCompat.MediaItem.FLAG_BROWSABLE
                    }
                } else if ("playable" == fieldName) {
                    if (reader.nextBoolean()) {
                        flags = flags or MediaBrowserCompat.MediaItem.FLAG_PLAYABLE
                    }
                } else if ("mediaUri" == fieldName) {
                    mediaDescriptionBuilder.setMediaUri(
                        Uri.parse(
                            reader.nextString()
                        )
                    )
                } else if ("iconUri" == fieldName) {
                    mediaDescriptionBuilder.setIconUri(
                        Uri.parse(
                            reader.nextString()
                        )
                    )
                } else if ("type" == fieldName) {
                    val type = reader.nextString()
                    val extras = Bundle(1)
                    if ("song" == type) {
                        extras.putInt(
                            "itemType",
                            MediaItemType.SONG
                        )
                    } else if ("album" == type) {
                        extras.putInt(
                            "containerType",
                            MediaContainerType.ALBUM
                        )
                    } else if ("playlist" == type) {
                        extras.putInt(
                            "containerType",
                            MediaContainerType.PLAYLIST
                        )
                    }
                    mediaDescriptionBuilder.setExtras(extras)
                } else {
                    reader.skipValue()
                }
            }
            reader.endObject()
            return MediaBrowserCompat.MediaItem(
                mediaDescriptionBuilder.build(),
                flags
            )
        }
    }

    init {
        fileName = getFileName(parentId)
        this.result = result
    }


}