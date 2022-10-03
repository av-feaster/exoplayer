package com.example.example

import com.google.gson.annotations.SerializedName


data class Medium (

  @SerializedName("url"    ) var url    : String? = null,
  @SerializedName("width"  ) var width  : Int?    = null,
  @SerializedName("height" ) var height : Int?    = null,
  @SerializedName("size"   ) var size   : Int?    = null

)