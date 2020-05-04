package com.arshak.freeiptv.screens.home.data.network

import com.arshak.core.data.local.cache.TemporaryData
import com.arshak.core.data.network.model.SearchHintRequestModel
import com.arshak.core.data.network.model.SearchRequestModel
import com.arshak.core.data.repository.BaseRepository
import com.arshak.freeiptv.screens.authentication.data.repository.MusicApi

/**
 * Created by Arshak Avagyan on 2020-02-24.
 * Project Name: FreeIPTV
 */
class HomeRepository(private val musicApi: MusicApi) : BaseRepository() {

    suspend fun getSearchResult(searchRequestModel: SearchRequestModel) = musicApi.search(
        storeFront = TemporaryData.storeFront,
        term = searchRequestModel.term,
        limit = searchRequestModel.limit,
        offset = searchRequestModel.offset,
        types = searchRequestModel.types
    )

    suspend fun getSearchHints(searchHintRequestModel: SearchHintRequestModel) =
        musicApi.searchHints(
            storeFront = TemporaryData.storeFront,
            term = searchHintRequestModel.term,
            limit = searchHintRequestModel.limit,
            types = searchHintRequestModel.types
        )

}