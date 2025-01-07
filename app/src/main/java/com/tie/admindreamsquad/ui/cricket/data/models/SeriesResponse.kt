package com.tie.admindreamsquad.ui.cricket.data.models

data class SeriesResponse(
    val apikey: String,
    val data: List<Series>
) {
    data class Series(
        val id: String,
        val name: String,
        val startDate: String,
        val endDate: String,
        val odi: String,
        val t20: String,
        val test: String,
        val squads: String,
        val matches: String
    )
}

