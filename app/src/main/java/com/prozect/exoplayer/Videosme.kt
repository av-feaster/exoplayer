package com.example.example

import com.google.gson.annotations.SerializedName


data class Videosme (

  @SerializedName("large"  ) var large  : Large?  = Large(),
//  @SerializedName("medium" ) var medium : Medium? = Medium(),
//  @SerializedName("small"  ) var small  : Small?  = Small(),
//  @SerializedName("tiny"   ) var tiny   : Tiny?   = Tiny()

)