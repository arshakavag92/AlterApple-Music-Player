package com.arshak.freeiptv.screens.home.data.network

import com.arshak.core.data.local.cache.TemporaryData
import com.arshak.core.data.repository.BaseRepository
import com.arshak.freeiptv.screens.authentication.data.repository.MusicApi

/**
 * Created by Arshak Avagyan on 2020-02-20.
 * Project Name: FreeIPTV
 */
class MusicRepository(private val musicApi: MusicApi) : BaseRepository() {

    suspend fun history(limit: Int? = null, offset: Int? = null) =
        musicApi.history(limit = limit, offset = offset)

    suspend fun libraryAlbums(limit: Int? = null, offset: String? = null) =
        musicApi.libraryAlbums(limit = limit, offset = offset)

    suspend fun libraryAlbumDetails(id: String, relationship: String) =
        musicApi.libraryAlbumById(id, relationship)

    suspend fun librarySearch(
        term: String,
        limit: Int? = null,
        offset: String? = null,
        types: List<String> = emptyList()
    ) = musicApi.librarySearch(term = term, limit = limit, offset = offset, types = types)

    suspend fun libraryPlaylist(
        include: List<String>? = null,
        limit: Int? = null,
        offset: String? = null
    ) =
        musicApi.libraryPlaylists(include, limit = limit, offset = offset)

    suspend fun libraryArtist(
        include: List<String>? = null,
        limit: Int? = null,
        offset: String? = null
    ) =
        musicApi.libraryArtists(include, limit = limit, offset = offset)

    suspend fun librarySongs(
        include: List<String>? = null,
        limit: Int? = null,
        offset: String? = null
    ) =
        musicApi.librarySongs(include, limit = limit, offset = offset)

    suspend fun getLibraryArtistDetails(
        id: String,
        relationship: String,
        include: List<String>? = null
    ) =
        musicApi.getLibraryArtistsDetails(id, relationship, include)

    suspend fun getAlbumDetails(
        id: String,
        storefront: String,
        include: List<String>? = null
    ) =
        musicApi.getLibraryArtistsDetails(id, storefront, include)

    suspend fun getAlbumDetailsRelationship(
        id: String,
        relationship: String,
        storefront: String = TemporaryData.storeFront
    ) = musicApi.getAlbumWithRelationship(id, relationship, storefront)


}