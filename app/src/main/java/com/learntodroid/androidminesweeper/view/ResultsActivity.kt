package com.learntodroid.androidminesweeper.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.learntodroid.androidminesweeper.R

import com.learntodroid.androidminesweeper.model.Logs
import com.learntodroid.androidminesweeper.modelview.ResultRecyclerAdapter
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class ResultsActivity : AppCompatActivity() {

    private lateinit var logs: Logs
    //private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        //context = applicationContext;
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_results)
        val logRecycler = findViewById<RecyclerView>(R.id.logRecycler)
        logs = getLogs()
        logRecycler.layoutManager = LinearLayoutManager(this)
        logRecycler.adapter = ResultRecyclerAdapter(logs)

    }

    fun getLogs(): Logs {
        try {

            val file = File(applicationContext.filesDir, "save.json")
            if (file.exists()) {
                FileInputStream(file).bufferedReader()
                    .use { return Gson().fromJson(it.readText(), Logs::class.java) }
            }
            return Logs()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return Logs()
    }
}