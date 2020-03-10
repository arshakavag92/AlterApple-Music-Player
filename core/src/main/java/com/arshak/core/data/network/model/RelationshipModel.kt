package com.arshak.core.data.network.model

import androidx.annotation.Keep

/**
 * Created by Arshak Avagyan on 2020-02-24.
 * Project Name: FreeIPTV
 */
@Keep
data class RelationshipModel<T>(val data: ResourceModel<T>, val href: String)