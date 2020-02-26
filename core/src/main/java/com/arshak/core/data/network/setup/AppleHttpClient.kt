package com.arshak.core.data.network.setup

import com.arshak.core.utils.SecurePreferenceHelper
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.security.cert.CertificateException
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

/**
 * Created by Arshak Avagyan on 2020-01-17.
 * Project Name: FreeIPTV
 */
class AppleHttpClient(val securePreferenceHelper: SecurePreferenceHelper) {

    private var requestHeaders: MutableSet<Pair<String, String>> = mutableSetOf()

    private val retrofitInstance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .client(getTrustedHttpClient())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    fun addRequestHeaders(newHeaders: Set<Pair<String, String>>) {
        requestHeaders.addAll(newHeaders)
    }

    fun <T> createService(service: Class<T>) = retrofitInstance.create(service)

    private fun getTrustedHttpClient(): OkHttpClient {
        try {
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) = Unit

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) = Unit

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> =
                    arrayOf()
            })

            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())

            val sslSocketFactory = sslContext.socketFactory
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val headerIntercepor = HeaderInterceptor(requestHeaders)

            val builder = OkHttpClient.Builder().apply {
                sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                addInterceptor(httpLoggingInterceptor)
                addInterceptor(headerIntercepor)
                hostnameVerifier(HostnameVerifier { _, _ -> true })
            }
            return builder
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES).build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}