package com.arshak.core.data.network.setup

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Arshak Avagyan on 2020-02-24.
 * Project Name: FreeIPTV
 */
class HeaderInterceptor(val headers: Set<Pair<String, String>>) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.run {
            proceed(
                request()
                    .newBuilder().apply {
                        headers.forEach {
                            addHeader(it.first, it.second)
                        }
                    }
                    .build()
            )
        }
    }
}