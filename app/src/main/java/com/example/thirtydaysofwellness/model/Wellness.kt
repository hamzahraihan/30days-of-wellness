package com.example.thirtydaysofwellness.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Wellness(
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    @StringRes val dayRes: Int,
    @DrawableRes val imageRes: Int,
)