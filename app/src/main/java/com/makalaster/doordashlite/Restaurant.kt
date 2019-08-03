package com.makalaster.doordashlite

import com.google.gson.annotations.SerializedName

data class Restaurant(@SerializedName("business")
                      val business: Business,
                      @SerializedName("status")
                      var status: String,
                      @SerializedName("tags")
                      var tags: Array<String>,
                      @SerializedName("cover_img_url")
                      var imageUrl: String) {

    override fun equals(other: Any?): Boolean {
        return other is Restaurant &&
                other.business.id == business.id &&
                other.business.name == business.name
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}

data class Business(@SerializedName("id")
                    val id: Int,
                    @SerializedName("name")
                    var name: String)