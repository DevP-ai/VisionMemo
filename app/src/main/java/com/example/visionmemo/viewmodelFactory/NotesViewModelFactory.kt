package com.example.visionmemo.viewmodelFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.visionmemo.repository.NotesRepository
import com.example.visionmemo.viewmodel.NotesViewModel

class NotesViewModelFactory(private val notesRepository: NotesRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesViewModel(notesRepository) as T
    }
}