package com.example.visionmemo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.visionmemo.R
import com.example.visionmemo.databinding.ActivityUpdateNotesBinding
import com.example.visionmemo.db.NotesDB
import com.example.visionmemo.model.NoteEntity
import com.example.visionmemo.repository.NotesRepository
import com.example.visionmemo.viewmodel.NotesViewModel
import com.example.visionmemo.viewmodelFactory.NotesViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.launch
import java.util.Date

class UpdateNotesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateNotesBinding
    private var priority:String=""
    private lateinit var notesViewModel: NotesViewModel
    private var sId:Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var dao= NotesDB.getDatabase(this).notesDao()
        var repo= NotesRepository(dao)
        notesViewModel=ViewModelProvider(this, NotesViewModelFactory(repo))[NotesViewModel::class.java]


        sId=intent.extras?.getInt("ID")
        val sTitle=intent.extras?.getString("TITLE")
        val sSubtitle=intent.extras?.getString("SUBTITLE")
        val sNotes=intent.extras?.getString("NOTES")
        val sPriority=intent.extras?.getString("PRIORITY")

        if(sPriority.equals("1")){
           binding.redPriority.setImageResource(R.drawable.ic_check)
        }else if(sPriority.equals("2")){
            binding.bluePriority.setImageResource(R.drawable.ic_check)
        }else if(sPriority.equals("3")){
            binding.yellowPriority.setImageResource(R.drawable.ic_check)
        }

        binding.updateTitle.setText(sTitle)
        binding.updateSubtitle.setText(sSubtitle)
        binding.updateText.setText(sNotes)

        binding.redPriority.setOnClickListener {
            priority="1"
            binding.redPriority.setImageResource(R.drawable.ic_check)
            binding.bluePriority.setImageResource(0)
            binding.yellowPriority.setImageResource(0)
        }

        binding.bluePriority.setOnClickListener {
            priority="2"
            binding.bluePriority.setImageResource(R.drawable.ic_check)
            binding.redPriority.setImageResource(0)
            binding.yellowPriority.setImageResource(0)
        }

        binding.yellowPriority.setOnClickListener {
            priority="3"
            binding.yellowPriority.setImageResource(R.drawable.ic_check)
            binding.redPriority.setImageResource(0)
            binding.bluePriority.setImageResource(0)
        }





        binding.updateBtn.setOnClickListener {
            val uTitle=binding.updateTitle.text.toString()
            val uSubtitle=binding.updateSubtitle.text.toString()
            val uNotes=binding.updateText.text.toString()
            var id=sId
            lifecycleScope.launch {
                if (id != null) {
                    validateData(uTitle,uSubtitle,uNotes,id)
                }
            }
        }

    }

    private suspend fun validateData(uTitle: String, uSubtitle: String, uNotes: String, id: Int) {
        if(uTitle.isBlank()){
            binding.updateTitle.error="Title Required"
        }else if(uSubtitle.isBlank()){
            binding.updateSubtitle.error="Subtitle required"
        }else if(uNotes.isBlank()){
            binding.updateText.error="Please Enter notes"
        }else{
            uploadNotes(uTitle,uSubtitle,uNotes,priority,id)
            binding.updateTitle.setText("")
            binding.updateSubtitle.setText("")
            binding.updateText.setText("")
        }
    }

    private suspend fun uploadNotes(uTitle: String, uSubtitle: String, uNotes: String, priority: String, id: Int) {
        notesViewModel.updateNotes(NoteEntity(id,uTitle,uSubtitle,uNotes,Date(),priority))

        Toast.makeText(this,"Notes Update", Toast.LENGTH_LONG).show()

        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.deleteBtn){
           var bottomSheetDialog=BottomSheetDialog(this)

            var view:View=LayoutInflater.from(this)
                .inflate(R.layout.delete_bottom_sheet, findViewById<LinearLayout>(R.id.bottomSheet))

            bottomSheetDialog.setContentView(view)

            val yes=view.findViewById<TextView>(R.id.txtYes)
            val no=view.findViewById<TextView>(R.id.txtNo)

            yes.setOnClickListener {
                lifecycleScope.launch {
                    notesViewModel.deleteNotes(sId!!)
                }
                Toast.makeText(this,"Notes Deleted",Toast.LENGTH_LONG).show()

                finish()
            }

            no.setOnClickListener {
                bottomSheetDialog.dismiss()
            }

            bottomSheetDialog.show()
        }
        return true
    }


}