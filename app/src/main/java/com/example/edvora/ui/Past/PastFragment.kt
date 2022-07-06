package com.example.edvora.ui.Past

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edvora.MainActivity
import com.example.edvora.R
import com.example.edvora.adapter.RideAdapter
import com.example.edvora.data.RidesItemX
import com.example.edvora.databinding.FragmentHomeBinding
import com.example.edvora.extra.Resource
import com.example.edvora.ui.viewModel
import java.util.*
import kotlin.collections.ArrayList

class PastFragment : Fragment(R.layout.fragment_dashboard) {
    lateinit var binding: FragmentHomeBinding
    lateinit var viewmodel: viewModel
    lateinit var rideadpt: RideAdapter
    lateinit var rideList: ArrayList<RidesItemX>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentHomeBinding.bind(view)
        rideList=ArrayList<RidesItemX>()
        viewmodel=(activity as MainActivity).viewmodel
        viewmodel.user1.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success->{
                    it.data?.let {
                        rideadpt=RideAdapter(it.stationCode)
                        binding.rcylerview.apply {
                            adapter=rideadpt
                            layoutManager=LinearLayoutManager(activity)
                        }
                    }
                }
            }
        })
        viewmodel.rides.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let {
                        rideList.clear()
                        rideList=it
                        rcyelr(it)
                    }
                }
                is Resource.Loading->{
                    showProgressBar()
                }
            }
        })

    }
    fun rcyelr(a:ArrayList<RidesItemX>){
        var simpleDateFormat=java.text.SimpleDateFormat("MM/dd/yyyy HH:mm a", Locale.getDefault())
        val currentdate=Date(System.currentTimeMillis())
        var a1=filterclass(a)
        rideadpt.differ.submitList(a1)
    }
    fun filterclass(list:ArrayList<RidesItemX>): List<RidesItemX> {
        val simpleDateFormat=java.text.SimpleDateFormat("MM/dd/yyyy HH:mm a", Locale.getDefault())
        val currentdate= Date(System.currentTimeMillis())
        return list.filter{
            val it1:Date = simpleDateFormat.parse(it.date) ?: return@filter false
            return@filter currentdate.after(it1)
        }
    }
    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }
    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }
}