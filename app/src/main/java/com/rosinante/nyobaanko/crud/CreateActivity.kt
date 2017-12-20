package com.rosinante.nyobaanko.crud

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.rosinante.nyobaanko.R
import com.rosinante.nyobaanko.data.DataStore
import com.rosinante.nyobaanko.data.NoteDataClass
import kotlinx.android.synthetic.main.activity_create.*
import java.util.*

class CreateActivity : AppCompatActivity() {

    companion object {
        operator fun get(context: Context): Intent {
            return Intent(context, CreateActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_accept, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_accept -> {
                saveData()
                finish()
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveData() {
        DataStore.execute {
            val note = updateNoteData()
            DataStore.notes.insert(note)
        }
    }

    private fun updateNoteData(): NoteDataClass {
        val note = NoteDataClass()
        note.text = edit_text_create.text.toString()
        note.updatedAt = Date()
        return note
    }
}
