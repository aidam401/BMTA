package com.learntodroid.androidminesweeper.modelview

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.learntodroid.androidminesweeper.modelview.MineGridRecyclerAdapter.MineTileViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.learntodroid.androidminesweeper.R
import com.learntodroid.androidminesweeper.model.Cell

class MineGridRecyclerAdapter(
    private var cells: List<Cell?>?,
    private val listener: OnCellClickListener
) : RecyclerView.Adapter<MineTileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MineTileViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cell, parent, false)
        return MineTileViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MineTileViewHolder, position: Int) {
        holder.bind(cells!![position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return cells!!.size
    }

    fun setCells(cells: List<Cell?>?) {
        this.cells = cells
        notifyDataSetChanged()
    }

    inner class MineTileViewHolder(itemView: View) : ViewHolder(itemView) {
        var valueTextView: TextView

        init {
            valueTextView = itemView.findViewById(R.id.item_cell_value)
        }

        fun bind(cell: Cell?) {
            itemView.setBackgroundColor(Color.GRAY)
            itemView.setOnClickListener { listener.cellClick(cell) }
            if (cell!!.revealed) {
                if (cell.value == Cell.Companion.BOMB) {
                    valueTextView.setText(R.string.bomb)
                } else if (cell.value == Cell.Companion.BLANK) {
                    valueTextView.text = ""
                    itemView.setBackgroundColor(Color.WHITE)
                } else {
                    valueTextView.text = cell.value.toString()
                    if (cell.value == 1) {
                        valueTextView.setTextColor(Color.BLUE)
                    } else if (cell.value == 2) {
                        valueTextView.setTextColor(Color.GREEN)
                    } else if (cell.value == 3) {
                        valueTextView.setTextColor(Color.RED)
                    }
                }
            } else if (cell.flagged) {
                valueTextView.setText(R.string.flag)
            }
        }
    }
}