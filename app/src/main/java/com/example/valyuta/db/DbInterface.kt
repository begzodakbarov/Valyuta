package com.example.valyuta.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.valyuta.models.MainValyuta

@Dao
interface DbInterface {
    @Insert
    fun addContact(mainValyuta: MainValyuta)

    @Query("select * from MainValyuta")
    fun getAllContacts():List<MainValyuta>
}