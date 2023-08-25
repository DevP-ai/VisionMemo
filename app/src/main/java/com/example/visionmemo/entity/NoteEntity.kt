package com.example.visionmemo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_db")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val notes:String?="",
    val notesTitle:String?="",
    val notesSubTitle:String?="",
    val notesDate:String?="",
    val notesPriority:String?=""
)
