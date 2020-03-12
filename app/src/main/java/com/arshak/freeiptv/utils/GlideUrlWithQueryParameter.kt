package com.arshak.freeiptv.utils

import com.bumptech.glide.load.model.GlideUrl

class GlideUrlWithQueryParameter(
    private val mSourceUrl: String,
    width: Int,
    height: Int
) : GlideUrl(buildUrl(mSourceUrl, width, height)) {
    override fun getCacheKey(): String = mSourceUrl

    override fun toString(): String = super.getCacheKey()

    companion object {
        private fun buildUrl(
            baseUrl: String,
            width: Int,
            height: Int
        ): String {
            return baseUrl.replace("{w}", width.toString()).replace("{h}", height.toString())
        }
    }

}