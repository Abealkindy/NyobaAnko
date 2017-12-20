package com.rosinante.nyobaanko.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by Rosinante24 on 20/12/17.
 */
class NotesOpenHelper(context: Context) : SQLiteOpenHelper(context, "notes.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(NotesContract.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(NotesContract.SQL_DELETE_ENTRIES)
        onCreate(db)
    }
}