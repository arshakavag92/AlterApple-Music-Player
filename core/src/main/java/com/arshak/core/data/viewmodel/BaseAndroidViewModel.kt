package com.arshak.core.data.viewmodel

import android.app.Application
import android.content.Intent
import android.content.LocusId
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.lifecycle.*
import com.arshak.core.R
import com.arshak.core.data.network.model.Output
import com.arshak.core.data.network.model.ResponseRootModel
import com.arshak.core.extensions.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import java.io.IOException

/**
 * Created by Arshak Avagyan on 2020-02-17.
 * Project Name: FreeIPTV
 */
open class BaseAndroidViewModel(val context: Application) : AndroidViewModel(context),
    OnActivityResult {

    val asyncMessageHandlerLiveData = SingleLiveEvent<String>()

    val navigationDestinatonLiveData = SingleLiveEvent<@IdRes Int>()

    fun <T : Any> executeBackendCall(call: suspend () -> Response<T>): LiveData<Output<T>> =
        liveData(
            context = viewModelScope.coroutineContext + Dispatchers.IO
        ) {
            try {
                val result: Output<T> = safeApiResult(call)
                emit(result)
            } catch (e: Exception) {
                emit(Output.Error(IOException(context.getString(R.string.message_error_network))))
            }
        }

    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>): Output<T> {
        val response = call.invoke()
        if (response.isSuccessful) {
            return when (val responseBody = response.body()) {
//                is ResponseRootModel<*> -> when {
//                    responseBody.error.isNullOrEmpty() -> Output.Success(responseBody) as Output<T>
//                    else -> Output.Error(IOException(responseBody.error.toString()))
//                }
                is T -> Output.Success(responseBody)
                else -> Output.Error(IOException(context.getString(R.string.message_error_network)))
            }
        }
        return Output.Error(IOException(response.errorBody().toString()))
    }

    fun navigate(destinationId: Int) {
        navigationDestinatonLiveData.postValue(destinationId)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) = Unit
}