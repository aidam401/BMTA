package com.learntodroid.androidminesweeper.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.CountDownTimer
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import android.widget.Toast
import android.graphics.drawable.GradientDrawable
import android.view.View
import com.learntodroid.androidminesweeper.modelview.MineGridRecyclerAdapter
import com.learntodroid.androidminesweeper.modelview.MineSweeperGame
import com.learntodroid.androidminesweeper.modelview.OnCellClickListener
import com.learntodroid.androidminesweeper.databinding.ActivityGameBinding
import com.learntodroid.androidminesweeper.model.Cell
import com.learntodroid.androidminesweeper.model.Log
import org.json.JSONException
import org.json.JSONObject

import java.io.File
import java.io.FileOutputStream
import java.util.*
import java.io.FileInputStream
import com.google.gson.Gson
import com.learntodroid.androidminesweeper.model.Logs
import kotlin.reflect.typeOf

class GameActivity : AppCompatActivity(), OnCellClickListener {
    private lateinit var binding: ActivityGameBinding
    private var mineGridRecyclerAdapter: MineGridRecyclerAdapter? = null
    private var mineSweeperGame: MineSweeperGame? = null
    private var countDownTimer: CountDownTimer? = null
    private var secondsElapsed = 0
    private var timerStarted = false


    override fun onCreate(savedInstanceState: Bundle?) {

        val timerLength = intent.extras!!.getLong("time")
        secondsElapsed = (timerLength / 1000).toInt()
        val bombCount = intent.extras!!.getInt("mines")

        val gridSize = 10
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        binding.activityMainTimer.text = (timerLength / 1000).toString()
        setContentView(binding.root)
        binding.activityMainGrid.layoutManager = GridLayoutManager(this, 10)

        timerStarted = false
        countDownTimer = object : CountDownTimer(timerLength, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                secondsElapsed -= 1
                binding.activityMainTimer.text = String.format("%03d", secondsElapsed)
            }

            override fun onFinish() {
                mineSweeperGame!!.outOfTime()
                Toast.makeText(applicationContext, "Game Over: Timer Expired", Toast.LENGTH_SHORT)
                    .show()
                saveNewLog(Log(false, secondsElapsed, Calendar.getInstance().time))
                mineSweeperGame!!.mineGrid.revealAllBombs()
                mineGridRecyclerAdapter!!.setCells(mineSweeperGame!!.mineGrid.cells)
            }
        }


        mineSweeperGame = MineSweeperGame(gridSize, bombCount)
        binding.activityMainFlagsleft.text = String.format(
            "%03d",
            mineSweeperGame!!.numberBombs - mineSweeperGame!!.flagCount
        )
        mineGridRecyclerAdapter = MineGridRecyclerAdapter(mineSweeperGame!!.mineGrid.cells, this)
        binding.activityMainGrid.adapter = mineGridRecyclerAdapter
        binding.activityMainSmiley.setOnClickListener(View.OnClickListener {
            mineSweeperGame = MineSweeperGame(gridSize, bombCount)
            mineGridRecyclerAdapter!!.setCells(mineSweeperGame!!.mineGrid.cells)
            timerStarted = false
            countDownTimer!!.cancel()
            secondsElapsed = (timerLength / 1000).toInt()
            binding.activityMainTimer.text = (timerLength / 1000).toString()
            binding.activityMainFlagsleft.text = String.format(
                "%03d",
                mineSweeperGame!!.numberBombs - mineSweeperGame!!.flagCount
            )
        })

        binding.activityMainFlag.setOnClickListener(View.OnClickListener {
            mineSweeperGame!!.toggleMode()
            if (mineSweeperGame!!.isFlagMode) {
                val border = GradientDrawable()
                border.setColor(-0x1)
                border.setStroke(1, -0x1000000)
                binding.activityMainFlag.background = border
            } else {
                val border = GradientDrawable()
                border.setColor(-0x1)
                binding.activityMainFlag.background = border
            }
        })
    }

    override fun cellClick(cell: Cell?) {
        mineSweeperGame!!.handleCellClick(cell, secondsElapsed, applicationContext)
        binding.activityMainFlagsleft.text = String.format(
            "%03d",
            mineSweeperGame!!.numberBombs - mineSweeperGame!!.flagCount
        )
        if (!timerStarted) {
            countDownTimer!!.start()
            timerStarted = true
        }
        if (mineSweeperGame!!.isGameOver) {
            countDownTimer!!.cancel()
            Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_SHORT).show()
            saveNewLog(Log(false, secondsElapsed, Calendar.getInstance().time))
            mineSweeperGame!!.mineGrid.revealAllBombs()
        }
        if (mineSweeperGame!!.isGameWon) {
            countDownTimer!!.cancel()
            Toast.makeText(applicationContext, "Game Won", Toast.LENGTH_SHORT).show()
            saveNewLog(Log(true, secondsElapsed, Calendar.getInstance().time))
            mineSweeperGame!!.mineGrid.revealAllBombs()
        }
        mineGridRecyclerAdapter!!.setCells(mineSweeperGame!!.mineGrid.cells)
    }

    fun saveNewLog(log: Log) {
        try {
            var allLogs = Logs()
            val file = File(applicationContext.filesDir, "save.json")
            if(file.exists()) {
                FileInputStream(file).bufferedReader().use { allLogs = Gson().fromJson(it.readText(), Logs::class.java ) }
            }
            allLogs.logs.add(log)
            FileOutputStream(file).use { it.write(Gson().toJson(allLogs).toString().encodeToByteArray()) }
            Toast.makeText(applicationContext, "Result of game saved.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}