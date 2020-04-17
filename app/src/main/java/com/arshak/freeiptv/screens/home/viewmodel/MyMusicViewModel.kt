package com.arshak.freeiptv.screens.home.viewmodel

import android.app.Application
import com.arshak.core.data.viewmodel.BaseAndroidViewModel
import com.arshak.freeiptv.screens.home.data.network.MusicRepository

class MyMusicViewModel(context: Application, private val musicRepository: MusicRepository) :
    BaseAndroidViewModel(context) {

    fun history() = executeBackendCall { musicRepository.history() }

    fun libraryAlbums() = executeBackendCall { musicRepository.libraryAlbums() }

    fun searchInLibrary(term: String, types: List<String>) =
        executeBackendCall { musicRepository.librarySearch(term, types = types) }

    fun libraryPlaylist() = executeBackendCall { musicRepository.libraryPlaylist() }

    fun libraryArtists() = executeBackendCall { musicRepository.libraryArtist() }

    fun librarySongs() = executeBackendCall { musicRepository.librarySongs() }

    fun getLibraryArtistDetails(id: String) =
        executeBackendCall { musicRepository.getLibraryArtistDetails(id, "albums") }

}