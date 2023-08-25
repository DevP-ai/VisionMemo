package com.example.visionmemo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.visionmemo.entity.NoteEntity

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes_db")
    fun getAllNotes():LiveData<List<NoteEntity>>

    @Insert
    suspend fun insertNote(noteEntity: NoteEntity)

    @Query("DELETE FROM notes_db WHERE id=:id")
    suspend fun deleteNote(id:Int)

    @Update
    suspend fun updateNote(noteEntity: NoteEntity)
}