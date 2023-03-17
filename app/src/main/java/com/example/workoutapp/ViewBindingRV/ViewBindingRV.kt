package com.example.workoutapp.ViewBindingRV

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.databinding.ActivityViewBindingRvBinding

class ViewBindingRV : AppCompatActivity() {

    private var _binding:ActivityViewBindingRvBinding?=null

    private lateinit var rvAdapter: RVAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityViewBindingRvBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        rvAdapter=RVAdapter(TaskList.taskList)
        _binding?.rvTaskList?.layoutManager=LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        _binding?.rvTaskList?.adapter=rvAdapter

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}