package com.example.eventssicredi.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.eventssicredi.domain.models.Event

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Event)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: List<Event>)

    @Query("DELETE FROM events_table")
    suspend fun deleteAllEvents()

    @Query("SELECT * FROM events_table")
    fun getAllEvents(): LiveData<List<Event>>

    @Update(entity = Event::class)
    suspend fun update(event: Event)
}