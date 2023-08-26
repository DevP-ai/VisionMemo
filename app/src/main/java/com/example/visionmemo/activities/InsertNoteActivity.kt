package com.example.visionmemo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.visionmemo.R
import com.example.visionmemo.databinding.ActivityInsertNoteBinding
import com.example.visionmemo.db.NotesDB
import com.example.visionmemo.model.NoteEntity
import com.example.visionmemo.repository.NotesRepository
import com.example.visionmemo.viewmodel.NotesViewModel
import com.example.visionmemo.viewmodelFactory.NotesViewModelFactory
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.Date

class InsertNoteActivity : AppCompatActivity() {
    private lateinit var binding:ActivityInsertNoteBinding
    private lateinit var notesViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityInsertNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var dao=NotesDB.getDatabase(this).notesDao()
        var repo=NotesRepository(dao)
        notesViewModel=ViewModelProvider(this,NotesViewModelFactory(repo))[NotesViewModel::class.java]

        binding.updateBtn.setOnClickListener {
            val title=binding.notesTitle.text.toString()
            val subTitle=binding.notesSubtitle.text.toString()
            val notes=binding.notesText.text.toString()

            lifecycleScope.launch {
                validateData(title,subTitle,notes)
            }
        }

    }

    private suspend fun validateData(title: String, subTitle: String, notes: String) {
         if(title.isBlank()){
             binding.notesTitle.error="Title Required"
         }else if(subTitle.isBlank()){
             binding.notesSubtitle.error="Subtitle required"
         }else if(notes.isBlank()){
             binding.notesText.error="Please Enter notes"
         }else{
             uploadNotes(title,subTitle,notes)
             binding.notesTitle.setText("")
             binding.notesSubtitle.setText("")
             binding.notesText.setText("")
         }
    }

    private suspend fun uploadNotes(title: String, subTitle: String, notes: String) {
         notesViewModel.insertNotes(NoteEntity(0,title,subTitle,notes,Date()))

        Toast.makeText(this,"Notes Uploaded",Toast.LENGTH_LONG).show()

        finish()

    }
}