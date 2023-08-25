package com.example.visionmemo.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.visionmemo.dao.NotesDao
import com.example.visionmemo.db.NotesDB
import com.example.visionmemo.model.NoteEntity

//lateinit var notesDao: NotesDao
class NotesRepository(private val notesDao: NotesDao){


    fun getAllNotes():LiveData<List<NoteEntity>>{
        return notesDao.getAllNotes()
    }


    suspend fun insertNote(notesEntity:NoteEntity){
        notesDao.insertNote(notesEntity)
    }

    suspend fun deleteNote(id:Int){
        notesDao.deleteNote(id)
    }

    suspend fun updateNotes(notesEntity: NoteEntity){
        notesDao.updateNote(notesEntity)
    }
}