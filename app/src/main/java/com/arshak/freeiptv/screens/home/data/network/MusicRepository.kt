package com.arshak.freeiptv.screens.home.data.network

import com.arshak.core.data.repository.BaseRepository
import com.arshak.freeiptv.screens.authentication.data.repository.MusicApi

/**
 * Created by Arshak Avagyan on 2020-02-20.
 * Project Name: FreeIPTV
 */
class MusicRepository(private val musicApi: MusicApi) : BaseRepository() {

    suspend fun history(limit: Int? = null, offset: Int? = null) =
        musicApi.history(localisation = "", limit = limit, offset = offset)

    suspend fun libraryAlbums(limit: Int? = null, offset: String? = null) =
        musicApi.libraryAlbums(localisation = "", limit = limit, offset = offset)

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


}