package com.arshak.core.data.local.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.arshak.core.data.network.model.EditorialNotesModel
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class EditorialNotesUIModel(val short: String?, val standard: String?) : Parcelable {
    companion object {
        fun from(editorialNotesModel: EditorialNotesModel?) = EditorialNotesUIModel(
            short = editorialNotesModel?.short,
            standard = editorialNotesModel?.standard
        )
    }
}