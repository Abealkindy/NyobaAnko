package com.rosinante.nyobaanko

import android.app.Application
import com.rosinante.nyobaanko.data.DataStore

/**
 * Created by Rosinante24 on 20/12/17.
 */
class AnkoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DataStore.init(this)
    }
}