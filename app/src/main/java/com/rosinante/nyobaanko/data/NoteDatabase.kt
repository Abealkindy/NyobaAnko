package com.rosinante.nyobaanko.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import com.rosinante.nyobaanko.data.NotesContract.NoteTable.CREATED_AT
import com.rosinante.nyobaanko.data.NotesContract.NoteTable.IS_PINNED
import com.rosinante.nyobaanko.data.NotesContract.NoteTable.TEXT
import com.rosinante.nyobaanko.data.NotesContract.NoteTable.UPDATED_AT
import com.rosinante.nyobaanko.data.NotesContract.NoteTable._TABLE_NAME
import org.jetbrains.anko.db.transaction
import java.util.*

/**
 * Created by Rosinante24 on 20/12/17.
 */
class NoteDatabase(context: Context) {
    private val helper = NotesOpenHelper(context)
    fun getAll(): List<NoteDataClass> {
        return helper.readableDatabase.query(_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                CREATED_AT).use(this::allFromCursor)
    }

    fun insert(vararg notes: NoteDataClass) {
        helper.writableDatabase.transaction {
            fromNotes(notes).forEach {
                insert(_TABLE_NAME, null, it)
            }
        }
    }

    fun update(note: NoteDataClass) {
        val values = fromNote(note)
        helper.writableDatabase.update(_TABLE_NAME,
                values,
                BaseColumns._ID + " = ?",
                arrayOf("${note.id}"))
    }

    fun delete(note: NoteDataClass) {
        helper.writableDatabase.delete(_TABLE_NAME,
                BaseColumns._ID + " = ?",
                arrayOf("${note.id}"))
    }

    fun loadAllByIds(vararg ids: Int): List<NoteDataClass> {
        val questionMarks = ids.map { "?" }.joinToString { ", " }
        val args = ids.map { "$it" }.toTypedArray()
        val selection = "${BaseColumns._ID} IN ($questionMarks)"
        return helper.readableDatabase.query(_TABLE_NAME,
                null,
                selection,
                args,
                null,
                null,
                CREATED_AT).use(this::allFromCursor)
    }

    private fun allFromCursor(cursor: Cursor): List<NoteDataClass> {
        val retval = mutableListOf<NoteDataClass>()
        while (cursor.moveToNext()) {
            retval.add(fromCursor(cursor))
        }
        return retval
    }

    private fun fromCursor(cursor: Cursor): NoteDataClass {
        var col = 0
        return NoteDataClass().apply {
            id = cursor.getInt(col++)
            text = cursor.getString(col++)
            isPinned = cursor.getInt(col++) != 0
            createdAt = Date(cursor.getLong(col++))
            updatedAt = Date(cursor.getLong(col))
        }
    }

    private fun fromNote(note: NoteDataClass): ContentValues {
        return ContentValues().apply {
            val id = note.id
            if (id != -1) {
                put(BaseColumns._ID, id)
            }
            put(TEXT, note.text)
            put(IS_PINNED, note.isPinned)
            put(CREATED_AT, note.createdAt.time)
            put(UPDATED_AT, note.updatedAt!!.time)
        }
    }

    private fun fromNotes(notes: Array<out NoteDataClass>): List<ContentValues> {
        return notes.map(this::fromNote)
    }
}