package com.hackaprende.earthquakemonitor.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hackaprende.earthquakemonitor.Earthquake

@Database(entities = [Earthquake::class], version = 1)
abstract class EqDatabase : RoomDatabase() {
    abstract val eqDao: EqDao
}

private lateinit var INSTANCE: EqDatabase

fun getDatabase(context: Context): EqDatabase {
    synchronized(EqDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                EqDatabase::class.java,
                "earthquakes"
            ).build()
        }
        return INSTANCE
    }
}