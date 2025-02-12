package com.example.workoutapp

import android.app.Application
import com.example.workoutapp.data.HistoryDatabase

class WorkOutApp : Application() {

    val db by lazy {
        HistoryDatabase.getInstance(this)
    }
}