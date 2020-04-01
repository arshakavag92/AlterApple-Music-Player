package com.arshak.freeiptv.screens.home.widget

import androidx.annotation.LayoutRes

object FragmentBuilder {

    @DslMarker
    annotation class FragmentMakerDsl

    infix fun FragmentComponent(title: TitleModel) {

    }

    data class TitleModel(val title: String)

    data class FragmentContent<T>(
        val title: TitleModel,
        val data: MutableList<T>,
        @LayoutRes val layoutResourceId: Int
    )
}