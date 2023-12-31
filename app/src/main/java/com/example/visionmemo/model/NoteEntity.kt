package com.example.visionmemo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes_db")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val notes:String?="",
    val notesTitle:String?="",
    val notesSubTitle:String?="",
    val notesDate: Date,
    val notesPriority:String?=""
)
