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
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): Response<HistoryResponseModel>

    @GET(NetworkConstants.PATH_LIBRARY_ALBUMS)
    suspend fun libraryAlbums(
        @Query("limit") limit: Int?,
        @Query("offset") offset: String?
    ): Response<AlbumResponseModel>

    @GET(NetworkConstants.PATH_LiBRARY_ALBUM_DETAILS)
    suspend fun libraryAlbumById(
        @Path("id") id: String,
        @Path("relationship") relationship: String
    ): Response<SongsResponseModel>

    @GET(NetworkConstants.PATH_LIBRARY_SEARCH)
    suspend fun librarySearch(
        @Query("term") term: String,
        @Query("limit") limit: Int?,
        @Query("offset") offset: String?,
        @Query("types") types: List<String>?
    ): Response<LibrarySearchResponseModel>

    @GET(NetworkConstants.PATH_LIBRARY_PLAYLISTS)
    suspend fun libraryPlaylists(
        @Query("include") include: List<String>? = null,
        @Query("limit") limit: Int?,
        @Query("offset") offset: String?
    ): Response<PlaylistsResponseModel>

    @GET(NetworkConstants.PATH_LIBRARY_ARTISTS)
    suspend fun libraryArtists(
        @Query("include") include: List<String>? = null,
        @Query("limit") limit: Int?,
        @Query("offset") offset: String?
    ): Response<ArtistResponseModel>

    @GET(NetworkConstants.PATH_LIBRARY_SONGS)
    suspend fun librarySongs(
        @Query("include") include: List<String>? = null,
        @Query("limit") limit: Int?,
        @Query("offset") offset: String?
    ): Response<SongsResponseModel>

    @GET(NetworkConstants.PATH_LIBRARY_ARTIST_DETAILS)
    suspend fun getLibraryArtistsDetails(
        @Path("id") id: String,
        @Path("relationship") relationship: String,
        @Query("include") include: List<String>? = null
    ): Response<AlbumResponseModel>

    @GET(NetworkConstants.PATH_ALBUM_RELATIONSHIP)
    suspend fun getAlbumWithRelationship(
        @Path("id") id: String,
        @Path("relationship") relationship: String,
        @Path("storefront") storefront: String
    ): Response<AlbumDetailsResponseModel>

    @GET(NetworkConstants.PATH_CATALOG_ARTIST_RELATIONSHIP)
    suspend fun getArtistWithRelationship(
        @Path("id") id: String,
        @Path("relationship") relationship: String,
        @Path("storefront") storefront: String
    ): Response<AlbumResponseModel>
}