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
        var equalTags = true

        if (other is Restaurant) {
            for (i in tags.indices) {
                if (other.tags[i] != tags[i]) {
                    equalTags = false
                }
            }
        }

        return other is Restaurant &&
                other.business.id == business.id &&
                other.business.name == business.name &&
                other.status == status &&
                other.imageUrl == imageUrl &&
                other.tags.size == tags.size &&
                equalTags
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}

data class Business(@SerializedName("id")
                    val id: Int,
                    @SerializedName("name")
                    var name: String)