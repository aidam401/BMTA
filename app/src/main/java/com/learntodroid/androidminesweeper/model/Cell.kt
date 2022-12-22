package com.learntodroid.androidminesweeper.model

class Cell(val value: Int) {
    var revealed = false
    var flagged = false

    companion object {
        const val BOMB = -1
        const val BLANK = 0
    }
}