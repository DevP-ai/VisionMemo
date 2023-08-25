package com.example.visionmemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.visionmemo.model.NoteEntity
import com.example.visionmemo.repository.NotesRepository

class NotesViewModel(private val notesRepository: NotesRepository):ViewModel() {

    fun getAllNotes():LiveData<List<NoteEntity>>{
        return notesRepository.getAllNotes()
    }

    suspend fun insertNotes(noteEntity: NoteEntity){
        notesRepository.insertNote(noteEntity)
    }

    suspend fun deleteNotes(id:Int){
        notesRepository.deleteNote(id)
    }

    suspend fun updateNotes(noteEntity: NoteEntity){
        notesRepository.updateNotes(noteEntity)
    }
}