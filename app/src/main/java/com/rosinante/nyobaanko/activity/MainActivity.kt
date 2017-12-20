package com.rosinante.nyobaanko.activity


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.rosinante.nyobaanko.R
import com.rosinante.nyobaanko.crud.CreateActivity
import com.rosinante.nyobaanko.recycler.NotesAdapter
import com.rosinante.nyobaanko.util.SpaceItemDeclaration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { startActivity(CreateActivity.get(this@MainActivity)) }
        recycler_note.adapter = NotesAdapter(this)
        recycler_note.addItemDecoration(SpaceItemDeclaration(this, R.dimen.jarak8dp))
    }

    override fun onResume() {
        super.onResume()
        refresh()
    }

    private fun refresh() {
        (recycler_note.adapter as NotesAdapter).refresh()
    }

    override fun onDestroy() {
        super.onDestroy()
        recycler_note.adapter = null
    }
}
