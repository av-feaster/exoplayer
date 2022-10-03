package com.example.example

import com.google.gson.annotations.SerializedName


data class VideoApi (

  @SerializedName("total"     ) var total     : Int?            = null,
//  @SerializedName("totalHits" ) var totalHits : Int?            = null,
  @SerializedName("hits"      ) var hits      : ArrayList<Hits> = arrayListOf()

)