package com.arshak.freeiptv.utils

import android.content.Context
import com.arshak.core.data.local.model.ArtistUIModel
import com.arshak.core.data.local.model.SongsUIModel
import com.arshak.core.data.network.model.*
import com.arshak.freeiptv.R

object DTOConverter {

    fun libraryArtistsUIConvert(artists: List<ResourceModel<ArtistModel.Attributes>>?) =
        artists?.map { ArtistUIModel.from(it) }

    fun librarySongsUIConverter(songs: List<ResourceModel<SongAttributesModel>>?) =
        songs?.map { SongsUIModel.from(it) }


    fun searchResultsToSearchUIModel(
        searchResponseModel: SearchResponseModel,
        context: Context
    ): MutableList<SearchResponseItemUiModel<*>> {
        val data: MutableList<SearchResponseItemUiModel<*>> = mutableListOf()
        val response = searchResponseModel.results

        data.apply {
            addNonNullResource(
                response.songs,
                data,
                SearchItemTypeEnum.SONGS,
                context.getString(R.string.tracks)
            )
            addNonNullResource(
                response.artists,
                data,
                SearchItemTypeEnum.ARTISTS,
                context.getString(R.string.artists)
            )
            addNonNullResource(
                response.albums,
                data,
                SearchItemTypeEnum.ALBUMS,
                context.getString(R.string.albums)
            )
        }
        return data
    }

    private fun <T> addNonNullResource(
        searchResponseModel: ResponseRootModel<T>?,
        data: MutableList<SearchResponseItemUiModel<*>>,
        searchItemTypeEnum: SearchItemTypeEnum,
        categoryTitle: String
    ) =
        searchResponseModel?.let {
            data.add(
                SearchResponseItemUiModel(
                    href = it.href,
                    type = searchItemTypeEnum.type,
                    name = categoryTitle,
                    data = it.data,
                    id = it.href
                )
            )
        }

}