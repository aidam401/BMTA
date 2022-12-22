package com.learntodroid.androidminesweeper.modelview

import com.learntodroid.androidminesweeper.model.Cell

interface OnCellClickListener {
    fun cellClick(cell: Cell?)

}