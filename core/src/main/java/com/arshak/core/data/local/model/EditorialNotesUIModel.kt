package com.arshak.core.data.local.model

import androidx.annotation.Keep
import com.arshak.core.data.network.model.EditorialNotesModel

@Keep
data class EditorialNotesUIModel(val short: String?, val standard: String?) {
    companion object {
        fun from(editorialNotesModel: EditorialNotesModel?) = EditorialNotesUIModel(
            short = editorialNotesModel?.short,
            standard = editorialNotesModel?.standard
        )
    }
}