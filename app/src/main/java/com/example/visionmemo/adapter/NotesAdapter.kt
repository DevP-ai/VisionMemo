package com.example.visionmemo.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.visionmemo.R
import com.example.visionmemo.activities.UpdateNotesActivity
import com.example.visionmemo.model.NoteEntity


class NotesAdapter(private var context: Context, private var list: List<NoteEntity>):RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    inner class NotesViewHolder(private var view:View):RecyclerView.ViewHolder(view){
        var title=view.findViewById<TextView>(R.id.txtTitle)
        var subTitle=view.findViewById<TextView>(R.id.txtSubtitle)
        var date=view.findViewById<TextView>(R.id.txtDate)
        var priority=view.findViewById<View>(R.id.priorityView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return  NotesViewHolder(LayoutInflater.from(context.applicationContext).inflate(R.layout.item_notes,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val notes=list[position]

        holder.title.text=notes.notesTitle
        holder.subTitle.text=notes.notesSubTitle
        holder.date.text=notes.notesDate.toString()

        if(notes.notesPriority.equals("1")){
            holder.priority.setBackgroundResource(R.drawable.red_shape)
        }else if(notes.notesPriority.equals("2")){
            holder.priority.setBackgroundResource(R.drawable.blue_shape)
        }else{
            holder.priority.setBackgroundResource(R.drawable.yellow_shape)
        }

        holder.itemView.setOnClickListener {
            val intent= Intent(context,UpdateNotesActivity::class.java)
            intent.putExtra("ID",notes.id)
            intent.putExtra("TITLE",notes.notesTitle)
            intent.putExtra("SUBTITLE",notes.notesSubTitle)
            intent.putExtra("NOTES",notes.notes)
            intent.putExtra("PRIORITY",notes.notesPriority)

            context.startActivity(intent)
        }

    }
}