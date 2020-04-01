package com.arshak.freeiptv.screens.authentication.data.repository

import com.arshak.core.data.network.model.*
import com.arshak.core.data.network.setup.NetworkConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Arshak Avagyan on 2020-02-20.
 * Project Name: FreeIPTV
 */
interface MusicApi {

    @GET(NetworkConstants.PATH_STOREFRONT)
    suspend fun getUserStoreFront(): Response<StorefrontResponseModel>

    @GET(NetworkConstants.PATH_SEARCH)
    suspend fun search(
        @Path("storefront") storeFront: String,
        @Query("term") term: String,
        @Query("l") localisation: String,
        @Query("limit") limit: Int?,
        @Query("offset") offset: String?,
        @Query("types") types: List<String>?
    ): Response<SearchResponseModel>

    @GET(NetworkConstants.PATH_SEARCH_HINT)
    suspend fun searchHints(
        @Path("storefront") storeFront: String,
        @Query("term") term: String,
        @Query("limit") limit: Int?,
        @Query("types") types: List<String>?
    ): Response<SearchHintResponseModel>

    @GET(NetworkConstants.PATH_HISTORY)
    suspend fun history(
        @Query("l") localisation: String,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): Response<HistoryResponseModel>

    @GET(NetworkConstants.PATH_LIBRARY_ALBUMS)
    suspend fun libraryAlbums(
        @Query("l") localisation: String,
        @Query("limit") limit: Int?,
        @Query("offset") offset: String?
    ): Response<AlbumResponseModel>

    @GET(NetworkConstants.PATH_LIBRARY_SEARCH)
    suspend fun librarySearch(
        @Query("term") term: String,
        @Query("limit") limit: Int?,
        @Query("offset") offset: String?,
        @Query("types") types: List<String>?
    ): Response<LibrarySearchResponseModel>


}