package com.rosinante.nyobaanko.data

import android.content.Context
import org.jetbrains.anko.doAsync

/**
 * Created by Rosinante24 on 20/12/17.
 */
object DataStore {
    @JvmStatic
    lateinit var notes: NoteDatabase

    fun init(context: Context) {
        notes = NoteDatabase(context)
    }

    fun execute(runnable: Runnable) {
        execute {
            runnable.run()
        }
    }

    fun execute(fn: () -> Unit) {
        doAsync { fn() }
    }
}