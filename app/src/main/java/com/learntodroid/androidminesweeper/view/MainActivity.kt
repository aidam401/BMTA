package com.learntodroid.androidminesweeper.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learntodroid.androidminesweeper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gameActivity = Intent(this, GameActivity::class.java)


        binding.easyBtn.setOnClickListener(){
            gameActivity.putExtra("mines", 10)
            gameActivity.putExtra("time", 600000L)
            startActivity(gameActivity)
        }
        binding.normalBtn.setOnClickListener(){
            gameActivity.putExtra("mines", 25)
            gameActivity.putExtra("time", 300000L)
            startActivity(gameActivity)
        }
        binding.hardBtn.setOnClickListener(){
            gameActivity.putExtra("mines", 50)
            gameActivity.putExtra("time", 150000L)
            startActivity(gameActivity)
        }
        binding.resultsBtn.setOnClickListener(){
            startActivity(Intent(this, ResultsActivity::class.java))
        }

    }
}