package com.example.androiddevchallenge.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

/**
 * @author airsaid
 */
@Parcelize
data class Dog(
  val name: String,
  val breed: String,
  val location: String,
  val age: String,
  val gender: String,
  val size: String,
  @DrawableRes val picture: Int,
) : Parcelable