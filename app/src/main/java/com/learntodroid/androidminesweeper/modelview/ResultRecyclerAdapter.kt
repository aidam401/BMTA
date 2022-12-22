package com.learntodroid.androidminesweeper.modelview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.learntodroid.androidminesweeper.R
import com.learntodroid.androidminesweeper.model.Log
import com.learntodroid.androidminesweeper.model.Logs
import java.text.SimpleDateFormat
import java.util.*

class ResultRecyclerAdapter(
    private var logs: Logs,
) : RecyclerView.Adapter<ResultRecyclerAdapter.LogViewHolder>() {


    inner class LogViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private var dateV : TextView?
        private var timeV : TextView?
        private var statusV : TextView?
        init{
            dateV = view.findViewById(R.id.dateView)
            timeV = view.findViewById(R.id.timeView)
            statusV = view.findViewById(R.id.statusView)
        }
        fun bind(log:Log){
            dateV!!.text = SimpleDateFormat("HH:mm dd-MM-yyyy ", Locale.getDefault()).format(log.date)
            timeV!!.text = log.time.toString()
            statusV!!.text =  if (log.status) "Win" else  "Lose"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        return LogViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_log, parent, false))
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.bind(logs.logs[position])
    }

    override fun getItemCount(): Int {
        return logs.logs.size
    }

}