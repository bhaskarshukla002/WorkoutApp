package com.example.workoutapp.ViewBindingRV

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.databinding.RecyclerviewItemBinding

class RVAdapter(private val taskList:List<Task>):RecyclerView.Adapter<RVAdapter.RVViewHolder>() {

    inner class RVViewHolder(val view: RecyclerviewItemBinding):RecyclerView.ViewHolder(view.root)
    {
        fun bindItem(task:Task){
            view.tvTitle.text=task.title
            view.tvTime.text=task.timeStamp
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
       val binding= RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RVViewHolder(binding)
}
    override fun getItemCount(): Int {
        return taskList.size

    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        holder.bindItem(taskList[position])
    }
}