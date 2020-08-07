package com.hackaprende.earthquakemonitor.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hackaprende.earthquakemonitor.Earthquake

@Dao
interface EqDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg earthquakes: Earthquake)

    @Query("select * from eq_table")
    fun getEarthquakes(): List<Earthquake>
}