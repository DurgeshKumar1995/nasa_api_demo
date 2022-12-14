package com.unorg.nasa.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Camera(
    val full_name: String?,
    val id: Int?,
    val name: String?,
    val rover_id: Int?
):Parcelable