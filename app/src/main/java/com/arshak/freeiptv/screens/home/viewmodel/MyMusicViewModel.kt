package com.arshak.freeiptv.screens.home.viewmodel

import android.app.Application
import com.arshak.core.data.network.model.SearchItemTypeEnum
import com.arshak.core.data.viewmodel.BaseAndroidViewModel
import com.arshak.freeiptv.screens.home.data.network.MusicRepository

class MyMusicViewModel(context: Application, private val musicRepository: MusicRepository) :
    BaseAndroidViewModel(context) {

    fun history() = executeBackendCall { musicRepository.history() }

    fun libraryAlbums() = executeBackendCall { musicRepository.libraryAlbums() }

    fun libraryAlbumById(libraryId: String, relationship: String) =
        executeBackendCall { musicRepository.libraryAlbumDetails(libraryId, relationship) }

    fun searchInLibrary(term: String, types: List<String>) =
        executeBackendCall { musicRepository.librarySearch(term, types = types) }

    fun libraryPlaylist() = executeBackendCall { musicRepository.libraryPlaylist() }

    fun libraryArtists() = executeBackendCall { musicRepository.libraryArtist() }

    fun librarySongs() = executeBackendCall { musicRepository.librarySongs() }

    fun getLibraryAlbumDetails(id: String, relationship: String) =
        executeBackendCall { musicRepository.libraryAlbumDetails(id, relationship) }

    fun getLibraryArtistDetails(id: String) =
        executeBackendCall { musicRepository.getLibraryArtistDetails(id, "albums") }

    fun getAlbumDetailsRelationship(id: String, relationship: String = "") =
        executeBackendCall { musicRepository.getAlbumDetailsRelationship(id, relationship) }

    fun getArtistWithRelationship(
        id: String,
        relationship: String = SearchItemTypeEnum.ALBUMS.type
    ) =
        executeBackendCall {
            musicRepository.getArtistWithRelationship(
                id = id,
                relationship = relationship
            )
        }
}