package com.example.example

import com.google.gson.annotations.SerializedName


data class Hits (

  @SerializedName("id"           ) var id           : Int?    = null,
//  @SerializedName("pageURL"      ) var pageURL      : String? = null,
//  @SerializedName("type"         ) var type         : String? = null,
//  @SerializedName("tags"         ) var tags         : String? = null,
//  @SerializedName("duration"     ) var duration     : Int?    = null,
//  @SerializedName("picture_id"   ) var pictureId    : String? = null,
  @SerializedName("videos"       ) var videosme       : Videosme? = Videosme(),
//  @SerializedName("views"        ) var views        : Int?    = null,
//  @SerializedName("downloads"    ) var downloads    : Int?    = null,
//  @SerializedName("likes"        ) var likes        : Int?    = null,
//  @SerializedName("comments"     ) var comments     : Int?    = null,
//  @SerializedName("user_id"      ) var userId       : Int?    = null,
//  @SerializedName("user"         ) var user         : String? = null,
  @SerializedName("userImageURL" ) var userImageURL : String? = null

)