package com.asdeporte.asdeportev2.data.responses.events


data class EventResponse(
    val code: Int?,
    val data: EventData?,
)

data class EventData(
    val eventid: String?,
    val official_name: String?,
    val logo: String?,
    val cover: String?,
    val isAmigo: Boolean? = false
)

data class ScoreData(
    val position: Int?,
    val name: String?,
    val time: String?
)