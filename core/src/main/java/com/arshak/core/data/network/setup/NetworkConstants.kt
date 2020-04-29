package com.arshak.core.data.network.setup

/**
 * Created by Arshak Avagyan on 2020-01-16.
 * Project Name: FreeIPTV
 */
object NetworkConstants {
    const val BASE_URL = "https://api.music.apple.com/v1/"
    const val PATH_STOREFRONT = "me/storefront"

    const val PATH_SEARCH = "catalog/{storefront}/search"
    const val PATH_SEARCH_HINT = "catalog/{storefront}/search/hints"
    const val PATH_HISTORY = "me/recent/played"

    // User Personal Data
    const val PATH_LIBRARY_SEARCH = "me/library/search"
    const val PATH_LIBRARY_ALBUMS = "me/library/albums"
    const val PATH_LIBRARY_PLAYLISTS = "me/library/playlists"
    const val PATH_LIBRARY_ARTISTS = "me/library/artists"
    const val PATH_LIBRARY_SONGS = "me/library/songs"
    const val PATH_LIBRARY_ARTIST_DETAILS = "me/library/artists/{id}/{relationship}"

    // Global Data
    const val PATH_ALBUM = "{storefront}/albums/{id}"
    const val PATH_ALBUM_RELATIONSHIP = "catalog/{storefront}/albums/{id}/{relationship}"
    const val PATH_ALBUM_DETAILS = "catalog/{storefront}/albums/{id}"
}