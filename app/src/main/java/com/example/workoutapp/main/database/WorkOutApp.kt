package com.example.workoutapp.main.database

import android.app.Application

class WorkOutApp:Application() {
    val db by lazy {
        HistoryDatabase.getInstance(this)
    }
}