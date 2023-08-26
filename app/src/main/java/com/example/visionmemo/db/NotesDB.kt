package com.example.visionmemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.visionmemo.converter.Converter
import com.example.visionmemo.dao.NotesDao
import com.example.visionmemo.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class NotesDB:RoomDatabase() {

    abstract fun notesDao():NotesDao

    companion object{
        private var INSTANCE:NotesDB?=null

        fun getDatabase(context: Context):NotesDB{
            if(INSTANCE==null){
                synchronized(this){
                    INSTANCE=Room.databaseBuilder(context.applicationContext,
                        NotesDB::class.java,"notes_db").build()
                }
            }

            return INSTANCE!!
        }
    }

}