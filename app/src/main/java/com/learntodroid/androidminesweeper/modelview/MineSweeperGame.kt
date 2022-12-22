package com.learntodroid.androidminesweeper.modelview

import android.content.Context
import android.widget.Toast
import com.learntodroid.androidminesweeper.model.Cell
import com.learntodroid.androidminesweeper.model.Log
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.nio.charset.Charset
import java.util.*

class MineSweeperGame(size: Int, val numberBombs: Int) {
    val mineGrid: MineGrid
    var isGameOver = false
        private set
    var isFlagMode = false
        private set
    var isClearMode = true
        private set
    var flagCount = 0
        private set
    private var timeExpired = false

    init {
        mineGrid = MineGrid(size)
        mineGrid.generateGrid(numberBombs)
    }

    fun handleCellClick(cell: Cell?, clickTime: Int, context: Context) {
        if (!isGameOver && !isGameWon && !timeExpired && !cell!!.revealed) {
            if (isClearMode) {
                clear(cell)
            } else if (isFlagMode) {
                flag(cell)
            }
        }
    }

    fun clear(cell: Cell?) {
        val index = mineGrid.cells.indexOf(cell)
        mineGrid.cells[index]?.revealed = true
        if (cell?.value == Cell.Companion.BOMB) {
            isGameOver = true
        } else if (cell?.value == Cell.Companion.BLANK) {
            val toClear: MutableList<Cell?> = ArrayList()
            val toCheckAdjacents: MutableList<Cell?> = ArrayList()
            toCheckAdjacents.add(cell)
            while (toCheckAdjacents.size > 0) {
                val c = toCheckAdjacents[0]
                val cellIndex = mineGrid.cells.indexOf(c)
                val cellPos = mineGrid.toXY(cellIndex)
                for (adjacent in mineGrid.adjacentCells(
                    cellPos[0], cellPos[1]
                )) {
                    if (adjacent.value == Cell.Companion.BLANK) {
                        if (!toClear.contains(adjacent)) {
                            if (!toCheckAdjacents.contains(adjacent)) {
                                toCheckAdjacents.add(adjacent)
                            }
                        }
                    } else {
                        if (!toClear.contains(adjacent)) {
                            toClear.add(adjacent)
                        }
                    }
                }
                toCheckAdjacents.remove(c)
                toClear.add(c)
            }
            for (c in toClear) {
                c?.revealed = true
            }
        }
    }

    fun flag(cell: Cell?) {
        cell?.flagged = !cell!!.flagged
        var count = 0
        for (c in mineGrid.cells) {
            if (c!!.flagged) {
                count++
            }
        }
        flagCount = count
    }

    val isGameWon: Boolean
        get() {
            var numbersUnrevealed = 0
            for (c in mineGrid.cells) {
                if (c?.value != Cell.Companion.BOMB && c?.value != Cell.Companion.BLANK && !c!!.revealed) {
                    numbersUnrevealed++
                }
            }
            return numbersUnrevealed == 0
        }

    fun toggleMode() {
        isClearMode = !isClearMode
        isFlagMode = !isFlagMode
    }

    fun outOfTime() {
        timeExpired = true
    }
}