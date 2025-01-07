package com.tie.admindreamsquad.ui.cricket.data.models

import com.google.gson.annotations.SerializedName

data class GetSeriesResponse(
    @SerializedName("status")
    val status: String,

    @SerializedName("data")
    val data: List<AllSeries>
)

data class AllSeries(
    @SerializedName("id")
    val id: String,

    @SerializedName("series_id")
    val seriesId: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("startDate")
    val startDate: String,

    @SerializedName("endDate")
    val endDate: String,

    @SerializedName("odi")
    val odi: String,

    @SerializedName("t20")
    val t20: String,

    @SerializedName("test")
    val test: String,

    @SerializedName("squads")
    val squads: String,

    @SerializedName("matches")
    val matches: String,

    @SerializedName("created_at")
    val createdAt: String
)
