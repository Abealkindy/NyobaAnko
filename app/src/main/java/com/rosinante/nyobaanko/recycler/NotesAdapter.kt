package com.rosinante.nyobaanko.recycler

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.rosinante.nyobaanko.R
import com.rosinante.nyobaanko.data.DataStore
import com.rosinante.nyobaanko.data.NoteDataClass
import com.rosinante.nyobaanko.util.layoutInflater
import kotlinx.android.synthetic.main.note_item.view.*
import java.util.ArrayList

/**
 * Created by Rosinante24 on 20/12/17.
 */
class NotesAdapter(private val context: Context) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var notes: List<NoteDataClass> = ArrayList()
    private var isRefreshing = false

    init {
        setHasStableIds(true)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        refresh()
    }

    fun refresh() {
        if (isRefreshing) return
        isRefreshing = true
        DataStore.execute {
            val notes = DataStore.notes.getAll()
            Handler(Looper.getMainLooper()).post {
                this@NotesAdapter.notes = notes
                notifyDataSetChanged()
                isRefreshing = false
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return notes[position].id.toLong()
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.text_item_note.text = notes[position].text
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NotesViewHolder {
        return NotesViewHolder(context.layoutInflater.inflate(R.layout.note_item, parent, false))
    }

    class NotesViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text_item_note = itemView.text_item_note
    }
}