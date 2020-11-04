package com.example.eventssicredi.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.eventssicredi.domain.models.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        Event::class
    ],
    version = 1,
    exportSchema = false
)
abstract class EventDataBase: RoomDatabase() {

    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var INSTANCE: EventDataBase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): EventDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EventDataBase::class.java,
                    "event_database"
                )
                    .addCallback(EventDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class EventDatabaseCallback(
            private val scope: CoroutineScope
        ): RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
//                        populateDatabase(database.eventDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(eventDao: EventDao) {
            eventDao.deleteAllEvents()
        }
    }
}