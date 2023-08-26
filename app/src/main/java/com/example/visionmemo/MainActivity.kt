package com.example.visionmemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.visionmemo.activities.InsertNoteActivity
import com.example.visionmemo.databinding.ActivityMainBinding
import com.example.visionmemo.db.NotesDB
import com.example.visionmemo.repository.NotesRepository
import com.example.visionmemo.viewmodel.NotesViewModel
import com.example.visionmemo.viewmodelFactory.NotesViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var mainViewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val dao=NotesDB.getDatabase(this).notesDao()
        val repository=NotesRepository(dao)
//        mainViewModel=ViewModelProvider(this,NotesViewModelFactory(repository))[NotesViewModel::class.java]
//
//        val data=findViewById<TextView>(R.id.txt)
//
//        mainViewModel.getAllNotes().observe(this, Observer {
//            data.text=it.toString()
//        })



        binding.addBtn.setOnClickListener {
            startActivity(Intent(this,InsertNoteActivity::class.java))
        }
    }
}