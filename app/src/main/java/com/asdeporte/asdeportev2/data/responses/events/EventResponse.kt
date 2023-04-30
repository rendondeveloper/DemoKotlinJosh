package com.asdeporte.asdeportev2.data.responses.events

data class EventData(
    val eventid: String?,
    val official_name: String?,
    val logo: String?,
    val cover: String?,
    val isAmigo: Boolean? = false,
    val isVideo: Boolean? = false
)

data class ScoreData(
    val position: Int?,
    val name: String?,
    val time: String?
)