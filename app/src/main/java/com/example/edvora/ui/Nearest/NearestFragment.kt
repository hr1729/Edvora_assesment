package com.example.edvora.ui.Nearest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edvora.MainActivity
import com.example.edvora.R
import com.example.edvora.adapter.RideAdapter
import com.example.edvora.data.RidesItemX
import com.example.edvora.databinding.FragmentDashboardBinding
import com.example.edvora.extra.Resource
import com.example.edvora.ui.viewModel

class NearestFragment : Fragment(R.layout.fragment_dashboard){
lateinit var binding: FragmentDashboardBinding
lateinit var viewmodel:viewModel
lateinit var rideadpt:RideAdapter
lateinit var rideList: ArrayList<RidesItemX>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentDashboardBinding.bind(view)
        rideList=ArrayList<RidesItemX>()
        viewmodel=(activity as MainActivity).viewmodel
        var a:Int?=null
        viewmodel.user1.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success->{
                    it.data?.let {
                        rideadpt=RideAdapter(it.stationCode)
                        a=it.stationCode
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
                        rcyelr(it,a?:0)
                    }
                }
                is Resource.Loading->{
                    showProgressBar()
                }
            }
        })
    }
    fun rcyelr(a:ArrayList<RidesItemX>,b:Int) {
        var listofrides=a.sortWith(object:Comparator<RidesItemX>{
            override fun compare(o1: RidesItemX, o2: RidesItemX): Int {
                var a1=o1.stationPath.sorted()
                var a2=o2.stationPath.sorted()

                var b1= a1?.last()
                var b2=a1?.last()
                if (a1 != null) {
                    for(i in a1){
                        if(i>=b){
                           b1=i
                           break
                        }
                    }
                }
                if(a2!=null){
                    for(i in a2){
                        if(i>=b){
                            b2=i
                            break
                        }
                    }
                }
                var op3=b1-b
                var op4=b2-b
                if(op3>op4)
                    return 1
                if(op3==op4)
                    return 0
                return -1
            }

        })
        rideadpt.differ.submitList(a)
    }
    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }
    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }
}