package com.example.eventssicredi.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "events_table")
data class Event(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val price: Double,
    val latitude: Double,
    val longitude: Double,
    val image: String,
    val description: String,
    val date: Long
//    val people: List<People>
): Serializable